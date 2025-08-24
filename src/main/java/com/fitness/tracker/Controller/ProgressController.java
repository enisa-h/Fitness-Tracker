package com.fitness.tracker.Controller;

import com.fitness.tracker.DTO.CreateProgressDto;
import com.fitness.tracker.Service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/progress")
public class ProgressController {

    @Autowired
    private ProgressService progressService;

    @PostMapping
    public void createProgress(@RequestBody CreateProgressDto createProgressDto) {
        progressService.createProgress(createProgressDto);
    }

}
