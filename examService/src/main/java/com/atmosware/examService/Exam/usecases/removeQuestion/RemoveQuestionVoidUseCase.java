package com.atmosware.examService.Exam.usecases.removeQuestion;

import com.atmosware.common.exam.GetQuestionAndOption;
import com.atmosware.core.services.JwtService;
import com.atmosware.examService.Exam.Exam;
import com.atmosware.examService.Exam.ExamBusinessRules;
import com.atmosware.examService.Exam.ExamRepository;
import com.atmosware.examService.usecase.VoidUseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RemoveQuestionVoidUseCase implements VoidUseCase <RemoveQuestionVoidUseCaseInput>{

    private final ExamRepository examRepository;
    private final ExamBusinessRules examBusinessRules;
    private final JwtService jwtService;

    @Override
    public void execute(RemoveQuestionVoidUseCaseInput input, HttpServletRequest request) {
        Exam exam = this.examBusinessRules.checkExamIsAlreadyStarted(input.getRemoveQuestionRequest().getExamId());

        String token = this.jwtService.extractJwtFromRequest(request);
        String roleName = this.jwtService.extractRoles(token);
        String userId = this.jwtService.extractUserId(token);

        this.examBusinessRules.checkRequestRole(roleName, exam, userId);

        List<GetQuestionAndOption> questionAndOptions = exam.getQuestionAndOptions();
        questionAndOptions.removeIf(item -> item.getQuestionId().
                equals(input.getRemoveQuestionRequest().getQuestionId()));
        exam.setQuestionAndOptions(questionAndOptions);

        this.examRepository.save(exam);
    }


}
