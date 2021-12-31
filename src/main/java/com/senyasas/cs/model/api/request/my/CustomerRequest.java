package com.senyasas.cs.model.api.request.my;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotNull;

@Builder
@Jacksonized
public record CustomerRequest(@NotNull String name,
                              @NotNull String age,
                              @NotNull String email) {


}
