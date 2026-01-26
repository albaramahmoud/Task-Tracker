package com.devtiro.tasks.repositories;

import com.devtiro.tasks.entities.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskListRepo extends JpaRepository<TaskList, UUID> {

}
