package com.atmosware.common.exam;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class GetQuestionAndOption {
    private String userRole;
    private String description;
    private int optionCount;
    private String imageUrl;
    List<OptionResponse> optionResponseList;
}
