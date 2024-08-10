package com.atmosware.examService.Exam.usecases.startExam;


import com.atmosware.examService.Exam.Exam;
import com.atmosware.examService.Exam.ExamRepository;
import com.atmosware.examService.usecase.VoidUseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StartExamVoidUseCase implements VoidUseCase<StartExamUseCaseInput> {

    private final ExamRepository examRepository;

    @Override
    public void execute(StartExamUseCaseInput input, HttpServletRequest request) {
        Exam exam = this.examRepository.findById(input.getStartExamRequest().getExamId()).orElse(null);

        assert exam != null;
        exam.setStartTime(LocalDateTime.now());

        LocalDateTime endTime = LocalDateTime.now().plusMinutes((long) exam.getDuration());

        exam.setEndTime(endTime);

        this.examRepository.save(exam);
    }
}
