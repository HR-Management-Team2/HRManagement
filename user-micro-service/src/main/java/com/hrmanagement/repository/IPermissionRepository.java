package com.hrmanagement.repository;

import com.hrmanagement.repository.entity.Advance;
import com.hrmanagement.repository.entity.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IPermissionRepository extends MongoRepository<Permission, String > {

    List<Permission> findOptionalByAuthId(Long authId);

    List<Permission> findAllByTaxNoAndCompanyName(String taxNo, String companyName);

}
