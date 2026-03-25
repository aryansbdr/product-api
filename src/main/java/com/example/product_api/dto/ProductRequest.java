package com.example.product_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank(message = "Nama produk tidak boleh kosong")
    private String name;

    @NotNull(message = "Harga tidak boleh kosong")
    @Positive(message = "Harga harus lebih besar dari 0")
    private Double price;

    @NotNull(message = "Stok tidak boleh kosong")
    @PositiveOrZero(message = "Stok tidak boleh negatif")
    private Integer stock;
}