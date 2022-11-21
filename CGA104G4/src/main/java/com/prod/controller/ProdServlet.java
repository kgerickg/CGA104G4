package com.prod.controller;

import java.io.*;
import java.util.*;
import java.sql.Date;

import javax.servlet.*;
import javax.servlet.http.*;

import com.prod.model.ProdService;
import com.prod.model.ProdVO;

public class ProdServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自 的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("prodId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("prodId", "請輸入商品編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-prod/menu.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer prodId = null;
			try {
				prodId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("prodId", "商品編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-prod/menu.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ProdService prodSvc = new ProdService();
			ProdVO prodVO = prodSvc.getOneProd(prodId);
			if (prodVO == null) {
				errorMsgs.put("prodId", "查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-prod/menu.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("prodVO", prodVO); // 資料庫取出的prodVO物件,存入req
			String url = "/gront-prod/listOneProd.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneProd.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllProds.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer prodId = Integer.valueOf(req.getParameter("prodId"));

			/*************************** 2.開始查詢資料 ****************************************/
			ProdService prodSvc = new ProdService();
			ProdVO prodVO = prodSvc.getOneProd(prodId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			String param = "?prodId=" + prodVO.getProdId() + "&prodTypeId=" + prodVO.getProdTypeId() + "&storeId="
					+ prodVO.getStoreId() + "&prodName=" + prodVO.getProdName() + "&prodCont=" + prodVO.getProdCont()
					+ "&prodPrc=" + prodVO.getProdPrc() + "&prodTime=" + prodVO.getProdTime() + "&prodStat="
					+ prodVO.getProdStat();
			String url = "/front-prod/update_prod_input.jsp" + param;
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_prod_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_prod_input.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer prodId = Integer.valueOf(req.getParameter("prodId").trim());
			Integer prodTypeId = Integer.valueOf(req.getParameter("prodTypeId").trim());
			Integer storeId = Integer.valueOf(req.getParameter("storeId").trim());

			String prodName = req.getParameter("prodName");
			String prodNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,100}$";
			if (prodName == null || prodName.trim().length() == 0) {
				errorMsgs.put("prodName", "商品名稱: 請勿空白");
			} else if (!prodName.trim().matches(prodNameReg)) {
				errorMsgs.put("prodName", "商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在1到100之間");
			}

			String prodCont = req.getParameter("prodCont");
			String prodContReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,500}$";
			if (prodCont == null || prodCont.trim().length() == 0) {
				errorMsgs.put("prodCont", "商品介紹: 請勿空白");
			} else if (!prodCont.trim().matches(prodContReg)) {
				errorMsgs.put("prodCont", "商品介紹: 只能是中、英文字母、數字和_ , 且長度必需在1到500之間");
			}

			Integer prodPrc = null;
			try {
				prodPrc = Integer.valueOf(req.getParameter("prodPrc").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("prodPrc", "商品狀態請輸入數字");
			}

			Date prodTime = null;
			try {
				prodTime = Date.valueOf(req.getParameter("prodTime").trim());
			} catch (IllegalArgumentException e) {
				errorMsgs.put("prodTime", "請輸入日期");
			}

			Integer prodStat = null;
			try {
				prodStat = Integer.valueOf(req.getParameter("prodStat").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("prodStat", "商品狀態請輸入數字");
			}

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/prod/update_prod_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			ProdService prodSvc = new ProdService();
			ProdVO prodVO = prodSvc.updateProd(prodId, prodTypeId, storeId, prodName, prodCont, prodPrc, prodTime,
					prodStat);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("prodVO", prodVO); // 資料庫update成功後,正確的的prodVO物件,存入req
			String url = "/prod/listOneProd.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneProd.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addProd.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer prodTypeId = Integer.valueOf(req.getParameter("prodTypeId").trim());
			Integer storeId = Integer.valueOf(req.getParameter("storeId").trim());

			String prodName = req.getParameter("prodName");
			String prodNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,100}$";
			if (prodName == null || prodName.trim().length() == 0) {
				errorMsgs.put("prodName", "商品名稱: 請勿空白");
			} else if (!prodName.trim().matches(prodNameReg)) {
				errorMsgs.put("prodName", "商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在1到100之間");
			}

			String prodCont = req.getParameter("prodCont");
			String prodContReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,500}$";
			if (prodCont == null || prodCont.trim().length() == 0) {
				errorMsgs.put("prodCont", "商品介紹: 請勿空白");
			} else if (!prodCont.trim().matches(prodContReg)) {
				errorMsgs.put("prodCont", "商品介紹: 只能是中、英文字母、數字和_ , 且長度必需在1到500之間");
			}

			Integer prodPrc = null;
			try {
				prodPrc = Integer.valueOf(req.getParameter("prodPrc").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("prodPrc", "商品狀態請輸入數字");
			}

			Date prodTime = null;
			try {
				prodTime = Date.valueOf(req.getParameter("prodTime").trim());
			} catch (IllegalArgumentException e) {
				errorMsgs.put("prodTime", "請輸入日期");
			}

			Integer prodStat = null;
			try {
				prodStat = Integer.valueOf(req.getParameter("prodStat").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("prodStat", "商品狀態請輸入數字");
			}

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/prod/update_prod_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始新增資料 ***************************************/
			ProdService prodSvc = new ProdService();
			prodSvc.addProd(prodTypeId, storeId, prodName, prodCont, prodPrc, prodTime, prodStat);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/front-prod/listAllProds.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllProds.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllProds.jsp

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer prodId = Integer.valueOf(req.getParameter("prodId"));

			/*************************** 2.開始刪除資料 ***************************************/
			ProdService prodSvc = new ProdService();
			prodSvc.deleteProd(prodId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/front-prod/listAllProds.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}

		// 來自front-prod/memberMenu.jsp的請求
		if ("listProdTypeIds_ByStoreId".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer storeId = Integer.valueOf(req.getParameter("storeId"));
			/*************************** 2.開始查詢資料 ****************************************/
			ProdService prodSvc = new ProdService();
			Set<ProdVO> set = prodSvc.getProdTypeIdsByStoreId(storeId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("listProdTypeIds_ByStoreId", set); // 資料庫取出的list物件,存入request

			String url = "/front-prod/memberMenu.jsp"; // 成功轉交 /front-prod/memberMenu.jsp

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		// 來自front-prod/memberMenu.jsp的請求
		if ("listProds_ByProdTypeId".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer prodTypeId = Integer.valueOf(req.getParameter("prodTypeId"));
			/*************************** 2.開始查詢資料 ****************************************/
			ProdService prodSvc = new ProdService();
			Set<ProdVO> set = prodSvc.getProdsByProdTypeId(prodTypeId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("listProds_ByProdTypeId", set); // 資料庫取出的list物件,存入request

			String url = "/front-prod/memberMenu.jsp"; // 成功轉交 /front-prod/memberMenu.jsp

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("listProds_ByCompositeQuery".equals(action)) { // 來自/front-prod/memberMenu.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.將輸入資料轉為Map **********************************/
			// 採用Map<String,String[]> getParameterMap()的方法
			// 注意:an immutable java.util.Map
			Map<String, String[]> map = req.getParameterMap();

			/*************************** 2.開始複合查詢 ***************************************/
			ProdService prodSvc = new ProdService();
			List<ProdVO> list = prodSvc.getAll(map);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("listProds_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
			RequestDispatcher successView = req.getRequestDispatcher("/front-prod/memberMenu.jsp"); // 成功轉交/front-prod/memberMenu.jsp
			successView.forward(req, res);
		}
	}
}