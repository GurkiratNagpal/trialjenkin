package com.auth.Signing.Project_Login.LoginServices;

import com.auth.Signing.Project_Login.entity.User;
import com.auth.Signing.Project_Login.entity.UserLogin;
import com.auth.Signing.Project_Login.entity.otpVerif;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;


public interface ControllerServices {
    void processregistration(User user) throws MessagingException, UnsupportedEncodingException;

    String processlogin(UserLogin userLogin) throws Exception;

    void sendVerificationEmail(User user) throws MessagingException, UnsupportedEncodingException;

    void verifyotp(otpVerif otp);
}
