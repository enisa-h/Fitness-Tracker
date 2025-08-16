package com.fitness.tracker.DTO;

import com.fitness.tracker.Enum.DayOfWeek;
import com.fitness.tracker.Enum.WorkoutTypeEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreatedWorkoutDto {
    @NotNull(message = "Workout type is required")
    private WorkoutTypeEnum workoutType;
    @Min(value = 1, message = "Duration must be at least 1 minute")
    private Integer duration;
    @Min(value = 0, message = "Calories burned cannot be negative")
    private Integer calories;
    private DayOfWeek[] daysOfWeek;
    private String notes;

}
