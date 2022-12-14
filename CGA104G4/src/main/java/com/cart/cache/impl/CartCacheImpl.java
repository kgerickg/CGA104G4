package com.cart.cache.impl;

import com.cart.cache.CartCache;
import com.cart.pojo.Cart;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class CartCacheImpl implements CartCache {

    final Jedis jedis = new Jedis("localhost");
    final Gson gson = new GsonBuilder().create();

    @Override
    public void put(Cart cart) {

        jedis.set(cart.getUserId(), gson.toJson(cart));

    }

    @Override
    public Cart get(String userId) {
        return gson.fromJson(jedis.get(userId), Cart.class);

    }
    @Override
    public String getCart(String userId) {
        return jedis.get(userId);

    }

    @Override
    public void clear(String userId) {

        jedis.del(userId);
    }

//    public static void main(String[] args) {
//        CartCacheImpl dao = new CartCacheImpl();
//        dao.clear("1",3,6);
//
//    }


}
