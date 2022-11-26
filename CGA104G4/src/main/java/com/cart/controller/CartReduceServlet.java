package com.cart.controller;

import com.cart.pojo.Cart;
import com.cart.pojo.Item;
import com.cart.service.impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cart/reduce")

public class CartReduceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final Integer prodId = Integer.valueOf(request.getParameter("prodId"));
        String userId = String.valueOf(request.getSession().getAttribute("memId"));
        CartServiceImpl cartSvc = new CartServiceImpl();

        Cart cart = cartSvc.get(userId);
        Item item = new Item();
        if (cart != null) {
            item = cart.getItemMap().get(prodId);
            if (item == null) {
                item = new Item();
                item.setProdId(prodId);
                item.setProdQty(1);
                cart.getItemMap().put(item.getProdId(), item);
                cartSvc.put(cart);
            } else {
                cartSvc.addQty(userId,item.getProdId());
            }
        } else {
            cart = new Cart();
            cart.setUserId(userId);
            item.setProdId(prodId);
            item.setProdQty(1);
            cart.getItemMap().put(item.getProdId(), item);
            cartSvc.put(cart);
        }
    }
}
