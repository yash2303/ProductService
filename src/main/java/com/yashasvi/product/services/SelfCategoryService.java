package com.yashasvi.product.services;

import com.yashasvi.product.models.Category;
import com.yashasvi.product.models.Product;
import com.yashasvi.product.repositories.CategoryRepository;
import com.yashasvi.product.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfCategoryService")
public class SelfCategoryService implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public SelfCategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Product> getProductsInCategory(String name) {
        return productRepository.findAllByCategory_name(name);
    }
}
