package com.auth.Signing.Project_Login.LoginServices;

import com.auth.Signing.Exceptions.SocialDetailsInaccessible;
import com.auth.Signing.Project_Login.Repo.UserRepository;
import com.auth.Signing.Project_Login.entity.Provider;
import com.auth.Signing.Project_Login.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OauthService {

    private @Getter @Setter String curremail;
    @Autowired
    UserRepository repo;

    public void processOAuthPostLogin(CustomOath2User customOath2User) throws SocialDetailsInaccessible {

        if (customOath2User.getEmail()==null|| customOath2User.getName()==null){
            throw new SocialDetailsInaccessible("600","Your EmailId and Password are not Accessible");
        }

        Optional<User> existUser = repo.findByEmailid(customOath2User.getEmail()+"@"+customOath2User.getProviderName());
        curremail=customOath2User.getEmail()+"@"+customOath2User.getProviderName();
        if(existUser.isEmpty()){
                User newUser = new User();
                newUser.setEmailid(customOath2User.getEmail()+"@"+customOath2User.getProviderName());
                newUser.setFirstname(customOath2User.getName());
                newUser.setEnabled(true);
                newUser.setPassword("ok");
                newUser.setProvider(Provider.valueOf(customOath2User.getProviderName()));
                System.err.println("saving new user");
                repo.save(newUser);

                }
    }
}