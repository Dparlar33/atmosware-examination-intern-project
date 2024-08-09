package com.atmosware.examService.Exam.usecases.getExam;

import com.atmosware.examService.usecase.UseCaseInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class GetExamUseCaseInput implements UseCaseInput {
    private UUID id;
}
