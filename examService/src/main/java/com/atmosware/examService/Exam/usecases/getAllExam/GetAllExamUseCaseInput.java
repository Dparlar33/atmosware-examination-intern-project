package com.atmosware.examService.Exam.usecases.getAllExam;


import com.atmosware.examService.usecase.UseCaseInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllExamUseCaseInput implements UseCaseInput {
    private GetAllExamRequest getAllExamRequest;
}
