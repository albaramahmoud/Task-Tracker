package com.devtiro.tasks.services;

import com.devtiro.tasks.dto.TaskListDTO;
import com.devtiro.tasks.entities.TaskList;

import java.util.List;
import java.util.UUID;

public interface TaskListService {
    List<TaskListDTO> listTaskLists();


    TaskListDTO createTaskList(TaskListDTO taskListDTO);

    TaskListDTO getTaskListById(UUID id);

    TaskListDTO updateTaskList(UUID id, TaskListDTO taskListDTO);

    void deleteTaskList(UUID id);
}
