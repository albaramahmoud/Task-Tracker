package com.devtiro.tasks.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table
@Entity(name = "task_lists")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class TaskList {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID Id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description")
    private String description;


    @OneToMany(mappedBy = "taskList"
            // mappedBy job: Look at the taskList variable on the Task class
            // to know more information about this relationship
            ,cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<Task> tasks;

    @Column(name = "created",nullable = false)
    private LocalDateTime created;

    @Column(name = "updated",nullable = false)
    private LocalDateTime updated;

    public TaskList(UUID id, String title, String description, List<Task> tasks, LocalDateTime created, LocalDateTime updated) {
        Id = id;
        this.title = title;
        this.description = description;
        this.tasks = tasks;
        this.created = created;
        this.updated = updated;
    }
}
