package com.yashasvi.product.services;

import com.yashasvi.product.dtos.FakeStoreProductDto;
import com.yashasvi.product.models.Category;
import com.yashasvi.product.models.Product;

public class ProductConverter {

    static FakeStoreProductDto convertProductToFakeStoreProduct(Product product) {
        FakeStoreProductDto fakeStoreProduct = new FakeStoreProductDto();
        if (product.getId() != null)
            fakeStoreProduct.setId(product.getId());
        if (product.getTitle() != null)
            fakeStoreProduct.setTitle(product.getTitle());
        if (product.getPrice() != null)
            fakeStoreProduct.setPrice(product.getPrice());
        if (product.getDescription() != null)
            fakeStoreProduct.setDescription(product.getDescription());
        if (product.getImageUrl() != null)
            fakeStoreProduct.setImage(product.getImageUrl());
        Category category = product.getCategory();
        if (category != null)
            fakeStoreProduct.setCategory(category.getName());
        return fakeStoreProduct;
    }

    static Product convertFakeStoreProductToProduct(FakeStoreProductDto fakeStoreProduct) {
        Product product = new Product();
        product.setTitle(fakeStoreProduct.getTitle());
        product.setId(fakeStoreProduct.getId());
        product.setPrice(fakeStoreProduct.getPrice());
        product.setDescription(fakeStoreProduct.getDescription());
        product.setImageUrl(fakeStoreProduct.getImage());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProduct.getCategory());

        return product;
    }
}
