package com.senyasas.cs.model.api.response.my;

import lombok.Builder;

public record CustomerResponse(String text) {

    @Builder
    public CustomerResponse {
    }
}