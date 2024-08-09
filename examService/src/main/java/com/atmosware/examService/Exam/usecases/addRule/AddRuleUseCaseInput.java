package com.atmosware.examService.Exam.usecases.addRule;

import com.atmosware.examService.usecase.UseCaseInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddRuleUseCaseInput implements UseCaseInput {
    private AddRuleRequest addRuleRequest;
}
