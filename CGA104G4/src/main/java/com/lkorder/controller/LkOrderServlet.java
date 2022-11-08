package com.lkorder.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lkorder.model.LkOrderService;
import com.lkorder.model.LkOrderVO;

@WebServlet(name = "LkOrderServlet", urlPatterns = {"/LkOrderServlet", "/back-lkorder/lkorderback.do"} )
public class LkOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自【IndexLkorder.jsp】的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("lkOrderId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入福袋訂單編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-lkorder/BackIndexLkorder.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer lkOrderId = null;
			try {
				lkOrderId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("福袋訂單編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-lkorder/BackIndexLkorder.jsp"); 
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			LkOrderService lkorderSvc = new LkOrderService();
			LkOrderVO lkorderVO = lkorderSvc.lkorderVO(lkOrderId);
			if (lkorderVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-lkorder/BackIndexLkorder.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("lkorderVO", lkorderVO); // 資料庫取出的empVO物件,存入req
			String url = "/back-lkorder/BackListOneLkorder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自【listAllEmp.jsp】的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer lkOrderId = Integer.valueOf(req.getParameter("lkOrderId"));
			/*************************** 2.開始查詢資料 ****************************************/
			LkOrderService lkorderSvc = new LkOrderService();
			LkOrderVO lkorderVO = lkorderSvc.lkorderVO(lkOrderId);
			

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("lkorderVO", lkorderVO); // 資料庫取出的empVO物件,存入req
			String url = "/back-lkorder/BackUpdateLkOrderInput.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自【UpdateLkOrderInput.jsp】的請求-----------------------------------------------------

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			
			
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer lkOrderId = Integer.valueOf(req.getParameter("lkOrderId").trim()); // 設定自動編號

			Integer memId = null;
			try {
				memId = Integer.valueOf(req.getParameter("memId").trim());
				if (memId <= 0) {
					errorMsgs.add("數字不得小於零");
                }
			} catch (Exception e) {
				memId = 0;
				errorMsgs.add("請輸入正確會員編號");
			}	
			
			Integer lkId = null;
			try {
				lkId = Integer.valueOf(req.getParameter("lkId").trim());
			} catch (Exception e) {
				lkId = 0;
				errorMsgs.add("請輸入正確福袋編號");
			}

			Integer lkTodayId = null;
			try {
				lkTodayId = Integer.valueOf(req.getParameter("lkTodayId").trim());
			} catch (Exception e) {
				lkTodayId = 0;
				errorMsgs.add("請輸入正確每日福袋編號");
			}

			Integer lkOrdAmt = null;
			try {
				lkOrdAmt = Integer.valueOf(req.getParameter("lkOrdAmt").trim());
			} catch (Exception e) {
				lkOrdAmt = 0;
				errorMsgs.add("請輸入正確金額");
			}

			Integer lkOrdStat = null;
			try {
				lkOrdStat = Integer.valueOf(req.getParameter("lkOrdStat").trim());
			} catch (Exception e) {
				errorMsgs.add("請輸入正確訂單狀態");
			}

			java.sql.Date lkOrdTimeS = null;
			try {
				lkOrdTimeS = java.sql.Date.valueOf(req.getParameter("lkOrdTimeS"));
			} catch (IllegalArgumentException e) {
				lkOrdTimeS = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入正確訂單成立日期");
			}
			

			java.sql.Date lkOrdTaketime = null;
			try {
				lkOrdTaketime = java.sql.Date.valueOf(req.getParameter("lkOrdTaketime"));
			} catch (IllegalArgumentException e) {
				lkOrdTaketime = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入正確取貨日期");
			}

			java.sql.Date lkOrdTimeE = null;
			try {
				lkOrdTimeE = java.sql.Date.valueOf(req.getParameter("lkOrdTimeE"));
			} catch (IllegalArgumentException e) {
				lkOrdTimeE = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入正確訂單完成日期");
			}

			LkOrderVO lkorderVO = new LkOrderVO();
			lkorderVO.setLkOrderId(lkOrderId);
			lkorderVO.setLkId(lkId);
			lkorderVO.setMemId(memId);
			lkorderVO.setLkTodayId(lkTodayId);
			lkorderVO.setLkOrdAmt(lkOrdAmt);
			lkorderVO.setLkOrdStat(lkOrdStat);
			lkorderVO.setLkOrdTimeS(lkOrdTimeS);
			lkorderVO.setLkOrdTaketime(lkOrdTaketime);
			lkorderVO.setLkOrdTimeE(lkOrdTimeE);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("lkorderVO", lkorderVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-lkorder/BackUpdateLkOrderInput.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			LkOrderService lkorderSvc = new LkOrderService();
			lkorderVO = lkorderSvc.update(lkOrderId, lkId, memId, lkTodayId, lkOrdAmt, lkOrdStat, lkOrdTimeS, lkOrdTaketime,
					lkOrdTimeE);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("lkorderVO", lkorderVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back-lkorder/BackListOneLkorder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
		
		if ("updata".equals(action)) {  // 廢CODE 但刪掉會崩
			LkOrderService lkorderService = new LkOrderService();
			List<LkOrderVO> list = lkorderService.getAll();
			req.setAttribute( "list" , list);
			req.getRequestDispatcher("/back-lkorder/BackListAllLkorder.jsp").forward(req, res);
		}

		if ("insert".equals(action)) { // 來自【AddLkorder.jsp】的請求-----------------------------------------------------------

			 List<String> errorMsgs = new LinkedList<String>();
//			 Store this set in the request scope, in case we need to
//			 send the ErrorPage view.
			 req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer lkId = null;
			try {
				lkId = Integer.valueOf(req.getParameter("lkId").trim());
				if (lkId <= 0) {
					errorMsgs.add("數字不得小於零");
                }
			} catch (Exception e) {
				lkId = 0;
				 errorMsgs.add("請輸入正確福袋編號");
			}

			Integer memId = null;
			try {
				memId = Integer.valueOf(req.getParameter("memId").trim());
			} catch (Exception e) {
				memId = 0;
				 errorMsgs.add("請輸入正確會員編號");
			}

			Integer lkTodayId = null;
			try {
				lkTodayId = Integer.valueOf(req.getParameter("lkTodayId").trim());
			} catch (Exception e) {
				lkTodayId = 0;
				 errorMsgs.add("請輸入正確每日福袋編號");
			}

			Integer lkOrdAmt = null;
			try {
				lkOrdAmt = Integer.valueOf(req.getParameter("lkOrdAmt").trim());
			} catch (Exception e) {
				lkOrdAmt = 0;
				 errorMsgs.add("請輸入正確金額");
			}

			Integer lkOrdStat = null;
			try {
				lkOrdStat = Integer.valueOf(req.getParameter("lkOrdStat").trim());
			} catch (Exception e) {
				 errorMsgs.add("請輸入正確訂單狀態");
			}

			java.sql.Date lkOrdTimeS = null;
			try {
				lkOrdTimeS = java.sql.Date.valueOf(req.getParameter("lkOrdTimeS"));
			} catch (IllegalArgumentException e) {
				lkOrdTimeS = new java.sql.Date(System.currentTimeMillis());
				 errorMsgs.add("請輸入正確訂單成立日期");
			}

			java.sql.Date lkOrdTaketime = null;
			try {
				lkOrdTaketime = java.sql.Date.valueOf(req.getParameter("lkOrdTaketime"));
			} catch (IllegalArgumentException e) {
				lkOrdTaketime = new java.sql.Date(System.currentTimeMillis());
				 errorMsgs.add("請輸入正確取貨日期");
			}

			java.sql.Date lkOrdTimeE = null;
			try {
				lkOrdTimeE = java.sql.Date.valueOf(req.getParameter("lkOrdTimeE"));
			} catch (IllegalArgumentException e) {
				lkOrdTimeE = new java.sql.Date(System.currentTimeMillis());
				 errorMsgs.add("請輸入正確訂單完成日期");
			}

			LkOrderVO lkorderVO = new LkOrderVO();
			lkorderVO.setLkId(lkId);
			lkorderVO.setMemId(memId);
			lkorderVO.setLkTodayId(lkTodayId);
			lkorderVO.setLkOrdAmt(lkOrdAmt);
			lkorderVO.setLkOrdStat(lkOrdStat);
			lkorderVO.setLkOrdTimeS(lkOrdTimeS);
			lkorderVO.setLkOrdTaketime(lkOrdTaketime);
			lkorderVO.setLkOrdTimeE(lkOrdTimeE);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("lkorderVO", lkorderVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-lkorder/BackAddLkorder.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			LkOrderService lkorderSvc = new LkOrderService();
			lkorderSvc.insert(lkId, memId, lkTodayId, lkOrdAmt, lkOrdStat, lkOrdTimeS, lkOrdTaketime, lkOrdTimeE);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back-lkorder/BackListAllLkorder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自【ListAllLkorder.jsp】-----------------------------------------------------------

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer lkOrderId = Integer.valueOf(req.getParameter("lkOrderId"));

			/*************************** 2.開始刪除資料 ***************************************/
			LkOrderService lkorderSvc = new LkOrderService();
			lkorderSvc.delete(lkOrderId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/back-lkorder/BackListAllLkorder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}
