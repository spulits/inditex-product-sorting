package com.inditex.product.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(name = "sales_units", nullable = false)
    private Integer salesUnits;
    
    @Column(name = "stock_s", nullable = false)
    private Integer stockS;
    
    @Column(name = "stock_m", nullable = false)
    private Integer stockM;
    
    @Column(name = "stock_l", nullable = false)
    private Integer stockL;

    public Product() {
    }

    public Product(Long id, String name, Integer salesUnits, Integer stockS, Integer stockM, Integer stockL) {
        this.id = id;
        this.name = name;
        this.salesUnits = salesUnits;
        this.stockS = stockS;
        this.stockM = stockM;
        this.stockL = stockL;
    }

    public Product(String name, Integer salesUnits, Integer stockS, Integer stockM, Integer stockL) {
        this.name = name;
        this.salesUnits = salesUnits;
        this.stockS = stockS;
        this.stockM = stockM;
        this.stockL = stockL;
    }

    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public Integer getSalesUnits() {
        return salesUnits;
    }
    
    public Integer getStockS() {
        return stockS;
    }
    
    public Integer getStockM() {
        return stockM;
    }
    
    public Integer getStockL() {
        return stockL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salesUnits=" + salesUnits +
                ", stockS=" + stockS +
                ", stockM=" + stockM +
                ", stockL=" + stockL +
                '}';
    }
}
