package com.atmosware.questionService.business.concretes;

import com.atmosware.questionService.business.abstracts.OptionService;
import com.atmosware.questionService.business.abstracts.QuestionService;
import com.atmosware.questionService.business.dtos.requests.question.CreateQuestionRequest;
import com.atmosware.questionService.business.dtos.requests.question.UpdateQuestionRequest;
import com.atmosware.questionService.business.dtos.responses.question.GetAllQuestionsResponse;
import com.atmosware.questionService.business.dtos.responses.question.GetQuestionByIdResponse;
import com.atmosware.questionService.core.utilities.exceptions.types.BusinessException;
import com.atmosware.questionService.core.utilities.mapping.QuestionMapper;
import com.atmosware.questionService.dataAccess.QuestionRepository;
import com.atmosware.questionService.entities.Question;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class QuestionManager implements QuestionService {

    private QuestionRepository questionRepository;
    private OptionService optionService;

    @Override
    public void addQuestion(CreateQuestionRequest createQuestionRequest) {
        Question mappedQuestion = QuestionMapper.INSTANCE.createQuestionRequestToQuestion(createQuestionRequest);
        questionRepository.save(mappedQuestion);
    }

    @Override
    public List<GetAllQuestionsResponse> getAllQuestions() {
        List<Question> questions = this.questionRepository.findAll();
        return questions.stream().map(QuestionMapper.INSTANCE::questionToGetAllQuestionsResponse).toList();
    }

    @Override
    public GetQuestionByIdResponse getQuestionById(UUID id) {
      Optional<Question> question = this.questionRepository.findById(id);
      if (question.isEmpty()){
          throw new BusinessException("Question not found");
      }
      return QuestionMapper.INSTANCE.questionToGetQuestionByIdResponse(question.get());
    }

    @Override
    public void updateQuestion(UpdateQuestionRequest updateQuestionRequest) {
        //Guncelleme icin Admin veya operatör eklendikten sonra is kurallari eklenecek.

        Question updatedQuestion = QuestionMapper.INSTANCE.updateQuestionRequestToQuestion(updateQuestionRequest);
        questionRepository.save(updatedQuestion);
    }

    @Override
    public void deleteQuestion(UUID id) {
        //Admin veya operator kuralları yazılıp soru silindikten sonra cevaplar silinecektir.
        //Eger silme basarili olursa
        this.optionService.deleteOption(id);
    }
}
