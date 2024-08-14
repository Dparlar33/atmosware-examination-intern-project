package com.atmosware.examService.Exam;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ExamRepository extends MongoRepository<Exam, UUID> {

    Page<Exam> findAllByOrderByDescriptionAsc(Pageable pageable);

}
