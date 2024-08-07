package com.atmosware.examService.core.exceptions.problemDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessDetails {
    private String type;
    private String title;
    private int status;
    private String detail;
}