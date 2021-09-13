package com.auth.Signing.Exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InvalidCredentialsException extends RuntimeException{

    private String errorCode;
    private String erroMsg;

    public InvalidCredentialsException(String errorCode, String erroMsg){
        super();
        this.erroMsg=erroMsg;
        this.errorCode=errorCode;
    }
}
