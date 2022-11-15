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

@WebServlet(name = "LkOrderFrontServlet", urlPatterns = {"/LkOrderFrontServlet", "/front-lkorder/lkorderfront.do"})
public class LkOrderFrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自【FrontIndexLkorder.jsp】的請求
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
				RequestDispatcher failureView = req.getRequestDispatcher("/front-lkorder/FrontIndexLkorder.jsp");
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
				RequestDispatcher failureView = req.getRequestDispatcher("/front-lkorder/FrontIndexLkorder.jsp"); 
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
				RequestDispatcher failureView = req.getRequestDispatcher("/front-lkorder/FrontIndexLkorder.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("lkorderVO", lkorderVO); // 資料庫取出的empVO物件,存入req
			String url = "/front-lkorder/FrontListOneLkorder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("listAllMember".equals(action)) { // 來自【listAllEmp.jsp】的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer MemId = Integer.valueOf(req.getParameter("memId"));
			/*************************** 2.開始查詢資料 ****************************************/
			LkOrderService lkorderSvc = new LkOrderService();
			LkOrderVO lkorderVO = lkorderSvc.lkordermemidVO(MemId);
			

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("lkordermemidVO", lkorderVO); // 資料庫取出的empVO物件,存入req
			String url = "/front-lkorder/FrontListAllLkorder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

	}
}
