package com.devtiro.tasks.controllers;


import com.devtiro.tasks.dto.TaskListDTO;
import com.devtiro.tasks.mapper.TaskListMapper;
import com.devtiro.tasks.services.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/task-lists")
@RequiredArgsConstructor
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    @GetMapping
    public ResponseEntity<List<TaskListDTO>> getTaskLists(){
//    public List<TaskListDTO> getTaskLists(){

//        return taskListService.listTaskLists();
        return ResponseEntity.ok(taskListService.listTaskLists());

    }


    @PostMapping
    public ResponseEntity<TaskListDTO> createTaskList(@RequestBody TaskListDTO taskListDTO){

        return ResponseEntity.ok(taskListService.createTaskList(taskListDTO));
    }

    @GetMapping("/{task_list_id}")
    public ResponseEntity<TaskListDTO> getTaskListById(@PathVariable("task_list_id") UUID id){

        return ResponseEntity.ok(taskListService.getTaskListById(id));
    }

    @PutMapping("/{task_list_id}")
    public ResponseEntity<TaskListDTO> updateTaskList(@PathVariable("task_list_id") UUID id,@RequestBody TaskListDTO taskListDTO){
        return ResponseEntity.ok(taskListService.updateTaskList(id,taskListDTO));
    }

    @DeleteMapping("/{task_list_id}")
    public ResponseEntity<Void> DeleteTaskList(@PathVariable("task_list_id") UUID id){
        taskListService.deleteTaskList(id);
        return ResponseEntity.noContent().build();
    }

}
