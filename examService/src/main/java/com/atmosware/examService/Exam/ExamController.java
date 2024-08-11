package com.atmosware.examService.Exam;

import com.atmosware.examService.Exam.usecases.addQuestion.AddQuestionUseCaseInput;
import com.atmosware.examService.Exam.usecases.addRule.AddRuleUseCaseInput;
import com.atmosware.examService.Exam.usecases.createExam.CreateExamRequest;
import com.atmosware.examService.Exam.usecases.createExam.CreatedExamResponse;
import com.atmosware.examService.Exam.usecases.createExam.CreateExamUseCaseInput;
import com.atmosware.examService.Exam.usecases.createExam.CreateExamUseCaseOutput;
import com.atmosware.examService.Exam.usecases.getExam.GetExamByIdResponse;
import com.atmosware.examService.Exam.usecases.getExam.GetExamUseCaseInput;
import com.atmosware.examService.Exam.usecases.getExam.GetExamUseCaseOutput;
import com.atmosware.examService.Exam.usecases.removeQuestion.RemoveQuestionVoidUseCaseInput;
import com.atmosware.examService.Exam.usecases.startExam.StartExamUseCaseInput;
import com.atmosware.examService.Exam.usecases.updateExam.UpdateExamRequest;
import com.atmosware.examService.Exam.usecases.updateExam.UpdateExamUseCaseInput;
import com.atmosware.examService.Exam.usecases.updateExam.UpdateExamUseCaseOutput;
import com.atmosware.examService.Exam.usecases.updateExam.UpdatedExamResponse;
import com.atmosware.examService.usecase.UseCase;
import com.atmosware.examService.usecase.VoidUseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exam-service/api/v1/exams")
public class ExamController {

    private final UseCase<CreateExamUseCaseInput, CreateExamUseCaseOutput> createUseCase;
    private final UseCase<GetExamUseCaseInput, GetExamUseCaseOutput> getExamUseCase;
    private final VoidUseCase<AddQuestionUseCaseInput> addQuestionUseCase;
    private final VoidUseCase<AddRuleUseCaseInput> addRuleUseCase;
    private final VoidUseCase<StartExamUseCaseInput> startExamUseCase;
    private final VoidUseCase<RemoveQuestionVoidUseCaseInput> removeQuestionVoidUseCase;
    private final UseCase<UpdateExamUseCaseInput, UpdateExamUseCaseOutput> updateExamUseCase;

    @PostMapping("/add")
    public ResponseEntity<CreatedExamResponse> createExam(@RequestBody CreateExamRequest createExamRequest, HttpServletRequest request) {
        CreateExamUseCaseOutput output = this.createUseCase.execute(
                new CreateExamUseCaseInput(
                        createExamRequest.getDescription(),
                        createExamRequest.getDuration()), request);
        return new ResponseEntity<>(output.getCreatedExamResponse(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<UpdatedExamResponse> updateExam(@RequestBody UpdateExamRequest updateExamRequest, HttpServletRequest request) {
        UpdateExamUseCaseOutput output = this.updateExamUseCase.execute(
                new UpdateExamUseCaseInput(
                       updateExamRequest),request);
        return new ResponseEntity<>(output.getUpdatedExamResponse(), HttpStatus.OK);
    }


    @GetMapping("/getById/{id}")
    public ResponseEntity<GetExamByIdResponse> getExam(@PathVariable UUID id, HttpServletRequest request) {
        GetExamUseCaseOutput output =
                this.getExamUseCase.execute(new GetExamUseCaseInput(id),request);
        return new ResponseEntity<>(output.getGetExamByIdResponse(), HttpStatus.OK);
    }

    @PostMapping("/add-question")
    public void addQuestion(@RequestBody AddQuestionUseCaseInput input, HttpServletRequest request) {
        this.addQuestionUseCase.execute(input, request);
    }

    @PostMapping("/add-rules")
    public void addRules(@RequestBody AddRuleUseCaseInput input, HttpServletRequest request) {
        this.addRuleUseCase.execute(input, request);
    }

    @GetMapping("/start-exam")
    public void startExam(@RequestBody StartExamUseCaseInput input ,HttpServletRequest request) {
        this.startExamUseCase.execute(input, request);
    }

    @DeleteMapping("/remove-question")
    public void deleteQuestion(@RequestBody RemoveQuestionVoidUseCaseInput input, HttpServletRequest request){
        this.removeQuestionVoidUseCase.execute(input,request);
    }
}
