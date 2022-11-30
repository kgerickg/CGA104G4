package com.cart.pojo;

import com.google.gson.GsonBuilder;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

@Data
public class Cart implements Serializable {
    private  String userId;
    private final HashMap<Integer, HashMap<Integer, Item>> storeMap = new HashMap();
    private Integer totalPrc;

//    public static void main(String[] args) {
//        Cart cart = new Cart();
//        cart.setUserId("1");
//        Item item = new Item();
//        item.setProdId(1);
//        item.setProdQty(9);
//
//        Item item2 = new Item();
//        item2.setProdId(2);
//        item2.setProdQty(7);
//
//        cart.getItemMap().put(item.getProdId(), item);
//        cart.getItemMap().put(item.getProdId(), item2);
//        String test = new GsonBuilder().create().toJson(cart);
//        System.out.println(test);
//        System.out.println(cart);
//    }
}
