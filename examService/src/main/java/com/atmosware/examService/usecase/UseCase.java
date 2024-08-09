package com.atmosware.examService.usecase;

import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface UseCase<I extends UseCaseInput, O extends UseCaseOutput> {
    O execute(I input, HttpServletRequest request);
}
