package com.inditex.sorting.service.criteria.impl;

import com.inditex.sorting.dto.ProductDTO;
import com.inditex.sorting.service.criteria.SortingCriterion;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SalesCriterion implements SortingCriterion {

    @Override
    public double calculateScore(ProductDTO product, Map<String, Object> params) {
        return product.getSalesUnits();
    }

    @Override
    public Map<String, Object> getDefaultParams() {
        return Map.of("order", "desc");
    }
}
