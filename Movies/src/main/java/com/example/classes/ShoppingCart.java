package com.example.classes;

import java.util.List;

//@Remote
public interface ShoppingCart {
    public ShoppingCart initialize();
    public void addProduct(String product);
    public void removeProduct(Movie product);
    public List<Integer> getContents();
    public void remove();
}