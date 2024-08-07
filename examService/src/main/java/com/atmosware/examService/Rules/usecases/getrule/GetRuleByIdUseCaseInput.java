package com.atmosware.examService.Rules.usecases.getrule;

import com.atmosware.examService.usecase.UseCaseInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetRuleByIdUseCaseInput implements UseCaseInput {
    private UUID id;
}
