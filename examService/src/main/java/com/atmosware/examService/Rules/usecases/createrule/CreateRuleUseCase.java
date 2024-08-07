package com.atmosware.examService.Rules.usecases.createrule;

import com.atmosware.examService.Rules.Rule;
import com.atmosware.examService.Rules.RuleRepository;
import com.atmosware.examService.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateRuleUseCase implements UseCase<CreateRuleUseCaseInput, CreateRuleUseCaseOutput> {

    private final RuleRepository ruleRepository;

    @Override
     public CreateRuleUseCaseOutput execute(CreateRuleUseCaseInput input) {

        Rule rule = new Rule();
        rule.setDescription(input.getDescription());
        Rule savedRole = this.ruleRepository.save(rule);

        return new CreateRuleUseCaseOutput(buildCreateRuleResponse(savedRole));
    }

    private static CreateRuleResponse buildCreateRuleResponse(final Rule rule) {
        return new CreateRuleResponse(
                rule.getId(),
                rule.getDescription());
    }
}
