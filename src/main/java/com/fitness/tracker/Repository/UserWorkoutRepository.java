package com.fitness.tracker.Repository;

import com.fitness.tracker.Entity.User;
import com.fitness.tracker.Entity.UserWorkout;
import com.fitness.tracker.Entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserWorkoutRepository extends JpaRepository<UserWorkout, Long> {
    Optional<UserWorkout> findByWorkout(Workout workout);
}
