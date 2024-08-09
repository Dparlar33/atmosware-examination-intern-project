package com.atmosware.questionService.business.abstracts;

import com.atmosware.common.exam.OptionResponse;
import com.atmosware.questionService.business.dtos.requests.option.CreateOptionRequest;
import com.atmosware.questionService.business.dtos.requests.option.UpdateOptionRequest;
import com.atmosware.questionService.business.dtos.responses.option.GetAllOptionsResponse;
import com.atmosware.questionService.business.dtos.responses.option.GetOptionByIdResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface OptionService {
    void addOption(CreateOptionRequest createOptionRequest) throws Exception;
    Page<GetAllOptionsResponse> getAllOptions(Pageable pageable);
    GetOptionByIdResponse getOptionById(UUID optionId) throws Exception;
    void updateOption(UpdateOptionRequest updateOptionRequest);
    void deleteOption(UUID optionId);
    List<OptionResponse> getOptionsByQuestionId(UUID questionId);
    void deleteOptionByQuestionId(UUID questionId);
}
