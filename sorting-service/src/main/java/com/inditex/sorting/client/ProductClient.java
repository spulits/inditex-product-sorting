package com.inditex.sorting.client;

import com.inditex.sorting.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "product-service", url = "${product.service.url}")
public interface ProductClient {
    
    @GetMapping("/api/products")
    List<Product> getAllProducts();
    
    @GetMapping("/api/products/{id}")
    Product getProductById(@PathVariable("id") Long id);
    
    @GetMapping("/api/products/ids")
    List<Product> getProductsByIds(@RequestParam("ids") List<Long> ids);
}