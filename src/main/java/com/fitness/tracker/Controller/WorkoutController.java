package com.fitness.tracker.Controller;

import com.fitness.tracker.DTO.CreatedWorkoutDto;
import com.fitness.tracker.DTO.WorkoutResponseDto;
import com.fitness.tracker.Entity.Workout;
import com.fitness.tracker.Service.WorkoutService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;
    private ModelMapper modelMapper = new ModelMapper();

    @PostMapping("/create")
    public ResponseEntity<WorkoutResponseDto>  createWorkout(@RequestBody @Valid CreatedWorkoutDto createdWorkoutDto) {
        Workout workout = workoutService.createWorkout(createdWorkoutDto);
        WorkoutResponseDto responseDto = modelMapper.map(workout, WorkoutResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    @PatchMapping("/{workoutId}")
    public ResponseEntity<WorkoutResponseDto> updateWorkout(@PathVariable Long workoutId, @RequestBody @Valid CreatedWorkoutDto createdWorkoutDto) {
        Workout workout = workoutService.updateWorkout(workoutId, createdWorkoutDto);
        WorkoutResponseDto responseDto = modelMapper.map(workout, WorkoutResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    @DeleteMapping("/{workoutId}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Long workoutId) {
        workoutService.deleteWorkout(workoutId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<WorkoutResponseDto>> getAll() {
        List<Workout> workouts = workoutService.getAll();
        List<WorkoutResponseDto> responseDtos = workouts.stream()
                .map(workout -> modelMapper.map(workout, WorkoutResponseDto.class))
                .toList();
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }
}


