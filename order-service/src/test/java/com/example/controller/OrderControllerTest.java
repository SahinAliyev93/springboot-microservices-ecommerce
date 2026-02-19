package com.example.controller;


import com.example.dto.OrderRequest;
import com.example.dto.OrderResponse;
import com.example.service.OrderService;
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

@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc(addFilters = false)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderService orderService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void createTest() throws Exception {
        OrderRequest request = new OrderRequest("test1", 100, new BigDecimal(1000));
        OrderResponse response = new OrderResponse(1L, "test1", 100, new BigDecimal(1000),
                "CREATED", OffsetDateTime.now());
        Mockito.when(orderService.createOrder(request)).thenReturn(response);
        mockMvc.perform(post("/api/v1/orders")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.productCode").value("test1"))
                .andExpect(jsonPath("$.quantity").value(100))
                .andExpect(jsonPath("$.status").value("CREATED"));
    }
}
