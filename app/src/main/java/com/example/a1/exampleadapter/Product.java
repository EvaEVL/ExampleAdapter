package com.example.a1.exampleadapter;

import java.io.Serializable;

public class Product implements Serializable {

    String name;
    String price;

    Product(String name, String price) {
        this.name = name;
        this.price = price;
    }
}