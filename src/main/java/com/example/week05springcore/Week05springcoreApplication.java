package com.example.week05springcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Week05springcoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week05springcoreApplication.class, args);
    }

}
