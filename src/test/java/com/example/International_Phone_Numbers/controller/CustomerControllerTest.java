package com.example.International_Phone_Numbers.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.io.File;
import java.nio.file.Files;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listCustomers() throws Exception {

        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void filterByCountry() throws Exception{
        this.mockMvc.perform(get("/country?country=Cameroon"))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    void filterByState() throws Exception{
        this.mockMvc.perform(get("/state?state=valid"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}