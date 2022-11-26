package com.cart.service;

import com.cart.pojo.Cart;

public interface CartService {

    public void put(Cart cart);
    public Cart get(String userId);

    public void clear(String userId);

    public void reduceQty(String userId, Integer prodId);
    public void addQty(String userId, Integer prodId);

}
