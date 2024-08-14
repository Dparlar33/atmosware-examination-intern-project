package com.atmosware.questionService.core.utilities.mapping;

import com.atmosware.common.exam.OptionResponse;
import com.atmosware.questionService.business.dtos.requests.option.CreateOptionRequest;
import com.atmosware.questionService.business.dtos.requests.option.UpdateOptionRequest;
import com.atmosware.questionService.business.dtos.responses.option.GetAllOptionsResponse;
import com.atmosware.questionService.business.dtos.responses.option.GetOptionByIdResponse;
import com.atmosware.questionService.entities.Option;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OptionMapper {

    Option createOptionRequestToOption(CreateOptionRequest createOptionRequest);

    Option updateOptionRequestToOption(UpdateOptionRequest updateOptionRequest);

    @Mapping(source = "question.id",target = "questionId")
    GetAllOptionsResponse optionToGetAllOptionResponse(Option option);

    GetOptionByIdResponse optionToGetOptionByIdResponse(Option option);

    OptionResponse optionToOptionResponse(Option option);
}
