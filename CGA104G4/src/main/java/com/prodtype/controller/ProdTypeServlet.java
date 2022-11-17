package com.prodtype.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prodtype.model.ProdTypeService;
import com.prodtype.model.ProdTypeVO;

public class ProdTypeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自 的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("prodTypeId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.put("prodTypeId","請輸入商品類型編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-prod/memberMenu.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer prodTypeId = null;
				try {
					prodTypeId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.put("prodTypeId","商品類型編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-prod/memberMenu.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ProdTypeService prodTypeSvc = new ProdTypeService();
				ProdTypeVO prodTypeVO = prodTypeSvc.getOneProdType(prodTypeId);
				if (prodTypeVO == null) {
					errorMsgs.put("prodTypeId","查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-prod/memberMenu.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("prodTypeVO", prodTypeVO); // 資料庫取出的prodTypeVO物件,存入req
				String url = "/gront-prod/listOneProdType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneProdType.jsp
				successView.forward(req, res);
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllProdTypes.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer prodTypeId = Integer.valueOf(req.getParameter("prodTypeId"));
				
				/***************************2.開始查詢資料****************************************/
				ProdTypeService prodTypeSvc = new ProdTypeService();
				ProdTypeVO prodTypeVO = prodTypeSvc.getOneProdType(prodTypeId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				String param = "?prodTypeId=" + prodTypeVO.getProdTypeId()+
							   "&prodTypeName=" + prodTypeVO.getProdTypeName();
				String url = "/front-prod/update_prodType_input.jsp"+param;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_prodType_input.jsp
				successView.forward(req, res);
		}
		
		if ("update".equals(action)) { // 來自update_prodType_input.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer prodTypeId = Integer.valueOf(req.getParameter("prodTypeId").trim());
				
				String prodTypeName = req.getParameter("prodTypeName");
				String prodTypeNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,100}$";
				if (prodTypeName == null || prodTypeName.trim().length() == 0) {
					errorMsgs.put("prodTypeName","商品類別名稱: 請勿空白");
				} else if(!prodTypeName.trim().matches(prodTypeNameReg)) {
					errorMsgs.put("prodName","商品類別名稱: 只能是中、英文字母、數字和_ , 且長度必需在1到100之間");
	            }
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/prod/update_prod_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ProdTypeService prodTypeSvc = new ProdTypeService();
				ProdTypeVO prodTypeVO = prodTypeSvc.updateProdType(prodTypeId, prodTypeName);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("prodTypeVO", prodTypeVO); // 資料庫update成功後,正確的的prodTypeVO物件,存入req
				String url = "/front-prod/listOneProdType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneProdType.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // 來自addProd.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/			
				String prodTypeName = req.getParameter("prodTypeName");
				String prodTypeNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,100}$";
				if (prodTypeName == null || prodTypeName.trim().length() == 0) {
					errorMsgs.put("prodName","商品類型名稱: 請勿空白");
				} else if(!prodTypeName.trim().matches(prodTypeNameReg)) {
					errorMsgs.put("prodName","商品類型名稱: 只能是中、英文字母、數字和_ , 且長度必需在1到100之間");
				}
		
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/prod/update_prod_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始新增資料***************************************/
				ProdTypeService prodTypeSvc = new ProdTypeService();
				prodTypeSvc.addProdType(prodTypeName);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-prod/listAllProdTypes.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllProdTypes.jsp
				successView.forward(req, res);				
		}
		
		if ("delete".equals(action)) { // 來自listAllProdTypes.jsp

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer prodTypeId = Integer.valueOf(req.getParameter("prodTypeId"));
				
				/***************************2.開始刪除資料***************************************/
				ProdTypeService prodTypeSvc = new ProdTypeService();
				prodTypeSvc.deleteProdType(prodTypeId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-prod/listAllProdTypes.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}
}