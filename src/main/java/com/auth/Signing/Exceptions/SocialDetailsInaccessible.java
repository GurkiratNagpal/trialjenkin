package com.auth.Signing.Exceptions;

import lombok.Data;


@Data
public class SocialDetailsInaccessible extends RuntimeException{
    private String errorCode;
    private String erroMsg;

    public SocialDetailsInaccessible(String errorCode, String erroMsg){
        super();
        this.erroMsg=erroMsg;
        this.errorCode=errorCode;
    }

}
