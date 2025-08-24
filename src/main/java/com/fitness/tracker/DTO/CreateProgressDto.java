package com.fitness.tracker.DTO;


import lombok.Data;

@Data
public class CreateProgressDto {
    private Integer caloriesBurned;
    private Integer totalSteps;
}
