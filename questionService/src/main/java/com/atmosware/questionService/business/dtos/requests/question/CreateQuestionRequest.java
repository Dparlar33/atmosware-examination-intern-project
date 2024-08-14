package com.atmosware.questionService.business.dtos.requests.question;

import com.atmosware.questionService.business.messages.ValidationMessage;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateQuestionRequest {
    @Size(max = 2000)
    private String description;

    @Min(2)
    @Max(5)
    private int optionCount;

    private String imageUrl;
}
