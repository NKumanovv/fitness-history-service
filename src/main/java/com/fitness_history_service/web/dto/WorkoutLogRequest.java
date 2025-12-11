package com.fitness_history_service.web.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class WorkoutLogRequest {
    private UUID userId;
    private String workoutName;
    private int duration;
}