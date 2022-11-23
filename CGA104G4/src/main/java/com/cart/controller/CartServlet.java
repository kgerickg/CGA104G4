//package com.cart.controller;
//
//import com.cart.pojo.Cart;
//import com.cart.service.CartService;
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
//	private static final long serialVersionUID = 1L;
//
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		CartService cartService = null;
//		final String cart = GSON.fromJson(request.getReader(), Cart.class).getUserId();
//
//		cartService.put(cart);
//
//		final Admin admin = (Admin) request.getSession().getAttribute("admin");
//		final Core core = new Core();
//		if (admin == null) {
//			core.setMessage("無會員資訊");
//			core.setSuccessful(false);
//		} else {
//			final String currentPassword = admin.getAdmPwd();
//			if (Objects.equals(admPwd, currentPassword)) {
//				core.setSuccessful(true);
//			} else {
//				core.setMessage("舊密碼錯誤");
//				core.setSuccessful(false);
//			}
//		}
//		writePojo2Json(response, core);
//	}
//}
