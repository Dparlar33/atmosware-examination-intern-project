package com.atmosware.examService.Rules.usecases.getrule;

import com.atmosware.examService.usecase.UseCaseOutput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetRuleByIdUseCaseOutput implements UseCaseOutput {
    private GetRuleByIdResponse getRuleByIdResponse;
}
