package com.atmosware.examService.Rules.usecases.createrule;

import com.atmosware.examService.usecase.UseCaseInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateRuleUseCaseInput implements UseCaseInput {
    private String description;
}
