
package com.fitness_history_service.workout_log.service;

import com.fitness_history_service.workout_log.model.WorkoutLog;
import com.fitness_history_service.workout_log.repository.WorkoutLogRepository;
import com.fitness_history_service.web.dto.WorkoutLogRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

    @ExtendWith(MockitoExtension.class)
    class WorkoutLogServiceTest {

        @Mock
        private WorkoutLogRepository workoutLogRepository;

        @InjectMocks
        private WorkoutLogService workoutLogService;

        @Test
        void logWorkout_SavesToRepository() {
            WorkoutLogRequest request = new WorkoutLogRequest();
            request.setUserId(UUID.randomUUID());
            request.setWorkoutName("Chest Day");
            request.setDuration(60);

            workoutLogService.logWorkout(request);

            verify(workoutLogRepository).save(any(WorkoutLog.class));
        }

        @Test
        void getUserHistory_ReturnsList() {
            UUID userId = UUID.randomUUID();
            when(workoutLogRepository.findAllByUserIdOrderByDateCompletedDesc(userId)).thenReturn(List.of(new WorkoutLog()));

            List<WorkoutLog> result = workoutLogService.getUserHistory(userId);

            assertEquals(1, result.size());
        }
    }