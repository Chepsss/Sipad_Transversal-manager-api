package it.almaviva.difesa.security.redis.entity;

import lombok.Data;

@Data
public class PostAuthResponse {

    private String token;
    private String refreshToken;
}
