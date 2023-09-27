package com.hrmanagement.repository;

import com.hrmanagement.repository.entity.Advance;
import com.hrmanagement.repository.entity.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IExpenseRepository extends MongoRepository<Expense,String> {
    List<Expense> findAllByAuthId(Long authId);
    List<Expense> findAllByTaxNoAndCompanyName(String taxNo,String companyName);
}
