package com.atmosware.examService.Exam.usecases.getAllExam;


import com.atmosware.common.exam.GetQuestionAndOption;
import com.atmosware.examService.Exam.Exam;
import com.atmosware.examService.Exam.ExamRepository;
import com.atmosware.examService.usecase.UseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetAllExamUseCase implements UseCase<GetAllExamUseCaseInput,GetAllExamUseCaseOutput> {

    private final ExamRepository examRepository;

    @Override
    public GetAllExamUseCaseOutput execute(GetAllExamUseCaseInput input, HttpServletRequest request) {

        Page<Exam> exams = this.examRepository.
                findAllByOrderByDescriptionAsc(input.getGetAllExamRequest().getPageable());


        List<GetAllExamResponse> examResponses =
                exams.getContent().stream().map(exam -> new GetAllExamResponse(
                exam.getId(),
                exam.getDescription(),
                exam.getDuration(),
                exam.getUserId(),
                exam.getRoleName(),
                exam.getRules(),
                exam.getQuestionAndOptions().stream()
                        .map(q -> new GetQuestionAndOption())
                        .collect(Collectors.toList())
        )).collect(Collectors.toList());

        GetAllExamUseCaseOutput output = new GetAllExamUseCaseOutput();
        output.setGetAllExamResponse(examResponses);
        return output;
    }
}
