package com.atmosware.questionService.api.controllers;


import com.atmosware.questionService.business.abstracts.QuestionService;
import com.atmosware.questionService.business.dtos.requests.question.CreateQuestionRequest;
import com.atmosware.questionService.business.dtos.requests.question.UpdateQuestionRequest;
import com.atmosware.questionService.business.dtos.responses.question.GetAllQuestionsResponse;
import com.atmosware.questionService.business.dtos.responses.question.GetQuestionByIdResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/question-service/api/v1/questions")
@AllArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/add")
    public void addQuestion(@Valid @RequestBody CreateQuestionRequest createQuestionRequest, HttpServletRequest httpServletRequest) {
        this.questionService.addQuestion(createQuestionRequest, httpServletRequest);
    }

    @PutMapping("/update")
    public void updateQuestion(@Valid @RequestBody UpdateQuestionRequest updateQuestionRequest, HttpServletRequest httpServletRequest) {
        this.questionService.updateQuestion(updateQuestionRequest, httpServletRequest);
    }

    //?page=1&size=20
    @GetMapping("/getAllQuestions")
    public Page<GetAllQuestionsResponse> getAllQuestions(@RequestParam int page,
                                                         @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.questionService.getAllQuestions(pageable);
    }

    @GetMapping("/getById/{id}")
    public GetQuestionByIdResponse getQuestionById(@PathVariable UUID id) throws Exception {
        return this.questionService.getQuestionById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteQuestion(@PathVariable UUID id, HttpServletRequest httpServletRequest) {
        this.questionService.deleteQuestion(id, httpServletRequest);
    }
}
