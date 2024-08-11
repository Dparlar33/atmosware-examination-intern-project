package com.atmosware.examService.Exam.usecases.protractEndTime;


import com.atmosware.core.services.JwtService;
import com.atmosware.examService.Exam.Exam;
import com.atmosware.examService.Exam.ExamBusinessRules;
import com.atmosware.examService.Exam.ExamRepository;
import com.atmosware.examService.usecase.VoidUseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProtractEndTimeVoidUseCase implements VoidUseCase<ProtractEndTimeUseCaseInput> {

    private final ExamRepository examRepository;
    private final ExamBusinessRules examBusinessRules;
    private final JwtService jwtService;

    @Override
    public void execute(ProtractEndTimeUseCaseInput input, HttpServletRequest request) {
        Exam exam = this.examBusinessRules.
                checkExamIsStartedAndNotFinished(input.getProtractEndTimeRequest().getExamId());

        String token = this.jwtService.extractJwtFromRequest(request);
        String roleName = this.jwtService.extractRoles(token);
        String userId = this.jwtService.extractUserId(token);

        this.examBusinessRules.checkRequestRole(roleName, exam, userId);

        LocalDateTime newEndTime = exam.getEndTime().plusMinutes((long) input.getProtractEndTimeRequest().getExtraTime());
        exam.setEndTime(newEndTime);
        this.examRepository.save(exam);
    }
}
