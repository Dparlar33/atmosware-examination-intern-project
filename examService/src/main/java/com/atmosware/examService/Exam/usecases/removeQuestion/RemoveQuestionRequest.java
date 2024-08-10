package com.atmosware.examService.Exam.usecases.removeQuestion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RemoveQuestionRequest {
    private UUID questionId;
    private UUID examId;
}
