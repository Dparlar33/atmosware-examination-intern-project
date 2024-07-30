package com.atmosware.questionService.business.rules;

import com.atmosware.questionService.business.abstracts.QuestionService;
import com.atmosware.questionService.business.dtos.requests.option.CreateOptionRequest;
import com.atmosware.questionService.business.dtos.responses.question.GetQuestionByIdResponse;
import com.atmosware.questionService.business.messages.OptionMessages;
import com.atmosware.questionService.core.utilities.exceptions.types.BusinessException;
import com.atmosware.questionService.dataAccess.OptionRepository;
import com.atmosware.questionService.entities.Option;
import com.atmosware.questionService.entities.Question;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OptionBusinessRules {

    private OptionRepository optionRepository;

    public Option isOptionExistById(UUID id){
        Optional<Option> option = this.optionRepository.findById(id);
        if (option.isEmpty()){
            throw new BusinessException(OptionMessages.OPTION_NOT_FOUND);
        }
        return option.get();
    }

    public void isOptionCountLowerThanFive(GetQuestionByIdResponse question) {
       if (question.getOptionCount() > 5){
           throw new BusinessException(OptionMessages.OPTION_LIMIT_FULL);
       }
    }
}
