package com.devtiro.tasks.services.impl;



import com.devtiro.tasks.dto.TaskListDTO;
import com.devtiro.tasks.entities.TaskList;
import com.devtiro.tasks.mapper.TaskListMapper;
import com.devtiro.tasks.mapper.TaskMapper;
import com.devtiro.tasks.repositories.TaskListRepo;
import com.devtiro.tasks.services.TaskListService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Service
@AllArgsConstructor
public class TaskListServiceImpl implements TaskListService {

    private TaskListRepo taskListRepo;
    private TaskListMapper taskListMapper;
    private TaskMapper taskMapper;

    @Override
    @Transactional(readOnly = true) // Keeps the DB connection open!
    public List<TaskListDTO> listTaskLists() {
        return taskListRepo.findAll().stream()
                .map(taskListMapper::toDto).toList();
    }

    @Override
    @Transactional
    public TaskListDTO createTaskList(TaskListDTO taskListDTO) {

        if (taskListDTO.id() != null) {
            throw new IllegalArgumentException("this Id is already exists");
        }

        if (taskListDTO.title() == null || taskListDTO.title().isBlank()) {
            throw new IllegalArgumentException("title is required");
        }
        TaskList taskList = new TaskList(
                null,
                taskListDTO.title(),
                taskListDTO.description(),
                null,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        TaskList savedTaskList = taskListRepo.save(taskList);
        System.out.println("taskList: " + taskList.toString());
        System.out.println("======================================");
        System.out.println("savedTaskList: " + savedTaskList.toString());



        return taskListMapper.toDto(savedTaskList);
    }

    @Override
    @Transactional(readOnly = true) // Keeps the DB connection open!
    public TaskListDTO getTaskListById(UUID id) {

        return taskListRepo.findById(id)
                .map(taskListMapper::toDto)
                .orElseThrow(
                        ()-> new IllegalArgumentException("task list not found")
                );


    }

    @Override
    @Transactional
    public TaskListDTO updateTaskList(UUID id, TaskListDTO taskListDTO) {

        if (id == null){
            throw new IllegalArgumentException("id is required");
        }
        if (taskListDTO.title() == null || taskListDTO.title().isBlank()) {
            throw new IllegalArgumentException("title is required");
        }
        TaskList taskList = taskListRepo.findById(id).orElseThrow(
                () -> new IllegalArgumentException("task list not found"));

        taskList.setTitle(taskListDTO.title());
        taskList.setDescription(taskListDTO.description());
        taskList.setUpdated(LocalDateTime.now());

        System.out.println("*************************************************");
        System.out.println(taskList.toString());
        System.out.println("*************************************************");
//        taskList.setTasks(taskMapper.fromDtos(taskListDTO.tasks()));


        return taskListMapper.toDto(taskList);
    }

    @Override
    @Transactional
    public void deleteTaskList(UUID id) {
        taskListRepo.deleteById(id);
    }
}