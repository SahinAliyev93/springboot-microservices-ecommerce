package com.example.controller;

import com.example.dto.InventoryRequest;
import com.example.dto.InventoryResponse;
import com.example.service.InventoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.OffsetDateTime;



@WebMvcTest(InventoryController.class)
@AutoConfigureMockMvc(addFilters = false)
public class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InventoryService inventoryService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void addInventoryTest() throws  Exception{
        InventoryRequest request = new InventoryRequest("test1", 100);
        InventoryResponse response = new InventoryResponse(1L,"test1", 100,
                OffsetDateTime.now(), OffsetDateTime.now());
        Mockito.when(inventoryService.addInventory(request)).thenReturn(response);
        mockMvc.perform(post("/api/v1/inventory")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.productCode").value("test1"))
                .andExpect(jsonPath("$.availableQuantity").value(100));
    }
}
