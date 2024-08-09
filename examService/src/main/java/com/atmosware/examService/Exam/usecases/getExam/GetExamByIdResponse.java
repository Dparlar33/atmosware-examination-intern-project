package com.atmosware.examService.Exam.usecases.getExam;

import com.atmosware.common.exam.GetQuestionAndOption;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetExamByIdResponse {
    private UUID examId;
    private String description;
    private double duration;
    private UUID userId;
    private String roleName;
    private List<String> rules;
    private List<GetQuestionAndOption> getQuestionAndOptions;
}
