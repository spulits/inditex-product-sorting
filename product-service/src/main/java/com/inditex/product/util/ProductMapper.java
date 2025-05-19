package com.inditex.product.util;

import com.inditex.product.dto.ProductRequest;
import com.inditex.product.dto.ProductResponse;
import com.inditex.product.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {
    
    // Convert Product entity to ProductResponse DTO
    public static ProductResponse toResponse(Product product) {
        if (product == null) {
            return null;
        }
        
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setSalesUnits(product.getSalesUnits());
        response.setStockS(product.getStockS());
        response.setStockM(product.getStockM());
        response.setStockL(product.getStockL());
        
        return response;
    }
    
    // Convert ProductRequest DTO to Product entity
    public static Product toEntity(ProductRequest request) {
        if (request == null) {
            return null;
        }
        
        Product product = new Product(request.getName(), request.getSalesUnits(), request.getStockS(), request.getStockM(), request.getStockL());
       
        return product;
    }
    
    // Update existing Product entity with values from ProductRequest DTO
    public static void updateEntity(ProductRequest request, Product product) {
        if (request == null || product == null) {
            return;
        }
        
        product = new Product(request.getName(), request.getSalesUnits(), request.getStockS(), request.getStockM(), request.getStockL());
    }
    
    // Convert list of Product entities to list of ProductResponse DTOs
    public static List<ProductResponse> toResponseList(List<Product> products) {
        if (products == null) {
            return null;
        }
        
        return products.stream()
                .map(ProductMapper::toResponse)
                .collect(Collectors.toList());
    }
}
