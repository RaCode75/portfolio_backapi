package com.back.portfolioapi.dto;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String refreshToken;
}