package com.inditex.sorting.service;

import com.inditex.sorting.client.ProductClient;
import com.inditex.sorting.dto.SortRequestDTO;
import com.inditex.sorting.dto.SortResultDTO;
import com.inditex.sorting.dto.ProductDTO;
import com.inditex.sorting.model.Product;
import com.inditex.sorting.service.criteria.SortingCriterion;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SortingService {
    
    private final ProductClient productClient;
    private final List<SortingCriterion> criteriaList;
    private Map<String, SortingCriterion> sortingCriteria;

    @PostConstruct
    public void init() {
        this.sortingCriteria = criteriaList.stream()
            .collect(Collectors.toMap(
                c -> c.getClass().getSimpleName()
                    .replace("Criterion", "")
                    .toLowerCase(),
                Function.identity()
            ));
        log.info("Registered sorting criteria: {}", sortingCriteria.keySet());
    }

    public List<SortResultDTO> sortProducts(SortRequestDTO sortRequest) {
        List<ProductDTO> products = convertToDTOList(
            productClient.getProductsByIds(sortRequest.getProductIds()));

        return products.stream()
            .map(product -> {
                Map<String, Double> criteriaScores = new HashMap<>();
                double totalScore = 0.0;

                for (Map.Entry<String, Map<String, Object>> entry : 
                     sortRequest.getCriteriaWeights().entrySet()) {
                    String criteriaName = entry.getKey();
                    SortingCriterion criterion = sortingCriteria.get(criteriaName);
                    
                    if (criterion != null) {
                        Map<String, Object> params = entry.getValue();
                        if (params == null) {
                            params = criterion.getDefaultParams();
                        }
                        
                        double score = criterion.calculateScore(product, params);
                        String order = (String) params.getOrDefault("order", "asc");
                        
                        if ("desc".equals(order)) {
                            score = -score;
                        }
                        
                        double weight = (double) params.getOrDefault("weight", 1.0);
                        score *= weight;
                        
                        criteriaScores.put(criteriaName, score);
                        totalScore += score;
                    }
                }

                SortResultDTO result = new SortResultDTO();
                result.setProduct(convertToModel(product));
                result.setCriteriaScores(criteriaScores);
                result.setScore(totalScore);
                return result;
            })
            .sorted(Comparator.comparingDouble(SortResultDTO::getScore).reversed())
            .collect(Collectors.toList());
    }
    
    public List<String> getAvailableCriteria() {
        return new ArrayList<>(sortingCriteria.keySet());
    }
    
    private Product convertToModel(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setSalesUnits(dto.getSalesUnits() != null ? dto.getSalesUnits() : 0);
        product.setStockS(dto.getStockS() != null ? dto.getStockS() : 0);
        product.setStockM(dto.getStockM() != null ? dto.getStockM() : 0);
        product.setStockL(dto.getStockL() != null ? dto.getStockL() : 0);
        return product;
    }

    private ProductDTO convertToDTO(Product product) {
        if (product == null) {
            return null;
        }
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setSalesUnits(product.getSalesUnits());
        dto.setStockS(product.getStockS());
        dto.setStockM(product.getStockM());
        dto.setStockL(product.getStockL());
        return dto;
    }
    
    private List<ProductDTO> convertToDTOList(List<Product> products) {
        if (products == null) {
            return Collections.emptyList();
        }
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
}
