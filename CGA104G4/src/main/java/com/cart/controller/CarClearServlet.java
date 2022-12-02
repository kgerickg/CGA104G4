package com.cart.controller;

import com.cart.pojo.Cart;
import com.cart.service.CartService;
import com.cart.service.impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet("/cart/clear")
public class CarClearServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        Integer storId = (Integer) request.getSession().getAttribute("storeId");
        String userId = String.valueOf(request.getSession().getAttribute("memId"));
        CartServiceImpl cartSvc = new CartServiceImpl();
        Cart cart = cartSvc.get(userId);

        cartSvc.clear(userId);


        request.getRequestDispatcher("/front-orders/memberListAllOrders.jsp").forward(request, response);
    }
}
