package com.atmosware.examService.Exam.usecases.protractEndTime;

import com.atmosware.examService.usecase.UseCaseInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProtractEndTimeUseCaseInput implements UseCaseInput {
    private ProtractEndTimeRequest protractEndTimeRequest;
}
