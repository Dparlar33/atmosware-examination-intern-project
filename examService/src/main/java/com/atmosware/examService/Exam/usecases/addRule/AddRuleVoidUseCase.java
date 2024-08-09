package com.atmosware.examService.Exam.usecases.addRule;

import com.atmosware.examService.Exam.Exam;
import com.atmosware.examService.Exam.ExamRepository;

import com.atmosware.examService.usecase.VoidUseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddRuleVoidUseCase implements VoidUseCase <AddRuleUseCaseInput>{
    private final ExamRepository  examRepository;

    @Override
    public void execute(AddRuleUseCaseInput input, HttpServletRequest request) {
        Exam exam = this.examRepository.findById(input.getAddRuleRequest().getExamId()).orElse(null);
        assert exam != null;
        exam.getRules().addAll(input.getAddRuleRequest().getRules());

        this.examRepository.save(exam);
    }
}
