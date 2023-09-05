package com.hrmanagement.service;

import com.hrmanagement.dto.request.ActivateRequestDto;
import com.hrmanagement.dto.request.AuthUpdateRequestDto;
import com.hrmanagement.dto.request.LoginRequestDto;
import com.hrmanagement.dto.request.RegisterRequestDto;
import com.hrmanagement.dto.response.RegisterResponseDto;
import com.hrmanagement.exception.AuthManagerException;
import com.hrmanagement.exception.ErrorType;
import com.hrmanagement.mapper.IAuthMapper;
import com.hrmanagement.repository.IAuthRepository;
import com.hrmanagement.repository.entity.Auth;
import com.hrmanagement.repository.enums.EStatus;
import com.hrmanagement.utility.CodeGenerator;
import com.hrmanagement.utility.JwtTokenManager;
import com.hrmanagement.utility.ServiceManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth, Long> {
    private final IAuthRepository authRepository;
    private final JwtTokenManager jwtTokenManager;

    public AuthService(IAuthRepository authRepository, JwtTokenManager jwtTokenManager) {
        super(authRepository);
        this.authRepository = authRepository;
        this.jwtTokenManager = jwtTokenManager;
    }

    public RegisterResponseDto doRegister(RegisterRequestDto dto){
        Auth auth = IAuthMapper.INSTANCE.fromAuthRegisterRequestDtoToAuth(dto);
        if (auth.getPassword().equals(dto.getPasswordConfirm())){
            auth.setActivationCode((CodeGenerator.generateCode()));
            authRepository.save(auth);
            // user'a gönderilecek & mailsender bu kısma eklenecek.
        }else {
            throw new AuthManagerException(ErrorType.PASSWORD_ERROR);
        }
        RegisterResponseDto responseDto = IAuthMapper.INSTANCE.fromAuthToAuthRegisterResponseDto(auth);
        return responseDto;
    }

    public String doLogin(LoginRequestDto dto){
        Optional<Auth> auth = authRepository.findOptionalByEmailAndPassword(dto.getEmail(),dto.getPassword());
        if (auth.isEmpty()) throw new AuthManagerException(ErrorType.LOGIN_ERROR);
        if(!auth.get().getStatus().equals(EStatus.ACTIVE)){
            throw new AuthManagerException(ErrorType.ACCOUNT_NOT_ACTIVE);
        }
        Optional<String> token = jwtTokenManager.createToken(auth.get().getId(),auth.get().getRole());
        if (token.isEmpty()) throw new AuthManagerException(ErrorType.BAD_REQUEST);
        return token.get();

    }

    @Transactional
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
            return true;
        }else {
            throw new AuthManagerException(ErrorType.ACTIVATE_CODE_ERROR);
        }
    }

    public Boolean updateAuth(AuthUpdateRequestDto dto){
        Optional<Auth> auth = authRepository.findById(dto.getAuthId());
        if (auth.isPresent()){
            save(IAuthMapper.INSTANCE.fromAuthUpdateDtoToAuth(dto,auth.get()));
            return true;
        }
        throw new RuntimeException("Hata");
    }



}
