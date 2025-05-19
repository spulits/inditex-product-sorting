package com.inditex.product.service;

import com.inditex.product.model.Product;
import com.inditex.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        product = new Product(1L, "Test Product", 100, 10, 20, 30);
    }

    @Test
    void findAll_ShouldReturnAllProducts() {
        // Arrange
        when(productRepository.findAll()).thenReturn(Arrays.asList(product));

        // Act
        List<Product> products = productService.findAll();

        // Assert
        assertEquals(1, products.size());
        verify(productRepository, times(1)).findAll();
    }


    @Test
    void findById_WithValidId_ShouldReturnProduct() {
        // Arrange
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));


        // Act
        Optional<Product> foundProduct = productService.findById(1L);

        // Assert
        assertTrue(foundProduct.isPresent());
        assertEquals(1L, foundProduct.get().getId());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void findById_WithInvalidId_ShouldReturnEmpty() {
        // Arrange
        when(productRepository.findById(999L)).thenReturn(Optional.empty());


        // Act
        Optional<Product> foundProduct = productService.findById(999L);


        // Assert
        assertFalse(foundProduct.isPresent());
        verify(productRepository, times(1)).findById(999L);
    }

    @Test
    void save_ShouldReturnSavedProduct() {
        // Arrange
        when(productRepository.save(any(Product.class))).thenReturn(product);


        // Act
        Product savedProduct = productService.save(new Product());

        // Assert
        assertNotNull(savedProduct);
        assertEquals("Test Product", savedProduct.getName());
        verify(productRepository, times(1)).save(any(Product.class));
    }


    @Test
    void deleteById_ShouldCallRepositoryDelete() {
        // Arrange
        doNothing().when(productRepository).deleteById(1L);

        // Act
        productService.deleteById(1L);
        // Assert
        verify(productRepository, times(1)).deleteById(1L);
    }


    @Test
    void existsById_ShouldReturnTrueWhenProductExists() {
        // Arrange
        when(productRepository.existsById(1L)).thenReturn(true);

        // Act
        boolean exists = productService.existsById(1L);
        // Assert
        assertTrue(exists);
        verify(productRepository, times(1)).existsById(1L);
    }


    @Test
    void existsById_ShouldReturnFalseWhenProductDoesNotExist() {
        // Arrange
        when(productRepository.existsById(999L)).thenReturn(false);
        // Act
        boolean exists = productService.existsById(999L);
        // Assert
        assertFalse(exists);
        verify(productRepository, times(1)).existsById(999L);
    }
}
