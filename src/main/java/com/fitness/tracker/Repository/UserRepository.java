package com.fitness.tracker.Repository;

import com.fitness.tracker.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);// for login
    Optional<User> findByUsername(String username);
}
