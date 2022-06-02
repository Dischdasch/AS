package com.example.classes;

import java.util.ArrayList;
import java.util.List;

//@Stateful
public class CartBean implements ShoppingCart
{
    String customerName;
    String customerId;
    List<Integer> contents;

    public ShoppingCart initialize() {
        contents = new ArrayList<>();
        return this;
    }

    public void addProduct(String product){
        int toAdd = Integer.parseInt(product);
        contents.add(toAdd);
    };
    public void removeProduct(Movie product){};
    public List<Integer> getContents(){
        return contents;
    };
    //@Remove
    public void remove(){};
}