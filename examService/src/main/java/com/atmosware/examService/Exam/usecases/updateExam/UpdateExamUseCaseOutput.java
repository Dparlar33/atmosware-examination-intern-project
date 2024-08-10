package com.atmosware.examService.Exam.usecases.updateExam;

import com.atmosware.examService.usecase.UseCaseOutput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateExamUseCaseOutput implements UseCaseOutput {
    private UpdatedExamResponse updatedExamResponse;
}
