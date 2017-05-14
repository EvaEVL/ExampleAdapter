package com.example.a1.exampleadapter;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {

    String name;
    String price;
    ArrayList<Product> arrProducts;

    Product(String name, String price) {
        this.name = name;
        this.price = price;
    }

    Product(ArrayList<Product> arP,String s,String s1){
        this.arrProducts = arP;
    }
}