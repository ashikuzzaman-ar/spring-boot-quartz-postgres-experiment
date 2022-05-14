package dev.ashikuzzaman.experiment.springbootquartzpostgresexperiment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringBootQuartzPostgresExperimentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootQuartzPostgresExperimentApplication.class, args);
        log.info("Application Started");
    }
}

