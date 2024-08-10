package com.atmosware.examService.Exam.usecases.createExam;

import com.atmosware.examService.usecase.UseCaseOutput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CreateExamUseCaseOutput implements UseCaseOutput {
    private CreatedExamResponse createdExamResponse;
}
