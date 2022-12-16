package com.cart.controller;

import com.cart.pojo.Cart;
import com.cart.service.CartService;
import com.cart.service.impl.CartServiceImpl;
import com.orders.model.OrdersService;
import com.refill.model.RefillService;
import com.utils.SpringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet("/cart/clear")
public class CarClearServlet extends HttpServlet {

    private final OrdersService ordersService = new OrdersService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        String userId = String.valueOf(request.getSession().getAttribute("memId"));
        CartServiceImpl cartSvc = new CartServiceImpl();
        Cart cart = cartSvc.get(userId);
        ordersService.addOrder(cart);
        cartSvc.clear(userId);
        Integer memId = (Integer) request.getSession().getAttribute("memId");
        Integer chargeToken = Integer.valueOf(request.getParameter("value"));
        RefillService refillService = SpringUtil.getBean(getServletContext(), RefillService.class);
        refillService.setToken(memId,chargeToken);
        request.getRequestDispatcher("/front-orders/memberListAllOrders.jsp").forward(request, response);
    }
}
