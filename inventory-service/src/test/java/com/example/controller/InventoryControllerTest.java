package com.example.controller;

import com.example.dto.InventoryRequest;
import com.example.dto.InventoryResponse;
import com.example.service.InventoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import javax.print.DocFlavor;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import java.time.OffsetDateTime;



@WebMvcTest(InventoryController.class)
@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
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
        ConstrainedFields fields = new ConstrainedFields(InventoryRequest.class);
        Mockito.when(inventoryService.addInventory(request)).thenReturn(response);
        mockMvc.perform(post("/api/v1/inventory")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.productCode").value("test1"))
                .andExpect(jsonPath("$.availableQuantity").value(100))
                .andDo(document("inventory-create",
                        requestFields(
                                fields.withPath("productCode","String")
                                        .description("Product Code"),
                                fields.withPath("availableQuantity","Integer")
                                        .description("Stock Quantity")
                        ),
                        responseFields(
                                fieldWithPath("id").description("Inventory ID"),
                                fieldWithPath("productCode").description("Product Code"),
                                fieldWithPath("availableQuantity").description("Stock Quantity"),
                                fieldWithPath("createdAt").description("Creation Time"),
                                fieldWithPath("lastModifiedDate").description("last Modified Time")
                        )
                ));;
    }

    private static class ConstrainedFields {

        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path,String type) {
            return fieldWithPath(path).attributes(key("type").value(type),key("constraints").value(StringUtils
                    .collectionToDelimitedString(this.constraintDescriptions
                            .descriptionsForProperty(path), ". ")));
        }
    }
}
