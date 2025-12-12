package com.fitness_history_service.web;

import com.fitness_history_service.workout_log.model.WorkoutLog;
import com.fitness_history_service.web.dto.WorkoutLogRequest;
import com.fitness_history_service.workout_log.service.WorkoutLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

    private final WorkoutLogService workoutLogService;

    @Autowired
    public HistoryController(WorkoutLogService workoutLogService) {
        this.workoutLogService = workoutLogService;
    }


    @PostMapping
    public ResponseEntity<String> logWorkout(@RequestBody WorkoutLogRequest request) {
        workoutLogService.logWorkout(request);
        return ResponseEntity.ok("Workout logged successfully!");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WorkoutLog>> getUserHistory(@PathVariable UUID userId) {
        return ResponseEntity.ok(workoutLogService.getUserHistory(userId));
    }

}