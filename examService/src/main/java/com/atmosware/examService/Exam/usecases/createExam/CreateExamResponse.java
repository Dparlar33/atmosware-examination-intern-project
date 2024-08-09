package com.atmosware.examService.Exam.usecases.createExam;

import com.atmosware.common.exam.GetQuestionAndOption;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateExamResponse {
    private UUID id;
    private String description;
    private double duration;
    private List<String> rules;
    private List<GetQuestionAndOption> questionAndOptions;
}
