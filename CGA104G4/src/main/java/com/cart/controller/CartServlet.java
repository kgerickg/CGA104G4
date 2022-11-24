//package com.cart.controller;
//
//import com.cart.pojo.Cart;
//import com.cart.pojo.Item;
//import com.cart.service.CartService;
//import com.cart.service.impl.CartServiceImpl;
//import redis.clients.jedis.Jedis;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//import static core.util.Constants.GSON;
//
//@WebServlet("/cart/cartServlet")
//public class CartServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doPost(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//        final Integer prodId = (Integer) request.getAttribute("prodId");
//        String userId = (String) request.getSession().getAttribute("memId");
//        CartServiceImpl cartSvc = new CartServiceImpl();
//
//        if (cartSvc.get(userId) != null) {
//            Cart cart = cartSvc.get(userId);
//            List<Item> item = cart.getCartList();
//            if (item.getProdId().equals(prodId)) {
//                int nQty = item.getProdQty() + 1;
//                item.setProdQty(nQty);
//                item.setProdId(prodId);
//            } else {
//                item.setProdQty(1);
//                item.setProdId(prodId);
//            }
//            cart.getCartList().add(item);
//        } else {
//            Cart cart = new Cart();
//            if (item.getProdId().equals(prodId)) {
//                int nQty = item.getProdQty() + 1;
//                item.setProdQty(nQty);
//            } else {
//                item.setProdQty(1);
//            }
//            cart.getCartList().add(item);
//        }
//        ;
//    }
//}
