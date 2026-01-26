package com.devtiro.tasks.services;

import com.devtiro.tasks.dto.TaskDTO;
import com.devtiro.tasks.entities.Task;
import com.devtiro.tasks.entities.TaskList;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    List<TaskDTO> listTasks(UUID taskListId);
    TaskDTO createTask(UUID taskListId, TaskDTO taskDTO);

    TaskDTO getTaskById(UUID taskListId, UUID id);

    TaskDTO updateTask(UUID taskListId, UUID id, TaskDTO taskDTO);

    void deleteTask(UUID taskListId, UUID id);

}
