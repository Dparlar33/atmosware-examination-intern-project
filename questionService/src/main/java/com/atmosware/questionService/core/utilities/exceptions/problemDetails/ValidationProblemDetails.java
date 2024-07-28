package com.atmosware.questionService.core.utilities.exceptions.problemDetails;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ValidationProblemDetails extends ProblemDetails{
    public ValidationProblemDetails(){
        setTitle("Validation Rule Violation");
        setDetail("Validation Problem");
        setType("http://mydomain.com/exceptions/validation");
        setStatus("400");
    }
    private Map<String,String> errors;
}


