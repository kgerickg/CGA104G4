package com.cart.controller;

import com.cart.pojo.Cart;
import com.cart.service.impl.CartServiceImpl;
import com.store.model.StoreService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet("/cart/front")
public class CartFrontServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        CartServiceImpl cartSvc = new CartServiceImpl();
        StoreService storeSvc = new StoreService();
        Writer out = response.getWriter();
        String userId = String.valueOf(request.getSession().getAttribute("memId"));
        String cartJson = cartSvc.getCart(userId);


//        String storeName = storeSvc.getStoreNameById(storeId);
//        String itemName = cart.getStoreMap().get(storId).get(prodId).getProdName();
//        Integer itemQty = cart.getStoreMap().get(storId).get(prodId).getProdQty();
//        Integer itemPrc = cart.getStoreMap().get(storId).get(prodId).getProdPrc();
        out.write(cartJson);
    }
}
