package com.atmosware.examService.Exam.usecases.addQuestion;

import com.atmosware.examService.usecase.UseCaseInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AddQuestionUseCaseInput implements UseCaseInput {
    private AddQuestionRequest addQuestionRequest;
}
