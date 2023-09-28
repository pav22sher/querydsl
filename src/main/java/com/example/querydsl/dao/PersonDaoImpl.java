package com.example.querydsl.dao;

import com.example.querydsl.entity.Person;
import com.example.querydsl.entity.QPerson;
import com.querydsl.core.group.GroupBy;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Repository
public class PersonDaoImpl implements PersonDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Person save(Person person) {
        em.persist(person);
        return person;
    }

    @Override
    public List<Person> findByFirstname(final String firstname) {
        JPAQuery<Person> query = new JPAQuery<>(em);
        QPerson person = QPerson.person;
        return query.from(person)
                .where(person.firstname.eq(firstname))
                .fetch();
    }

    @Override
    public List<Person> findByFirstnameAndSurname(final String firstname, final String surname) {
        JPAQuery<Person> query = new JPAQuery<>(em);
        QPerson person = QPerson.person;
        return query.from(person)
                .where(person.firstname.eq(firstname).and(person.surname.eq(surname)))
                .fetch();
    }

    @Override
    public List<Person> findByFirstnameWithOrdering(String firstname) {
        JPAQuery<Person> query = new JPAQuery<>(em);
        QPerson person = QPerson.person;
        return query.from(person)
                .where(person.firstname.eq(firstname))
                .orderBy(person.age.desc())
                .fetch();
    }

    @Override
    public int findMaxAge() {
        JPAQuery<Person> query = new JPAQuery<>(em);
        QPerson person = QPerson.person;
        return query.from(person)
                .select(person.age.max())
                .fetchFirst();
    }

    @Override
    public Map<String, Integer> findMaxAgeByName() {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QPerson person = QPerson.person;
        return query.from(person)
                .transform(GroupBy.groupBy(person.firstname).as(GroupBy.max(person.age)));
    }

}
