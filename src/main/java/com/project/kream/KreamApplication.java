package com.project.kream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class KreamApplication {
    public static void main(String[] args) {
        SpringApplication.run(KreamApplication.class, args);
    }

}
