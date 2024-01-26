package it.almaviva.difesa.security.shared.exception;

import lombok.Data;

@Data
public class CustomErrorResponse {

    private String timestamp;
    private String message;
    private int status;
}