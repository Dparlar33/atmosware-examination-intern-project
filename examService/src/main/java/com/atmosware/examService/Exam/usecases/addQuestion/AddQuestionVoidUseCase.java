package com.atmosware.examService.Exam.usecases.addQuestion;

import com.atmosware.common.exam.GetQuestionAndOption;
import com.atmosware.core.services.JwtService;
import com.atmosware.examService.Exam.Exam;
import com.atmosware.examService.Exam.ExamBusinessRules;
import com.atmosware.examService.Exam.ExamRepository;
import com.atmosware.examService.Exam.QuestionClient;
import com.atmosware.examService.usecase.VoidUseCase;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddQuestionVoidUseCase implements VoidUseCase<AddQuestionUseCaseInput> {

    private final QuestionClient questionClient;
    private final ExamRepository examRepository;
    private final ExamBusinessRules examBusinessRules;
    private final JwtService jwtService;
    private final RequestInterceptor requestInterceptor;

    @Override
    public void execute(AddQuestionUseCaseInput input, HttpServletRequest request) {

        Exam exam = this.examBusinessRules.checkExamIsAlreadyStarted(input.getAddQuestionRequest().getExamId());
        this.examBusinessRules.isQuestionAlreadyAdded(exam, UUID.fromString(input.getAddQuestionRequest().getQuestionId()));

        String token = this.jwtService.extractJwtFromRequest(request);
        String roleName = this.jwtService.extractRoles(token);
        String userId = this.jwtService.extractUserId(token);

        this.examBusinessRules.checkRequestRole(roleName, exam, userId);

        GetQuestionAndOption getQuestionAndOption = this.questionClient.
                getQuestionAndOption(input.getAddQuestionRequest().getQuestionId());

        exam.getQuestionAndOptions().add(getQuestionAndOption);

        this.examRepository.save(exam);
    }
}
