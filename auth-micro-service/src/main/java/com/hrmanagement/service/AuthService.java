package com.hrmanagement.service;

import com.hrmanagement.dto.request.*;
import com.hrmanagement.dto.response.LoginResponseDto;
import com.hrmanagement.dto.response.RegisterResponseDto;
import com.hrmanagement.exception.AuthManagerException;
import com.hrmanagement.exception.ErrorType;
import com.hrmanagement.manager.IUserManager;
import com.hrmanagement.mapper.IAuthMapper;
import com.hrmanagement.rabbitmq.model.CreateEmployee;
import com.hrmanagement.rabbitmq.model.MailActivateModel;
import com.hrmanagement.rabbitmq.model.MailRegisterModel;
import com.hrmanagement.rabbitmq.producer.MailActivateProducer;
import com.hrmanagement.rabbitmq.producer.MailRegisterProducer;
import com.hrmanagement.rabbitmq.producer.UserRegisterProducer;
import com.hrmanagement.repository.IAuthRepository;
import com.hrmanagement.repository.entity.Auth;
import com.hrmanagement.repository.enums.ERole;
import com.hrmanagement.repository.enums.EStatus;
import com.hrmanagement.utility.CodeGenerator;
import com.hrmanagement.utility.JwtTokenManager;
import com.hrmanagement.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService extends ServiceManager<Auth, Long> {
    private final IAuthRepository authRepository;
    private final JwtTokenManager jwtTokenManager;
    private final UserRegisterProducer userRegisterProducer;
    private final MailRegisterProducer mailRegisterProducer;
    private final IUserManager userManager;
    private final MailActivateProducer activateProducer;


    public AuthService(IAuthRepository authRepository, JwtTokenManager jwtTokenManager, UserRegisterProducer userRegisterProducer, MailRegisterProducer mailRegisterProducer, IUserManager userManager, MailActivateProducer activateProducer) {
        super(authRepository);
        this.authRepository = authRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.userRegisterProducer = userRegisterProducer;
        this.mailRegisterProducer = mailRegisterProducer;
        this.userManager = userManager;
        this.activateProducer = activateProducer;
    }

    public RegisterResponseDto doRegister(RegisterRequestDto dto){
        Auth auth = IAuthMapper.INSTANCE.fromAuthRegisterRequestDtoToAuth(dto);
        if (authRepository.findOptionalByEmail(dto.getEmail()).isPresent()){
            throw new AuthManagerException(ErrorType.EMAIL_DUPLICATE);
        }
        if (auth.getPassword().equals(dto.getPasswordConfirm())){
            auth.setActivationCode((CodeGenerator.generateCode()));
            authRepository.save(auth);
            userRegisterProducer.sendNewUser(IAuthMapper.INSTANCE.fromAuthToUserRegisterModel(auth));
            // user'a mailsender bu kısma eklenecek.
            //link için
            String token = jwtTokenManager.createToken(auth.getId(), auth.getRole()).get();
            mailRegisterProducer.sendActivationCode(MailRegisterModel.builder()
                    .name(auth.getName())
                    .surname(auth.getSurname())
                    .email(auth.getEmail())
                    .token(token).build());
        }else {
            throw new AuthManagerException(ErrorType.PASSWORD_ERROR);
        }
        RegisterResponseDto responseDto = IAuthMapper.INSTANCE.fromAuthToAuthRegisterResponseDto(auth);
        return responseDto;
    }

    public RegisterResponseDto doRegisterManager(ManagerRegisterRequestDto dto){
        Auth auth = Auth.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .taxNo(dto.getTaxNo())
                .companyName(dto.getCompanyName())
                .role(ERole.MANAGER)
                .build();
        if (authRepository.findOptionalByEmail(dto.getEmail()).isPresent()){
            throw new AuthManagerException(ErrorType.EMAIL_DUPLICATE);
        }
        if (auth.getPassword().equals(dto.getPasswordConfirm())){
            authRepository.save(auth);
            userRegisterProducer.sendNewUser(IAuthMapper.INSTANCE.fromAuthToUserRegisterModel(auth));
        }else {
            throw new AuthManagerException(ErrorType.PASSWORD_ERROR);
        }
        RegisterResponseDto responseDto = IAuthMapper.INSTANCE.fromAuthToAuthRegisterResponseDto(auth);
        return responseDto;
    }

    public LoginResponseDto doLogin(LoginRequestDto dto){
        Optional<Auth> auth = authRepository.findOptionalByEmailAndPassword(dto.getEmail(),dto.getPassword());
        if (auth.isEmpty()) throw new AuthManagerException(ErrorType.LOGIN_ERROR);
        if(!auth.get().getStatus().equals(EStatus.ACTIVE)){
            throw new AuthManagerException(ErrorType.ACCOUNT_NOT_ACTIVE);
        }
        Optional<String> token = jwtTokenManager.createToken(auth.get().getId(),auth.get().getRole());
        if (token.isEmpty()) throw new AuthManagerException(ErrorType.BAD_REQUEST);
        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                .role(auth.get().getRole())
                .token(token.get())
                .build();
        return loginResponseDto;

    }

    /*@Transactional
    public Boolean activateStatus(ActivateRequestDto dto) {
        Optional<Auth> optionalAuth = findById(dto.getId());
        if (optionalAuth.isEmpty()){
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        }
        if(optionalAuth.get().getStatus().equals(EStatus.ACTIVE)){
            throw new AuthManagerException(ErrorType.ALREADY_ACTIVE);
        }
        if (!optionalAuth.get().getStatus().equals(EStatus.PENDING)){
            throw new AuthManagerException(ErrorType.USER_ACCESS_ERROR);
        }
        if(dto.getActivationCode().equals(optionalAuth.get().getActivationCode())){
            optionalAuth.get().setStatus(EStatus.ACTIVE);
            update(optionalAuth.get());
            //userprofilemanager yazılıp daha sonra user'a gönderilecek bu kısımda
            userManager.activateStatus(optionalAuth.get().getId());
            return true;
        }else {
            throw new AuthManagerException(ErrorType.ACTIVATE_CODE_ERROR);
        }
    }*/

    public Boolean activateStatus(String token) {
        System.out.println("Activate status service metoduna gelen token: " + token);
        Optional<Long> authId = jwtTokenManager.getIdFromToken(token);
        if (authId.isPresent()) {
            Optional<Auth> auth = findById(authId.get());
            auth.get().setStatus(EStatus.ACTIVE);
            update(auth.get());
            userManager.activateStatus("Bearer " + token);
            return true;
        }
        throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
    }

    public Boolean updateAuth(AuthUpdateRequestDto dto){
        Optional<Auth> auth = authRepository.findById(dto.getAuthId());
        if (auth.isPresent()){
            save(IAuthMapper.INSTANCE.fromAuthUpdateDtoToAuth(dto,auth.get()));
            return true;
        }
        throw new RuntimeException("Hata");
    }


    public Boolean activateStatusManager(Long id) {
        Optional<Auth> auth = authRepository.findById(id);
        auth.orElseThrow(() -> {
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        });
        auth.get().setStatus(EStatus.ACTIVE);
        update(auth.get());
        MailActivateModel model = new MailActivateModel();
        model.setUserId(auth.get().getId());
        activateProducer.sendActivateMail(model);
        return true;

    }


    public Long createEmployee(CreateEmployee createEmployee) {
        Optional<Auth> auth = authRepository.findOptionalByEmail(createEmployee.getEmail());
        if (auth.isEmpty()) {
            Auth authWorker = Auth.builder()
                    .email(createEmployee.getEmail())
                    .password(UUID.randomUUID().toString().substring(0, 8))
                    .companyName(createEmployee.getCompanyName())
                    .taxNo(createEmployee.getTaxNo())
                    .role(ERole.EMPLOYEE)
                    .build();
            authRepository.save(authWorker);
            System.out.println(authWorker);
            System.out.println(authWorker.getId().toString());
//            authProducer.sendPasswordAfterManagerCreate(MailManagerPassword.builder()
//                    .mail(authWorker.getEmail())
//                    .authid(authWorker.getId())
//                    .password(authWorker.getPassword())
//                    .build());
            return authWorker.getId();
        }
        return 0L;
    }



}
