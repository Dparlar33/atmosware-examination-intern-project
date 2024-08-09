package com.atmosware.questionService.business.rules;


import com.atmosware.common.exam.OptionResponse;
import com.atmosware.questionService.business.dtos.requests.question.CreateQuestionRequest;
import com.atmosware.questionService.business.messages.QuestionMessages;
import com.atmosware.questionService.core.utilities.exceptions.types.BusinessException;
import com.atmosware.questionService.dataAccess.QuestionRepository;
import com.atmosware.questionService.entities.Question;
import com.atmosware.questionService.entities.Status;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class QuestionBusinessRules {

    private QuestionRepository questionRepository;

    public Question isQuestionExistById(UUID id){
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isEmpty()){
            throw new BusinessException(QuestionMessages.QUESTION_NOT_FOUND);
        }
        return question.get();
    }

    public void checkRequestRole(String requestRoleName,Question question,String userId) {
        if (! requestRoleName.equals("ADMIN")) {
            if (! question.getUserId().equals(UUID.fromString(userId))){
                throw new BusinessException(QuestionMessages.INVALID_REQUEST_ROLE);
            }
        }
    }

    public void atLeastOneCorrectOptionMustBe(List<OptionResponse> optionResponseList){
        for (OptionResponse optionResponse : optionResponseList) {
            if (optionResponse.isCorrect()) {
                break;
            }
        }
        throw new BusinessException(QuestionMessages.QUESTION_HAS_TO_INCLUDE_ONE_CORRECT_OPTION_OR_CHANGE_ONE_OPTION_TO_CORRECT);
    }

    public void imageAndDescriptionShouldNotBeNullInTheOneQuestion(CreateQuestionRequest createQuestionRequest){
        if (createQuestionRequest.getDescription().isEmpty() && createQuestionRequest.getImageUrl().isEmpty()){
            throw new BusinessException(QuestionMessages.DESCRIPTION_AND_IMAGE_URL_CANNOT_BE_NULL_AT_THE_SAME_QUESTION);
        }
    }

    public void isQuestionAvailable(Question question){
        if (question.getStatus().equals(Status.OCCUPIED)){
            throw new BusinessException(QuestionMessages.THIS_QUESTION_IS_CURRENTLY_IN_USE_CANNOT_BE_UPDATED);
        }
    }

    public void checkOptionCountIsLowerThanFiveAngHigherThanTwo(List<OptionResponse> optionResponseList){
        if (optionResponseList.size() > 5 ) {
            throw new BusinessException(QuestionMessages.OPTION_COUNTS_HAVE_TO_BE_LOWER_THAN_FIVE);
        } else if (optionResponseList.size() < 2) {
            throw new BusinessException(QuestionMessages.OPTION_COUNTS_HAVE_TO_BE_HIGHER_THAN_TWO);
        }
    }
}
