package com.atmosware.questionService.business.abstracts;

import com.atmosware.questionService.business.dtos.requests.question.CreateQuestionRequest;
import com.atmosware.questionService.business.dtos.requests.question.UpdateQuestionRequest;
import com.atmosware.questionService.business.dtos.responses.question.GetAllQuestionsResponse;
import com.atmosware.questionService.business.dtos.responses.question.GetQuestionByIdResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.UUID;

public interface QuestionService {
    void addQuestion(CreateQuestionRequest createQuestionRequest, HttpServletRequest httpServletRequest);
    List<GetAllQuestionsResponse> getAllQuestions();
    GetQuestionByIdResponse getQuestionById(UUID id) throws Exception;
    void updateQuestion(UpdateQuestionRequest updateQuestionRequest, HttpServletRequest httpServletRequest);
    void deleteQuestion(UUID id, HttpServletRequest httpServletRequest);
}
