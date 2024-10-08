package com.atmosware.common.exam;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OptionResponse {
    private String description;
    private boolean isCorrect;
    private String imageUrl;
}