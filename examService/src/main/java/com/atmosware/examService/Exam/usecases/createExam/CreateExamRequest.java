package com.atmosware.examService.Exam.usecases.createExam;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateExamRequest {
    private String description;
    private double duration;
}
