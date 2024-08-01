package com.atmosware.questionService.business.dtos.requests.question;

import com.atmosware.questionService.business.messages.ValidationMessage;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class UpdateQuestionRequest {

    @NotNull(message = ValidationMessage.THIS_FIELD_IS_REQUIRED)
    private UUID id;

    @NotNull
    @Size(max = 2000)
    private String description;

    @Size(min = 2,max = 5)
    @Pattern(regexp = "\\d+", message = ValidationMessage.OPTION_COUNT_MUST_CONTAIN_ONLY_DIGITS)
    private int optionCount;

    private String imageUrl;
}
