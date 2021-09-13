package com.auth.Signing.advice;

import com.auth.Signing.Exceptions.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class MyControllerAdvice {


    @ExceptionHandler(DuplicateEmailIdException.class)
    public ResponseEntity<String> emailIdExists(DuplicateEmailIdException duplicateEmailIdException){

        return new ResponseEntity<>("EmailID Already Registered for Local Signup !!", HttpStatus.NOT_ACCEPTABLE);

    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> noSuchCompanyExists(NoSuchElementException noSuchElementException){

        return new ResponseEntity<>("No Results Found !! Either Search By CompanyName or InvoiceId using format 'inv:InvoiceId' with valid Invoice Number!!", HttpStatus.NO_CONTENT);

    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> invalidInvoiceId(NumberFormatException numberFormatException){

        return new ResponseEntity<>("Please Enter valid search input !! Follow 'inv:InvoiceId' format, where InvoiceId is a number..!", HttpStatus.NOT_ACCEPTABLE);

    }

    @ExceptionHandler(WrongOtpException.class)
    public ResponseEntity<String> wrongotp(){

        return new ResponseEntity<>("You've entered the wrong otp! Please Register again!", HttpStatus.EXPECTATION_FAILED);

    }
    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<String> disabled(){

        return new ResponseEntity<>("You've not verified your email address! Please Register again!", HttpStatus.PARTIAL_CONTENT);

    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> invalidCredentials(InvalidCredentialsException invalidCredentialsException){

        return new ResponseEntity<>("Please Enter valid Credentials !", HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(InvalidSearchFormatException.class)
    public ResponseEntity<String> invalidFormat(InvalidSearchFormatException invalidSearchFormatException){

        return new ResponseEntity<>("Invalid Search Format !! Use 'inv:InvoiceId' format to search using Invoice Id", HttpStatus.NOT_ACCEPTABLE);

    }

    @ExceptionHandler(SocialDetailsInaccessible.class)
    public ResponseEntity<String> inaccessibleDetails(){
        return  new ResponseEntity<>("Your details are inaccessible! Please make your profile Public", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> ioException(){
        return  new ResponseEntity<>("IO exception incurred", HttpStatus.EXPECTATION_FAILED);

    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> badcreds(){
        return  new ResponseEntity<>("You entered the wrong credentials! :(", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception(){
        return  new ResponseEntity<>(" OOps! exception incurred", HttpStatus.EXPECTATION_FAILED);

    }

}