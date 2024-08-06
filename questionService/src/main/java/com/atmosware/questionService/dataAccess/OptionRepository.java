package com.atmosware.questionService.dataAccess;

import com.atmosware.questionService.entities.Option;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OptionRepository extends JpaRepository<Option, UUID> {
    Page<Option> findAllByOrderByIdAsc(Pageable pageable);
    void deleteOptionsByQuestionId(UUID questionId);
    List<Option> findOptionsByQuestionId(UUID questionId);
}
