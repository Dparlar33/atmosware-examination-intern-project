package com.atmosware.examService.Exam.usecases.createExam;

import com.atmosware.examService.usecase.UseCaseInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateExamUseCaseInput implements UseCaseInput {
    private String description;
    private double duration;
}
