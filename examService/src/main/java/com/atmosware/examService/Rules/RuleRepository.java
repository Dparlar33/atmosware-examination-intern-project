package com.atmosware.examService.Rules;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RuleRepository extends JpaRepository<Rule, UUID> {
}
