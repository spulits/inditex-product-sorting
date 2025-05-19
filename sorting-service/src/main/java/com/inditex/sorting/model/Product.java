package com.inditex.sorting.model;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private int salesUnits;
    private int stockS;
    private int stockM;
    private int stockL;
}
