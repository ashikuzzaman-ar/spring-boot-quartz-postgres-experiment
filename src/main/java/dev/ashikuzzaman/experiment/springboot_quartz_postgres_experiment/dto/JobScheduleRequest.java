package dev.ashikuzzaman.experiment.springboot_quartz_postgres_experiment.dto;

import dev.ashikuzzaman.experiment.springboot_quartz_postgres_experiment.util.JobType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public record JobScheduleRequest(
        @NotNull(message = "jobType cannot null") JobType jobType,
        @NotBlank(message = "jobIdentity cannot blank") String jobIdentity,
        @NotBlank(message = "jobDescription cannot blank") String jobDescription,
        @Positive(message = "intervalInMilliSecond cannot negative") Long intervalInMilliSecond
) {
}
