package com.atmosware.questionService.business.dtos.requests.question;

import com.atmosware.questionService.business.messages.ValidationMessage;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateQuestionRequest {

    @NotNull(message = ValidationMessage.THIS_FIELD_IS_REQUIRED)
    private UUID id;

    @Size(max = 2000)
    private String description;

    @Min(2)
    @Max(5)
    private int optionCount;

    private String imageUrl;
}
