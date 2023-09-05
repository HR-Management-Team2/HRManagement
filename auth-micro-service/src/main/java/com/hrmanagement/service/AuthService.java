package com.hrmanagement.service;

import com.hrmanagement.dto.request.RegisterRequestDto;
import com.hrmanagement.dto.response.RegisterResponseDto;
import com.hrmanagement.exception.AuthManagerException;
import com.hrmanagement.exception.ErrorType;
import com.hrmanagement.mapper.IAuthMapper;
import com.hrmanagement.repository.IAuthRepository;
import com.hrmanagement.repository.entity.Auth;
import com.hrmanagement.utility.CodeGenerator;
import com.hrmanagement.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class AuthService extends ServiceManager<Auth, Long> {
    private final IAuthRepository authRepository;

    public AuthService(IAuthRepository authRepository) {
        super(authRepository);
        this.authRepository = authRepository;
    }

    public RegisterResponseDto doRegister(RegisterRequestDto dto){
        Auth auth = IAuthMapper.INSTANCE.fromAuthRegisterRequestDtoToAuth(dto);
        if (auth.getPassword().equals(dto.getPasswordConfirm())){
            auth.setActivationCode((CodeGenerator.generateCode()));
            authRepository.save(auth);

        }else {
            throw new AuthManagerException(ErrorType.PASSWORD_ERROR);
        }
        RegisterResponseDto responseDto = IAuthMapper.INSTANCE.fromAuthToAuthRegisterResponseDto(auth);
        return responseDto;
    }



}
