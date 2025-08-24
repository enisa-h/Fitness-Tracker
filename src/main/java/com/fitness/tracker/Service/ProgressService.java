package com.fitness.tracker.Service;


import com.fitness.tracker.DTO.CreateProgressDto;
import com.fitness.tracker.Entity.Progress;
import com.fitness.tracker.Entity.User;
import com.fitness.tracker.Repository.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ProgressService {

    @Autowired
    private ProgressRepository progressRepository;
    @Autowired
    private UserService userService;

    public void createProgress(CreateProgressDto createProgressDto) {
        Progress progress = new Progress();
        progress.setCaloriesBurned(createProgressDto.getCaloriesBurned());
        progress.setTotalSteps(createProgressDto.getTotalSteps());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(authentication.getName()).orElseThrow(() -> new RuntimeException("User not found"));

        progress.setUser(user);
        progressRepository.save(progress);
    }
}
