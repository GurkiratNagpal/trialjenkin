package com.auth.Signing;

import com.auth.Signing.BillingPackage.portaldb.entity.Broadband;
import com.auth.Signing.BillingPackage.portaldb.repository.BroadbandRepository;
import com.auth.Signing.BillingPackage.portaldb.service.BroadbandService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest
public class BroadBandServiceTests {

    @Autowired
    BroadbandService broadbandService;



    @Autowired
    BroadbandRepository broadbandRepository;


    @Test
    void listallbroadbandtest() throws Exception{

        List<Broadband> broadbandList = broadbandService.listAll();
        Assertions.assertThat(broadbandList).size().isGreaterThan(0);
    }

    @Test
    void broadbandStoring() throws Exception{
        int oldsize   = broadbandService.listAll().size();
        Broadband b = new Broadband();
        b.setCompanyid(20);
        b.setData_consumption(100);
        b.setMonth("nov");
        b.setStatus("pending");
        b.setYear(2020);
        broadbandService.save(b);
        int newsize   = broadbandService.listAll().size();
        Assertions.assertThat(newsize>oldsize);
        broadbandRepository.delete(b);

    }
}
