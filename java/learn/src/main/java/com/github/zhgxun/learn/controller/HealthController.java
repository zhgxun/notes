package com.github.zhgxun.learn.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HealthController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping(path = "/health", method = RequestMethod.GET)
    public String health() {
        return "success";
    }

    @GetMapping("/test")
    public List<Entity> mongoTest() {
        List<Entity> entities = mongoTemplate.find(new Query(Criteria.where("name").is("abc")), Entity.class, "kuaidibird");
        return entities;
    }

    @Data
    class Entity {
        private String name;
        private int age;
        private String desc;
    }
}
