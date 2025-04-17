package com.project_sena.spring_boot.Gallery.Repository;


import com.project_sena.spring_boot.Gallery.Entity.ContentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ContentRepo extends MongoRepository<ContentEntity, BigInteger> {

 }
