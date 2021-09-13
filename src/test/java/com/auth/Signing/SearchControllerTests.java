package com.auth.Signing;

import com.auth.Signing.BillingPackage.portaldb.entity.Invoice;
import com.auth.Signing.BillingPackage.portaldb.repository.InvoiceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Base64;
import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest
public class SearchControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Test
    void testForCompanyName() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/portal/search/company/mydo").contentType(MediaType.APPLICATION_JSON).content(""))
                .andExpect(MockMvcResultMatchers
                        .status().is4xxClientError());
    }

    @Test
    void testForInvoice() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/portal/search/inv/10").contentType(MediaType.APPLICATION_JSON).content(""))
                .andExpect(MockMvcResultMatchers
                        .status().is4xxClientError());
    }

    @Test
    void testForInvoices() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/portal/search/compinv/mydo").contentType(MediaType.APPLICATION_JSON).content(""))
                .andExpect(MockMvcResultMatchers
                        .status().is4xxClientError());
    }

    @Test
    void testForMail() throws Exception {

        List<Invoice> myinvs = invoiceRepository.findAll();
        Long myinvid = myinvs.get(0).getId();

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/portal/inv/mail/"+myinvid.toString()).contentType(MediaType.APPLICATION_JSON).content(""))
                .andExpect(MockMvcResultMatchers
                        .status().is4xxClientError());
    }



    @Test
    void testForInvoiceWithHeader() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/portal/search/inv/10").header("Authorization","Basic "+ Base64.getEncoder().encodeToString("Abhijeet.Debadwar@team.telstra.com@Google:ok".getBytes())).contentType(MediaType.APPLICATION_JSON).content(""))
                .andExpect(MockMvcResultMatchers
                        .status().is2xxSuccessful());
    }


    @Test
    void testForCopanyWithHeader() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/portal/search/company/mydo").header("Authorization","Basic "+Base64.getEncoder().encodeToString("Abhijeet.Debadwar@team.telstra.com@Google:ok".getBytes())).contentType(MediaType.APPLICATION_JSON).content(""))
                .andExpect(MockMvcResultMatchers
                        .status().is2xxSuccessful());
    }

    @Test
    void testForInvoicesWithHeader() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/portal/search/compinv/mydo").header("Authorization","Basic "+Base64.getEncoder().encodeToString("Abhijeet.Debadwar@team.telstra.com@Google:ok".getBytes())).contentType(MediaType.APPLICATION_JSON).content(""))
                .andExpect(MockMvcResultMatchers
                        .status().is2xxSuccessful());
    }

    @Test
    void testForMailWithHeader() throws Exception {

        List<Invoice> myinvs = invoiceRepository.findAll();
        Long myinvid = myinvs.get(0).getId();

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/portal/inv/mail/"+myinvid.toString()).header("Authorization","Basic "+ Base64.getEncoder().encodeToString("Abhijeet.Debadwar@team.telstra.com@Google:ok".getBytes())).contentType(MediaType.APPLICATION_JSON).content(""))
                .andExpect(MockMvcResultMatchers
                        .status().is2xxSuccessful());
    }

}
