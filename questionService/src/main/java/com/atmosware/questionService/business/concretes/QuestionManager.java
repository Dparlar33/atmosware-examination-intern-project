package com.atmosware.questionService.business.concretes;

import com.atmosware.common.exam.OptionResponse;
import com.atmosware.core.services.JwtService;
import com.atmosware.questionService.business.abstracts.QuestionService;
import com.atmosware.questionService.business.dtos.requests.question.CreateQuestionRequest;
import com.atmosware.questionService.business.dtos.requests.question.UpdateQuestionRequest;
import com.atmosware.questionService.business.dtos.responses.question.GetAllQuestionsResponse;
import com.atmosware.questionService.business.dtos.responses.question.GetQuestionByIdResponse;
import com.atmosware.questionService.business.messages.QuestionMessages;
import com.atmosware.questionService.core.utilities.exceptions.types.BusinessException;
import com.atmosware.questionService.core.utilities.mapping.QuestionMapper;
import com.atmosware.questionService.dataAccess.QuestionRepository;
import com.atmosware.questionService.entities.Question;
import com.atmosware.questionService.entities.Status;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class QuestionManager implements QuestionService {

    private QuestionRepository questionRepository;
    private QuestionMapper questionMapper;
    private JwtService jwtService;

    @Override
    public void addQuestion(CreateQuestionRequest createQuestionRequest, HttpServletRequest httpServletRequest) {

        imageAndDescriptionShouldNotBeNullInTheOneQuestion(createQuestionRequest);

        String token = this.jwtService.extractJwtFromRequest(httpServletRequest);
        String roleName = this.jwtService.extractRoles(token);
        String userId = this.jwtService.extractUserId(token);

        Question mappedQuestion = this.questionMapper.createQuestionRequestToQuestion(createQuestionRequest);
        mappedQuestion.setUserRole(roleName);
        mappedQuestion.setStatus(Status.AVAILABLE);
        mappedQuestion.setUserId(UUID.fromString(userId));

        this.questionRepository.save(mappedQuestion);
    }

    @Override
    public Page<GetAllQuestionsResponse> getAllQuestions(Pageable pageable) {

        Page<Question> questions = this.questionRepository.findAllByOrderByIdAsc(pageable);

        return questions.map(this.questionMapper::questionToGetAllQuestionsResponse);
    }

    @Override
    public GetQuestionByIdResponse getQuestionById(UUID id) {

        Question question = isQuestionExistById(id);

        return this.questionMapper.questionToGetQuestionByIdResponse(question);
    }

    @Override
    public void updateQuestion(UpdateQuestionRequest updateQuestionRequest, HttpServletRequest httpServletRequest) {
        Question question = isQuestionExistById(updateQuestionRequest.getId());

        isQuestionAvailable(question);

        String token = this.jwtService.extractJwtFromRequest(httpServletRequest);
        String roleName = this.jwtService.extractRoles(token);
        String userId = this.jwtService.extractUserId(token);

        checkRequestRole(roleName,question,userId);

        Question mappedQuestion = this.questionMapper.updateQuestionRequestToQuestion(updateQuestionRequest);

        mappedQuestion.setStatus(Status.AVAILABLE);
        mappedQuestion.setOptions(question.getOptions());
        mappedQuestion.setUserId(UUID.fromString(userId));
        mappedQuestion.setUserRole(roleName);

        this.questionRepository.save(mappedQuestion);
    }

    @Override
    public void deleteQuestion(UUID id, HttpServletRequest httpServletRequest) {
        Question question = isQuestionExistById(id);

        String token = this.jwtService.extractJwtFromRequest(httpServletRequest);
        String roleName = this.jwtService.extractRoles(token);
        String userId = this.jwtService.extractUserId(token);

        checkRequestRole(roleName,question,userId);

        this.questionRepository.deleteById(id);
    }

    @Override
    public void checkRulesOfOptionResponseList(List<OptionResponse> optionResponseList) {
        atLeastOneCorrectOptionMustBe(optionResponseList);
        checkOptionCountIsLowerThanFiveAngHigherThanTwo(optionResponseList);
    }

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
        boolean hasCorrectOption = false;

        for (OptionResponse optionResponse : optionResponseList) {
            if (optionResponse.isCorrect()) {
                hasCorrectOption = true;
                break;
            }
        }

        if (!hasCorrectOption) {
            throw new BusinessException(QuestionMessages.QUESTION_HAS_TO_INCLUDE_ONE_CORRECT_OPTION_OR_CHANGE_ONE_OPTION_TO_CORRECT);
        }
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
