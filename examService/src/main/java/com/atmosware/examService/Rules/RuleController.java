package com.atmosware.examService.Rules;

import com.atmosware.examService.Rules.usecases.createrule.CreateRuleRequest;
import com.atmosware.examService.Rules.usecases.createrule.CreateRuleResponse;
import com.atmosware.examService.Rules.usecases.createrule.CreateRuleUseCaseInput;
import com.atmosware.examService.Rules.usecases.createrule.CreateRuleUseCaseOutput;
import com.atmosware.examService.Rules.usecases.deleterule.DeleteRuleVoidUseCaseInput;
import com.atmosware.examService.Rules.usecases.getrule.GetRuleByIdResponse;
import com.atmosware.examService.Rules.usecases.getrule.GetRuleByIdUseCaseInput;
import com.atmosware.examService.Rules.usecases.getrule.GetRuleByIdUseCaseOutput;
import com.atmosware.examService.usecase.UseCase;
import com.atmosware.examService.usecase.VoidUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/exam-service/api/v1/rules")
@RequiredArgsConstructor
public class RuleController {
    private final UseCase<CreateRuleUseCaseInput, CreateRuleUseCaseOutput> createUseCase;
    private final UseCase<GetRuleByIdUseCaseInput, GetRuleByIdUseCaseOutput> getRuleByIdUseCase;
    private final VoidUseCase<DeleteRuleVoidUseCaseInput> deleteRuleVoidUseCase;

    @PostMapping("/add")
    public ResponseEntity<CreateRuleResponse> createRule(@RequestBody CreateRuleRequest createRuleRequest) {
        CreateRuleUseCaseOutput output = this.createUseCase.execute(
                        new CreateRuleUseCaseInput(
                                createRuleRequest.getDescription()));
        return new ResponseEntity<>(output.getCreateRuleResponse(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetRuleByIdResponse> getRule(@PathVariable UUID id) {
         GetRuleByIdUseCaseOutput output =
                this.getRuleByIdUseCase.execute(new GetRuleByIdUseCaseInput(id));
        return new ResponseEntity<>(output.getGetRuleByIdResponse(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRule(@PathVariable UUID id) {
        this.deleteRuleVoidUseCase.execute(new DeleteRuleVoidUseCaseInput(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
