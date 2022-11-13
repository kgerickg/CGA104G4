package com.prodtype.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.detail.model.DetailVO;
import com.orders.model.OrdersService;
import com.prod.model.ProdService;
import com.prod.model.ProdVO;
import com.prodtype.model.ProdTypeService;
import com.store.model.StoreVO;

public class ProdTypeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 來自 manageProd.jsp的請求 // 來自 listAllProdType.jsp的請求
		if ("listProds_ByProdTypeId_A".equals(action) || "listProds_ByProdTypeId_B".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer prodTypeId = new Integer(req.getParameter("prodTypeId"));

				/*************************** 2.開始查詢資料 ****************************************/
				ProdTypeService prodTypeSvc = new ProdTypeService();
				Set<ProdVO> set = prodTypeSvc.getProdsByProdTypeId(prodTypeId);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listProds_ByProdTypeId", set); // 資料庫取出的set物件,存入request

				String url = null;
				if ("listProds_ByProdTypeId_A".equals(action))
					url = "listProds_ByProdTypeId.jsp"; // 成功轉交 listProds_ByProdTypeId.jsp
				else if ("listProds_ByProdTypeId_B".equals(action))
					url = "listAllProdType.jsp"; // 成功轉交 listAllProdType.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		if ("delete_ProdType".equals(action)) { // 來自 listAllProdTypes.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer prodTypeId = new Integer(req.getParameter("prodTypeId"));

				/*************************** 2.開始刪除資料 ***************************************/
				ProdTypeService prodTypeSvc = new ProdTypeService();
				prodTypeSvc.deleteProdType(prodTypeId);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "listAllProdTypes.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後, 成功轉交 回到 listAllProdTypes.jsp

				successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("listAllProdTypes.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
