package com.atmosware.questionService.business.concretes;

import com.atmosware.core.services.JwtService;
import com.atmosware.questionService.business.abstracts.QuestionService;
import com.atmosware.questionService.business.dtos.requests.question.CreateQuestionRequest;
import com.atmosware.questionService.business.dtos.requests.question.UpdateQuestionRequest;
import com.atmosware.questionService.business.dtos.responses.question.GetAllQuestionsResponse;
import com.atmosware.questionService.business.dtos.responses.question.GetQuestionByIdResponse;
import com.atmosware.questionService.business.rules.QuestionBusinessRules;
import com.atmosware.questionService.core.utilities.mapping.QuestionMapper;
import com.atmosware.questionService.dataAccess.QuestionRepository;
import com.atmosware.questionService.entities.Question;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
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

        String token = extractJwtFromRequest(httpServletRequest);
        List<String> roleName = this.jwtService.extractRoles(token);

        Question mappedQuestion = this.questionMapper.createQuestionRequestToQuestion(createQuestionRequest);
        mappedQuestion.setUserRole(roleName.get(0));
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
    public List<GetAllQuestionsResponse> getAllQuestions() {
        List<Question> questions = this.questionRepository.findAll();
        return questions.stream().map(this.questionMapper::questionToGetAllQuestionsResponse).toList();
    }

    @Override
    public GetQuestionByIdResponse getQuestionById(UUID id) {

        return this.questionMapper.questionToGetQuestionByIdResponse(this.questionBusinessRules.isQuestionExistById(id));
    }

    @Override
    public void updateQuestion(UpdateQuestionRequest updateQuestionRequest, HttpServletRequest httpServletRequest) {

        this.questionBusinessRules.isQuestionExistById(updateQuestionRequest.getId());

        Question question =
                this.questionRepository.findById(updateQuestionRequest.getId()).orElse(null);

        String token = extractJwtFromRequest(httpServletRequest);
        List<String> roleName = this.jwtService.extractRoles(token);

        assert question != null;
        this.questionBusinessRules.checkRequestRole(roleName.get(0),question.getUserRole());

        Question updatedQuestion = this.questionMapper.updateQuestionRequestToQuestion(updateQuestionRequest);
        questionRepository.save(updatedQuestion);
    }

    @Override
    public void deleteQuestion(UUID id, HttpServletRequest httpServletRequest) {
        this.questionBusinessRules.isQuestionExistById(id);

        Question question =
                this.questionRepository.findById(id).orElse(null);

        String token = extractJwtFromRequest(httpServletRequest);
        List<String> roleName = this.jwtService.extractRoles(token);

        assert question != null;
        this.questionBusinessRules.checkRequestRole(roleName.get(0),question.getUserRole());

        this.questionRepository.deleteById(id);
    }
}
