package com.devtiro.tasks.mapper.impl;

import com.devtiro.tasks.dto.TaskDTO;
import com.devtiro.tasks.entities.Task;
import com.devtiro.tasks.mapper.TaskMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public Task fromDto(TaskDTO taskDto) {


        return new Task(
                taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.dueDate(),
                taskDto.status(),
                taskDto.priority(),
                null,
                null,
                null

        );
    }

    @Override
    public TaskDTO toDto(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus()
        );
    }

    @Override
    public List<Task> fromDtos(List<TaskDTO> taskDTOs){

        if(taskDTOs == null || taskDTOs.isEmpty()){
            return new ArrayList<>();
        }
        return taskDTOs.stream()
                .map(this::fromDto)
                .collect(Collectors.toList());
//
//        List<Task> tasks = new ArrayList<>();
//        for (TaskDTO taskDTO : taskDTOs) {
//
//            tasks.add(fromDto(taskDTO));
//        }
//        return tasks;
    }


    @Override
    public List<TaskDTO> toDtos(List<Task> tasks){

        if (tasks == null) {
            return new ArrayList<>();
        }
        return tasks.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
//        if (tasks == null || tasks.isEmpty()){
//            return null;
//        }
//        List<TaskDTO> taskDTOs = new ArrayList<>();
//        for (Task task : tasks) {
//
//            taskDTOs.add(toDto(task));
//        }
//        return taskDTOs;
    }
}
