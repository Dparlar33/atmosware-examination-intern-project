package com.atmosware.questionService.api.controllers;

import com.atmosware.questionService.business.abstracts.OptionService;
import com.atmosware.questionService.business.dtos.requests.option.CreateOptionRequest;
import com.atmosware.questionService.business.dtos.requests.option.UpdateOptionRequest;
import com.atmosware.questionService.business.dtos.responses.option.GetAllOptionsResponse;
import com.atmosware.questionService.business.dtos.responses.option.GetOptionByIdResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/question-service/api/v1/options")
@AllArgsConstructor
public class OptionController {

    private final OptionService optionService;

    @PostMapping("/add")
    public void addOption  (@Valid @RequestBody CreateOptionRequest createOptionRequest) throws Exception {
        this.optionService.addOption(createOptionRequest);
    }

    @PutMapping("/update")
    public void updateOption(@Valid @RequestBody UpdateOptionRequest updateOptionRequest) {
        this.optionService.updateOption(updateOptionRequest);
    }

    //?page=1&size=20
    @GetMapping("/getAllOptions")
    public Page<GetAllOptionsResponse> getAllOptions(@RequestParam int page,
                                                     @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.optionService.getAllOptions(pageable);
    }

    @GetMapping("/getById/{id}")
    public GetOptionByIdResponse getOptionsById(@PathVariable UUID id) throws Exception {
        return this.optionService.getOptionById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable UUID id)  {
        this.optionService.deleteOption(id);
    }
}

