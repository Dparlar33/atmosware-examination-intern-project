package com.atmosware.examService.Exam.usecases.getAllExam;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllExamRequest {
    private Pageable pageable;
}
