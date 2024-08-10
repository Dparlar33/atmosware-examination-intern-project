package com.atmosware.examService.Exam.usecases.protractEndTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProtractEndTimeRequest {
    private UUID examId;
    private double extraTime;
}
