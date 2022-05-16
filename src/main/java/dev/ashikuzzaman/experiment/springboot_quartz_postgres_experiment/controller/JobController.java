package dev.ashikuzzaman.experiment.springboot_quartz_postgres_experiment.controller;

import dev.ashikuzzaman.experiment.springboot_quartz_postgres_experiment.dto.JobRemoveRequest;
import dev.ashikuzzaman.experiment.springboot_quartz_postgres_experiment.dto.JobScheduleRequest;
import dev.ashikuzzaman.experiment.springboot_quartz_postgres_experiment.dto.JobScheduleResponse;
import dev.ashikuzzaman.experiment.springboot_quartz_postgres_experiment.exception.SchedulerCreationException;
import dev.ashikuzzaman.experiment.springboot_quartz_postgres_experiment.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/job")
public class JobController {

    private final JobService jobService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<JobScheduleResponse> createNewJob(@RequestBody JobScheduleRequest jobScheduleRequest) {
        log.debug("Calling: JobController.createNewJob");
        return ResponseEntity.ok(this.jobService.scheduleJob(jobScheduleRequest).orElseThrow(SchedulerCreationException::new));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void removeJob(@RequestBody JobRemoveRequest jobRemoveRequest) {
        log.debug("Calling: JobController.removeJob");
        this.jobService.removeJob(jobRemoveRequest);
    }
}
