package com.atmosware.questionService.business.dtos.responses.question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllQuestionsResponse {
    private UUID id;
    private String userRole;
    private String description;
    private int optionCount;
    private String imageUrl;
}
