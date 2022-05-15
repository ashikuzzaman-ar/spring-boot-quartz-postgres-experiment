package dev.ashikuzzaman.experiment.springboot_quartz_postgres_experiment.service;

import dev.ashikuzzaman.experiment.springboot_quartz_postgres_experiment.dto.JobRemoveRequest;
import dev.ashikuzzaman.experiment.springboot_quartz_postgres_experiment.dto.JobScheduleRequest;
import dev.ashikuzzaman.experiment.springboot_quartz_postgres_experiment.dto.JobScheduleResponse;
import org.quartz.JobDetail;
import org.quartz.Trigger;

import java.util.Optional;

public interface JobCreatorService {
    JobDetail createJobDetail(JobScheduleRequest request);

    Trigger createTrigger(JobScheduleRequest request, JobDetail jobDetail);

    Optional<JobScheduleResponse> scheduleJob(JobScheduleRequest request);

    Optional<JobScheduleResponse> scheduleJob(JobScheduleRequest request, JobDetail jobDetail, Trigger trigger);

    void removeJob(JobRemoveRequest request);
}
