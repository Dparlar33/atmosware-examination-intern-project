package com.atmosware.common.exam;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetQuestionAndOption {
    private UUID questionId;
    private String userRole;
    private String description;
    private int optionCount;
    private String imageUrl;
    List<OptionResponse> optionResponseList;
}
