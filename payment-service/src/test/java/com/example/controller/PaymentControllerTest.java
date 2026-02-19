package com.example.controller;

import com.example.dto.PaymentRequest;
import com.example.dto.PaymentResponse;
import com.example.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PaymentController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PaymentService paymentService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void createTest() throws Exception {
        PaymentRequest request = new PaymentRequest("test1", new BigDecimal(1000));
        PaymentResponse response = new PaymentResponse(1L, "test1",  new BigDecimal(1000),
                "CREATED", OffsetDateTime.now());
        Mockito.when(paymentService.createPayment(request)).thenReturn(response);
        mockMvc.perform(post("/api/v1/payments")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.orderId").value("test1"))
                .andExpect(jsonPath("$.amount").value( new BigDecimal(1000)))
                .andExpect(jsonPath("$.status").value("CREATED"));
    }
}
