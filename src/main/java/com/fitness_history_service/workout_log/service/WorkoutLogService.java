package com.fitness_history_service.workout_log.service;

import com.fitness_history_service.workout_log.model.WorkoutLog;
import com.fitness_history_service.workout_log.repository.WorkoutLogRepository;
import com.fitness_history_service.web.dto.WorkoutLogRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkoutLogService {

    private final WorkoutLogRepository repository;

    public void logWorkout(WorkoutLogRequest request) {
        WorkoutLog log = WorkoutLog.builder()
                .userId(request.getUserId())
                .workoutName(request.getWorkoutName())
                .duration(request.getDuration())
                .dateCompleted(LocalDateTime.now())
                .build();

        repository.save(log);
    }

    public List<WorkoutLog> getUserHistory(UUID userId) {
        return repository.findAllByUserIdOrderByDateCompletedDesc(userId);
    }

    public void deleteLog(UUID id) {
        repository.deleteById(id);
    }
}