package dev.ashikuzzaman.experiment.springboot_quartz_postgres_experiment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SchedulerRemoveException extends RuntimeException {
    public SchedulerRemoveException() {
        super("Exception in removing job");
    }
}
