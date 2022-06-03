package com.example.moviegf6;

import java.util.Set;

//@Remote
public interface ShoppingCart {
    ShoppingCart initialize();
    void addProduct(String product);
    void removeProduct(MovieEntity product);
    Set<Integer> getContents();
    void remove();
}