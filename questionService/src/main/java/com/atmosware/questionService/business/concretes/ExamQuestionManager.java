package com.atmosware.questionService.business.concretes;

import com.atmosware.common.exam.GetQuestionAndOption;
import com.atmosware.common.exam.OptionResponse;
import com.atmosware.questionService.business.abstracts.ExamQuestionService;
import com.atmosware.questionService.business.abstracts.OptionService;
import com.atmosware.questionService.business.abstracts.QuestionService;
import com.atmosware.questionService.business.dtos.responses.question.GetQuestionByIdResponse;
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

    private OptionService optionService;
    private QuestionService questionService;
    private QuestionRepository questionRepository;
    private QuestionMapper questionMapper;

    @Override
    public GetQuestionAndOption getQuestionAndOptionById(UUID questionId) throws Exception {

        GetQuestionByIdResponse getQuestionByIdResponse = this.questionService.getQuestionById(questionId);
        Question question = this.questionMapper.getQuestionByIdResponseToQuestion(getQuestionByIdResponse);

        List<OptionResponse> optionResponseList = this.optionService.getOptionsByQuestionId(questionId);

        this.questionService.checkRulesOfOptionResponseList(optionResponseList);

        assert question != null;
        question.setStatus(Status.OCCUPIED);
        this.questionRepository.save(question);

        GetQuestionAndOption getQuestionAndOptionResponse = this.questionMapper.questionToGetQuestionAndOptionResponse(question);

        getQuestionAndOptionResponse.setOptionResponseList(optionResponseList);
        return getQuestionAndOptionResponse;
    }
}
