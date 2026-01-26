package com.devtiro.tasks.repositories;

import com.devtiro.tasks.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepo extends JpaRepository<Task, UUID> {

    List<Task> findByTaskListId(UUID taskListId);
    Optional<Task> findByTaskListIdAndId(UUID taskListId,UUID id);

    void deleteByTaskListIdAndId(UUID taskListId,UUID id);

//    Task findByTaskListIdAndId(UUID taskListId, UUID id);

}
