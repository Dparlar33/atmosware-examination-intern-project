package com.atmosware.questionService.business.abstracts;

import com.atmosware.questionService.business.dtos.responses.question.GetQuestionAndOptionResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public interface ExamQuestionService {
    GetQuestionAndOptionResponse getQuestionAndOptionById(UUID questionId, HttpServletRequest httpServletRequest);
}
