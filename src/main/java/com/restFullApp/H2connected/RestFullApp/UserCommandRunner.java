package com.restFullApp.H2connected.RestFullApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserCommandRunner implements CommandLineRunner {

    @Autowired
    UserJpaResource repository;

    @Override
    public void run(String... args) throws Exception {
        repository.save(new User(1,"George", new Date()));
        repository.save(new User("Alina", new Date()));
        repository.save(new User("Geo", new Date()));
        System.out.println("Se afiseaza ----- \n");
        System.out.println(repository.findById(1l));
    }
}
