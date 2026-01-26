package com.devtiro.tasks.services.impl;

import com.devtiro.tasks.dto.TaskDTO;
import com.devtiro.tasks.dto.TaskListDTO;
import com.devtiro.tasks.entities.Task;
import com.devtiro.tasks.entities.TaskPriority;
import com.devtiro.tasks.entities.TaskStatus;
import com.devtiro.tasks.mapper.TaskMapper;
import com.devtiro.tasks.repositories.TaskListRepo;
import com.devtiro.tasks.repositories.TaskRepo;
import com.devtiro.tasks.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final ResourcePatternResolver resourcePatternResolver;
    private TaskRepo taskRepo;
    private TaskMapper taskMapper;
    private TaskListRepo taskListRepo;



    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> listTasks(UUID taskListId) {

        if (isTaskListNotExists(taskListId)){
            throw new IllegalArgumentException("task list not found");
        }
        List<Task> tasks = taskRepo.findByTaskListId(taskListId);
        return tasks.isEmpty()? List.of() : taskMapper.toDtos(tasks);
    }

    @Override
    @Transactional
    public TaskDTO createTask(UUID taskListId, TaskDTO taskDTO) {
        if (isTaskListNotExists(taskListId)){
            throw new IllegalArgumentException("task list already exists");
        }

        Task task = taskMapper.fromDto(taskDTO);

        if (task.getDescription() == null || task.getDescription().isEmpty()){
            task.setDescription("");
        }

//        it's not good to check because there might be users need to archive there tasks
//        if (task.getDueDate().isBefore(LocalDateTime.now())){
//            task.setDueDate(LocalDateTime.now());
//        }

        task.setTaskList(taskListRepo.findById(taskListId).get());
        task.setCreated(LocalDateTime.now());
        task.setUpdated(LocalDateTime.now());

        taskRepo.save(task);
        return taskMapper.toDto(task);
    }

    @Override
    @Transactional
    public TaskDTO getTaskById(UUID taskListId, UUID id) {
        return taskMapper.toDto(taskRepo.findByTaskListIdAndId(taskListId,id).orElseThrow(
                () -> new IllegalArgumentException("task not found or task don't belong to this task list"))
        );
    }

    @Override
    @Transactional
    public TaskDTO updateTask(UUID taskListId, UUID id, TaskDTO taskDTO) {

        Task task = taskRepo.findByTaskListIdAndId(taskListId,id).orElseThrow(
                () -> new IllegalArgumentException("task not found or task don't belong to this task list")
        );

        task.setTitle(taskDTO.title());
        task.setDescription(taskDTO.description());
        task.setDueDate(taskDTO.dueDate());
        task.setPriority(taskDTO.priority());
        task.setStatus(taskDTO.status());
        task.setUpdated(LocalDateTime.now());

//        taskRepo.save(task);
        return taskMapper.toDto(task);
    }

    @Override
    @Transactional
    public void deleteTask(UUID taskListId, UUID id) {
        taskRepo.deleteByTaskListIdAndId(taskListId, id);
    }


    private Boolean isTaskListNotExists(UUID taskListId){
        return taskListId == null && !taskListRepo.existsById(taskListId);
    }
}
