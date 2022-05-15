package dev.ashikuzzaman.experiment.springboot_quartz_postgres_experiment.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Slf4j
public class TestJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) {
        JobDetail jobDetail = context.getJobDetail();
        log.debug("Calling: TestJob.executeInternal: {}", jobDetail.getKey().getName());
    }
}