package com.atmosware.examService.Exam.usecases.addQuestion;

import com.atmosware.common.exam.GetQuestionAndOption;
import com.atmosware.examService.Exam.Exam;
import com.atmosware.examService.Exam.ExamRepository;
import com.atmosware.examService.Exam.QuestionClient;
import com.atmosware.examService.usecase.VoidUseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddQuestionVoidUseCase implements VoidUseCase<AddQuestionUseCaseInput> {

    private final QuestionClient questionClient;
    private final ExamRepository examRepository;

    @Override
    public void execute(AddQuestionUseCaseInput input, HttpServletRequest request) {

        GetQuestionAndOption getQuestionAndOption = this.questionClient.
                getQuestionAndOption(input.getAddQuestionRequest().getQuestionId());

        Exam exam = this.examRepository.findById(input.getAddQuestionRequest().
                getExamId()).orElse(null);

        assert exam != null;
        exam.getQuestionAndOptions().add(getQuestionAndOption);

        this.examRepository.save(exam);
    }
}
