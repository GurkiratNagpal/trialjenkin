package com.auth.Signing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Base64;

@AutoConfigureMockMvc
@SpringBootTest
public class MainControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testForAllComp() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/portal/companiesAll").contentType(MediaType.APPLICATION_JSON).content(""))
                .andExpect(MockMvcResultMatchers
                        .status().is4xxClientError());
    }

    @Test
    void testForDelayed() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/portal/delayed").contentType(MediaType.APPLICATION_JSON).content(""))
                .andExpect(MockMvcResultMatchers
                        .status().is4xxClientError());
    }

    @Test
    void testForTopBilled() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/portal/topbilled").contentType(MediaType.APPLICATION_JSON).content(""))
                .andExpect(MockMvcResultMatchers
                        .status().is4xxClientError());
    }

    @Test
    void testForUnpaid() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/portal/unpaid").contentType(MediaType.APPLICATION_JSON).content(""))
                .andExpect(MockMvcResultMatchers
                        .status().is4xxClientError());
    }

    @Test
    void testForAllCompWithHeader() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/portal/companiesAll").header("Authorization","Basic "+ Base64.getEncoder().encodeToString("Abhijeet.Debadwar@team.telstra.com@Google:ok".getBytes())).contentType(MediaType.APPLICATION_JSON).content(""))
                .andExpect(MockMvcResultMatchers
                        .status().is2xxSuccessful());
    }

    @Test
    void testForDelayedWithHeader() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/portal/delayed").header("Authorization","Basic "+ Base64.getEncoder().encodeToString("Abhijeet.Debadwar@team.telstra.com@Google:ok".getBytes())).contentType(MediaType.APPLICATION_JSON).content(""))
                .andExpect(MockMvcResultMatchers
                        .status().is2xxSuccessful());
    }

    @Test
    void testForTopbilledWithHeader() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/portal/topbilled").header("Authorization","Basic "+Base64.getEncoder().encodeToString("Abhijeet.Debadwar@team.telstra.com@Google:ok".getBytes())).contentType(MediaType.APPLICATION_JSON).content(""))
                .andExpect(MockMvcResultMatchers
                        .status().is2xxSuccessful());
    }

    @Test
    void testForUnpaidWithHeader() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/portal/unpaid").header("Authorization","Basic "+Base64.getEncoder().encodeToString("Abhijeet.Debadwar@team.telstra.com@Google:ok".getBytes())).contentType(MediaType.APPLICATION_JSON).content(""))
                .andExpect(MockMvcResultMatchers
                        .status().is2xxSuccessful());
    }
}
