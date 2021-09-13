package com.auth.Signing;

import com.auth.Signing.Exceptions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureMockMvc
@SpringBootTest
public class ExceptionsTests {

    @Test
    void testthrowsErrors() throws  Exception{

        try{
            throw new DuplicateEmailIdException("600","error");
        }
        catch(DuplicateEmailIdException e){
            System.out.println(e.getErroMsg());
            System.out.println(e.getErrorCode());

        }
        try{
            throw new InvalidCredentialsException("600","error");
        }
        catch(InvalidCredentialsException e){
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
        }
        try{
            throw new InvalidSearchFormatException("600","error");
        }
        catch(InvalidSearchFormatException e){
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
        }
        try{
            throw new SocialDetailsInaccessible("600","error");
        }
        catch(SocialDetailsInaccessible e){
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
        }
        try{
            throw new WrongOtpException("error");
        }
        catch(WrongOtpException e){
            System.out.println(e.getMessage());
        }
    }

}
