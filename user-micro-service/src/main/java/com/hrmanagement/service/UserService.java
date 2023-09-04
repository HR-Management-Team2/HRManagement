package com.hrmanagement.service;

import com.hrmanagement.dto.request.UserCreateRequestDto;
import com.hrmanagement.dto.request.UserUpdateRequestDto;
import com.hrmanagement.exceptions.ErrorType;
import com.hrmanagement.exceptions.UserManagerException;
import com.hrmanagement.mapper.IUserMapper;
import com.hrmanagement.repository.IUserRepository;
import com.hrmanagement.repository.entity.User;
import com.hrmanagement.repository.enums.EStatus;
import com.hrmanagement.utility.ServiceManager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService extends ServiceManager<User,String> {
    private final IUserRepository repository;
    public UserService(IUserRepository repository) {
        super(repository);
        this.repository=repository;
    }

    public Boolean createUser(UserCreateRequestDto dto) {
        repository.save(IUserMapper.INSTANCE.fromCreateDtoToUser(dto));
        return true;
    }

    @Transactional
    public Boolean updateUser (UserUpdateRequestDto dto) {

        Optional<User> user= repository.findOptionalByAuthId(dto.getAuthId());
        if (user.isPresent()) {
            repository.save(IUserMapper.INSTANCE.fromUpdateDtoToUserProfile(dto,user.get()));
            return true;
        }
        throw new RuntimeException("Hata");
    }

    public Boolean deleteById(Long authId){
        System.out.println(authId);
        Optional<User> user = repository.findOptionalByAuthId(authId);

        user.orElseThrow(() -> {throw new UserManagerException(ErrorType.USER_NOT_FOUND);});

        user.get().setStatus(EStatus.DELETED);
        update(user.get());
        return true;
    }


}
