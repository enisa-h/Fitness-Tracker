package com.fitness.tracker.Service;

import com.fitness.tracker.DTO.CreatedWorkoutDto;

import com.fitness.tracker.Entity.User;
import com.fitness.tracker.Entity.Workout;
import com.fitness.tracker.Repository.WorkoutRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final UserService userService;

    private ModelMapper modelMapper = new ModelMapper();

    public Workout createWorkout(CreatedWorkoutDto createdWorkoutDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("No authenticated user found");
        }

        Workout workout = modelMapper.map(createdWorkoutDto, Workout.class);
        User user = userService.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        workout.setUser(user); // Assuming userService provides the current user
        return workoutRepository.save(workout);
    }

    public Workout updateWorkout(Long workoutId, CreatedWorkoutDto createdWorkoutDto) {

        Workout existingWorkout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found"));
        // Map the fields from createdWorkoutDto to existingWorkout
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(createdWorkoutDto, existingWorkout);
        return workoutRepository.save(existingWorkout);
    }

    public void deleteWorkout(Long workoutId) {
        workoutRepository.deleteById(workoutId);
    }

    public List<Workout> getAll() {
        return workoutRepository.findAll();
    }
}
