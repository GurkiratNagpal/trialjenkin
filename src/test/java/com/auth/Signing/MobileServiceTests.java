package com.auth.Signing;

import com.auth.Signing.BillingPackage.portaldb.entity.Mobile;
import com.auth.Signing.BillingPackage.portaldb.repository.MobileRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureMockMvc
@SpringBootTest
public class MobileServiceTests {

    @Autowired
    com.auth.Signing.BillingPackage.portaldb.service.MobileService mobileService;

    @Autowired
    MobileRepository mobileRepository;

    @Test
    void mobsaveDel(){
        int msize = mobileService.listAll().size();

        Mobile m = new Mobile();
        m.setCompanyid(100);
        m.setMonth("oct");
        m.setStatus("pending");
        m.setData_consumption(1);
        m.setYear(2020);
        m.setSms(1);
        m.setCall_duration(1);

        mobileService.save(m);

        int newsize = mobileService.listAll().size();

        Assertions.assertThat(newsize>msize);

        mobileRepository.delete(m);

    }
}
