package com.auth.Signing;

import com.auth.Signing.BillingPackage.portaldb.entity.Invoice;
import com.auth.Signing.BillingPackage.portaldb.repository.InvoiceRepository;
import com.auth.Signing.BillingPackage.portaldb.service.InvoiceService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest
public class InvoiceServiceTests {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Test
    void gettopbilledtest(){
        List<Object[]> companyList = invoiceService.getTopBilled();
        Assertions.assertThat(companyList.size()==5);
    }

    @Test
    void invInsertData(){

        invoiceRepository.deleteAll();

        invoiceService.insertdata();

        Assertions.assertThat(invoiceRepository.findAll().size()>0);

        List<Invoice> invList = invoiceRepository.findAll();
        Assertions.assertThat(invList).size().isGreaterThan(0);

    }

    @Test
    void invGetUnpaid() {

        Assertions.assertThat(invoiceService.getUnpaid().size()>0);
    }

}
