package com.example.lyhassignment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter@Getter
@NoArgsConstructor
public class TaskDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    @ManyToOne
    private User teacher;
    @ManyToOne
    private Task task;
    private String reply;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime completeTime;
    private int isComplete = 0;
    private int isInTime;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            insertable = false)
    private LocalDateTime insertTime;
}
