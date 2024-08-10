package com.atmosware.examService.Exam.usecases.startExam;

import com.atmosware.examService.usecase.UseCaseInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StartExamUseCaseInput implements UseCaseInput {
    private StartExamRequest startExamRequest;

}
