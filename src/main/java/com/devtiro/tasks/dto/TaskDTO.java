package com.devtiro.tasks.dto;

import com.devtiro.tasks.entities.TaskPriority;
import com.devtiro.tasks.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDTO(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status

) {
}
