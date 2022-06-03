package com.example.moviegf6;

import java.util.HashSet;
import java.util.Set;

public class CartBean implements ShoppingCart
{
    String customerName;
    String customerId;
    Set<Integer> contents;

    public ShoppingCart initialize() {
        contents = new HashSet<>();
        return this;
    }

    public void addProduct(String product){
        int toAdd = Integer.parseInt(product);
        contents.add(toAdd);
    }

    public void removeProduct(MovieEntity product){}

    public Set<Integer> getContents(){
        return contents;
    }

    //@Remove
    public void remove(){}
}