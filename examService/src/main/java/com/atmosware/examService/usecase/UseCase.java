package com.atmosware.examService.usecase;

@FunctionalInterface
public interface UseCase<I extends UseCaseInput, O extends UseCaseOutput> {
    O execute(I input);
}
