package com.example.classes;

import java.util.Set;

//@Remote
public interface ShoppingCart {
    ShoppingCart initialize();
    void addProduct(String product);
    void removeProduct(Movie product);
    Set<Integer> getContents();
    void remove();
}