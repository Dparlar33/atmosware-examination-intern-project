package com.atmosware.questionService.business.abstracts;

import com.atmosware.questionService.business.dtos.requests.option.CreateOptionRequest;
import com.atmosware.questionService.business.dtos.requests.option.UpdateOptionRequest;
import com.atmosware.questionService.business.dtos.responses.option.GetAllOptionsResponse;
import com.atmosware.questionService.business.dtos.responses.option.GetOptionByIdResponse;

import java.util.List;
import java.util.UUID;

public interface OptionService {
    void addOption(CreateOptionRequest createOptionRequest);
    List<GetAllOptionsResponse> getAllOptions();
    GetOptionByIdResponse getOptionById(UUID optionId) throws Exception;
    void updateOption(UpdateOptionRequest updateOptionRequest);
    void deleteOption(UUID optionId);
}
