package dev.ashikuzzaman.experiment.springboot_quartz_postgres_experiment.util;

import dev.ashikuzzaman.experiment.springboot_quartz_postgres_experiment.job.TestJob;
import lombok.Getter;
import org.springframework.scheduling.quartz.QuartzJobBean;

public enum JobType {
    TEST_JOB(TestJob.class);

    @Getter
    private final Class<? extends QuartzJobBean> jobClass;

    JobType(Class<? extends QuartzJobBean> jobClass) {
        this.jobClass = jobClass;
    }
}
