package com.fitness_history_service.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitness_history_service.workout_log.model.WorkoutLog;
import com.fitness_history_service.web.dto.WorkoutLogRequest;
import com.fitness_history_service.workout_log.service.WorkoutLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HistoryController.class)
public class HistoryControllerTest {

    @MockitoBean
    private WorkoutLogService workoutLogService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void logWorkout_ValidRequest_ReturnsOk() throws Exception {
        WorkoutLogRequest request = new WorkoutLogRequest();
        request.setUserId(UUID.randomUUID());
        request.setWorkoutName("Legs");
        request.setDuration(45);

        mockMvc.perform(post("/api/history")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(workoutLogService).logWorkout(any(WorkoutLogRequest.class));
    }

    @Test
    void getUserHistory_ReturnsList() throws Exception {
        UUID userId = UUID.randomUUID();
        when(workoutLogService.getUserHistory(userId)).thenReturn(List.of(new WorkoutLog()));

        mockMvc.perform(get("/api/history/user/{userId}", userId))
                .andExpect(status().isOk());
    }
}