package com.fitness.tracker.Service;

import com.fitness.tracker.DTO.CreateUserPlanDto;
import com.fitness.tracker.DTO.CreatedWorkoutDto;

import com.fitness.tracker.Entity.User;
import com.fitness.tracker.Entity.UserWorkout;
import com.fitness.tracker.Entity.Workout;
import com.fitness.tracker.Repository.UserRepository;
import com.fitness.tracker.Repository.UserWorkoutRepository;
import com.fitness.tracker.Repository.WorkoutRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final UserService userService;
    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private UserWorkoutRepository userWorkoutRepository;

    public Workout createWorkout(CreatedWorkoutDto createdWorkoutDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("No authenticated user found");
        }

        Workout workout = modelMapper.map(createdWorkoutDto, Workout.class);
        User user = userService.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        workout.setUser(user);
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

    public void createWorkoutPlan(CreateUserPlanDto createUserPlanDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(authentication.getName()).orElseThrow(() -> new RuntimeException("User not found"));

        Set<UserWorkout> userWorkouts = createUserPlanDto.getWorkoutIds().stream()
                .map(id -> userWorkoutRepository.findByWorkout(new Workout(id))
                        .orElseThrow(() -> new RuntimeException("Workout not found with id: " + id)))
                .collect(java.util.stream.Collectors.toSet());

        user.setUserWorkouts(userWorkouts);
        userRepository.save(user);
    }

    public void completeWorkout(Long workoutId) {
        UserWorkout workout = userWorkoutRepository.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found with id: " + workoutId));

        workout.setComplete(true);
        userWorkoutRepository.save(workout);
    }
}
