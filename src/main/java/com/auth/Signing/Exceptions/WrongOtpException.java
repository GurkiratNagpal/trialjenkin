package com.auth.Signing.Exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class WrongOtpException extends RuntimeException{
    private String errormsg;

}
