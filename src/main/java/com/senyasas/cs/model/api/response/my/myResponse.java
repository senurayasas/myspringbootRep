package com.senyasas.cs.model.api.response.my;

import lombok.Builder;

public record myResponse(String text) {

    @Builder
    public myResponse {
    }
}