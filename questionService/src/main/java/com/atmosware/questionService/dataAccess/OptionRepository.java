package com.atmosware.questionService.dataAccess;

import com.atmosware.questionService.entities.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OptionRepository extends JpaRepository<Option, UUID> {
    void deleteOptionsByQuestionId(UUID questionId);
}
