package com.atmosware.examService.usecase;

import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface VoidUseCase<I extends UseCaseInput> {
    void execute(I input, HttpServletRequest request);
}
