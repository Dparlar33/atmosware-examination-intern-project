package com.atmosware.questionService.business.abstracts;

import com.atmosware.common.exam.OptionResponse;
import com.atmosware.questionService.business.dtos.requests.question.CreateQuestionRequest;
import com.atmosware.questionService.business.dtos.requests.question.UpdateQuestionRequest;
import com.atmosware.questionService.business.dtos.responses.question.GetAllQuestionsResponse;
import com.atmosware.questionService.business.dtos.responses.question.GetQuestionByIdResponse;
import com.atmosware.questionService.entities.Question;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface QuestionService {
    void addQuestion(CreateQuestionRequest createQuestionRequest, HttpServletRequest httpServletRequest);
    Page<GetAllQuestionsResponse> getAllQuestions(Pageable pageable);
    GetQuestionByIdResponse getQuestionById(UUID id) throws Exception;
    void updateQuestion(UpdateQuestionRequest updateQuestionRequest, HttpServletRequest httpServletRequest);
    void deleteQuestion(UUID id, HttpServletRequest httpServletRequest);
    void checkRulesOfOptionResponseList(List<OptionResponse> optionResponseList);
    Question isQuestionExistById(UUID id);
}
