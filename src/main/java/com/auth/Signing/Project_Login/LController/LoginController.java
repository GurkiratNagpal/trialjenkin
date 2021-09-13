package com.auth.Signing.Project_Login.LController;

import com.auth.Signing.Project_Login.LoginServices.ControllerServices;
import com.auth.Signing.Project_Login.LoginServices.OauthService;
import com.auth.Signing.Project_Login.entity.User;
import com.auth.Signing.Project_Login.entity.UserLogin;
import com.auth.Signing.Project_Login.entity.otpVerif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private ControllerServices controllerServices;

    @Autowired
    OauthService oauthService;

    @GetMapping("/setup")
    public String getTokenofOauth(){

        String currmail= oauthService.getCurremail();
        oauthService.setCurremail("");
        return currmail;

    }


    @PostMapping("/send_otp")
    public void sendotp(@RequestBody User user) throws MessagingException, UnsupportedEncodingException {
        System.err.println("I was told to send otp");
        controllerServices.processregistration(user);
    }

    @PostMapping("/process_otp")
    public void processotp(@RequestBody otpVerif myOtpverification) {
        System.err.println(myOtpverification+ "???");
        controllerServices.verifyotp(myOtpverification);
    }


    @PostMapping("/process_login")
    public void processlogin(
           @RequestBody UserLogin userLogin) throws Exception {
        System.err.println(userLogin.toString());
        controllerServices.processlogin(userLogin);
    }
}
