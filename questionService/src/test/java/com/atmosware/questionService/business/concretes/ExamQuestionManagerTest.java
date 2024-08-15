package com.atmosware.questionService.business.concretes;

import com.atmosware.common.exam.GetQuestionAndOption;
import com.atmosware.common.exam.OptionResponse;
import com.atmosware.questionService.business.abstracts.OptionService;
import com.atmosware.questionService.business.abstracts.QuestionService;
import com.atmosware.questionService.core.utilities.mapping.QuestionMapper;
import com.atmosware.questionService.core.utilities.mapping.QuestionMapperImpl;
import com.atmosware.questionService.dataAccess.QuestionRepository;
import com.atmosware.questionService.entities.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ExamQuestionManagerTest {

    @Mock
    private OptionService optionService;

    @Mock
    private QuestionService questionService;

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private ExamQuestionManager examQuestionManager;

    private UUID questionId;
    private Question question;
    private List<OptionResponse> optionResponseList;
    private GetQuestionAndOption expectedResponse;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        questionId = UUID.randomUUID();
        question = new Question();
        optionResponseList = List.of(new OptionResponse(), new OptionResponse());
        expectedResponse = new GetQuestionAndOption();

        QuestionMapper questionMapper = new QuestionMapperImpl();
        examQuestionManager = new ExamQuestionManager(optionService, questionService, questionRepository, questionMapper);
    }

    @Test
    public void testGetQuestionAndOptionById() {

        when(questionService.isQuestionExistById(questionId)).thenReturn(question);
        when(optionService.getOptionsByQuestionId(questionId)).thenReturn(optionResponseList);
        when(questionRepository.save(any(Question.class))).thenReturn(question);

        GetQuestionAndOption result = examQuestionManager.getQuestionAndOptionById(questionId);

        assertEquals(expectedResponse.getQuestionId(), result.getQuestionId());
        assertEquals(expectedResponse.getImageUrl(), result.getImageUrl());
        assertEquals(optionResponseList, result.getOptionResponseList());
    }

}