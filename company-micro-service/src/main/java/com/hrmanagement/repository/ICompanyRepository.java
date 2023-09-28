package com.hrmanagement.repository;

import com.hrmanagement.repository.entity.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICompanyRepository extends MongoRepository<Company,String > {

    Optional<Company> findOptionalByName(String companyName);

    Optional<Company> findById(Long companyId);

    Optional<Company> findByTaxNumber(String taxNumber);

    boolean existsByTaxNumberOrName(String taxNumber,String name);

    Optional<Company> findByTaxNumberAndName(String taxNumber,String name);
}
