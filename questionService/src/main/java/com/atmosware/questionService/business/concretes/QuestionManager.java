package com.atmosware.questionService.business.concretes;

import com.atmosware.core.services.JwtService;
import com.atmosware.questionService.business.abstracts.OptionService;
import com.atmosware.questionService.business.abstracts.QuestionService;
import com.atmosware.questionService.business.dtos.requests.question.CreateQuestionRequest;
import com.atmosware.questionService.business.dtos.requests.question.UpdateQuestionRequest;
import com.atmosware.questionService.business.dtos.responses.question.GetAllQuestionsResponse;
import com.atmosware.questionService.business.dtos.responses.question.GetQuestionByIdResponse;
import com.atmosware.questionService.business.rules.QuestionBusinessRules;
import com.atmosware.questionService.core.utilities.mapping.QuestionMapper;
import com.atmosware.questionService.dataAccess.QuestionRepository;
import com.atmosware.questionService.entities.Question;
import com.atmosware.questionService.entities.Status;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class QuestionManager implements QuestionService {

    private QuestionRepository questionRepository;
    private QuestionBusinessRules questionBusinessRules;
    private QuestionMapper questionMapper;
    private JwtService jwtService;

    @Override
    public void addQuestion(CreateQuestionRequest createQuestionRequest, HttpServletRequest httpServletRequest) {

        this.questionBusinessRules.imageAndDescriptionShouldNotBeNullInTheOneQuestion(createQuestionRequest);

        String token = extractJwtFromRequest(httpServletRequest);
        String roleName = this.jwtService.extractRoles(token);
        String userId = this.jwtService.extractUserId(token);

        Question mappedQuestion = this.questionMapper.createQuestionRequestToQuestion(createQuestionRequest);
        mappedQuestion.setUserRole(roleName);
        mappedQuestion.setStatus(Status.AVAILABLE);
        mappedQuestion.setUserId(UUID.fromString(userId));

        this.questionRepository.save(mappedQuestion);
    }

    public String extractJwtFromRequest(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }

    @Override
    public Page<GetAllQuestionsResponse> getAllQuestions(Pageable pageable) {

        Page<Question> questions = this.questionRepository.findAllByOrderByIdAsc(pageable);

        return questions.map(this.questionMapper::questionToGetAllQuestionsResponse);
    }

    @Override
    public GetQuestionByIdResponse getQuestionById(UUID id) {

        Question question = this.questionBusinessRules.isQuestionExistById(id);

        return this.questionMapper.questionToGetQuestionByIdResponse(question);
    }

    @Override
    public void updateQuestion(UpdateQuestionRequest updateQuestionRequest, HttpServletRequest httpServletRequest) {
        Question question = this.questionBusinessRules.isQuestionExistById(updateQuestionRequest.getId());

        this.questionBusinessRules.isQuestionAvailable(question);

        String token = extractJwtFromRequest(httpServletRequest);
        String roleName = this.jwtService.extractRoles(token);
        String userId = this.jwtService.extractUserId(token);

        this.questionBusinessRules.checkRequestRole(roleName,question,userId);

        question.setStatus(Status.AVAILABLE);

        this.questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(UUID id, HttpServletRequest httpServletRequest) {
        Question question =
                this.questionBusinessRules.isQuestionExistById(id);

        String token = extractJwtFromRequest(httpServletRequest);
        String roleName = this.jwtService.extractRoles(token);
        String userId = this.jwtService.extractUserId(token);

        this.questionBusinessRules.checkRequestRole(roleName,question,userId);

        this.questionRepository.deleteById(id);
    }
}
