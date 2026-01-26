package com.devtiro.tasks.controllers;

import com.devtiro.tasks.dto.TaskDTO;
import com.devtiro.tasks.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/task-lists/{task_list_id}/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    @GetMapping
    public ResponseEntity<List<TaskDTO>> listTasks(@PathVariable("task_list_id") UUID taskListId){

        return ResponseEntity.ok(
                taskService.listTasks(taskListId)
        );
//        return ResponseEntity.ok(new TaskListResponse());
    }


    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@PathVariable("task_list_id") UUID taskListId,@RequestBody TaskDTO taskDTO){

        return ResponseEntity.ok(
                taskService.createTask(taskListId,taskDTO)
        );
    }

    @GetMapping("/{task_id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable("task_list_id") UUID taskListId,@PathVariable("task_id") UUID task_id){

        return ResponseEntity.ok(taskService.getTaskById(taskListId,task_id));
    }

    @PutMapping("/{task_id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable("task_list_id") UUID taskListId,@PathVariable("task_id") UUID task_id,@RequestBody TaskDTO taskDTO){

        return ResponseEntity.ok(taskService.updateTask(taskListId,task_id,taskDTO));
    }

    @DeleteMapping("/{task_id}")
    public ResponseEntity<Void> DeleteTask(@PathVariable("task_list_id") UUID taskListId, @PathVariable UUID task_id){

        taskService.deleteTask(taskListId,task_id);
        return ResponseEntity.noContent().build();
    }

}
