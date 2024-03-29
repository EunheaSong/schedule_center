package com.seed_planner.schedule_center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ScheduleCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleCenterApplication.class, args);
    }

}
