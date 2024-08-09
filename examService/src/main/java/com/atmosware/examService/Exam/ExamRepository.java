package com.atmosware.examService.Exam;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ExamRepository extends MongoRepository<Exam, UUID> {
}
