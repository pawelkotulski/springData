package com.kot.jpa;

import com.kot.jpa.model.Person;
import com.kot.jpa.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner{

    @Autowired
    PersonRepository repository;

    Logger logger = LoggerFactory.getLogger(Application.class.getName());

    public static void main(String[] a) {
        SpringApplication.run(Application.class);
    }

    @Override
    public void run(String... args) throws Exception {
        setUpdata();
        findPersonsWithAgeAfter(18);
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
