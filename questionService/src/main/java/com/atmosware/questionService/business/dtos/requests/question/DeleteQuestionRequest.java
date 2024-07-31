package com.atmosware.questionService.business.dtos.requests.question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class DeleteQuestionRequest {
    private UUID id;
    private UUID userId;
}
