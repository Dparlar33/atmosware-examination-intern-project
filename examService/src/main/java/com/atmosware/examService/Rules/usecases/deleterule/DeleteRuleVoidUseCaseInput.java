package com.atmosware.examService.Rules.usecases.deleterule;

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
public class DeleteRuleVoidUseCaseInput implements UseCaseInput {
    private UUID id;
}
