package com.auth.Signing.Project_Login.LoginServices;

import com.auth.Signing.Exceptions.DuplicateEmailIdException;
import com.auth.Signing.Exceptions.WrongOtpException;
import com.auth.Signing.Project_Login.Repo.UserRepository;

import com.auth.Signing.Project_Login.entity.Provider;
import com.auth.Signing.Project_Login.entity.User;
import com.auth.Signing.Project_Login.entity.UserLogin;
import com.auth.Signing.Project_Login.entity.otpVerif;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Service
public class ControllerServicesImpl implements ControllerServices {
    @Autowired
    UserRepository
            userRepository;
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    myUserDetailsService myuds;
   public void sendVerificationEmail(User user) throws UnsupportedEncodingException, MessagingException {
        String toAddress = user.getEmailid();
        String fromAddress = "tbillerportal@gmail.com";
        String senderName = "Telstra Billfinity";
        String subject = "Please verify your registration with Telstra's Billfinity";
        String content = "Dear [[name]],<br>"
                + "<br>"
                + "Please verify your address using this verification code: " + user.getVerificationCode()
                +"<br><br><br><br> Do not share this with anybody!  "
                + "Thank you, Regards,  <br>"
                + "Telstra Billfinity.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getFirstname()+ " "+ user.getLastname());

        helper.setText(content, true);

        mailSender.send(message);
    }

    @Override
    public void verifyotp(otpVerif otp) {

        Optional<User> verified = userRepository.findByVerificationCode(otp.getOtp());
        verified.orElseThrow(() -> new WrongOtpException("  "));
        System.err.println("Correct otp? "+ otp.getOtp().equalsIgnoreCase(verified.get().getVerificationCode()) );
        System.err.println("User " + verified.get().getEmailid());
        if (otp.getOtp().equalsIgnoreCase(verified.get().getVerificationCode())) {
            verified.get().setVerificationCode(null);
            verified.get().setEnabled(true);
            userRepository.save(verified.get());
        }
        else{
            throw new WrongOtpException("");
        }
    }


    @Override
    public void processregistration(User user) throws MessagingException, UnsupportedEncodingException {
        Optional<User> optionalUser = userRepository.findByLocalEmailid(user.getEmailid());
        if (optionalUser.isEmpty()) {


            String randomCode = RandomStringUtils.randomAlphanumeric(7);
            System.out.println(randomCode+ "$$$") ;
            user.setVerificationCode(randomCode);
            user.setEnabled(false);

            user.setProvider(Provider.local);
            userRepository.save(user);
            sendVerificationEmail(user);
        } else {
            if (!optionalUser.get().isEnabled()){
                String randomCode = RandomStringUtils.randomAlphanumeric(7);
                System.err.println(randomCode+ "$$$") ;
                user.setVerificationCode(randomCode);
                user.setEnabled(false);
                userRepository.delete(optionalUser.get());
                user.setProvider(Provider.local);
                userRepository.save(user);
                sendVerificationEmail(user);
            }
            else {
                throw new DuplicateEmailIdException("600", "This Email Id already exists!");
            }
        }
    }
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String processlogin(UserLogin userLogin) throws BadCredentialsException, DisabledException {
        SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLogin.getEmailid_login(),
                            userLogin.getPassword_login()
                    )));


        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof CustomOath2User) {
            CustomOath2User curruser = (CustomOath2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.err.println("returning" + curruser.getName());
            return curruser.getName();
        } else if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof myUserDetails) {
            myUserDetails curruser = (myUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.err.println("returning" + curruser.getPrincipalName());
            return curruser.getPrincipalName();
        }
        return  "Stranger";
    }



}

