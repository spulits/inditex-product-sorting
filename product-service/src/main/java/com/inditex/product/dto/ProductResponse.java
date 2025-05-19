package com.inditex.product.dto;

public class ProductResponse {
    private Long id;
    private String name;
    private Integer salesUnits;
    private Integer stockS;
    private Integer stockM;
    private Integer stockL;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


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
