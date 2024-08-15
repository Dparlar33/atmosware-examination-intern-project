package com.atmosware.questionService.business.concretes;

import com.atmosware.common.exam.GetQuestionAndOption;
import com.atmosware.common.exam.OptionResponse;
import com.atmosware.questionService.business.abstracts.OptionService;
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

}