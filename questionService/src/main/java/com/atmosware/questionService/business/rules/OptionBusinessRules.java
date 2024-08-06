package com.atmosware.questionService.business.rules;

import com.atmosware.questionService.business.dtos.requests.option.CreateOptionRequest;
import com.atmosware.questionService.business.dtos.requests.question.CreateQuestionRequest;
import com.atmosware.questionService.business.dtos.responses.question.GetQuestionByIdResponse;
import com.atmosware.questionService.business.messages.OptionMessages;
import com.atmosware.questionService.business.messages.QuestionMessages;
import com.atmosware.questionService.core.utilities.exceptions.types.BusinessException;
import com.atmosware.questionService.dataAccess.OptionRepository;
import com.atmosware.questionService.entities.Option;
import com.atmosware.questionService.entities.Question;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void isOptionCountLowerThanRequestedOption(GetQuestionByIdResponse question ) {

        List<Option> optionList = this.optionRepository.findOptionsByQuestionId(question.getId());

        if (optionList.size() > question.getOptionCount()){
            throw new BusinessException(OptionMessages.OPTION_LIMIT_FULL_FOR_THIS_QUESTION);
        }
    }

    public void imageAndDescriptionShouldNotBeNullInTheOneOption(CreateOptionRequest createOptionRequest){
        if (createOptionRequest.getDescription().isEmpty() && createOptionRequest.getImageUrl().isEmpty()){
            throw new BusinessException(OptionMessages.DESCRIPTION_AND_IMAGE_URL_CANNOT_BE_NULL_AT_THE_SAME_OPTION);
        }
    }
}
