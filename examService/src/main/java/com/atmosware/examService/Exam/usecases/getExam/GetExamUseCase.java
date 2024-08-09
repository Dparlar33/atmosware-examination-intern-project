package com.atmosware.examService.Exam.usecases.getExam;

import com.atmosware.examService.Exam.Exam;
import com.atmosware.examService.Exam.ExamRepository;
import com.atmosware.examService.usecase.UseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetExamUseCase implements UseCase<GetExamUseCaseInput, GetExamUseCaseOutput> {

    private final ExamRepository examRepository;

    @Override
    public GetExamUseCaseOutput execute(GetExamUseCaseInput input, HttpServletRequest request) {
        Exam exam = this.examRepository.
                findById(input.getId()).orElse(null);

        assert exam != null;
        return new GetExamUseCaseOutput(buildGetByIdExamResponse(exam));
    }

    private static GetExamByIdResponse buildGetByIdExamResponse(final Exam exam) {
        return new GetExamByIdResponse(
                exam.getId(),
                exam.getDescription(),
                exam.getDuration(),
                exam.getUserId(),
                exam.getRoleName(),
                exam.getRules(),
                exam.getQuestionAndOptions());
    }
}
