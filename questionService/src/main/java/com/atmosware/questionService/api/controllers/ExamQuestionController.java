package com.atmosware.questionService.api.controllers;

import com.atmosware.questionService.business.abstracts.ExamQuestionService;
import com.atmosware.questionService.business.dtos.responses.question.GetQuestionAndOptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/question-service/api/v1/exam-question")
@AllArgsConstructor
public class ExamQuestionController {

    private ExamQuestionService examQuestionService;

    @GetMapping("/getById/{questionId}")
    public GetQuestionAndOptionResponse getQuestionAndOption(@PathVariable UUID questionId, HttpServletRequest request) {
        return this.examQuestionService.getQuestionAndOptionById(questionId,request);
    }
}
