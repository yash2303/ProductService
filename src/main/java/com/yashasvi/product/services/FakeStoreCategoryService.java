package com.yashasvi.product.services;

import com.yashasvi.product.dtos.FakeStoreProductDto;
import com.yashasvi.product.models.Category;
import com.yashasvi.product.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service("fakeStoreCategoryService")
public class FakeStoreCategoryService implements CategoryService {
    private final RestTemplate restTemplate;

    public FakeStoreCategoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Category> getAllCategories() {
        String[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/categories",
                String[].class
        );
        return response != null ? Arrays.stream(response)
                .map(this::buildCategory)
                .toList() : Collections.emptyList();
    }

    @Override
    public List<Product> getProductsInCategory(String name) {
        FakeStoreProductDto[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/category/" + name,
                FakeStoreProductDto[].class
        );
        return response != null ? Arrays.stream(response)
                .map(ProductConverter::convertFakeStoreProductToProduct)
                .toList() : Collections.emptyList();
    }

    private Category buildCategory(String name) {
        var category = new Category();
        category.setName(name);
        return category;
    }
}
