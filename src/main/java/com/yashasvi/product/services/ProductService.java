package com.yashasvi.product.services;

import com.yashasvi.product.exceptions.ProductNotFoundException;
import com.yashasvi.product.models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long id) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product addNewProduct(Product product);

    Product updateProduct(Long id, Product product) throws ProductNotFoundException;

    Product replaceProduct(Long id, Product product);

    void deleteProduct(Long id);
}
