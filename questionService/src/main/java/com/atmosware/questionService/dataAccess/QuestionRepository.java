package com.atmosware.questionService.dataAccess;


import com.atmosware.questionService.entities.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {
    Page<Question> findAllByOrderByIdAsc(Pageable pageable);
}
