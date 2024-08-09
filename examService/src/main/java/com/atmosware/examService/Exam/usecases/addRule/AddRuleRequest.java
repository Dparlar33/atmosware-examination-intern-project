package com.atmosware.examService.Exam.usecases.addRule;

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
public class AddRuleRequest {
    private UUID examId;
    private List<String> rules;
}
