package com.atmosware.examService.Exam.usecases.removeQuestion;

import com.atmosware.examService.usecase.UseCaseInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RemoveQuestionVoidUseCaseInput implements UseCaseInput {
    private RemoveQuestionRequest removeQuestionRequest;
}
