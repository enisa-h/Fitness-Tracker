package com.fitness.tracker.DTO;



import com.fitness.tracker.Enum.DayOfWeek;
import com.fitness.tracker.Enum.WorkoutTypeEnum;
import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Data
public class WorkoutResponseDto {
    private Long id;
    @NotBlank(message = "Workout type is required")
    private WorkoutTypeEnum workoutType;
    @Min(value = 1, message = "Duration must be at least 1 minute")
    private Integer duration;
    @Min(value = 0, message = "Calories burned cannot be negative")
    private Integer calories;
    private DayOfWeek[] daysOfWeek;
    private String notes;
    private String createdAt;
    private String updatedAt;

}