package com.atmosware.questionService.business.concretes;

import com.atmosware.questionService.business.abstracts.OptionService;
import com.atmosware.questionService.business.abstracts.QuestionService;
import com.atmosware.questionService.business.dtos.requests.question.CreateQuestionRequest;
import com.atmosware.questionService.business.dtos.requests.question.UpdateQuestionRequest;
import com.atmosware.questionService.business.dtos.responses.question.GetAllQuestionsResponse;
import com.atmosware.questionService.business.dtos.responses.question.GetQuestionByIdResponse;
import com.atmosware.questionService.business.rules.QuestionBusinessRules;
import com.atmosware.questionService.core.utilities.mapping.QuestionMapper;
import com.atmosware.questionService.dataAccess.QuestionRepository;
import com.atmosware.questionService.entities.Question;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class QuestionManager implements QuestionService {

    private QuestionRepository questionRepository;
    private OptionService optionService;
    private QuestionBusinessRules questionBusinessRules;
    private QuestionMapper questionMapper;

    @Override
    public void addQuestion(CreateQuestionRequest createQuestionRequest) {
        Question mappedQuestion = this.questionMapper.createQuestionRequestToQuestion(createQuestionRequest);
        questionRepository.save(mappedQuestion);
    }

    @Override
    public List<GetAllQuestionsResponse> getAllQuestions() {
        List<Question> questions = this.questionRepository.findAll();
        return questions.stream().map(this.questionMapper::questionToGetAllQuestionsResponse).toList();
    }

    @Override
    public GetQuestionByIdResponse getQuestionById(UUID id) {

      return this.questionMapper.questionToGetQuestionByIdResponse(this.questionBusinessRules.isQuestionExistById(id));
    }

    @Override
    public void updateQuestion(UpdateQuestionRequest updateQuestionRequest) {
        Question updatedQuestion = this.questionMapper.updateQuestionRequestToQuestion(updateQuestionRequest);
        questionRepository.save(updatedQuestion);
    }

    @Override
    public void deleteQuestion(UUID id) {
        this.optionService.deleteOption(id);
        this.questionRepository.deleteById(id);
    }
}
