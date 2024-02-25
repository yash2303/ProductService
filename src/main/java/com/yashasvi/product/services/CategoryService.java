package com.yashasvi.product.services;

import com.yashasvi.product.models.Category;
import com.yashasvi.product.models.Product;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    List<Product> getProductsInCategory(String name);
}
