package com.atmosware.examService.usecase;

@FunctionalInterface
public interface VoidUseCase<I extends UseCaseInput> {
    void execute(I input);
}
