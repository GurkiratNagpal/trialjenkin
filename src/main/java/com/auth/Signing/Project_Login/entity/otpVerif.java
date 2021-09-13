package com.auth.Signing.Project_Login.entity;

import lombok.Data;

import javax.persistence.Id;


public @Data
class otpVerif {

    @Id
    private String otp;

    private String usermail;
}
