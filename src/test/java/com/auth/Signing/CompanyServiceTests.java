package com.auth.Signing;

import com.auth.Signing.BillingPackage.portaldb.entity.Broadband;
import com.auth.Signing.BillingPackage.portaldb.entity.Company;
import com.auth.Signing.BillingPackage.portaldb.entity.Mobile;
import com.auth.Signing.BillingPackage.portaldb.repository.CompanyRepository;
import com.auth.Signing.BillingPackage.portaldb.service.BroadbandService;
import com.auth.Signing.BillingPackage.portaldb.service.CompanyService;
import com.auth.Signing.BillingPackage.portaldb.service.MobileService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest
public class CompanyServiceTests {

    @Autowired
    CompanyService companyService;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    MobileService mobileService;

    @Autowired
    BroadbandService broadbandService;

    @Test
    void testforcs(){
        Company c = new Company();

        int oldsize = companyService.listAll().size();

        c.setApple(1);
        c.setBroadband(1);
        c.setCompanyname("abhijeet");
        c.setCompany_id(100);
        c.setFixed(1);
        c.setNokia(1);
        c.setSamsung(1);
        c.setOneplus(1);

        companyService.save(c);


        int newsize = companyService.listAll().size();

        Assertions.assertThat(newsize>oldsize);
    }

    @Test
    void readAll() {

        List<Company> companyList = companyRepository.findAll();
        Assertions.assertThat(companyList).size().isGreaterThan(0);
    }

    @Test
    void allgetTest(){
        Company c = companyService.get(2);
        Broadband b = broadbandService.get(5);
        Mobile m = mobileService.get(5);

        Assertions.assertThat(c!=null);
        Assertions.assertThat(b!=null);
        Assertions.assertThat(m!=null);
    }
}
