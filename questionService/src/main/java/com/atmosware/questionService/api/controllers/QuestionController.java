package com.atmosware.questionService.api.controllers;


import com.atmosware.questionService.business.abstracts.QuestionService;
import com.atmosware.questionService.business.dtos.requests.question.CreateQuestionRequest;
import com.atmosware.questionService.business.dtos.requests.question.UpdateQuestionRequest;
import com.atmosware.questionService.business.dtos.responses.question.GetAllQuestionsResponse;
import com.atmosware.questionService.business.dtos.responses.question.GetQuestionByIdResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/question-service/api/v1/questions")
@AllArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/add")
    public void addQuestion(@RequestBody CreateQuestionRequest createQuestionRequest) {
        this.questionService.addQuestion(createQuestionRequest);
    }

    @PutMapping("/update")
    public void updateQuestion(@RequestBody UpdateQuestionRequest updateQuestionRequest) {
        this.questionService.updateQuestion(updateQuestionRequest);
    }

    @GetMapping("/getAllQuestions")
    public List<GetAllQuestionsResponse> getAllQuestions() {
        return this.questionService.getAllQuestions();
    }

    @GetMapping("/getById/{id}")
    public GetQuestionByIdResponse getAllQuestions(@PathVariable UUID id) throws Exception {
        return this.questionService.getQuestionById(id);
    }
}
