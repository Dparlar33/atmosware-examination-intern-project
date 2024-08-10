package com.atmosware.examService.Exam.usecases.updateExam;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedExamResponse {
    private UUID examId;
    private String description;
    private double duration;
    private List<String> rules;
}
