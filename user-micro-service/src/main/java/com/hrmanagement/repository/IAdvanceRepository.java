package com.hrmanagement.repository;

import com.hrmanagement.repository.entity.Advance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdvanceRepository extends MongoRepository<Advance, String> {

}
