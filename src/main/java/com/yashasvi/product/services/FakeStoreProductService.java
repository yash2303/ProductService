package com.yashasvi.product.services;

import com.yashasvi.product.dtos.FakeStoreProductDto;
import com.yashasvi.product.exceptions.ProductNotFoundException;
import com.yashasvi.product.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.yashasvi.product.services.ProductConverter.convertFakeStoreProductToProduct;
import static com.yashasvi.product.services.ProductConverter.convertProductToFakeStoreProduct;

@Service
public class FakeStoreProductService implements ProductService {
    public static final String FAKE_STORE_PRODUCT_ENDPOINT = "https://fakestoreapi.com/products/";
    private final RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        FakeStoreProductDto productDto = restTemplate.getForObject(
                FAKE_STORE_PRODUCT_ENDPOINT + id,
                FakeStoreProductDto.class
        );
        if (productDto == null) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }

        return convertFakeStoreProductToProduct(productDto);
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] productDtos = restTemplate.getForObject(
                FAKE_STORE_PRODUCT_ENDPOINT,
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
                FAKE_STORE_PRODUCT_ENDPOINT,
                fakeStoreProduct,
                FakeStoreProductDto.class
        );
        return response != null ? convertFakeStoreProductToProduct(response) : null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProduct = convertProductToFakeStoreProduct(product);
        FakeStoreProductDto response = restTemplate.patchForObject(
                FAKE_STORE_PRODUCT_ENDPOINT + id,
                fakeStoreProduct,
                FakeStoreProductDto.class
        );
        return response != null ? convertFakeStoreProductToProduct(response) : null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = convertProductToFakeStoreProduct(product);
        RequestEntity<FakeStoreProductDto> fakeStoreProductDtoHttpEntity = new RequestEntity<>(
                fakeStoreProductDto,
                HttpMethod.PUT,
                URI.create(FAKE_STORE_PRODUCT_ENDPOINT + id));
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                fakeStoreProductDtoHttpEntity,
                FakeStoreProductDto.class
        );
        return response.getBody() != null ? convertFakeStoreProductToProduct(response.getBody()) : null;
    }

    @Override
    public void deleteProduct(Long id) {
        restTemplate.delete(FAKE_STORE_PRODUCT_ENDPOINT + id);
    }
}
