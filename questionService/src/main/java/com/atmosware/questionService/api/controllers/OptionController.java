package com.atmosware.questionService.api.controllers;

import com.atmosware.questionService.business.abstracts.OptionService;
import com.atmosware.questionService.business.dtos.requests.option.CreateOptionRequest;
import com.atmosware.questionService.business.dtos.requests.option.UpdateOptionRequest;
import com.atmosware.questionService.business.dtos.responses.option.GetAllOptionsResponse;
import com.atmosware.questionService.business.dtos.responses.option.GetOptionByIdResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/question-service/api/v1/options")
@AllArgsConstructor
public class OptionController {

    private final OptionService optionService;

    @PostMapping("/add")
    public void addOption  (@RequestBody CreateOptionRequest createOptionRequest) throws Exception {
        this.optionService.addOption(createOptionRequest);
    }

    @PutMapping("/update")
    public void updateOption(@RequestBody UpdateOptionRequest updateOptionRequest) {
        this.optionService.updateOption(updateOptionRequest);
    }

    @GetMapping("/getAllOptions")
    public List<GetAllOptionsResponse> getAllOptions() {
        return this.optionService.getAllOptions();
    }

    @GetMapping("/getById/{id}")
    public GetOptionByIdResponse getAllOptions(@PathVariable UUID id) throws Exception {
        return this.optionService.getOptionById(id);
    }
}

