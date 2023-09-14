package com.hrmanagement.repository;

import com.hrmanagement.repository.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends MongoRepository<User,String> {

    Optional<User> findOptionalByAuthId(Long authId);

    Optional<User> findOptionalByEmail(String email);

    Optional<User> findOptionalByIdNumber(String idNumber);

    Optional<User> findById(Long aLong);

}
