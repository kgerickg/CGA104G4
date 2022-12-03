package com.cart.controller;

import com.cart.pojo.Cart;
import com.cart.service.impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cart/delete")

public class CartDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final Integer storeId = Integer.valueOf(request.getParameter("storeId"));
        final Integer prodId = Integer.valueOf(request.getParameter("prodId"));
        String userId = String.valueOf(request.getSession().getAttribute("memId"));
        CartServiceImpl cartSvc = new CartServiceImpl();
        Cart cart = cartSvc.get(userId);
        cart.getStoreMap().get(storeId).get(prodId).setProdQty(0);
        cartSvc.put(storeId, cart);
    }
}

