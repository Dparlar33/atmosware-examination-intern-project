package com.atmosware.questionService.business.concretes;

import com.atmosware.common.exam.GetQuestionAndOption;
import com.atmosware.common.exam.OptionResponse;
import com.atmosware.questionService.business.abstracts.ExamQuestionService;
import com.atmosware.questionService.business.abstracts.OptionService;
import com.atmosware.questionService.business.rules.QuestionBusinessRules;
import com.atmosware.questionService.core.utilities.mapping.QuestionMapper;
import com.atmosware.questionService.dataAccess.QuestionRepository;
import com.atmosware.questionService.entities.Question;
import com.atmosware.questionService.entities.Status;
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
    public GetQuestionAndOption getQuestionAndOptionById(UUID questionId) {
        Question question = this.questionBusinessRules.isQuestionExistById(questionId);

        List<OptionResponse> optionResponseList = this.optionService.getOptionsByQuestionId(questionId);

        this.questionBusinessRules.atLeastOneCorrectOptionMustBe(optionResponseList);
        this.questionBusinessRules.checkOptionCountIsLowerThanFiveAngHigherThanTwo(optionResponseList);

        assert question != null;
        question.setStatus(Status.OCCUPIED);
        this.questionRepository.save(question);

        GetQuestionAndOption getQuestionAndOptionResponse = this.questionMapper.questionToGetQuestionAndOptionResponse(question);

        getQuestionAndOptionResponse.setOptionResponseList(optionResponseList);
        return getQuestionAndOptionResponse;
    }
}
