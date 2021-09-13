package com.auth.Signing;

import com.auth.Signing.Project_Login.LController.LoginController;
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

@AutoConfigureMockMvc
@SpringBootTest
public class AuthUnauthTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private LoginController controller;

    @Test
    void isAuthorizedAccess() throws Exception {
        User u = new User();
        u.setEnabled(true);
        u.setPassword("111111");
        u.setProvider(Provider.local);
        u.setEmailid("abc@gmail.com");
        u.setFirstname("Jack");
        u.setLastname("Hol");
        userRepository.save(u);
        UserLogin l= new UserLogin();
        l.setEmailid_login(u.getEmailid());
        l.setPassword_login(u.getPassword());
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/process_login").contentType(MediaType.APPLICATION_JSON).content("{\"emailid_login\": \"abc@gmail.com\", \"password_login\": \"111111\"}"))
                .andExpect(MockMvcResultMatchers
                        .status().isOk());
        userRepository.delete(u);
    }

    @Test
    void unauthorizedaccess() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/portal/search/company/o")).andExpect(MockMvcResultMatchers.status().isUnauthorized());

    }

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(controller).isNotNull();
    }
}
