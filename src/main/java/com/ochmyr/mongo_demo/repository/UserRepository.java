package com.ochmyr.mongo_demo.repository;

import com.ochmyr.mongo_demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    List<User> findByAgeGreaterThan(int age);
    List<User> findBySkills(String skill);
}
