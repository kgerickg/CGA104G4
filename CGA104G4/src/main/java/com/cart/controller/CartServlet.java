//package com.cart.controller;
//
//import com.cart.pojo.Cart;
//import com.cart.service.CartService;
//import com.cart.service.impl.CartServiceImpl;
//
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//import static core.util.Constants.GSON;
//
//@WebServlet("/cart/cartServlet")
//public class CartServlet extends HttpServlet {
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		CartService cartService = null;
//        final Cart userId = (Cart) request.getSession().getAttribute("memId");
//        final Cart prodId = (Cart) request.getAttribute("prodId");
//        Cart cart = new Cart();
//		cart.setUserId((String) request.getSession().getAttribute("memId"));
//
//		CartServiceImpl cartSvc = new CartServiceImpl();
//
//	}
//}
