package com.atmosware.questionService.business.dtos.requests.option;

import com.atmosware.questionService.business.messages.ValidationMessage;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateOptionRequest {
    @NotNull(message = ValidationMessage.THIS_FIELD_IS_REQUIRED)
    private UUID id;

    @NotNull(message = ValidationMessage.THIS_FIELD_IS_REQUIRED)
    private UUID questionId;

    @Size(max = 500)
    private String description;

    @NotNull(message = ValidationMessage.THIS_FIELD_IS_REQUIRED)
    private boolean isCorrect;

    private String imageUrl;
}
