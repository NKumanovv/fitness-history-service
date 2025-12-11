package com.fitness_history_service.exception;

public class HistoryNotFoundException extends RuntimeException {
    public HistoryNotFoundException(String message) {
        super(message);
    }
}