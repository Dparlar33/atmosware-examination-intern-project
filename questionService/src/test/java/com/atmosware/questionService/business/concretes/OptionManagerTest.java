package com.atmosware.questionService.business.concretes;

import com.atmosware.common.exam.OptionResponse;
import com.atmosware.questionService.business.abstracts.QuestionService;
import com.atmosware.questionService.business.dtos.requests.option.CreateOptionRequest;
import com.atmosware.questionService.business.dtos.requests.option.UpdateOptionRequest;
import com.atmosware.questionService.business.dtos.responses.option.GetAllOptionsResponse;
import com.atmosware.questionService.business.dtos.responses.option.GetOptionByIdResponse;
import com.atmosware.questionService.business.dtos.responses.question.GetQuestionByIdResponse;
import com.atmosware.questionService.business.messages.OptionMessages;
import com.atmosware.questionService.core.utilities.exceptions.types.BusinessException;
import com.atmosware.questionService.core.utilities.mapping.OptionMapper;
import com.atmosware.questionService.core.utilities.mapping.OptionMapperImpl;
import com.atmosware.questionService.core.utilities.mapping.QuestionMapper;
import com.atmosware.questionService.core.utilities.mapping.QuestionMapperImpl;
import com.atmosware.questionService.dataAccess.OptionRepository;
import com.atmosware.questionService.entities.Option;
import com.atmosware.questionService.entities.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OptionManagerTest {

    @InjectMocks
    private OptionManager optionManager;

    @Mock
    private OptionRepository optionRepository;

    @Mock
    private QuestionService questionService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        QuestionMapper questionMapper = new QuestionMapperImpl();
        OptionMapper optionMapper = new OptionMapperImpl();

        optionManager = new OptionManager(optionRepository,optionMapper, questionService,questionMapper);
    }

    @Test
    void testAddOption_Success() throws Exception {
        CreateOptionRequest createOptionRequest = new CreateOptionRequest();
        createOptionRequest.setQuestionId(UUID.randomUUID());
        createOptionRequest.setDescription("Test Description");

        GetQuestionByIdResponse questionResponse = new GetQuestionByIdResponse();
        questionResponse.setId(createOptionRequest.getQuestionId());
        questionResponse.setOptionCount(4);

        when(questionService.getQuestionById(createOptionRequest.getQuestionId())).thenReturn(questionResponse);

        optionManager.addOption(createOptionRequest);

        ArgumentCaptor<Option> optionCaptor = ArgumentCaptor.forClass(Option.class);
        verify(optionRepository, times(1)).save(optionCaptor.capture());

        Option capturedOption = optionCaptor.getValue();
        assertEquals(createOptionRequest.getDescription(), capturedOption.getDescription());
        assertEquals(createOptionRequest.getQuestionId(), capturedOption.getQuestion().getId());
    }

    @Test
    void testAddOption_ThrowsException_WhenOptionCountExceedsLimit() throws Exception {

        CreateOptionRequest createOptionRequest = new CreateOptionRequest();
        createOptionRequest.setQuestionId(UUID.randomUUID());
        createOptionRequest.setDescription("Valid description");
        createOptionRequest.setImageUrl("");

        GetQuestionByIdResponse questionResponse = new GetQuestionByIdResponse();
        questionResponse.setId(createOptionRequest.getQuestionId());
        questionResponse.setOptionCount(2);


        when(questionService.getQuestionById(createOptionRequest.getQuestionId())).thenReturn(questionResponse);
        when(optionRepository.findOptionsByQuestionId(questionResponse.getId())).thenReturn(List.of(new Option(), new Option()));


        BusinessException thrown = assertThrows(
                BusinessException.class,
                () -> optionManager.addOption(createOptionRequest)
        );

        assertEquals(OptionMessages.OPTION_LIMIT_FULL_FOR_THIS_QUESTION, thrown.getMessage());
    }

    @Test
    void testGetAllOptions_Success() {
        Pageable pageable = PageRequest.of(0, 10);
        Option option = new Option();
        Page<Option> options = new PageImpl<>(List.of(option));

        when(optionRepository.findAllByOrderByIdAsc(pageable)).thenReturn(options);

        Page<GetAllOptionsResponse> result = optionManager.getAllOptions(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void testGetOptionsByQuestionId_Success() {
        UUID questionId = UUID.randomUUID();
        Option option = new Option();

        when(optionRepository.findOptionsByQuestionId(questionId)).thenReturn(List.of(option));

        List<OptionResponse> result = optionManager.getOptionsByQuestionId(questionId);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetOptionById_Success() throws Exception {
        UUID optionId = UUID.randomUUID();
        Option option = new Option();

        when(optionRepository.findById(optionId)).thenReturn(Optional.of(option));

        GetOptionByIdResponse result = optionManager.getOptionById(optionId);

        assertNotNull(result);
    }

    @Test
    void testUpdateOption_Success() {

        UpdateOptionRequest updateOptionRequest = new UpdateOptionRequest();
        updateOptionRequest.setId(UUID.randomUUID());
        updateOptionRequest.setDescription("Updated Description");

        Option mappedOption = new Option();
        mappedOption.setId(updateOptionRequest.getId());
        mappedOption.setDescription(updateOptionRequest.getDescription());


        optionManager.updateOption(updateOptionRequest);


        ArgumentCaptor<Option> optionCaptor = ArgumentCaptor.forClass(Option.class);
        verify(optionRepository, times(1)).save(optionCaptor.capture());

        Option capturedOption = optionCaptor.getValue();
        assertEquals(updateOptionRequest.getId(), capturedOption.getId());
        assertEquals(updateOptionRequest.getDescription(), capturedOption.getDescription());
    }

    @Test
    void testDeleteOption_Success() {
        UUID optionId = UUID.randomUUID();

        optionManager.deleteOption(optionId);

        verify(optionRepository, times(1)).deleteById(optionId);
    }

    @Test
    void testIsOptionExistById_Success() {
        UUID optionId = UUID.randomUUID();
        Option option = new Option();

        when(optionRepository.findById(optionId)).thenReturn(Optional.of(option));

        Option result = optionManager.isOptionExistById(optionId);

        assertNotNull(result);
        assertEquals(option, result);
    }

    @Test
    void testIsOptionExistById_ThrowsException_WhenOptionNotFound() {
        UUID optionId = UUID.randomUUID();

        when(optionRepository.findById(optionId)).thenReturn(Optional.empty());

        BusinessException thrown = assertThrows(
                BusinessException.class,
                () -> optionManager.isOptionExistById(optionId)
        );

        assertEquals(OptionMessages.OPTION_NOT_FOUND, thrown.getMessage());
    }

    @Test
    void testImageAndDescriptionShouldNotBeNullInTheOneOption_ThrowsException() {
        CreateOptionRequest createOptionRequest = new CreateOptionRequest();
        createOptionRequest.setDescription(""); // Set empty string
        createOptionRequest.setImageUrl("");

        BusinessException thrown = assertThrows(
                BusinessException.class,
                () -> optionManager.imageAndDescriptionShouldNotBeNullInTheOneOption(createOptionRequest)
        );

        assertEquals(OptionMessages.DESCRIPTION_AND_IMAGE_URL_CANNOT_BE_NULL_AT_THE_SAME_OPTION, thrown.getMessage());
    }

    @Test
    void testImageAndDescriptionShouldNotBeNullInTheOneOption_Success() {
        CreateOptionRequest createOptionRequest = new CreateOptionRequest();
        createOptionRequest.setDescription("Test Description");

        assertDoesNotThrow(() -> optionManager.imageAndDescriptionShouldNotBeNullInTheOneOption(createOptionRequest));
    }
}