package com.yashasvi.product.services;

import com.yashasvi.product.dtos.FakeStoreProductDto;
import com.yashasvi.product.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.yashasvi.product.services.ProductConverter.convertFakeStoreProductToProduct;
import static com.yashasvi.product.services.ProductConverter.convertProductToFakeStoreProduct;

@Service
public class FakeStoreProductService implements ProductService {
    private final RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long id) {
        FakeStoreProductDto productDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class
        );

        return productDto != null ? convertFakeStoreProductToProduct(productDto) : null;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] productDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );

        return productDtos != null ? Arrays.stream(productDtos)
                .map(ProductConverter::convertFakeStoreProductToProduct)
                .toList() : Collections.emptyList();
    }

    @Override
    public Product addNewProduct(Product product) {
        FakeStoreProductDto fakeStoreProduct = convertProductToFakeStoreProduct(product);
        FakeStoreProductDto response = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                fakeStoreProduct,
                FakeStoreProductDto.class
        );
        return response != null ? convertFakeStoreProductToProduct(response) : null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProduct = convertProductToFakeStoreProduct(product);
        FakeStoreProductDto response = restTemplate.patchForObject(
                "https://fakestoreapi.com/products/" + id,
                fakeStoreProduct,
                FakeStoreProductDto.class
        );
        return response != null ? convertFakeStoreProductToProduct(response) : null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = convertProductToFakeStoreProduct(product);
//        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
//                "https://fakestoreapi.com/products/" + id,
//                HttpMethod.PUT,
//                fakeStoreProductDto,
//                FakeStoreProductDto.class
//        );
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        restTemplate.delete("https://fakestoreapi.com/products/" + id);
    }
}
