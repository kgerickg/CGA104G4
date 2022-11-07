package com.detail.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.detail.model.DetailService;
import com.detail.model.DetailVO;

public class DetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("prodId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.put("prodId","請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/detail/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer prodId = null;
				try {
					prodId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.put("prodId","商品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/detail/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				DetailService detailSvc = new DetailService();
				DetailVO detailVO = detailSvc.getOneDetail(prodId);
				if (detailVO == null) {
					errorMsgs.put("prodId","查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/detail/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("detailVO", detailVO); // 資料庫取出的detailVO物件,存入req
				String url = "/detail/listOneDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneDetail.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllDetail.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer prodId = Integer.valueOf(req.getParameter("prodId"));
				
				/***************************2.開始查詢資料****************************************/
				DetailService detailSvc = new DetailService();
				DetailVO detailVO = detailSvc.getOneDetail(prodId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				String param = "?prodId="  +detailVO.getProdId()+
						       "&prodQty="  +detailVO.getProdQty()+
						       "&ordId=" +detailVO.getOrdId();
				String url = "/detail/update_detail_input.jsp"+param;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_detail_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_detail_input.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer prodId = Integer.valueOf(req.getParameter("prodId").trim());
				
				Integer prodQty = null;
				try {
					prodQty = Integer.valueOf(req.getParameter("prodQty").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("prodQty","商品數量請填數字");
				}
								
				Integer ordId = Integer.valueOf(req.getParameter("ordId").trim());

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/detail/update_detail_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				DetailService detailSvc = new DetailService();
				DetailVO detailVO = detailSvc.updateDetail(prodId, prodQty, ordId);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("detailVO", detailVO); // 資料庫update成功後,正確的的detailVO物件,存入req
				String url = "/detail/listOneDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneDetail.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // 來自addDetail.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer prodId = Integer.valueOf(req.getParameter("prodId").trim());
				
				Integer prodQty = null;
				try {
					prodQty = Integer.valueOf(req.getParameter("prodQty").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("prodQty","商品數量請填數字");
				}
								
				Integer ordId = Integer.valueOf(req.getParameter("ordId").trim());
	
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/detail/update_detail_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始新增資料***************************************/
				DetailService detailSvc = new DetailService();
				detailSvc.addDetail(prodQty,ordId);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/detail/listAllDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllDetail.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // 來自listAllDetail.jsp

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer prodId = Integer.valueOf(req.getParameter("prodId"));
				
				/***************************2.開始刪除資料***************************************/
				DetailService detailSvc = new DetailService();
				detailSvc.deleteDetail(prodId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/detail/listAllDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}
}