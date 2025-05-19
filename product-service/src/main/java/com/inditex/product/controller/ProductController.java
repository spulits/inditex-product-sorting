package com.inditex.product.controller;

import com.inditex.product.dto.ProductRequest;
import com.inditex.product.dto.ProductResponse;
import com.inditex.product.model.Product;
import com.inditex.product.service.ProductService;
import com.inditex.product.util.ProductMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(ProductMapper.toResponseList(products));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        return productService.findById(id)
                .map(ProductMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/ids")
    public ResponseEntity<List<ProductResponse>> getProductsByIds(@RequestParam("ids") List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<Product> products = productService.findByIdIn(ids);
        return ResponseEntity.ok(ProductMapper.toResponseList(products));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        Product product = ProductMapper.toEntity(productRequest);
        Product savedProduct = productService.save(product);
        ProductResponse response = ProductMapper.toResponse(savedProduct);
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.getId())
                .toUri();
                
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest productRequest) {
        
        return productService.findById(id)
                .map(product -> {
                    ProductMapper.updateEntity(productRequest, product);
                    Product updatedProduct = productService.save(product);
                    return ResponseEntity.ok(ProductMapper.toResponse(updatedProduct));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (!productService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
