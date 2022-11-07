package com.prod.controller;

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
import com.prod.model.ProdService;

public class ProdServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		String action = req.getParameter("action");

		     // 來自select_page.jsp的請求               // 來自 prod/listAllProds.jsp的請求
		if ("listDetails_ByProdId_A".equals(action) || "listDetails_ByProdId_B".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer prodId = Integer.valueOf(req.getParameter("prodId"));

			/*************************** 2.開始查詢資料 ****************************************/
			ProdService prodSvc = new ProdService();
			Set<DetailVO> set = prodSvc.getDetailsByProdId(prodId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("listDetails_ByProdId", set);    // 資料庫取出的list物件,存入request

			String url = null;
			if ("listDetails_ByProdId_A".equals(action))
				url = "/prod/listDetails_ByProdId.jsp";        // 成功轉交 prod/listDetails_ByProdId.jsp
			else if ("listDetails_ByProdId_B".equals(action))
				url = "/prod/listAllProds.jsp";              // 成功轉交 prod/listAllProds.jsp

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			}
				
				
			if ("delete_Prod".equals(action)) { // 來自/prod/listAllProds.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數***************************************/
				Integer prodId = Integer.valueOf(req.getParameter("prodId"));
						
				/***************************2.開始刪除資料***************************************/
				ProdService prodSvc = new ProdService();
				prodSvc.deleteProd(prodId);
						
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				String url = "/prod/listAllProds.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後, 成功轉交 回到 /prod/listAllProds.jsp
				successView.forward(req, res);
			}
		}
	}