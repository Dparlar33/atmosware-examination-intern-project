package com.atmosware.examService.Exam;

import com.atmosware.common.exam.GetQuestionAndOption;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Document(collection = "exams")
public class Exam {

    @Id
    private UUID id;

    private String description;
    private double duration;
    private UUID userId;
    private String roleName;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Field("rules")
    private List<String> rules;

    @Field("questionAndOptions")
    private List<GetQuestionAndOption> questionAndOptions;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    private LocalDateTime deletedDate;

    public Exam() {
        this.id = UUID.randomUUID();
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
}
