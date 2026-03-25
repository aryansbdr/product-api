package com.example.product_api.repository;

import com.example.product_api.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testSaveAndFindProduct() {
        // Membuat objek product
        Product product = new Product();
        product.setName("Smartphone");
        product.setPrice(5000000.0);
        product.setStock(20);

        // Menyimpan product
        Product savedProduct = productRepository.save(product);

        // Memverifikasi product tersimpan
        assertThat(savedProduct.getId()).isNotNull();

        // Mencari product berdasarkan ID
        Optional<Product> found = productRepository.findById(savedProduct.getId());
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getName()).isEqualTo("Smartphone");
    }

    @Test
    public void testFindAllProducts() {
        // Membuat beberapa produk
        entityManager.persist(new Product(null, "Laptop", 15000000.0, 5));
        entityManager.persist(new Product(null, "Mouse", 150000.0, 50));

        // Mencari semua produk
        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(2);
    }
}