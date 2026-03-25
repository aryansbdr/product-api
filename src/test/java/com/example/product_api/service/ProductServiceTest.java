package com.example.product_api.service;

import com.example.product_api.dto.ProductRequest;
import com.example.product_api.dto.ProductResponse;
import com.example.product_api.model.Product;
import com.example.product_api.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testGetAllProducts() {
        // Mempersiapkan data mock
        Product product1 = new Product(1L, "Laptop", 15000000.0, 5);
        Product product2 = new Product(2L, "Mouse", 150000.0, 20);

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        // Memanggil method yang diuji
        List<ProductResponse> responses = productService.getAllProducts();

        // Memverifikasi hasil
        assertThat(responses).hasSize(2);
        assertThat(responses.get(0).getName()).isEqualTo("Laptop");
        assertThat(responses.get(1).getName()).isEqualTo("Mouse");

        // Memverifikasi bahwa repository dipanggil
        verify(productRepository).findAll();
    }

    @Test
    public void testCreateProduct() {
        // Mempersiapkan data mock
        ProductRequest request = new ProductRequest();
        request.setName("Keyboard");
        request.setPrice(500000.0);
        request.setStock(15);

        Product savedProduct = new Product(1L, "Keyboard", 500000.0, 15);

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        // Memanggil method yang diuji
        ProductResponse response = productService.createProduct(request);

        // Memverifikasi hasil
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getName()).isEqualTo("Keyboard");
        assertThat(response.getPrice()).isEqualTo(500000.0);
        assertThat(response.getStock()).isEqualTo(15);

        // Memverifikasi bahwa repository dipanggil
        verify(productRepository).save(any(Product.class));
    }
}