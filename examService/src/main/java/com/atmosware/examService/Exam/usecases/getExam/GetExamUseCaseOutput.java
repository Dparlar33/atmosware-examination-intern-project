package com.atmosware.examService.Exam.usecases.getExam;

import com.atmosware.examService.usecase.UseCaseOutput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class GetExamUseCaseOutput implements UseCaseOutput {
    private GetExamByIdResponse getExamByIdResponse;
}
