package com.atmosware.examService.Rules;

import com.atmosware.examService.core.exceptions.types.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RuleBusinessRules {
    private final RuleRepository ruleRepository;

    public Rule isRuleExistById(UUID id){
        Optional<Rule> rule = this.ruleRepository.findById(id);
        if (rule.isEmpty()){
            throw new BusinessException("Rule does not exist");
        }
        return rule.get();
    }
}
