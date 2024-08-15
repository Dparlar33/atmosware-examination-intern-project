package com.atmosware.questionService.business.concretes;

import com.atmosware.core.services.JwtService;
import com.atmosware.questionService.business.dtos.requests.question.CreateQuestionRequest;
import com.atmosware.questionService.business.dtos.requests.question.UpdateQuestionRequest;
import com.atmosware.questionService.business.dtos.responses.question.GetAllQuestionsResponse;
import com.atmosware.questionService.business.dtos.responses.question.GetQuestionByIdResponse;
import com.atmosware.questionService.core.utilities.mapping.QuestionMapper;
import com.atmosware.questionService.core.utilities.mapping.QuestionMapperImpl;
import com.atmosware.questionService.dataAccess.QuestionRepository;
import com.atmosware.questionService.entities.Question;
import com.atmosware.questionService.entities.Status;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuestionManagerTest {

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private QuestionManager questionManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        QuestionMapper questionMapper = new QuestionMapperImpl();
        questionManager = new QuestionManager(questionRepository,questionMapper,jwtService);
    }

    @Test
    void testAddQuestion() {
        CreateQuestionRequest request = new CreateQuestionRequest();
        request.setDescription("Test description");
        request.setImageUrl("http://test.com/image.jpg");

        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

        String token = "mockedToken";
        String roleName = "ADMIN";
        String userId = UUID.randomUUID().toString();

        when(jwtService.extractJwtFromRequest(httpServletRequest)).thenReturn(token);
        when(jwtService.extractRoles(token)).thenReturn(roleName);
        when(jwtService.extractUserId(token)).thenReturn(userId);

        Question mappedQuestion = new Question();

        questionManager.addQuestion(request, httpServletRequest);

        verify(questionRepository).save(mappedQuestion);
        assertEquals(UUID.fromString(userId), mappedQuestion.getUserId());
        assertEquals(roleName, mappedQuestion.getUserRole());
        assertEquals(Status.AVAILABLE, mappedQuestion.getStatus());
    }

    @Test
    void testGetAllQuestions() {
        PageRequest pageable = PageRequest.of(0, 10);
        Question question = new Question();
        Page<Question> questionPage = new PageImpl<>(List.of(question));

        when(questionRepository.findAllByOrderByIdAsc(pageable)).thenReturn(questionPage);
        Page<GetAllQuestionsResponse> result = questionManager.getAllQuestions(pageable);

        assertEquals(1, result.getTotalElements());
        verify(questionRepository).findAllByOrderByIdAsc(pageable);
    }

    @Test
    void testGetQuestionById() {
        UUID questionId = UUID.randomUUID();
        Question question = new Question();
        when(questionRepository.findById(questionId)).thenReturn(Optional.of(question));

        GetQuestionByIdResponse response = questionManager.getQuestionById(questionId);

        assertNotNull(response);
        verify(questionRepository).findById(questionId);
    }

    @Test
    void testUpdateQuestion() {
        UUID questionId = UUID.randomUUID();
        UpdateQuestionRequest request = new UpdateQuestionRequest();
        request.setId(questionId);

        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

        Question existingQuestion = new Question();
        existingQuestion.setId(questionId);
        existingQuestion.setStatus(Status.AVAILABLE);

        when(questionRepository.findById(request.getId())).thenReturn(Optional.of(existingQuestion));

        String token = "mockedToken";
        String roleName = "ADMIN";
        String userId = UUID.randomUUID().toString();

        when(jwtService.extractJwtFromRequest(httpServletRequest)).thenReturn(token);
        when(jwtService.extractRoles(token)).thenReturn(roleName);
        when(jwtService.extractUserId(token)).thenReturn(userId);

        Question mappedQuestion = new Question();
        mappedQuestion.setId(questionId);
        mappedQuestion.setStatus(Status.AVAILABLE);
        mappedQuestion.setOptions(existingQuestion.getOptions());
        mappedQuestion.setUserId(UUID.fromString(userId));
        mappedQuestion.setUserRole(roleName);

        ArgumentCaptor<Question> questionCaptor = ArgumentCaptor.forClass(Question.class);

        questionManager.updateQuestion(request, httpServletRequest);

        verify(questionRepository).save(mappedQuestion);
        Question savedQuestion = questionCaptor.getValue();

        assertEquals(UUID.fromString(userId), savedQuestion.getUserId());
        assertEquals(roleName, savedQuestion.getUserRole());
        assertEquals(Status.AVAILABLE, savedQuestion.getStatus());
    }

    @Test
    void testDeleteQuestion() {
        UUID questionId = UUID.randomUUID();
        Question question = new Question();
        when(questionRepository.findById(questionId)).thenReturn(Optional.of(question));

        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

        String token = "mockedToken";
        String roleName = "ADMIN";
        String userId = UUID.randomUUID().toString();

        when(jwtService.extractJwtFromRequest(httpServletRequest)).thenReturn(token);
        when(jwtService.extractRoles(token)).thenReturn(roleName);
        when(jwtService.extractUserId(token)).thenReturn(userId);

        questionManager.deleteQuestion(questionId, httpServletRequest);

        verify(questionRepository).deleteById(questionId);
    }
}