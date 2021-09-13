package com.auth.Signing;

import com.auth.Signing.Project_Login.LoginServices.ControllerServicesImpl;
import com.auth.Signing.Project_Login.Repo.UserRepository;
import com.auth.Signing.Project_Login.entity.Provider;
import com.auth.Signing.Project_Login.entity.User;
import com.auth.Signing.Project_Login.entity.UserLogin;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;


@AutoConfigureMockMvc
@SpringBootTest
public class LoginControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ControllerServicesImpl controllerServices;

    @Autowired
    UserRepository userRepository;

    @Test
    void testForSetupWithHeader() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/setup").header("Authorization","Basic "+Base64.getEncoder().encodeToString("Abhijeet.Debadwar@team.telstra.com@Google:ok".getBytes())).contentType(MediaType.APPLICATION_JSON).content(""))
                .andExpect(MockMvcResultMatchers
                        .status().is2xxSuccessful());
    }

    @Test
    void testForProcessLogin(){
        UserLogin userLogin = new UserLogin();
        userLogin.setPassword_login("123456");
        userLogin.setEmailid_login("abhijeetdebadwar123@gmail.com");
        Assertions.assertThat(controllerServices.processlogin(userLogin)!="Stranger");
    }

    @Test
    void testForProcessRegistration() throws MessagingException, UnsupportedEncodingException {

        User user = new User();
        user.setEmailid("asba@hdbsh");
        user.setFirstname("tester");

        userRepository.delete(user);

        controllerServices.processregistration(user);

        Assertions.assertThat(user.getVerificationCode()).isNotNull();

        userRepository.delete(user);
    }

    @Test
    void userloading() {
        List<User> ulist = userRepository.findAll();
        int initsize = ulist.size();
        User u = new User();
        u.setEnabled(true);
        u.setPassword("1234");
        u.setProvider(Provider.local);
        u.setEmailid("w@gmail.com");
        u.setFirstname("Jack");
        u.setLastname("Hol");
        userRepository.save(u);
        List<User> newulist = userRepository.findAll();
        Assertions.assertThat(newulist.size()>initsize);
        userRepository.delete(u);
    }

}
