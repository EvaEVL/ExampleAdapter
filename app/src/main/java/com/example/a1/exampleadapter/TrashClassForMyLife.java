package com.example.a1.exampleadapter;

import java.util.ArrayList;

public class TrashClassForMyLife {
    private ArrayList<String> arrRushWords  = new ArrayList<>();
    private ArrayList<String> arrEnglhWords  = new ArrayList<>();

    TrashClassForMyLife(ArrayList<String> a ,ArrayList<String> b){
        arrEnglhWords = b;
        arrRushWords = a;
    }


    public ArrayList<String> getArrRushWords() {
        return arrRushWords;
    }

    public void setArrRushWords(ArrayList<String> arrRushWords) {
        this.arrRushWords = arrRushWords;
    }

    public ArrayList<String> getArrEnglhWords() {
        return arrEnglhWords;
    }

    public void setArrEnglhWords(ArrayList<String> arrEnglhWords) {
        this.arrEnglhWords = arrEnglhWords;
    }




}
