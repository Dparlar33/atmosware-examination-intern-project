package com.atmosware.questionService.business.concretes;

import com.atmosware.common.exam.OptionResponse;
import com.atmosware.questionService.business.abstracts.OptionService;
import com.atmosware.questionService.business.abstracts.QuestionService;
import com.atmosware.questionService.business.dtos.requests.option.CreateOptionRequest;
import com.atmosware.questionService.business.dtos.requests.option.UpdateOptionRequest;
import com.atmosware.questionService.business.dtos.responses.option.GetAllOptionsResponse;
import com.atmosware.questionService.business.dtos.responses.option.GetOptionByIdResponse;
import com.atmosware.questionService.business.dtos.responses.question.GetQuestionByIdResponse;
import com.atmosware.questionService.business.messages.OptionMessages;
import com.atmosware.questionService.core.utilities.exceptions.types.BusinessException;
import com.atmosware.questionService.core.utilities.mapping.OptionMapper;
import com.atmosware.questionService.core.utilities.mapping.QuestionMapper;
import com.atmosware.questionService.dataAccess.OptionRepository;
import com.atmosware.questionService.entities.Option;
import com.atmosware.questionService.entities.Question;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class OptionManager implements OptionService {

    private OptionRepository optionRepository;
    private OptionMapper optionMapper;
    private QuestionService questionService;
    private QuestionMapper questionMapper;

    @Override
    public void addOption(CreateOptionRequest createOptionRequest) throws Exception {
        imageAndDescriptionShouldNotBeNullInTheOneOption(createOptionRequest);

        GetQuestionByIdResponse question = this.questionService.getQuestionById(createOptionRequest.getQuestionId());
        isOptionCountLowerThanRequestedOption(question);

        Option mappedOption = this.optionMapper.createOptionRequestToOption(createOptionRequest);
        Question mappedQuestion = this.questionMapper.getQuestionByIdResponseToQuestion(question);
        mappedOption.setQuestion(mappedQuestion);

        this.optionRepository.save(mappedOption);
    }

    @Override
    public Page<GetAllOptionsResponse> getAllOptions(Pageable pageable) {
        Page<Option> options = this.optionRepository.findAllByOrderByIdAsc(pageable);
        return options.map(this.optionMapper::optionToGetAllOptionResponse);
    }

    @Override
    public List<OptionResponse> getOptionsByQuestionId(UUID questionId){
        List<Option> options = this.optionRepository.findOptionsByQuestionId(questionId);
        return options.stream().map(this.optionMapper::optionToOptionResponse).toList();
    }

    @Override
    public GetOptionByIdResponse getOptionById(UUID id) throws Exception {
        return this.optionMapper.
                optionToGetOptionByIdResponse(isOptionExistById(id));
    }

    @Override
    public void updateOption(UpdateOptionRequest updateOptionRequest) {
        Option mappedOption = this.optionMapper.updateOptionRequestToOption(updateOptionRequest);
        this.optionRepository.save(mappedOption);
    }

    @Override
    public void deleteOption(UUID id) {
        this.optionRepository.deleteById(id);
    }


    public Option isOptionExistById(UUID id){
        Optional<Option> option = this.optionRepository.findById(id);
        if (option.isEmpty()){
            throw new BusinessException(OptionMessages.OPTION_NOT_FOUND);
        }
        return option.get();
    }

    public void isOptionCountLowerThanRequestedOption(GetQuestionByIdResponse question ) {

        List<Option> optionList = this.optionRepository.findOptionsByQuestionId(question.getId());

        if (optionList.size() >= question.getOptionCount()){
            throw new BusinessException(OptionMessages.OPTION_LIMIT_FULL_FOR_THIS_QUESTION);
        }
    }

    public void imageAndDescriptionShouldNotBeNullInTheOneOption(CreateOptionRequest createOptionRequest){
        if (createOptionRequest.getDescription().isEmpty() && createOptionRequest.getImageUrl().isEmpty()){
            throw new BusinessException(OptionMessages.DESCRIPTION_AND_IMAGE_URL_CANNOT_BE_NULL_AT_THE_SAME_OPTION);
        }
    }
}
