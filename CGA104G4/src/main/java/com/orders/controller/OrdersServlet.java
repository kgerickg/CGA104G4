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
import javax.servlet.http.HttpSession;

import com.detail.model.DetailVO;
import com.orders.model.OrdersService;
import com.orders.model.OrdersVO;

public class OrdersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		String action = req.getParameter("action");
		
		 // 來自front-orders/storeListAllOrders.jsp的請求
		if ("updateOrdStat_A".equals(action)
		
		 // 來自front-orders/memberListAllOrders.jsp的請求
		 || "updateOrdStat_B".equals(action)
				
		 // 來自back-orders/listAllOrders.jsp的請求
		 || "updateOrdStat_C".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/*************************** 1.接收請求參數 ****************************************/
			Integer ordId = Integer.valueOf(req.getParameter("ordId"));	
			Integer ordStat = Integer.valueOf(req.getParameter("ordStat"));	
		
			OrdersVO ordersVO = new OrdersVO();
			ordersVO.setOrdId(ordId);
			ordersVO.setOrdStat(ordStat);
			
			/*************************** 2.開始修改資料 ****************************************/
			OrdersService ordersSvc = new OrdersService();
			ordersVO = ordersSvc.updateOrdStat(ordId, ordStat);
			
			/*************************** 3.修改完成,準備轉交(Send the Success view) ************/
			req.setAttribute("ordersVO", ordersVO);
			
			String url = null;
			if ("updateOrdStat_A".equals(action))
				url = "/front-orders/storeListAllOrders.jsp";	// 成功轉交 front-orders/storeListAllOrders.jsp
			else if ("updateOrdStat_B".equals(action))
				url = "/front-orders/memberListAllOrders.jsp";	// 成功轉交 front-orders/memberListAllOrders.jsp
			else if ("updateOrdStat_C".equals(action))
				url = "/back-orders/listAllOrders.jsp";	// 成功轉交 back-orders/listAllOrders.jsp

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		// 來自front-orders/storeListAllOrders.jsp的請求
		if ("listOrders_ByStoreId".equals(action)) {
					
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
					
			/*************************** 1.接收請求參數 ****************************************/
			HttpSession session = req.getSession();
			Integer storeId = (Integer) session.getAttribute("storeId");
			/*************************** 2.開始查詢資料 ****************************************/
			OrdersService ordersSvc = new OrdersService();
			Set<OrdersVO> set = ordersSvc.getOrdersByStoreId(storeId);
					
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("listOrders_ByStoreId", set);    // 資料庫取出的list物件,存入request
					
			String url = "/front-orders/storeListAllOrders.jsp";	// 成功轉交 front-orders/storeListAllOrders.jsp
					          
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		// 來自front-orders/memberListAllOrders.jsp的請求
		if ("listOrders_ByMemdId".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/*************************** 1.接收請求參數 ****************************************/
			HttpSession session = req.getSession();
			Integer memId = (Integer) session.getAttribute("memId");
			/*************************** 2.開始查詢資料 ****************************************/
			OrdersService ordersSvc = new OrdersService();
			Set<OrdersVO> set = ordersSvc.getOrdersByMemId(memId);
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("listOrders_ByMemdId", set);    // 資料庫取出的list物件,存入request
			
			String url = "/front-orders/memberListAllOrders.jsp";	// 成功轉交 front-orders/memberListAllOrders.jsp
			          
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		// 來自front-orders/storeListAllOrders.jsp的請求
		if ("listDetails_ByOrdId_A".equals(action)
		// 來自front-orders/memberListAllOrders.jsp的請求
		 || "listDetails_ByOrdId_B".equals(action)
		// 來自back-orders/listAllOrders.jsp的請求
		 || "listDetails_ByOrdId_C".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer ordId = Integer.valueOf(req.getParameter("ordId"));

			/*************************** 2.開始查詢資料 ****************************************/
			OrdersService ordersSvc = new OrdersService();
			Set<DetailVO> set = ordersSvc.getDetailsByOrdId(ordId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("listDetails_ByOrdId", set);	// 資料庫取出的list物件,存入request

			String url = null;
			if ("listDetails_ByOrdId_A".equals(action))
				url = "/front-orders/storeListAllOrders.jsp";	// 成功轉交 front-orders/storeListAllOrders.jsp
			else if ("listDetails_ByOrdId_B".equals(action))
				url = "/front-orders/memberListAllOrders.jsp";	// 成功轉交 front-orders/memberListAllOrders.jsp
			else if ("listDetails_ByOrdId_C".equals(action))
				url = "/back-orders/listAllOrders.jsp";	// 成功轉交 back-orders/listAllOrders.jsp

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}
}