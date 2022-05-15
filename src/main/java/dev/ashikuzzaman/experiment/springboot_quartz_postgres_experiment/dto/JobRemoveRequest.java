package dev.ashikuzzaman.experiment.springboot_quartz_postgres_experiment.dto;

import javax.validation.constraints.NotBlank;

public record JobRemoveRequest(@NotBlank(message = "jobIdentity cannot blank") String jobIdentity) {
}
