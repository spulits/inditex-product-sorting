package com.inditex.sorting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortRequestDTO {
    @NotEmpty(message = "Product IDs cannot be empty")
    private List<Long> productIds;
    
    @NotNull(message = "Criteria weights cannot be null")
    private Map<String, Map<String, Object>> criteriaWeights;
}
