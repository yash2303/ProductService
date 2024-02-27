package com.yashasvi.product.controllers;

import com.yashasvi.product.models.Category;
import com.yashasvi.product.models.Product;
import com.yashasvi.product.services.CategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(@Qualifier("selfCategoryService") CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{name}/products")
    public ResponseEntity<List<Product>> getProductsInCategory(@PathVariable String name) {
        return ResponseEntity.ok(categoryService.getProductsInCategory(name));
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}
