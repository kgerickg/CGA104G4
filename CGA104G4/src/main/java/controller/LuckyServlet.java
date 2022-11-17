package com.lucky.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.store.model.StoreService;
import com.lucky.model.LuckyService;
import com.lucky.model.LuckyVO;
import com.lucky.model.*;

public class LuckyServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getAll".equals(action)) {
			/***************************開始查詢資料 ****************************************/
			LuckyDAO dao = new LuckyDAO();
			List<LuckyVO> list = dao.getAll();

			/***************************查詢完成,準備轉交(Send the Success view)*************/
			HttpSession session = req.getSession();
			session.setAttribute("list", list);    // 資料庫取出的list物件,存入session
			// Send the Success view
			String url = "/lucky/listAllLucky2_getFromSession.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交listAllLucky2_getFromSession.jsp
			successView.forward(req, res);
			return;
		}


		if ("getOne_For_Update".equals(action)) { // 來自listAllLucky.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/lucky/listAllLucky.jsp】 		
			
				/***************************1.接收請求參數****************************************/
				Integer luckyId = Integer.valueOf(req.getParameter("luckyId"));
				
				/***************************2.開始查詢資料****************************************/
				LuckyService LuckySvc = new LuckyService();
				LuckyVO LuckyVO = LuckySvc.getOneLucky(luckyId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("LuckyVO", LuckyVO); // 資料庫取出的LuckyVO物件,存入req
				String url = "/lucky/update_lucky_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_lucky_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_lucky_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/lucky/listAllLucky.jsp】 
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer luckyId = Integer.valueOf(req.getParameter("luckyId").trim());
				
				String lkName = req.getParameter("lkName");
				String lkNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if (lkName == null || lkName.trim().length() == 0) {
					errorMsgs.add("福袋名稱: 請勿空白");
				} else if(!lkName.trim().matches(lkNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("福袋名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
	            }
				
				Integer lkStat = null;
				try {
					lkStat = Integer.valueOf(req.getParameter("lkPrc").trim());
				} catch (NumberFormatException e) {
					lkStat = 0;
					errorMsgs.add("福袋狀態請填0或1.");
				}						
				
				java.sql.Date lkTime = null;
				try {
					lkTime = java.sql.Date.valueOf(req.getParameter("lkTime").trim());
				} catch (IllegalArgumentException e) {
					lkTime=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Integer lkPrc = null;
				try {
					lkPrc = Integer.valueOf(req.getParameter("lkPrc").trim());
				} catch (NumberFormatException e) {
					lkPrc = 0;
					errorMsgs.add("價格請填數字.");
				}

				String lkCont = req.getParameter("lkName");
				String lkContReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,200}$";
				if (lkCont == null || lkCont.trim().length() == 0) {
					errorMsgs.add("福袋內容: 請勿空白");
				} else if(!lkCont.trim().matches(lkContReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("福袋內容: 只能是中、英文字母、數字和_ , 且長度必需在2到200之間");
				}

				Integer storeId = Integer.valueOf(req.getParameter("storeId").trim());

				LuckyVO LuckyVO = new LuckyVO();
				LuckyVO.setLuckyId(luckyId);
				LuckyVO.setLkName(lkName);
				LuckyVO.setLkStat(lkStat);
				LuckyVO.setLkTime(lkTime);
				LuckyVO.setLkPrc(lkPrc);
				LuckyVO.setLkCont(lkCont);
				LuckyVO.setStoreId(storeId);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("LuckyVO", LuckyVO); // 含有輸入格式錯誤的LuckyVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/lucky/update_lucky_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				LuckyService LuckySvc = new LuckyService();
				LuckyVO = LuckySvc.updateLucky(luckyId, storeId, lkStat, lkName, lkCont, lkPrc, lkTime);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/				
				StoreService storeSvc = new StoreService();
				if(requestURL.equals("/store/listLuckys_ByStoreId.jsp") || requestURL.equals("/store/listAllstore.jsp"))
					req.setAttribute("listLuckys_ByStoreId",storeSvc.getLuckysByStoreId(storeId)); // 資料庫取出的list物件,存入request

                String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // 來自addLucky.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			Integer luckyId = Integer.valueOf(req.getParameter("luckyId").trim());
			
			String lkName = req.getParameter("lkName");
			String lkNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
			if (lkName == null || lkName.trim().length() == 0) {
				errorMsgs.add("福袋名稱: 請勿空白");
			} else if(!lkName.trim().matches(lkNameReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("福袋名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
            }
			
			Integer lkStat = null;
			try {
				lkStat = Integer.valueOf(req.getParameter("lkPrc").trim());
			} catch (NumberFormatException e) {
				lkStat = 0;
				errorMsgs.add("福袋狀態請填0或1.");
			}						
			
			java.sql.Date lkTime = null;
			try {
				lkTime = java.sql.Date.valueOf(req.getParameter("lkTime").trim());
			} catch (IllegalArgumentException e) {
				lkTime=new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}

			Integer lkPrc = null;
			try {
				lkPrc = Integer.valueOf(req.getParameter("lkPrc").trim());
			} catch (NumberFormatException e) {
				lkPrc = 0;
				errorMsgs.add("價格請填數字.");
			}

			String lkCont = req.getParameter("lkName");
			String lkContReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,200}$";
			if (lkCont == null || lkCont.trim().length() == 0) {
				errorMsgs.add("福袋內容: 請勿空白");
			} else if(!lkCont.trim().matches(lkContReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("福袋內容: 只能是中、英文字母、數字和_ , 且長度必需在2到200之間");
			}
				
				Integer storeId = Integer.valueOf(req.getParameter("storeId").trim());

				LuckyVO LuckyVO = new LuckyVO();
				LuckyVO.setLkName(lkName);
				LuckyVO.setLkStat(lkStat);
				LuckyVO.setLkTime(lkTime);
				LuckyVO.setLkPrc(lkPrc);
				LuckyVO.setLkCont(lkCont);
				LuckyVO.setStoreId(storeId);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("LuckyVO", LuckyVO); // 含有輸入格式錯誤的LuckyVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/lucky/addLucky.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				LuckyService LuckySvc = new LuckyService();
				LuckyVO = LuckySvc.addLucky(storeId, lkStat, lkName, lkCont, lkPrc, lkTime);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/lucky/listAllLucky.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllLucky.jsp
				successView.forward(req, res);				
		}
		
       
		if ("delete".equals(action)) { // 來自listAllLucky.jsp 或  /store/listLuckys_ByStoreId.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/lucky/listAllLucky.jsp】 或  【/store/listLuckyS_BystoreId.jsp】 或 【 /store/listAllstore.jsp】

				/***************************1.接收請求參數***************************************/
				Integer luckyId = Integer.valueOf(req.getParameter("luckyId"));
				
				/***************************2.開始刪除資料***************************************/
				LuckyService LuckySvc = new LuckyService();
				LuckyVO LuckyVO = LuckySvc.getOneLucky(luckyId);
				LuckySvc.deleteLucky(luckyId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				StoreService storeSvc = new StoreService();
				if(requestURL.equals("/store/listLuckyS_BystoreId.jsp") || requestURL.equals("/store/listAllstore.jsp"))
					req.setAttribute("listLuckyS_BystoreId",storeSvc.getLuckysByStoreId(LuckyVO.getStoreId())); // 資料庫取出的list物件,存入request
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
		
		if ("listLuckyS_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				Map<String, String[]> map = req.getParameterMap();
				
				/***************************2.開始複合查詢***************************************/
				LuckyService LuckySvc = new LuckyService();
				List<LuckyVO> list  = LuckySvc.getAll();
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listLuckyS_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/lucky/listLuckyS_ByCompositeQuery.jsp"); // 成功轉交listLuckyS_ByCompositeQuery.jsp
				successView.forward(req, res);
		}
	}
}
