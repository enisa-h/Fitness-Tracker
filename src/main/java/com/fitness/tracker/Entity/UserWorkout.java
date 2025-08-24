package com.fitness.tracker.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserWorkout {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;

    @Column(nullable = false)
    private boolean complete = false;
}
