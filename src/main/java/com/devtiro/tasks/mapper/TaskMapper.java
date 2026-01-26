package com.devtiro.tasks.mapper;

import com.devtiro.tasks.dto.TaskDTO;
import com.devtiro.tasks.entities.Task;

import java.util.List;

public interface TaskMapper {

    Task fromDto(TaskDTO taskDto);

    TaskDTO toDto(Task task);

    public List<Task> fromDtos(List<TaskDTO> taskDTOs);

    public List<TaskDTO> toDtos(List<Task> tasks);
}