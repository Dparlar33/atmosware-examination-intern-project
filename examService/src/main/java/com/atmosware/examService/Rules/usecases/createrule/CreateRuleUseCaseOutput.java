package com.atmosware.examService.Rules.usecases.createrule;

import com.atmosware.examService.usecase.UseCaseOutput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CreateRuleUseCaseOutput implements UseCaseOutput {
    private CreateRuleResponse createRuleResponse;
}
