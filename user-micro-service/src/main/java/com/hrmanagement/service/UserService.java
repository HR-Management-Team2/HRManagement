package com.hrmanagement.service;

import com.hrmanagement.dto.request.AuthUpdateRequestDto;
import com.hrmanagement.dto.request.UserCreateRequestDto;
import com.hrmanagement.dto.request.UserUpdateRequestDto;
import com.hrmanagement.exceptions.ErrorType;
import com.hrmanagement.exceptions.UserManagerException;
import com.hrmanagement.manager.IAuthManager;
import com.hrmanagement.mapper.IUserMapper;
import com.hrmanagement.rabbitmq.model.UserRegisterModel;
import com.hrmanagement.repository.IUserRepository;
import com.hrmanagement.repository.entity.User;
import com.hrmanagement.repository.enums.EStatus;
import com.hrmanagement.utility.JwtTokenManager;
import com.hrmanagement.utility.ServiceManager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService extends ServiceManager<User,String> {
    private final IUserRepository repository;
    private final JwtTokenManager jwtTokenManager;
    private final IAuthManager authManager;

    public UserService(IUserRepository repository, JwtTokenManager jwtTokenManager, IAuthManager authManager) {
        super(repository);
        this.repository = repository;
        this.jwtTokenManager = jwtTokenManager;
        this.authManager = authManager;
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
}
