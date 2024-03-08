package com.yashasvi.product.controllers;

import com.yashasvi.product.exceptions.ProductNotFoundException;
import com.yashasvi.product.models.Product;
import com.yashasvi.product.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;
    @MockBean(name = "selfProductService")
    private ProductService productService;

    @Test
    void testGetAllProducts() {
        Product p1 = Product.builder()
                .title("Product 1")
                .description("Description 1")
                .price(100.0)
                .build();
        Product p2 = Product.builder()
                .title("Product 2")
                .description("Description 2")
                .price(200.0)
                .build();
        when(productService.getAllProducts(0, 2, "id", "asc")).thenReturn(new PageImpl<>(List.of(p1, p2)));

        Page<Product> products = productController.getAllProducts(0, 2, "id", "asc").getBody();

        Assertions.assertNotNull(products);
        Assertions.assertArrayEquals(new Product[]{p1, p2}, products.stream().toArray());
    }

    @Test
    void testGetSingleProduct() throws ProductNotFoundException {
        Product p1 = Product.builder()
                .title("Product 1")
                .description("Description 1")
                .price(100.0)
                .build();
        when(productService.getSingleProduct(1L)).thenReturn(p1);

        Product product = productController.getSingleProduct(1L).getBody();

        Assertions.assertNotNull(product);
        Assertions.assertEquals(p1, product);
    }

    @Test
    void testGetSingleProductNotFound() throws ProductNotFoundException {
        when(productService.getSingleProduct(1L)).thenThrow(new ProductNotFoundException("Product with id 1 not found"));

        Assertions.assertThrows(ProductNotFoundException.class, () -> productController.getSingleProduct(1L));
    }
}