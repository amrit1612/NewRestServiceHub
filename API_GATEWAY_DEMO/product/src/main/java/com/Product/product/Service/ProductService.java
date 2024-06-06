package com.Product.product.Service;

import com.Product.product.Entity.Product;
import com.Product.product.Request.ProductRequest;
import com.Product.product.Response.ProductResponse;

import java.util.List;

public interface ProductService {

    long addProduct(ProductRequest productRequest);
    Product getProductById(long productId);
    void reduceQuantity(long productId,long quantity);
    public void deleteProductById(long productId);
    public List<Product> getAllProducts();
}
