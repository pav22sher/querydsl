package com.example.querydsl.dao;

import com.example.querydsl.entity.Person;

import java.util.List;
import java.util.Map;

public interface PersonDao {
    Person save(Person person);
    List<Person> findByFirstname(String firstname);
    List<Person> findByFirstnameAndSurname(String firstname, String surname);
    List<Person> findByFirstnameWithOrdering(String firstname);
    int findMaxAge();
    Map<String, Integer> findMaxAgeByName();
}
