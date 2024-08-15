package com.atmosware.questionService.api.controllers;

import com.atmosware.common.exam.GetQuestionAndOption;
import com.atmosware.questionService.business.abstracts.ExamQuestionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/question-service/api/v1/exam-questions")
@AllArgsConstructor
public class ExamQuestionController {

    private ExamQuestionService examQuestionService;

    @GetMapping("/getById/{questionId}")
    public GetQuestionAndOption getQuestionAndOption(@PathVariable String questionId) throws Exception {
        return this.examQuestionService.getQuestionAndOptionById(UUID.fromString(questionId));
    }
}
