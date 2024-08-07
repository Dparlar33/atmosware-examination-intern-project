package com.atmosware.examService.Rules.usecases.createrule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateRuleResponse {
    private UUID id;
    private String description;
}
