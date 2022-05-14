package dev.ashikuzzaman.experiment.springbootquartzpostgresexperiment.configuration;

import dev.ashikuzzaman.experiment.springbootquartzpostgresexperiment.job.TestJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfiguration {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob()
                .ofType(TestJob.class)
                .storeDurably()
                .withIdentity("TEST_JOB")
                .withDescription("This is a test job")
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("TEST_JOB_KEY")
                .withDescription("This is a test job trigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .repeatForever()
                        .withIntervalInSeconds(5)
                )
                .startNow()
                .build();
    }
}
