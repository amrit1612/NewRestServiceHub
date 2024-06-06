package com.Product.product.Controller;

import com.Product.product.Entity.Product;
import com.Product.product.Request.ProductRequest;
import com.Product.product.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping
    public ResponseEntity<Long>addProduct(@RequestBody ProductRequest productRequest){
        Long productId=productService.addProduct(productRequest);
        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<Product>getProductById(@PathVariable("id") long productId){
        Product productResponse=productService.getProductById(productId);
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }

    @GetMapping("all")
    public List<Product> getAll()
    {
        return productService.getAllProducts();
    }

}
