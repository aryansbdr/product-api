package com.example.product_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.product_api.dto.ProductRequest;
import com.example.product_api.dto.ProductResponse;
import com.example.product_api.model.Product;
import com.example.product_api.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // 1. Get All Products
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // 2. Get Product by ID (Baru)
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product tidak ditemukan dengan ID: " + id));
        return convertToResponse(product);
    }

    // 3. Create Product
    public ProductResponse createProduct(ProductRequest request) {
        // Membuat entity dari request
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        // Menyimpan ke database
        Product savedProduct = productRepository.save(product);

        // Mengubah ke response DTO
        return convertToResponse(savedProduct);
    }

    // 4. Update Product (Baru)
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        // Cari produk berdasarkan ID terlebih dahulu
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product tidak ditemukan dengan ID: " + id));

        // Update data produk dengan data baru dari request
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        // Simpan perubahan ke database
        Product updatedProduct = productRepository.save(product);

        return convertToResponse(updatedProduct);
    }

    // 5. Delete Product (Baru)
    public void deleteProduct(Long id) {
        // Pastikan produk ada sebelum dihapus
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product tidak ditemukan dengan ID: " + id));
        
        productRepository.delete(product);
    }

    // Helper method untuk mengubah Entity ke Response DTO
    private ProductResponse convertToResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock());
    }
}