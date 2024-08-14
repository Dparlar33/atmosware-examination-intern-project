package com.atmosware.examService.Exam;


import com.atmosware.common.exam.GetQuestionAndOption;
import com.atmosware.examService.core.exceptions.types.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExamBusinessRules {

    private final ExamRepository examRepository;

    public Exam checkExamIsAlreadyStarted(UUID examId){

        Exam exam = this.examRepository.findById(examId).orElse(null);
        assert exam != null;
        if (exam.getStartTime() != null && exam.getEndTime().isAfter(LocalDateTime.now()) ){
            throw new BusinessException(ExamMessages.EXAM_ALREADY_STARTED);
        }
        return exam;
    }

    public void checkRequestRole(String requestRoleName, Exam exam,String userId) {
        if (! requestRoleName.equals("ADMIN")) {
            if (! exam.getUserId().equals(UUID.fromString(userId))){
                throw new BusinessException(ExamMessages.INVALID_REQUEST_ROLE_OR_USER);
            }
        }
    }

    public Exam checkExamIsStartedAndNotFinished(UUID examId){
        Exam exam = this.examRepository.findById(examId).orElse(null);
        assert exam != null;
        if (exam.getStartTime() == null) {
            throw new BusinessException(ExamMessages.EXAM_HAS_NOT_STARTED_YET);
        }

        if (exam.getEndTime() != null && exam.getEndTime().isBefore(LocalDateTime.now())) {
            throw new BusinessException(ExamMessages.EXAM_HAS_FINISHED);
        }

        return exam;
    }

    public void isQuestionAlreadyAdded(Exam exam, UUID questionId){
        List<GetQuestionAndOption> questionAndOptions = exam.getQuestionAndOptions();

        boolean isAlreadyAdded = questionAndOptions.stream()
                .anyMatch(questionAndOption -> questionAndOption.getQuestionId().equals(questionId));

        if (isAlreadyAdded) {
            throw new BusinessException(ExamMessages.THIS_QUESTION_IS_ALREADY_ADDED_TO_THE_EXAM);
        }
    }
}
