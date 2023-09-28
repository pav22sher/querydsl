package com.example.querydsl;

import com.example.querydsl.dao.PersonDao;
import com.example.querydsl.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuerydslApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(QuerydslApplication.class, args);
    }

    @Autowired
    private PersonDao dao;

    @Override
    public void run(String... args) throws Exception {
        dao.save(new Person("Firstname0", "Surname0", 0));
        dao.findByFirstname("Firstname0").forEach(System.out::println);
        dao.findByFirstnameAndSurname("Firstname0", "Surname0").forEach(System.out::println);
        dao.findByFirstnameWithOrdering("Firstname0").forEach(System.out::println);
        System.out.println(dao.findMaxAge());
        System.out.println(dao.findMaxAgeByName());
    }
}
