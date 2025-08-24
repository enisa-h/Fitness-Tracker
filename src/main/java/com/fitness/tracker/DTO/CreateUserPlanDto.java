package com.fitness.tracker.DTO;

import lombok.Data;

import java.util.Set;

@Data
public class CreateUserPlanDto {
    private Set<Long> workoutIds;
}
