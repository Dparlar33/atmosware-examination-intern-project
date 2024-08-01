package com.atmosware.questionService.business.rules;


import com.atmosware.questionService.business.messages.QuestionMessages;
import com.atmosware.questionService.core.utilities.exceptions.types.BusinessException;
import com.atmosware.questionService.dataAccess.QuestionRepository;
import com.atmosware.questionService.entities.Question;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    public void checkRequestRole(String requestRoleName,String questionRoleName) {
        if (requestRoleName.equals("ORGANIZATION") && !questionRoleName.equals("ORGANIZATION")) {
            throw new BusinessException(QuestionMessages.INVALID_REQUEST_ROLE);
        }
    }
}
