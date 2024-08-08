package com.atmosware.examService.Rules.usecases.deleterule;

import com.atmosware.examService.Rules.RuleRepository;
import com.atmosware.examService.usecase.VoidUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteRuleVoidUseCase implements VoidUseCase<DeleteRuleVoidUseCaseInput> {
    private final RuleRepository ruleRepository;

    @Override
    public void execute(DeleteRuleVoidUseCaseInput input) {
        UUID id = input.getId();
        this.ruleRepository.deleteById(id);
    }
}
