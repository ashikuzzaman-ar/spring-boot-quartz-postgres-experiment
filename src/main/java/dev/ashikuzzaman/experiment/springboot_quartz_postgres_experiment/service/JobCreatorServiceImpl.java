package dev.ashikuzzaman.experiment.springboot_quartz_postgres_experiment.service;

import dev.ashikuzzaman.experiment.springboot_quartz_postgres_experiment.dto.JobRemoveRequest;
import dev.ashikuzzaman.experiment.springboot_quartz_postgres_experiment.dto.JobScheduleRequest;
import dev.ashikuzzaman.experiment.springboot_quartz_postgres_experiment.dto.JobScheduleResponse;
import dev.ashikuzzaman.experiment.springboot_quartz_postgres_experiment.exception.NullParameterException;
import dev.ashikuzzaman.experiment.springboot_quartz_postgres_experiment.exception.SchedulerCreationException;
import dev.ashikuzzaman.experiment.springboot_quartz_postgres_experiment.exception.SchedulerRemoveException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class JobCreatorServiceImpl implements JobCreatorService {

    private final Scheduler scheduler;

    @Value("${custom.scheduler.job.identity-prefix}")
    private String identityPrefix;

    @Value("${custom.scheduler.job.trigger-prefix}")
    private String triggerPrefix;

    @Override
    public JobDetail createJobDetail(JobScheduleRequest request) {
        log.debug("Calling: JobCreatorServiceImpl.createJobDetail");
        return JobBuilder.newJob()
                .ofType(request.jobType().getJobClass())
//                .storeDurably()
                .withIdentity(this.identityPrefix + request.jobIdentity())
                .withDescription(request.jobDescription())
                .build();
    }

    @Override
    public Trigger createTrigger(JobScheduleRequest request, JobDetail jobDetail) {
        log.debug("Calling: JobCreatorServiceImpl.createTrigger");
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(this.triggerPrefix + request.jobIdentity())
                .withDescription(request.jobDescription())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .repeatForever()
                        .withIntervalInMilliseconds(request.intervalInMilliSecond())
                )
                .startNow()
                .build();
    }

    @Override
    public Optional<JobScheduleResponse> scheduleJob(JobScheduleRequest request) {
        log.debug("Calling: JobCreatorServiceImpl.scheduleJob(JobDetailRequest)");
        JobDetail jobDetail = this.createJobDetail(request);
        Trigger trigger = this.createTrigger(request, jobDetail);
        return this.scheduleJob(request, jobDetail, trigger);
    }

    @Override
    public Optional<JobScheduleResponse> scheduleJob(JobScheduleRequest request, JobDetail jobDetail, Trigger trigger) {
        log.debug("Calling: JobCreatorServiceImpl.scheduleJob(JobDetail, Trigger)");
        if (jobDetail == null) throw new NullParameterException("jobDetail cannot null");
        if (trigger == null) throw new NullParameterException("trigger cannot null");
        try {
            this.scheduler.scheduleJob(jobDetail, trigger);
            return Optional.of(new JobScheduleResponse(request.jobIdentity()));
        } catch (Exception e) {
            log.error("Exception in scheduling the job with identity: {}", jobDetail.getKey().getName(), e);
            throw new SchedulerCreationException();
        }
    }

    @Override
    public void removeJob(JobRemoveRequest request) {
        log.debug("Calling: JobCreatorServiceImpl.removeJob");
        try {
            this.scheduler.unscheduleJob(TriggerKey.triggerKey(this.triggerPrefix + request.jobIdentity()));
        } catch (Exception e) {
            log.error("Exception in removing job with identity: {}", request.jobIdentity());
            throw new SchedulerRemoveException();
        }
    }
}
