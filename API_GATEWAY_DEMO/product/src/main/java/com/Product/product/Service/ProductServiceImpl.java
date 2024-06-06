package com.Product.product.Service;

import com.Product.product.Entity.Product;
import com.Product.product.Exception.ProductCustomeException;
import com.Product.product.Repository.ProductRepository;
import com.Product.product.Request.ProductRequest;
import com.Product.product.Response.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    @Override
    public long addProduct(ProductRequest productRequest) {
        Product product= Product.builder()
                .productName(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .build();
        product=productRepository.save(product);
        return product.getProductId();
    }

    @Override
    public Product getProductById(long productId) {
        Product product=productRepository.findById(productId)
                .orElseThrow(
                        ()->new ProductCustomeException("Product with given Id not found ","Product_Not_Found"));
        return product;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {

    }

    @Override
    public void deleteProductById(long productId) {

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
