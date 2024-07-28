package com.atmosware.questionService.core.utilities.mapping;

import com.atmosware.questionService.business.dtos.requests.question.CreateQuestionRequest;
import com.atmosware.questionService.business.dtos.requests.question.UpdateQuestionRequest;
import com.atmosware.questionService.business.dtos.responses.question.GetAllQuestionsResponse;
import com.atmosware.questionService.business.dtos.responses.question.GetQuestionByIdResponse;
import com.atmosware.questionService.entities.Question;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    Question createQuestionRequestToQuestion(CreateQuestionRequest createQuestionRequest);
    Question updateQuestionRequestToQuestion(UpdateQuestionRequest updateQuestionRequest);
    GetAllQuestionsResponse questionToGetAllQuestionsResponse(Question question);
    GetQuestionByIdResponse questionToGetQuestionByIdResponse(Question question);
}
