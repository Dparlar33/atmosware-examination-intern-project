package com.atmosware.examService.Exam;

import com.atmosware.common.exam.GetQuestionAndOption;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "questionService", url = "http://localhost:10040/question-service/api/v1/exam-questions")
public interface QuestionClient {
    @GetMapping("/getById/{questionId}")
    GetQuestionAndOption getQuestionAndOption(@PathVariable String questionId);
}
