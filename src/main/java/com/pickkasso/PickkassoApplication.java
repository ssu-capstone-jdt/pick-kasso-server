package com.pickkasso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PickkassoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PickkassoApplication.class, args);
    }
}
