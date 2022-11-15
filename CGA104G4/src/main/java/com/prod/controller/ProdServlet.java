package com.prod.controller;

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
import com.prodtype.model.ProdTypeVO;
import com.store.model.StoreVO;

public class ProdServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("prodId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/manageProd.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer prodId = null;
				try {
					prodId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/manageProd.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				ProdService prodSvc = new ProdService();
				ProdVO prodVO = prodSvc.getOneProd(prodId);
				if (prodVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/manageProd.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("prodVO", prodVO); // 資料庫取出的prodVO物件,存入req
				String url = "/listOneProd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneProd.jsp
				successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/manageProd.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllProds.jsp 或  /front-prod/listProds_ByStoreId.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/front-prod/listAllProd.jsp】 或  【/front-prod/listProds_ByStoreId.jsp】 或 【 /front-prod/listAllProdTypes.jsp】		
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer prodId = new Integer(req.getParameter("prodId"));
				
				/***************************2.開始查詢資料****************************************/
				ProdService prodSvc = new ProdService();
				ProdVO prodVO = prodSvc.getOneProd(prodId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("prodVO", prodVO); // 資料庫取出的prodVO物件,存入req
				String url = "/prod/update_prod_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_prod_input.jsp
				successView.forward(req, res);

			/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_prod_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/front-prod/listAllProds.jsp】 或  【/front-prod/listProds_ByStoreId.jsp】 或 【 /front-prod/listAllProdTypes.jsp】
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer prodId = new Integer(req.getParameter("prodId").trim());
				
				String prodName = req.getParameter("prodName");
				String prodNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,100}$";
				if (prodName == null || prodName.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} else if(!prodName.trim().matches(prodNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在1到100之間");
	            }
				
				String prodCont = req.getParameter("prodCont").trim();
				String prodContReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,100}$";
				if (prodCont == null || prodCont.trim().length() == 0) {
					errorMsgs.add("商品介紹: 請勿空白");
				} else if(!prodCont.trim().matches(prodContReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品介紹: 只能是中、英文字母、數字和_ , 且長度必需在1到100之間");
	            }			
				
				Date prodTime = null;
				try {
					prodTime = Date.valueOf(req.getParameter("prodTime").trim());
				} catch (IllegalArgumentException e) {
					prodTime=new Date(System.currentTimeMillis());
					errorMsgs.add("請輸入上架日期!");
				}

				Integer prodStat = null;
				try {
					prodStat = new Integer(req.getParameter("prodStat").trim());
				} catch (NumberFormatException e) {
					prodStat = 0;
					errorMsgs.add("商品狀態請填數字.");
				}

				Integer prodPrc = null;
				try {
					prodPrc = new Integer(req.getParameter("prodPrc").trim());
				} catch (NumberFormatException e) {
					prodPrc = 0;
					errorMsgs.add("商品價格請填數字.");
				}

				Integer storeId = new Integer(req.getParameter("storeId").trim());
				
				Integer prodTypeId = new Integer(req.getParameter("prodTypeId").trim());

				ProdVO prodVO = new ProdVO();
				prodVO.setProdId(prodId);
				prodVO.setProdStat(prodStat);
				prodVO.setProdName(prodName);
				prodVO.setProdCont(prodCont);
				prodVO.setProdPrc(prodPrc);
				prodVO.setProdTime(prodTime);

				//以下3行程式碼因為要配合Hibernate的prodVO,以能夠使用Hibernate的強大功能,所以這裏顯得比較麻煩!!
				StoreVO storeVO = new StoreVO();
				storeVO.setStoreId(storeId);
				prodVO.setStoreVO(storeVO);
				
				ProdTypeVO prodTypeVO = new ProdTypeVO();
				prodTypeVO.setProdTypeId(prodTypeId);
				prodVO.setProdTypeVO(prodTypeVO);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("prodVO", prodVO); // 含有輸入格式錯誤的prodVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-prod/update_prod_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ProdService prodSvc = new ProdService();
				prodVO = prodSvc.updateProd(prodId, storeId, prodTypeId, prodStat, prodName, prodCont, prodPrc, prodTime);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/				
				ProdService prodSvc1 = new ProdService();
				if(requestURL.equals("/front-prod/listProds_ByStoreId.jsp") || requestURL.equals("/front-prod/listAllProdTypes.jsp"))
					req.setAttribute("listProds_ByStoreId",prodSvc1.getProdsByStoreId(storeId)); // 資料庫取出的list物件,存入request

                String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-prod/update_prod_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addProd.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/		
				String prodName = req.getParameter("prodName");
				String prodNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,100}$";
				if (prodName == null || prodName.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} else if(!prodName.trim().matches(prodNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在1到100之間");
	            }
				
				String prodCont = req.getParameter("prodCont").trim();
				String prodContReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,100}$";
				if (prodCont == null || prodCont.trim().length() == 0) {
					errorMsgs.add("商品介紹: 請勿空白");
				} else if(!prodCont.trim().matches(prodContReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品介紹: 只能是中、英文字母、數字和_ , 且長度必需在1到100之間");
	            }			
				
				Date prodTime = null;
				try {
					prodTime = Date.valueOf(req.getParameter("prodTime").trim());
				} catch (IllegalArgumentException e) {
					prodTime=new Date(System.currentTimeMillis());
					errorMsgs.add("請輸入上架日期!");
				}

				Integer prodStat = null;
				try {
					prodStat = new Integer(req.getParameter("prodStat").trim());
				} catch (NumberFormatException e) {
					prodStat = 0;
					errorMsgs.add("商品狀態請填數字.");
				}

				Integer prodPrc = null;
				try {
					prodPrc = new Integer(req.getParameter("prodPrc").trim());
				} catch (NumberFormatException e) {
					prodPrc = 0;
					errorMsgs.add("商品價格請填數字.");
				}

				Integer storeId = new Integer(req.getParameter("storeId").trim());
				
				Integer prodTypeId = new Integer(req.getParameter("prodTypeId").trim());

				ProdVO prodVO = new ProdVO();
				prodVO.setProdStat(prodStat);
				prodVO.setProdName(prodName);
				prodVO.setProdCont(prodCont);
				prodVO.setProdPrc(prodPrc);
				prodVO.setProdTime(prodTime);

				//以下3行程式碼因為要配合Hibernate的prodVO,以能夠使用Hibernate的強大功能,所以這裏顯得比較麻煩!!
				StoreVO storeVO = new StoreVO();
				storeVO.setStoreId(storeId);
				prodVO.setStoreVO(storeVO);
				
				ProdTypeVO prodTypeVO = new ProdTypeVO();
				prodTypeVO.setProdTypeId(prodTypeId);
				prodVO.setProdTypeVO(prodTypeVO);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("prodVO", prodVO); // 含有輸入格式錯誤的prodVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-prod/update_prod_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始新增資料***************************************/
				ProdService prodSvc = new ProdService();
				prodVO = prodSvc.addProd(storeId, prodTypeId, prodStat, prodName, prodCont, prodPrc, prodTime);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-prod/listAllProds.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllProds.jsp
				successView.forward(req, res);				
				
			/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-prod/addProd.jsp");
				failureView.forward(req, res);
			}
		}
		
       
		if ("delete".equals(action)) { // 來自listAllProds.jsp 或  /listProds_ByStoreId.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/front-prod/listAllProds.jsp】 或  【/front-prod/listProds_ByStoreId.jsp】 或 【 /front-prod/listAllProdTypes.jsp】

			try {
				/***************************1.接收請求參數***************************************/
				Integer prodId = new Integer(req.getParameter("prodId"));
				
				/***************************2.開始刪除資料***************************************/
				ProdService prodSvc = new ProdService();
				ProdVO prodVO = prodSvc.getOneProd(prodId);
				prodSvc.deleteProd(prodId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				ProdService prodSvc1 = new ProdService();
				if(requestURL.equals("/front-prod/listAllProds.jsp") || requestURL.equals("/front-prod/listAllProdTypes.jsp"))
				  //req.setAttribute("listProds_ByStoreId",prodSvc1.getProdsByDeptno(prodVO.getDeptno())); // 資料庫取出的list物件,存入request
				    req.setAttribute("listProds_ByStoreId",prodSvc1.getProdsByStoreId(prodVO.getStoreVO().getStoreId())); // 資料庫取出的list物件,存入request
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
			/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
	
		if ("listProds_ByCompositeQuery".equals(action)) { // 來自manageProd.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				Map<String, String[]> map = req.getParameterMap();
				
				/***************************2.開始複合查詢***************************************/
				ProdService prodSvc = new ProdService();
				List<ProdVO> list  = prodSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listProds_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/front-prod/listProds_ByCompositeQuery.jsp"); // 成功轉交listProds_ByCompositeQuery.jsp
				successView.forward(req, res);
				
			/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/manageProd.jsp");
				failureView.forward(req, res);
			}
		}	
	}
}