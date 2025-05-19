package com.inditex.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Sales units are required")
    @Min(value = 0, message = "Sales units must be greater than or equal to 0")
    private Integer salesUnits;

    @NotNull(message = "Stock S is required")
    @Min(value = 0, message = "Stock S must be greater than or equal to 0")
    private Integer stockS;

    @NotNull(message = "Stock M is required")
    @Min(value = 0, message = "Stock M must be greater than or equal to 0")
    private Integer stockM;

    @NotNull(message = "Stock L is required")
    @Min(value = 0, message = "Stock L must be greater than or equal to 0")
    private Integer stockL;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getSalesUnits() {
        return salesUnits;
    }

    public void setSalesUnits(Integer salesUnits) {
        this.salesUnits = salesUnits;
    }


    public Integer getStockS() {
        return stockS;
    }
    public void setStockS(Integer stockS) {
        this.stockS = stockS;
    }


    public Integer getStockM() {
        return stockM;
    }
    public void setStockM(Integer stockM) {
        this.stockM = stockM;
    }


    public Integer getStockL() {
        return stockL;
    }
    public void setStockL(Integer stockL) {
        this.stockL = stockL;
    }
}
