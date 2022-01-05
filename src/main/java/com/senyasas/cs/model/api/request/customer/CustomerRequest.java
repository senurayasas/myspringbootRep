package com.senyasas.cs.model.api.request.customer;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotNull;

@Builder
@Jacksonized
public record CustomerRequest(@NotNull String name,
                              @NotNull String age,
                              @NotNull String email) {


}
