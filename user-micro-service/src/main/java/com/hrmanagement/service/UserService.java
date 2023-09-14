package com.hrmanagement.service;

import com.hrmanagement.dto.request.*;
import com.hrmanagement.dto.response.EmployeeListResponseDto;
import com.hrmanagement.exceptions.ErrorType;
import com.hrmanagement.exceptions.UserManagerException;
import com.hrmanagement.manager.IAuthManager;
import com.hrmanagement.mapper.IUserMapper;
import com.hrmanagement.rabbitmq.model.CreateEmployee;
import com.hrmanagement.rabbitmq.model.UserRegisterModel;
import com.hrmanagement.rabbitmq.producer.EmployeeProducer;
import com.hrmanagement.repository.IUserRepository;
import com.hrmanagement.repository.entity.User;
import com.hrmanagement.repository.enums.ERole;
import com.hrmanagement.repository.enums.EStatus;
import com.hrmanagement.utility.JwtTokenManager;
import com.hrmanagement.utility.ServiceManager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService extends ServiceManager<User,String> {
    private final IUserRepository repository;
    private final JwtTokenManager jwtTokenManager;
    private final IAuthManager authManager;
    private final EmployeeProducer employeeProducer;

    public UserService(IUserRepository repository, JwtTokenManager jwtTokenManager, IAuthManager authManager, EmployeeProducer employeeProducer) {
        super(repository);
        this.repository = repository;
        this.jwtTokenManager = jwtTokenManager;
        this.authManager = authManager;
        this.employeeProducer = employeeProducer;
    }

    public Boolean createUser(UserRegisterModel model) {
        repository.save(IUserMapper.INSTANCE.fromRegisterModelToUser(model));
        return true;
    }

    public Boolean createUserBasic(UserCreateRequestDto dto) {
        repository.save(IUserMapper.INSTANCE.fromCreateDtoToUser(dto));
        return true;
    }

    @Transactional
    public Boolean updateUser(String token, UserUpdateRequestDto dto) {

        Optional<Long> authId = jwtTokenManager.getIdFromToken(token);
        if (authId.isEmpty()) {
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<User> user = repository.findOptionalByAuthId(authId.get());
        if (user.isPresent()) {
            repository.save(IUserMapper.INSTANCE.fromUpdateDtoToUserProfile(dto, user.get()));
            AuthUpdateRequestDto authUpdateRequestDto = IUserMapper.INSTANCE.fromUserToAuthUpdateDto(user.get());
            authManager.updateAuth(authUpdateRequestDto);
            return true;
        }
        throw new RuntimeException("Hata");
    }

    public Boolean deleteById(Long authId) {
        System.out.println(authId);
        Optional<User> user = repository.findOptionalByAuthId(authId);

        user.orElseThrow(() -> {
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        });

        user.get().setStatus(EStatus.DELETED);
        update(user.get());
        return true;
    }


    /*public Boolean activateStatus(Long authId) {
        Optional<User> userProfile = repository.findOptionalByAuthId(authId);
        userProfile.orElseThrow(() -> {
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        });

        userProfile.get().setStatus(EStatus.ACTIVE);
        update(userProfile.get());
        return true;

    }*/

    public Boolean activateStatus(String token) {
        System.out.println("Service' e gelen token --> " + token);
        Optional<Long> authId = jwtTokenManager.getIdFromToken(token.substring(7));
        System.out.println("Service' de substring edilmiş token --> " + token.substring(7));
        if (authId.isEmpty()) {
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<User> user = repository.findOptionalByAuthId(authId.get());
        if (user.isEmpty()) {
            throw new RuntimeException("Auth id bulunamadı");
        }
        user.get().setStatus(EStatus.ACTIVE);
        update(user.get());
        return true;
    }

    @Transactional
    public Boolean activateStatusManager(Long authId) {
        Optional<User> optionalUser = repository.findOptionalByAuthId(authId);
        if (optionalUser.isEmpty()){
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        if(optionalUser.get().getStatus().equals(EStatus.ACTIVE)){
            throw new UserManagerException(ErrorType.ALREADY_ACTIVE);
        }
        if (!optionalUser.get().getStatus().equals(EStatus.PENDING)){
            throw new UserManagerException(ErrorType.USER_ACCESS_ERROR);
        }else {
            optionalUser.get().setStatus(EStatus.ACTIVE);
            update(optionalUser.get());
            //userprofilemanager yazılıp daha sonra user'a gönderilecek bu kısımda
            authManager.activateStatusManager(optionalUser.get().getAuthId());
            return true;
        }
    }

//    public Boolean createEmployee(EmployeeCreateRequestDto dto){
//        User user = User.builder()
//                .name(dto.getName())
//                .surname(dto.getSurname())
//                .idNumber(dto.getIdNumber())
//                .email(dto.getEmail())
//                .password(dto.getPassword())
//                .address(dto.getAddress())
//                .phone(dto.getPhone())
//                .birthday(dto.getBirthday())
//                .birthdayPlace(dto.getBirthdayPlace())
//                .company_name(dto.getCompany_name())
//                .occupation(dto.getOccupation())
//                .salary(dto.getSalary())
//                .role(ERole.EMPLOYEE)
//                .build();
//        if (repository.findOptionalByEmail(dto.getEmail()).isPresent()){
//            throw new UserManagerException(ErrorType.EMAIL_DUPLICATE);
//        }
//        repository.save(user);
//        return true;
//    }


    public Boolean addEmployee(EmployeeCreateRequestDto dto) {

        Optional<Long> authId = jwtTokenManager.getIdFromToken(dto.getToken());
        if (authId.isEmpty()) {
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<User> user = repository.findOptionalByAuthId(authId.get());
        if (user.isEmpty()) {
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        Long idEmployee = employeeProducer.sendEmployeeAuth(CreateEmployee.builder()
                .email(dto.getEmail())
                .companyName(user.get().getCompanyName())
                .taxNo(user.get().getTaxNo()).build());
        System.out.println(idEmployee);
        if(idEmployee!=0L) {
            Optional<User> employeeOptional=repository.findOptionalByIdNumber(dto.getIdNumber());
            if (employeeOptional.isPresent()) throw new UserManagerException(ErrorType.EMPLOYEE_HAS_BEEN);
                User employee = User.builder()
                        .name(dto.getName())
                        .surname(dto.getSurname())
                        .idNumber(dto.getIdNumber())
                        .email(dto.getEmail())
                        .address(dto.getAddress())
                        .phone(dto.getPhone())
                        .birthday(dto.getBirthday())
                        .birthdayPlace(dto.getBirthdayPlace())
                        .companyName(user.get().getCompanyName())
                        .taxNo(user.get().getTaxNo())
                        .occupation(dto.getOccupation())
                        .salary(dto.getSalary())
                        .authId(idEmployee)
                        .role(ERole.EMPLOYEE)
                        .build();
                repository.save(employee);
        }
        return true;
        }


    public List<EmployeeListResponseDto> findAllEmployee(String token) {

        Optional<Long> authId = jwtTokenManager.getIdFromToken(token);
        if (authId.isEmpty()) {
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<User> user = repository.findOptionalByAuthId(authId.get());
        if (user.isEmpty()) {
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        return repository.findAllByTaxNo(user.get().getTaxNo()).stream().map(x->{
            return EmployeeListResponseDto.builder().name(x.getName()).surname(x.getSurname()).email(x.getEmail()).address(x.getAddress()).phone(x.getPhone()).occupation(x.getOccupation()).build();
        }).collect(Collectors.toList());
    }

    public Boolean updateEmployee(UpdateEmployeeRequestDto dto) {
        User userEmp= repository.findOptionalByEmail(dto.getEmail())
                .orElseThrow(()->new UserManagerException(ErrorType.USER_NOT_FOUND));
        userEmp.setName(dto.getName());
        userEmp.setSurname(dto.getSurname());
        userEmp.setIdNumber(dto.getIdNumber());
        userEmp.setEmail(dto.getEmail());
        userEmp.setAddress(dto.getAddress());
        userEmp.setPhone(dto.getPhone());
        userEmp.setBirthday(dto.getBirthday());
        userEmp.setBirthdayPlace(dto.getBirthdayPlace());
        userEmp.setOccupation(dto.getOccupation());
        userEmp.setSalary(dto.getSalary());
        repository.save(userEmp);
        AuthEmployeeUpdateRequestDto authEmployeeUpdateRequestDto=IUserMapper.INSTANCE.fromUserToAuthEmployeeUpdateDto(userEmp);
        authManager.updateAuthEmployee(authEmployeeUpdateRequestDto);
        return true;
    }

}
