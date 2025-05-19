package com.inditex.sorting.dto;

import com.inditex.sorting.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortResultDTO {
    private Product product;
    private double score;
    private Map<String, Double> criteriaScores;
}
