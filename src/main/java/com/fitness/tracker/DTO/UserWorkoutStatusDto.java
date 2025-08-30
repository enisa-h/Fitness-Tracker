package com.fitness.tracker.DTO;

import java.time.LocalDateTime;

public class UserWorkoutStatusDto {
    private Long workoutId;
    private String name;
    private String description;
    private String difficulty;
    private boolean isComplete;
    private LocalDateTime completedAt;
}


