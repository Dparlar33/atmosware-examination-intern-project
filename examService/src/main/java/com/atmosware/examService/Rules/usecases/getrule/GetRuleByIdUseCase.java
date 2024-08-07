package com.atmosware.examService.Rules.usecases.getrule;

import com.atmosware.examService.Rules.Rule;
import com.atmosware.examService.Rules.RuleBusinessRules;
import com.atmosware.examService.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetRuleByIdUseCase implements UseCase<GetRuleByIdUseCaseInput,GetRuleByIdUseCaseOutput> {

    private final RuleBusinessRules ruleBusinessRules;

    @Override
    public GetRuleByIdUseCaseOutput execute(GetRuleByIdUseCaseInput input) {

        Rule rule = this.ruleBusinessRules.isRuleExistById(input.getId());

        return new GetRuleByIdUseCaseOutput(buildGetRuleByIdResponse(rule));
    }

    private static GetRuleByIdResponse buildGetRuleByIdResponse(final Rule rule) {
        return new GetRuleByIdResponse(
                rule.getId(),
                rule.getDescription());
    }
}
