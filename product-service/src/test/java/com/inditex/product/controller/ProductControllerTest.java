package com.inditex.product.controller;

import com.inditex.product.dto.ProductRequest;
import com.inditex.product.dto.ProductResponse;
import com.inditex.product.model.Product;
import com.inditex.product.service.ProductService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private Product product;
    private ProductRequest productRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/api/products");
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));   
        
        product = new Product(1L, "Test Product", 100, 10, 20, 30);

        productRequest = new ProductRequest();
        productRequest.setName("Test Product");
        productRequest.setSalesUnits(100);
        productRequest.setStockS(10);
        productRequest.setStockM(20);
        productRequest.setStockL(30);
    }

    @AfterEach
    void tearDown() {
        RequestContextHolder.resetRequestAttributes();
    }   

    @Test
    void getAllProducts_ShouldReturnListOfProducts() {
        // Arrange
        when(productService.findAll()).thenReturn(Arrays.asList(product));

        // Act
        ResponseEntity<List<ProductResponse>> response = productController.getAllProducts();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(productService, times(1)).findAll();
    }

    @Test
    void getProductById_WithValidId_ShouldReturnProduct() {
        // Arrange
        when(productService.findById(1L)).thenReturn(Optional.of(product));

        // Act
        ResponseEntity<ProductResponse> response = productController.getProductById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        verify(productService, times(1)).findById(1L);
    }

    @Test
    void getProductById_WithInvalidId_ShouldReturnNotFound() {
        // Arrange
        when(productService.findById(999L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<ProductResponse> response = productController.getProductById(999L);


        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(productService, times(1)).findById(999L);
    }

    @Test
    void createProduct_ShouldReturnCreatedProduct() {
        // Arrange
        when(productService.save(any(Product.class))).thenReturn(product);

        // Act
        ResponseEntity<ProductResponse> response = productController.createProduct(productRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test Product", response.getBody().getName());
        verify(productService, times(1)).save(any(Product.class));
    }

    @Test
    void updateProduct_WithValidId_ShouldReturnUpdatedProduct() {
        // Arrange
        when(productService.findById(1L)).thenReturn(Optional.of(product));
        when(productService.save(any(Product.class))).thenReturn(product);

        // Act
        ResponseEntity<ProductResponse> response = productController.updateProduct(1L, productRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(productService, times(1)).findById(1L);
        verify(productService, times(1)).save(any(Product.class));
    }

    @Test
    void updateProduct_WithInvalidId_ShouldReturnNotFound() {
        // Arrange
        when(productService.findById(999L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<ProductResponse> response = productController.updateProduct(999L, productRequest);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(productService, times(1)).findById(999L);
        verify(productService, never()).save(any(Product.class));
    }

    @Test
    void deleteProduct_WithValidId_ShouldReturnNoContent() {
        // Arrange
        when(productService.existsById(1L)).thenReturn(true);
        doNothing().when(productService).deleteById(1L);

        // Act
        ResponseEntity<Void> response = productController.deleteProduct(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService, times(1)).existsById(1L);
        verify(productService, times(1)).deleteById(1L);
    }

    @Test
    void deleteProduct_WithInvalidId_ShouldReturnNotFound() {
        // Arrange
        when(productService.existsById(999L)).thenReturn(false);

        // Act
        ResponseEntity<Void> response = productController.deleteProduct(999L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(productService, times(1)).existsById(999L);
        verify(productService, never()).deleteById(anyLong());
    }
}
