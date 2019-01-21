package com.kot.jpa;

import com.kot.jpa.model.Person;
import com.kot.jpa.repository.PersonCrudRepository;
import com.kot.jpa.repository.PersonJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Application implements CommandLineRunner{

    @Autowired
    PersonCrudRepository repository;

    @Autowired
    PersonJpaRepository jpaRepository;

    Logger logger = LoggerFactory.getLogger(Application.class.getName());

    public static void main(String[] a) {
        SpringApplication.run(Application.class);
    }

    @Override
    public void run(String... args) throws Exception {
        setUpdata();
        findPersonsWithAgeAfter(18);
        deleteFirstTwoPersons();
        updateAge();
    }

    private void updateAge() {
        logger.info("Update all users age");
        jpaRepository.setAllUsersAgeOnOne();
        printAll();
    }

    private void printAll() {
        logger.info("All users in DB:");
        jpaRepository.findAll()
                .stream()
                .map(Person::toString)
                .forEach(logger::info);
    }

    private void deleteFirstTwoPersons() {
        logger.info("Person in database before delete: " + jpaRepository.count());
        printAll();

        Page<Person> personPage = jpaRepository.findAll(PageRequest.of(1, 2));
        List<Person> personList = personPage.get()
                .collect(Collectors.toList());

        jpaRepository.deleteInBatch(personList);

        logger.info("Person in database after delete: " + jpaRepository.count());
        printAll();

    }

    private void findPersonsWithAgeAfter(int age) {
        repository.findByAgeAfter(age)
                .forEach(person -> logger.info("Person with age above " + age + " " + person));
    }

    private void setUpdata() {
        repository.save(Person.builder().age(12).name("John").surname("Mock").build());
        repository.save(Person.builder().age(13).name("John").surname("Franc").build());
        repository.save(Person.builder().age(14).name("John").surname("Young").build());
        repository.save(Person.builder().age(20).name("John").surname("Old").build());
        repository.save(Person.builder().age(30).name("John").surname("Oldest").build());

        logger.info("Person in database: " + repository.count());
    }
}
