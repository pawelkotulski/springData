package com.kot.jpa.repository;

import com.kot.jpa.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonCrudRepository extends CrudRepository<Person, Long> {

    List<Person> findByAgeAfter(Integer afterAge);
}
