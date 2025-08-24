package com.fitness.tracker.Repository;

import com.fitness.tracker.Entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
}
