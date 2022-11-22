package com.cart.cache;

import com.cart.pojo.Cart;

public interface CartCache {
    public void put(Cart cart);
    public Cart get(String userId);
}
