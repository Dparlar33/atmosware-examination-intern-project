package com.atmosware.examService.Exam;


import com.atmosware.examService.Exam.usecases.addQuestion.AddQuestionUseCaseInput;
import com.atmosware.examService.Exam.usecases.removeQuestion.RemoveQuestionVoidUseCaseInput;
import com.atmosware.examService.core.exceptions.types.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExamBusinessRules {

    private final ExamRepository examRepository;

    public Exam checkExamIsAlreadyStarted(UUID examId){

        Exam exam = this.examRepository.findById(examId).orElse(null);
        assert exam != null;
        if (exam.getStartTime() != null && exam.getEndTime().isAfter(LocalDateTime.now()) ){
            throw new BusinessException("EXAM ALREADY STARTED");
        }
        return exam;
    }

    public void checkRequestRole(String requestRoleName, Exam exam,String userId) {
        if (! requestRoleName.equals("ADMIN")) {
            if (! exam.getUserId().equals(UUID.fromString(userId))){
                throw new BusinessException("INVALID_REQUEST_ROLE");
            }
        }
    }

    public Exam checkExamIsStartedAndNotFinished(UUID examId){
        Exam exam = this.examRepository.findById(examId).orElse(null);
        assert exam != null;
        if (exam.getStartTime() == null) {
            throw new BusinessException("EXAM HAS NOT STARTED YET");
        }

        if (exam.getEndTime() != null && exam.getEndTime().isBefore(LocalDateTime.now())) {
            throw new BusinessException("EXAM HAS FINISHED");
        }

        return exam;
    }
}
