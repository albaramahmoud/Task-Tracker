package com.devtiro.tasks.mapper;

import com.devtiro.tasks.dto.TaskListDTO;
import com.devtiro.tasks.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDTO taskListDto);

    TaskListDTO toDto(TaskList taskList);

}
