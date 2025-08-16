package com.fitness.tracker.Entity;

import com.fitness.tracker.Converter.DayOfWeekArrayConverter;
import com.fitness.tracker.Enum.DayOfWeek;
import com.fitness.tracker.Enum.WorkoutTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Data
public class Workout {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
  @Enumerated(STRING)
  @Column(nullable = false, name = "workout_type")
  private WorkoutTypeEnum workoutType;

  private Integer duration;
  private Integer calories;

  @Convert(converter = DayOfWeekArrayConverter.class)
  @Column(name = "days_of_week")
  private DayOfWeek[] daysOfWeek;
  private String notes;
  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;
  @UpdateTimestamp
    @Column(name = "updated_at")
  private LocalDate updatedAt;

  public User getUser() {
    return user;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }
}
