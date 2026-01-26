package com.devtiro.tasks.mapper.impl;

import com.devtiro.tasks.dto.TaskListDTO;
import com.devtiro.tasks.entities.Task;
import com.devtiro.tasks.entities.TaskList;
import com.devtiro.tasks.entities.TaskStatus;
import com.devtiro.tasks.mapper.TaskListMapper;
import com.devtiro.tasks.mapper.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapper taskMapper;

    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskListDTO toDto(TaskList taskList) {
        List<Task> tasks = Optional.ofNullable(taskList.getTasks()).orElse(Collections.emptyList());
        
        return new TaskListDTO(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(tasks).map(List::size).orElse(0),
                calculateTaskListProgress(tasks),
                Optional.ofNullable(tasks).map(taskMapper::toDtos).orElse(Collections.emptyList())
        );
    }

    @Override
    public TaskList fromDto(TaskListDTO taskListDto) {
        return new TaskList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                Optional.ofNullable(taskListDto.tasks())
                        .map(taskMapper::fromDtos)
                        .orElse(null),
                null,
                null
        );
    }

    private Double calculateTaskListProgress(List<Task> tasks) {
        if (null == tasks || tasks.isEmpty()) {
            return 0.0;
        }

        long closedTaskCount = tasks.stream().filter(task ->
                TaskStatus.CLOSED == task.getStatus()
        ).count();

        return (double) closedTaskCount / tasks.size();
    }
}
