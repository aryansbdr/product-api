package com.example.product_api.controller;

import com.example.product_api.dto.ProductRequest;
import com.example.product_api.dto.ProductResponse;
import com.example.product_api.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    @SuppressWarnings("unused")
    private MockMvc mockMvc; 

    @MockBean
    private ProductService productService; 

    @Autowired
    private ObjectMapper objectMapper; 

    @Test
    public void testGetAllProducts() throws Exception {
        // Mempersiapkan data mock [cite: 755]
        ProductResponse product1 = new ProductResponse(1L, "Laptop", 15000000.0, 5);
        ProductResponse product2 = new ProductResponse(2L, "Mouse", 150000.0, 20);
        List<ProductResponse> products = Arrays.asList(product1, product2);

        when(productService.getAllProducts()).thenReturn(products); 

        // Melakukan permintaan GET dan verifikasi respons 
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk()) 
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) 
                .andExpect(jsonPath("$[0].name").value("Laptop")) 
                .andExpect(jsonPath("$[1].name").value("Mouse")); 
    }

    @Test
    public void testCreateProduct() throws Exception {
        // Mempersiapkan data mock request 
        ProductRequest request = new ProductRequest();
        request.setName("Keyboard");
        request.setPrice(500000.0);
        request.setStock(15);

        // Mempersiapkan data mock response 
        ProductResponse response = new ProductResponse(1L, "Keyboard", 500000.0, 15);
        
        when(productService.createProduct(any(ProductRequest.class))).thenReturn(response); 

        // Melakukan permintaan POST dan verifikasi respons 
        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON) 
                .content(objectMapper.writeValueAsString(request))) 
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Keyboard")) 
                .andExpect(jsonPath("$.price").value(500000.0)); 
    }
}