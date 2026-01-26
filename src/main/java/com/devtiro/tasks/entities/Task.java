package com.devtiro.tasks.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "tasks")
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID Id;



    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false )
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority",nullable = false )
    private TaskPriority priority;

    @ManyToOne(fetch = FetchType.LAZY)
    // this is mean the task_list will not be loaded from the database
    // with the task until it will be needed
    @JoinColumn(name = "task_list_id") // fk column name on the Tasks table
    private TaskList taskList;

    @Column(name = "created",nullable = false)
    private LocalDateTime created;

    @Column(name = "updated",nullable = false)
    private LocalDateTime updated;


    public Task(UUID id, String title, String description, LocalDateTime dueDate, TaskStatus status, TaskPriority priority, TaskList taskList, LocalDateTime created, LocalDateTime updated) {
        Id = id;
        this.taskList = taskList;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
        this.created = created;
        this.updated = updated;
    }
}
