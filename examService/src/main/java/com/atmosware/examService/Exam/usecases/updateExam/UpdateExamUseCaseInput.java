package com.atmosware.examService.Exam.usecases.updateExam;


import com.atmosware.examService.usecase.UseCaseInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateExamUseCaseInput implements UseCaseInput {
    private UpdateExamRequest updateExamRequest;
}
