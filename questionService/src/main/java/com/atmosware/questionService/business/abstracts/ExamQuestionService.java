package com.atmosware.questionService.business.abstracts;

import com.atmosware.common.exam.GetQuestionAndOption;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public interface ExamQuestionService {
    GetQuestionAndOption getQuestionAndOptionById(UUID questionId);
}
