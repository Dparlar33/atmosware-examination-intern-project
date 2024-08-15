package com.atmosware.questionService.core.utilities.mapping;

import com.atmosware.common.exam.GetQuestionAndOption;
import com.atmosware.questionService.business.dtos.requests.question.CreateQuestionRequest;
import com.atmosware.questionService.business.dtos.requests.question.UpdateQuestionRequest;
import com.atmosware.questionService.business.dtos.responses.question.GetAllQuestionsResponse;
import com.atmosware.questionService.business.dtos.responses.question.GetQuestionByIdResponse;
import com.atmosware.questionService.entities.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question createQuestionRequestToQuestion(CreateQuestionRequest createQuestionRequest);
    GetAllQuestionsResponse questionToGetAllQuestionsResponse(Question question);
    GetQuestionByIdResponse questionToGetQuestionByIdResponse(Question question);

    @Mapping(source = "description",target = "description")
    GetQuestionAndOption questionToGetQuestionAndOptionResponse(Question question);
    Question getQuestionByIdResponseToQuestion(GetQuestionByIdResponse getQuestionByIdResponse);

    Question updateQuestionRequestToQuestion(UpdateQuestionRequest updateQuestionRequest);
    UpdateQuestionRequest questionToUpdateQuestionRequest(Question question);
}
