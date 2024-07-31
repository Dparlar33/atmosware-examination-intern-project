package com.atmosware.questionService.business.rules;

import com.atmosware.questionService.api.clients.UserClient;
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
    private UserClient userClient;

    public Question isQuestionExistById(UUID id){
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isEmpty()){
            throw new BusinessException(QuestionMessages.QUESTION_NOT_FOUND);
        }
        return question.get();
    }

    //Check the owner of the question firstly
    public void checkOwnerOfTheQuestion(Question question,UUID userId){
        if (! this.userClient.isUserAnOrganizationByUserId(question.getUserId())){
            checkTheClientIsUser(userId);
        }
    }

    //Check the client role secondly
    public void checkTheClientIsUser(UUID userId){
        if (this.userClient.isUserAnAdminByUserId(userId)){
            throw new BusinessException(QuestionMessages.ONLY_ADMIN_CAN_DELETE_OR_UPDATE_FOR_THIS_QUESTION);
        }
    }
}
