package com.inditex.sorting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private Integer salesUnits;
    private Integer stockS;
    private Integer stockM;
    private Integer stockL;
    private Integer stockXL;

    public String getStockAsString() {
        return String.format("S:%d / M:%d / L:%d / XL:%d", 
            stockS != null ? stockS : 0, 
            stockM != null ? stockM : 0, 
            stockL != null ? stockL : 0, 
            stockXL != null ? stockXL : 0);
    }
}
