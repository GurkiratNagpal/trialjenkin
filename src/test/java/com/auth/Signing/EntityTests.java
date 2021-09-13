package com.auth.Signing;

import com.auth.Signing.BillingPackage.portaldb.entity.SearchContent;
import com.auth.Signing.Project_Login.entity.otpVerif;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureMockMvc
@SpringBootTest
public class EntityTests {

    @Test
    void testForSearchContent(){
        SearchContent sc = new SearchContent();
        sc.setSearchContent("Abhijeet");
        Assertions.assertThat(sc.getSearchContent()!=null);
    }

    @Test
    void testFOrOtpVerf(){
        otpVerif verif = new otpVerif();
        verif.setOtp("djdbjd");
        verif.setUsermail("sas@dfdsf");
        Assertions.assertThat(verif.getOtp()!=null);
        Assertions.assertThat(verif.getUsermail()!=null);
    }
}
