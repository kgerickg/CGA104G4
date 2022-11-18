package com.lucky.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.lucky.model.*;

@WebServlet(name = "LuckyServlet", urlPatterns = {"/LuckyServlet", "/lucky/lucky.do"})

public class LuckyServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		LuckyDAO dao = new LuckyDAO();
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getAll".equals(action)) {
			/***************************開始查詢資料****************************************/
			
			List<LuckyVO> list = dao.getAll();

			/***************************查詢完成,準備轉交(Send the Success view)*************/
			HttpSession session = req.getSession();
			session.setAttribute("list", list);
			// Send the Success view
			String url = "/front-lucky/listAllLucky2_getFromSession.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		
		if ("getAllForMember".equals(action)) {
			req.setAttribute("list", dao.getAll());
			req.getRequestDispatcher("/front-lucky/luckyCart.jsp").forward(req, res);
			return;
		}		

		if ("getOneForMember".equals(action)) {
			Integer luckyId = Integer.parseInt(req.getParameter("luckyId"));
			req.setAttribute("luckyVO", dao.findByPrimaryKey(luckyId));
			req.getRequestDispatcher("/front-lucky/luckyCartForOne.jsp").forward(req, res);			
			return;
		}		
		
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("luckyId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入福袋編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-lucky/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer luckyId = null;
				try {
					luckyId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("福袋編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-lucky/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				LuckyService luckySvc = new LuckyService();
				LuckyVO luckyVO = luckySvc.getOneLucky(luckyId);
				if (luckyVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-lucky/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("luckyVO", luckyVO); 
				String url = "/front-lucky/listOneLucky.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
		}		

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);	
			
				/***************************1.接收請求參數****************************************/
				Integer luckyId = Integer.valueOf(req.getParameter("luckyId"));
				
				/***************************2.開始查詢資料****************************************/
				LuckyService LuckySvc = new LuckyService();
				LuckyVO LuckyVO = LuckySvc.getOneLucky(luckyId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("luckyVO", LuckyVO);
				String url = "/front-lucky/update_lucky_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
		}
		
		if ("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer luckyId = Integer.valueOf(req.getParameter("luckyId").trim());
				
				String lkName = req.getParameter("lkName");
				String lkNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if (lkName == null || lkName.trim().length() == 0) {
					errorMsgs.add("福袋名稱: 請勿空白");
				} else if(!lkName.trim().matches(lkNameReg)) { 
					errorMsgs.add("福袋名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
	            }
				
				Integer lkStat = null;
				try {
					lkStat = Integer.valueOf(req.getParameter("lkStat").trim());
				} catch (NumberFormatException e) {
					lkStat = 0;
					errorMsgs.add("福袋狀態請填0或1.");
				}						
				
				java.sql.Date lkTime = null;
				try {
					lkTime = java.sql.Date.valueOf(req.getParameter("lkTime").trim());
				} catch (IllegalArgumentException e) {
					lkTime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Integer lkPrc = null;
				try {
					lkPrc = Integer.valueOf(req.getParameter("lkPrc").trim());
				} catch (NumberFormatException e) {
					lkPrc = 0;
					errorMsgs.add("價格請填數字.");
				}

				String lkCont = req.getParameter("lkCont");
				String lkContReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,200}$";
				if (lkCont == null || lkCont.trim().length() == 0) {
					errorMsgs.add("福袋內容，請勿空白");
				} else if(!lkCont.trim().matches(lkContReg)) { 
					errorMsgs.add("福袋內容只能是中、英文字母、數字和_ , 且長度必需在2到200之間");
				}

				Integer storeId = Integer.valueOf(req.getParameter("storeId").trim());

				LuckyVO luckyVO = new LuckyVO();
				luckyVO.setLuckyId(luckyId);
				luckyVO.setLkName(lkName);
				luckyVO.setLkStat(lkStat);
				luckyVO.setLkTime(lkTime);
				luckyVO.setLkPrc(lkPrc);
				luckyVO.setLkCont(lkCont);
				luckyVO.setStoreId(storeId);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("luckyVO", luckyVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-lucky/update_lucky_input.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				/***************************2.開始修改資料*****************************************/
				LuckyService LuckySvc = new LuckyService();
				luckyVO = LuckySvc.updateLucky(luckyId, storeId, lkStat, lkName, lkCont, lkPrc, lkTime);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/								
				req.setAttribute("luckyVO", luckyVO); 
                String url = "/front-lucky/listOneLucky.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);   
				successView.forward(req, res);
		}

        if ("insert".equals(action)) {  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			
			String lkName = req.getParameter("lkName");
			String lkNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
			if (lkName == null || lkName.trim().length() == 0) {
				errorMsgs.add("福袋名稱，請勿空白");
			} else if(!lkName.trim().matches(lkNameReg)) { 
				errorMsgs.add("福袋名稱只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
            }
			
			Integer lkStat = null;
			try {
				lkStat = Integer.valueOf(req.getParameter("lkStat").trim());
			} catch (NumberFormatException e) {
				lkStat = 0;
				errorMsgs.add("福袋狀態請填0或1.");
			}						
			
			java.sql.Date lkTime = null;
			try {
				lkTime = java.sql.Date.valueOf(req.getParameter("lkTime").trim());
			} catch (IllegalArgumentException e) {
				lkTime = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}

			Integer lkPrc = null;
			try {
				lkPrc = Integer.valueOf(req.getParameter("lkPrc").trim());
			} catch (NumberFormatException e) {
				lkPrc = 0;
				errorMsgs.add("價格請填數字.");
			}

			String lkCont = req.getParameter("lkCont");
			String lkContReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,200}$";
			if (lkCont == null || lkCont.trim().length() == 0) {
				errorMsgs.add("福袋內容，請勿空白");
			} else if(!lkCont.trim().matches(lkContReg)) { 
				errorMsgs.add("福袋內容只能是中、英文字母、數字和_ , 且長度必需在2到200之間");
			}
				
				Integer storeId = Integer.valueOf(req.getParameter("storeId").trim());

				LuckyVO luckyVO = new LuckyVO();
				luckyVO.setLkName(lkName);
				luckyVO.setLkStat(lkStat);
				luckyVO.setLkTime(lkTime);
				luckyVO.setLkPrc(lkPrc);
				luckyVO.setLkCont(lkCont);
				luckyVO.setStoreId(storeId);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("luckyVO", luckyVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-lucky/addLucky.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				LuckyService LuckySvc = new LuckyService();
				luckyVO = LuckySvc.addLucky(storeId, lkStat, lkName, lkCont, lkPrc, lkTime);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-lucky/listAllLucky.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
		}
        

		       
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數***************************************/
				Integer luckyId = Integer.valueOf(req.getParameter("luckyId"));
				
				/***************************2.開始刪除資料***************************************/
				LuckyService LuckySvc = new LuckyService();
				LuckySvc.deleteLucky(luckyId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/				
				String url = "/front-lucky/listAllLucky.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
		}
		
		if ("getImg".equals(action)) {
			LuckyService LuckySvc = new LuckyService();
			Integer luckyId = Integer.parseInt(req.getParameter("luckyId"));
			res.getOutputStream().write(LuckySvc.getImgById(luckyId));
		}
	}
}
