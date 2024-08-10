package com.atmosware.examService.Exam.usecases.createExam;

import com.atmosware.core.services.JwtService;
import com.atmosware.examService.Exam.Exam;
import com.atmosware.examService.Exam.ExamRepository;
import com.atmosware.examService.usecase.UseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CreateExamUseCase implements UseCase<CreateExamUseCaseInput, CreateExamUseCaseOutput> {

    private final ExamRepository examRepository;
    private final JwtService jwtService;

    @Override
    public CreateExamUseCaseOutput execute(CreateExamUseCaseInput input, HttpServletRequest request) {

        String token = extractJwtFromRequest(request);
        String roleName = this.jwtService.extractRoles(token);
        String userId = this.jwtService.extractUserId(token);

        Exam exam = new Exam();
        exam.setDescription(input.getDescription());
        exam.setRoleName(roleName);
        exam.setUserId(UUID.fromString(userId));
        exam.setDuration(input.getDuration());
        exam.setRules(new ArrayList<>());
        exam.setQuestionAndOptions(new ArrayList<>());

        Exam savedExam = this.examRepository.save(exam);

        return new CreateExamUseCaseOutput(buildCreateExamResponse(savedExam));
    }

    public String extractJwtFromRequest(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }

    private static CreatedExamResponse buildCreateExamResponse(final Exam exam) {
        return new CreatedExamResponse(
                exam.getId(),
                exam.getDescription(),
                exam.getDuration(),
                exam.getRules(),
                exam.getQuestionAndOptions());
    }
}
