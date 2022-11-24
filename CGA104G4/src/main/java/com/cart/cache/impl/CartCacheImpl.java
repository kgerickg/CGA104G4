package com.cart.cache.impl;

import com.cart.cache.CartCache;
import com.cart.pojo.Cart;
import com.cart.pojo.Item;
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

    public static void main(String[] args) {
        CartCacheImpl dao = new CartCacheImpl();
        Cart cart = new Cart();
        Item item = new Item();
        item.setProdId(2);
        item.setProdQty(9);
        cart.getCartMap().put(item.getProdId(), item);
        dao.put(cart);

        Cart test = dao.get("1");
        System.out.print(test);
    }


}
