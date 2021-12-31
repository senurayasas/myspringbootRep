package com.senyasas.cs.model.api.request.my;

import lombok.Builder;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record myRequest(@NotNull @NotBlank(message = "Please provide a value") String input) {


    @Builder
    public myRequest {
    }
}
