package com.cart.service;

import com.cart.pojo.Cart;

public interface CartService {

    public void put(Cart cart);
    public Cart get(String userId);

}
