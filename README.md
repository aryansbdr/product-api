# Product API

API RESTful yang kuat untuk mengelola produk yang dibangun dengan Spring Boot. Aplikasi ini menyediakan endpoint untuk membuat, membaca, memperbarui, dan menghapus informasi produk, termasuk validasi dan penanganan error.

## Tujuan Proyek

Product API ini dirancang untuk mendukung sistem manajemen produk dalam aplikasi e-commerce, inventory management, atau platform penjualan online. API ini memungkinkan integrasi yang mudah dengan frontend aplikasi atau sistem lain untuk mengelola katalog produk secara efisien.

## Struktur Proyek

```
src/
├── main/
│   ├── java/com/example/product_api/
│   │   ├── ProductApiApplication.java          # Kelas aplikasi utama
│   │   ├── controller/
│   │   │   └── ProductController.java          # Endpoint REST API
│   │   ├── dto/
│   │   │   ├── ProductRequest.java             # DTO Request
│   │   │   └── ProductResponse.java            # DTO Response
│   │   ├── model/
│   │   │   └── Product.java                    # Model entitas
│   │   ├── repository/
│   │   │   └── ProductRepository.java          # Lapisan akses data
│   │   └── service/
│   │       └── ProductService.java             # Lapisan logika bisnis
│   └── resources/
│       └── application.properties              # Konfigurasi
└── test/
    └── java/com/example/product_api/           # Kelas test
```


## Fitur

- **Operasi CRUD**: Fungsi lengkap Create, Read, Update, Delete untuk produk
- **Validasi Data**: Validasi input komprehensif untuk data produk
- **Penanganan Error**: Kode status HTTP yang tepat dan respons error
- **Integrasi Database**: JPA/Hibernate dengan database H2 in-memory untuk development
- **Unit Testing**: Cakupan test komprehensif untuk controller, service, dan repository
- **Desain RESTful**: API REST bersih mengikuti praktik terbaik

## Teknologi yang Digunakan

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **H2 Database** (untuk development/testing)
- **Maven** (alat build)
- **JUnit 5** (framework testing)
- **Mockito** (framework mocking)

## Prasyarat

- Java 17 atau lebih tinggi
- Maven 3.6 atau lebih tinggi

## Instalasi dan Setup

1. **Clone repository:**
   ```bash
   git clone <repository-url>
   cd product-api
   ```

2. **Build proyek:**
   ```bash
   ./mvnw clean compile
   ```

3. **Jalankan aplikasi:**
   ```bash
   ./mvnw spring-boot:run
   ```

Aplikasi akan berjalan di `http://localhost:8080`

## Endpoint API

### Produk

| Method | Endpoint | Deskripsi |
|--------|----------|-----------|
| GET | `/api/products` | Mengambil semua produk |
| GET | `/api/products/{id}` | Mengambil produk spesifik berdasarkan ID |
| POST | `/api/products` | Membuat produk baru |
| PUT | `/api/products/{id}` | Memperbarui produk yang ada |
| DELETE | `/api/products/{id}` | Menghapus produk |

### Contoh Request/Response

#### Membuat Produk (POST /api/products)
```json
{
  "name": "Laptop",
  "price": 999.99,
  "stock": 50
}
```

#### Respons Produk
```json
{
  "id": 1,
  "name": "Laptop",
  "price": 999.99,
  "stock": 50,
  "createdAt": "2024-01-01T10:00:00Z",
  "updatedAt": "2024-01-01T10:00:00Z"
}
```

## Aturan Validasi

- **Nama**: Wajib diisi, tidak boleh kosong, maksimal 100 karakter
- **Harga**: Wajib diisi, harus positif
- **Stok**: Wajib diisi, harus tidak negatif

## Testing

Jalankan suite test:
```bash
./mvnw test
```

Proyek ini mencakup unit test untuk:
- ProductController
- ProductService
- ProductRepository

## Konfigurasi Database

Aplikasi menggunakan database H2 in-memory secara default. Konfigurasi database dapat ditemukan di `src/main/resources/application.properties`.

Untuk deployment production, konfigurasikan database persistent (MySQL, PostgreSQL, dll.) di properti aplikasi.

