package com.atmosware.questionService.business.concretes;

import com.atmosware.questionService.business.abstracts.ExamQuestionService;
import com.atmosware.questionService.business.abstracts.OptionService;
import com.atmosware.questionService.business.dtos.responses.option.OptionResponse;
import com.atmosware.questionService.business.dtos.responses.question.GetQuestionAndOptionResponse;
import com.atmosware.questionService.business.rules.QuestionBusinessRules;
import com.atmosware.questionService.core.utilities.mapping.QuestionMapper;
import com.atmosware.questionService.dataAccess.QuestionRepository;
import com.atmosware.questionService.entities.Question;
import com.atmosware.questionService.entities.Status;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ExamQuestionManager implements ExamQuestionService {
    private QuestionBusinessRules questionBusinessRules;
    private OptionService optionService;
    private QuestionRepository questionRepository;
    private QuestionMapper questionMapper;

    @Override
    public GetQuestionAndOptionResponse getQuestionAndOptionById(UUID questionId, HttpServletRequest httpServletRequest) {
        Question question = this.questionBusinessRules.isQuestionExistById(questionId);

        List<OptionResponse> optionResponseList = this.optionService.getOptionsByQuestionId(questionId);

        this.questionBusinessRules.atLeastOneCorrectOptionMustBe(optionResponseList);
        this.questionBusinessRules.checkOptionCountIsLowerThanFiveAngHigherThanTwo(optionResponseList);

        assert question != null;
        question.setStatus(Status.OCCUPIED);
        this.questionRepository.save(question);

        GetQuestionAndOptionResponse getQuestionAndOptionResponse = this.questionMapper.questionToGetQuestionAndOptionResponse(question);

        getQuestionAndOptionResponse.setOptionResponseList(optionResponseList);
        return getQuestionAndOptionResponse;
    }
}
