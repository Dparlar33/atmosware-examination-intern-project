package com.atmosware.examService.Exam;

import com.atmosware.common.exam.GetQuestionAndOption;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "questionService", url = "http://localhost:10040/question-service/api/v1/exam-questions",configuration = FeignClientProperties.FeignClientConfiguration.class)
public interface QuestionClient {
    @GetMapping("/getById/{questionId}")
    GetQuestionAndOption getQuestionAndOption(@PathVariable String questionId);
}
