package com.atmosware.questionService.business.concretes;

import com.atmosware.questionService.business.abstracts.OptionService;
import com.atmosware.questionService.business.dtos.requests.option.CreateOptionRequest;
import com.atmosware.questionService.business.dtos.requests.option.UpdateOptionRequest;
import com.atmosware.questionService.business.dtos.responses.option.GetAllOptionsResponse;
import com.atmosware.questionService.business.dtos.responses.option.GetOptionByIdResponse;
import com.atmosware.questionService.core.utilities.exceptions.types.BusinessException;
import com.atmosware.questionService.core.utilities.mapping.OptionMapper;
import com.atmosware.questionService.dataAccess.OptionRepository;
import com.atmosware.questionService.entities.Option;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class OptionManager implements OptionService {

    private OptionRepository optionRepository;

    @Override
    public void addOption(CreateOptionRequest createOptionRequest) {
        Option mappedOption = OptionMapper.INSTANCE.createOptionRequestToOption(createOptionRequest);
        this.optionRepository.save(mappedOption);
    }

    @Override
    public List<GetAllOptionsResponse> getAllOptions() {
        List<Option> options = this.optionRepository.findAll();
        return options.stream().map(OptionMapper.INSTANCE::optionToGetAllOptionResponse).toList();
    }

    @Override
    public GetOptionByIdResponse getOptionById(UUID id) throws Exception {
        Optional<Option> option = this.optionRepository.findById(id);
        if (option.isEmpty()){
            throw new BusinessException("Option not found");
        }
        return OptionMapper.INSTANCE.optionToGetOptionByIdResponse(option.get());
    }

    @Override
    public void updateOption(UpdateOptionRequest updateOptionRequest) {
        Option mappedOption = OptionMapper.INSTANCE.updateOptionRequestToOption(updateOptionRequest);
        this.optionRepository.save(mappedOption);
    }

    @Override
    public void deleteOption(UUID questionId) {
        this.optionRepository.deleteOptionsByQuestionId(questionId);
    }
}
