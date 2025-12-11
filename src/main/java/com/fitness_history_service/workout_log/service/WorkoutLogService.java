package com.fitness_history_service.workout_log.service;

import com.fitness_history_service.workout_log.model.WorkoutLog;
import com.fitness_history_service.workout_log.repository.WorkoutLogRepository;
import com.fitness_history_service.web.dto.WorkoutLogRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkoutLogService {

    private final WorkoutLogRepository repository;

    public void logWorkout(WorkoutLogRequest request) {
        log.info("Saving workout history for User ID: [%s]. Workout: [%s]".formatted( request.getUserId(), request.getWorkoutName()));

        WorkoutLog log = WorkoutLog.builder()
                .userId(request.getUserId())
                .workoutName(request.getWorkoutName())
                .duration(request.getDuration())
                .dateCompleted(LocalDateTime.now())
                .build();

        repository.save(log);
    }

    public List<WorkoutLog> getUserHistory(UUID userId) {
        log.debug("Fetching history for user: [$s]".formatted( userId));
        return repository.findAllByUserIdOrderByDateCompletedDesc(userId);
    }

    public void deleteLog(UUID id) {
        repository.deleteById(id);
        log.debug("Deleting history log with ID: [$s]".formatted(id));

    }
}