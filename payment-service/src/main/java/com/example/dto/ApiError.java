package com.example.dto;

import java.time.LocalDateTime;

public record ApiError(String message, int status, LocalDateTime timestamp) {
}
