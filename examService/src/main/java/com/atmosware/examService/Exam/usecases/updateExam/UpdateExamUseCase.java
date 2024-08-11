package com.atmosware.examService.Exam.usecases.updateExam;

import com.atmosware.core.services.JwtService;
import com.atmosware.examService.Exam.Exam;
import com.atmosware.examService.Exam.ExamBusinessRules;
import com.atmosware.examService.Exam.ExamRepository;
import com.atmosware.examService.usecase.UseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class UpdateExamUseCase implements UseCase<UpdateExamUseCaseInput,UpdateExamUseCaseOutput> {

    private final ExamRepository examRepository;
    private final ExamBusinessRules examBusinessRules;
    private final JwtService jwtService;

    @Override
    public UpdateExamUseCaseOutput execute(UpdateExamUseCaseInput input, HttpServletRequest request) {
        Exam exam = this.examBusinessRules.checkExamIsAlreadyStarted(input.getUpdateExamRequest().getExamId());

        String token = this.jwtService.extractJwtFromRequest(request);
        String roleName = this.jwtService.extractRoles(token);
        String userId = this.jwtService.extractUserId(token);

        this.examBusinessRules.checkRequestRole(roleName, exam,userId);

        exam.setRules(input.getUpdateExamRequest().getRules());
        exam.setDuration(input.getUpdateExamRequest().getDuration());
        exam.setDescription(input.getUpdateExamRequest().getDescription());
        exam.setId(input.getUpdateExamRequest().getExamId());

        this.examRepository.save(exam);

        return new UpdateExamUseCaseOutput(buildUpdatedExamResponse(exam));
    }

    private static UpdatedExamResponse buildUpdatedExamResponse(final Exam exam) {
        return new UpdatedExamResponse(
                exam.getId(),
                exam.getDescription(),
                exam.getDuration(),
                exam.getRules());
    }
}
