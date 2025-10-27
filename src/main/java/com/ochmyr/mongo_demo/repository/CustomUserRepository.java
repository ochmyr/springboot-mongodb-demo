package com.ochmyr.mongo_demo.repository;

import com.ochmyr.mongo_demo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CustomUserRepository {

    private final MongoTemplate mongoTemplate;

    public List<User> findByAgeRange(int min, int max) {
        Query query = new Query();
        query.addCriteria(Criteria.where("age").gte(min).lte(max));
        return mongoTemplate.find(query, User.class);
    }

}
