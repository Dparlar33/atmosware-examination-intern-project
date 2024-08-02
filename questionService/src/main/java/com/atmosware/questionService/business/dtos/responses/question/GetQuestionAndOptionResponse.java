package com.atmosware.questionService.business.dtos.responses.question;

import com.atmosware.questionService.business.dtos.responses.option.OptionResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetQuestionAndOptionResponse {
    private String userRole;
    private String description;
    private int optionCount;
    private String imageUrl;
    List<OptionResponse> optionResponseList;
}
