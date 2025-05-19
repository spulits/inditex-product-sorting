package com.inditex.sorting.service.criteria;

import com.inditex.sorting.dto.ProductDTO;
import java.util.Map;

public interface SortingCriterion {
    double calculateScore(ProductDTO  product, Map<String, Object> params);
    Map<String, Object> getDefaultParams();
}
