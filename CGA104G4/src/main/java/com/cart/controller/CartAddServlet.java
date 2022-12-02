package com.cart.controller;

import com.cart.pojo.Cart;
import com.cart.pojo.Item;
import com.cart.service.impl.CartServiceImpl;
import com.store.model.StoreDAO;
import com.store.model.StoreDAO_interface;
import com.store.model.StoreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/cart/add")
public class CartAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final Integer storeId = Integer.valueOf(request.getParameter("storeId"));
        request.getSession().setAttribute("storeId",storeId);
        final Integer prodId = Integer.valueOf(request.getParameter("prodId"));
        String userId = String.valueOf(request.getSession().getAttribute("memId"));
        CartServiceImpl cartSvc = new CartServiceImpl();
        Cart cart = cartSvc.get(userId);
        StoreService storeService = new StoreService();
        String storeName = storeService.findByStoreId(storeId).getStoreName();
        Item item = new Item();
        HashMap<Integer, Item> itemMap;
        //判斷有沒有購物車
        if (cart != null) {
            //判斷有沒有買過該商店
            if (cart.getStoreMap().get(storeId) != null) {
                //判斷有沒有買過該商品
                if (cart.getStoreMap().get(storeId).get(prodId) != null) {
                    cartSvc.addQty(userId, storeId, prodId);
                    //沒買過該商品
                } else {
                    item = new Item();
                    item.setProdId(prodId);
                    item.setProdQty(1);
                    cart.getStoreMap().get(storeId).put(item.getProdId(), item);
                    cartSvc.put(storeId, cart);
                }
                //有買過東西但沒買過該商店
            } else {
                itemMap = new HashMap();
                item.setStoreId(storeId);
                item.setStoreName(storeName);
                item.setProdId(prodId);
                item.setProdQty(1);
                itemMap.put(item.getProdId(), item);
                cart.getStoreMap().put(item.getStoreId(), itemMap);
                cartSvc.put(item.getStoreId(), cart);
            }
            //完全沒買過東西
        } else {
            cart = new Cart();
            itemMap = new HashMap();
            cart.setUserId(userId);
            item.setStoreId(storeId);
            item.setStoreName(storeName);
            item.setProdId(prodId);
            item.setProdQty(1);
            itemMap.put(item.getProdId(), item);
            cart.getStoreMap().put(item.getStoreId(), itemMap);
            cartSvc.put(item.getStoreId(), cart);
        }
    }
}
