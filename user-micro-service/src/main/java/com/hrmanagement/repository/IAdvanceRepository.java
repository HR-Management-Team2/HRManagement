package com.hrmanagement.repository;

import com.hrmanagement.repository.entity.Advance;
import com.hrmanagement.repository.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAdvanceRepository extends MongoRepository<Advance, String> {
    List<Advance> findAllByAuthId(Long authId);
    List<Advance> findAllByTaxNoAndCompanyName(String taxNo, String companyName);
}
