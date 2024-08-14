package com.atmosware.examService.Exam.usecases.getAllExam;

import com.atmosware.examService.usecase.UseCaseOutput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllExamUseCaseOutput implements UseCaseOutput {
    private List<GetAllExamResponse> getAllExamResponse;
}
