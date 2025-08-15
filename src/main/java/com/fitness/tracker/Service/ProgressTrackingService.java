package com.fitness.tracker.Service;

import com.fitness.tracker.Entity.Workout;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProgressTrackingService {
    private final WorkoutService workoutService;
    private final UserService userService;

    public ProgressTrackingService(WorkoutService workoutService, UserService userService) {
        this.workoutService = workoutService;
        this.userService = userService;
    }


    public List<Workout> getProgressForUser(Long userId) {
        // Fetch all workouts for the user
        List<Workout> workouts = workoutService.getAll();

        // Filter workouts by user ID and date
        return workouts.stream()
                .filter(workout -> workout.getUser().getId().equals(userId) &&
                                   workout.getCreatedAt().isAfter(LocalDateTime.now().minusMonths(1)))
                .toList();
    }
}