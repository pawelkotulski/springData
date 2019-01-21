package com.kot.jpa.repository;

import com.kot.jpa.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PersonJpaRepository extends JpaRepository<Person, Long> {


    @Transactional
    @Modifying
    @Query(value = "update Person p set p.age = 1 ",
            nativeQuery = true)
    int setAllUsersAgeOnOne();
}
