package com.orders.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.detail.model.DetailVO;
import com.orders.model.OrdersService;

public class OrdersServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		String action = req.getParameter("action");

		     // 來自select_page.jsp的請求               // 來自 orders/listAllOrders.jsp的請求
		if ("listDetails_ByOrdId_A".equals(action) || "listDetails_ByOrdId_B".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer ordId = Integer.valueOf(req.getParameter("ordId"));

			/*************************** 2.開始查詢資料 ****************************************/
			OrdersService ordersSvc = new OrdersService();
			Set<DetailVO> set = ordersSvc.getDetailsByOrdId(ordId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("listDetails_ByOrdId", set);    // 資料庫取出的list物件,存入request

			String url = null;
			if ("listDetails_ByOrdId_A".equals(action))
				url = "/orders/listDetails_ByOrdId.jsp";        // 成功轉交 orders/listDetails_ByOrdId.jsp
			else if ("listDetails_ByOrdId_B".equals(action))
				url = "/orders/listAllOrders.jsp";              // 成功轉交 orders/listAllOrders.jsp

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			}
				
				
			if ("delete_Order".equals(action)) { // 來自/orders/listAllOrders.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數***************************************/
				Integer ordId = Integer.valueOf(req.getParameter("ordId"));
						
				/***************************2.開始刪除資料***************************************/
				OrdersService ordersSvc = new OrdersService();
				ordersSvc.deleteOrders(ordId);
						
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				String url = "/orders/listAllOrders.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後, 成功轉交 回到 /orders/listAllOrders.jsp
				successView.forward(req, res);
			}
		}
	}