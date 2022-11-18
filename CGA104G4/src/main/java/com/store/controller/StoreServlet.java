package com.store.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.store.model.StoreService;
import com.store.model.StoreVO;
@WebServlet(name = "StoreServlet", urlPatterns = {"/StoreServlet", "/store/store.do"})
public class StoreServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("storeId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入會員編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/store/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer storeId = null;
			try {
				storeId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("會員編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/store/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 2.開始查詢資料 *****************************************/
			StoreService storeSvc = new StoreService();
			StoreVO storeVO = storeSvc.getOneStore(storeId);
			if (storeVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/store/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("storeVO", storeVO); // 資料庫取出的StoreVO物件,存入req
			String url = "/store/listOneStore.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer storeId = Integer.valueOf(req.getParameter("storeId"));

			/*************************** 2.開始查詢資料 ****************************************/
			StoreService storeSvc = new StoreService();
			StoreVO storeVO = storeSvc.getOneStore(storeId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("storeVO", storeVO); // 資料庫取出的StoreVO物件,存入req
			String url = "/store/update_store_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_store_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer storeId = Integer.valueOf(req.getParameter("storeId").trim());
			String storeAcc = req.getParameter("storeAcc").trim();
			if (storeAcc == null || storeAcc.trim().length() == 0) {
				errorMsgs.add("帳號請勿空白");
			}
			String storePwd = req.getParameter("storePwd").trim();
			if (storePwd == null || storePwd.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			}
			String storeName = req.getParameter("storeName");
			String storeNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (storeName == null || storeName.trim().length() == 0) {
				errorMsgs.add("會員姓名: 請勿空白");
			} else if (!storeName.trim().matches(storeNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String storeGui = req.getParameter("storeGui").trim();
			String storeRep = req.getParameter("storeRep").trim();
			String storeTel = req.getParameter("storeTel").trim();
			String storeFax = req.getParameter("storeFax").trim();
			String storeAd = req.getParameter("storeAd").trim();
			String storeCon = req.getParameter("storeCon").trim();
			String storeConTel = req.getParameter("storeConTel").trim();
			String storeConAd = req.getParameter("storeConAd").trim();
			String storeEmail = req.getParameter("storeEmail").trim();

			
			StoreVO storeVO = new StoreVO();
			storeVO.setStoreId(storeId);
			storeVO.setStoreAcc(storeAcc);
			storeVO.setStorePwd(storePwd);
			storeVO.setStoreName(storeName);
			storeVO.setStoreGui(storeGui);
			storeVO.setStoreRep(storeRep);
			storeVO.setStoreTel(storeTel);
			storeVO.setStoreFax(storeFax);
			storeVO.setStoreAd(storeAd);
			storeVO.setStoreCon(storeCon);
			storeVO.setStoreConTel(storeConTel);
			storeVO.setStoreConAd(storeConAd);
			storeVO.setStoreEmail(storeEmail);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("storeVO", storeVO); // 含有輸入格式錯誤的StoreVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/store/update_store_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			StoreService storeSvc = new StoreService();
			storeVO = storeSvc.updateStore(storeId, storeAcc, storePwd, storeName, storeGui, storeRep, storeTel, storeFax, storeAd, storeCon, storeConTel, storeConAd, storeEmail );
			
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("storeVO", storeVO); // 資料庫update成功後,正確的StoreVO物件,存入req
			String url = "/store/listOneStore.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneStore.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addStore.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer storeId = Integer.valueOf(req.getParameter("storeId").trim());
			String storeAcc = req.getParameter("storeAcc").trim();
			if (storeAcc == null || storeAcc.trim().length() == 0) {
				errorMsgs.add("帳號請勿空白");
			}
			String storePwd = req.getParameter("storePwd").trim();
			String storePwdReg = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,18}$";
			if (storePwd == null || storePwd.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			} else if (!storePwd.trim().matches(storePwdReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("至少一個數字,一個小寫英文字母,一個大寫英文字母,長度 6 ~ 16字元");
			}
			String storeName = req.getParameter("storeName");
			String storeNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (storeName == null || storeName.trim().length() == 0) {
				errorMsgs.add("店家名稱: 請勿空白");
			} else if (!storeName.trim().matches(storeNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("店家名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}
			String storeGui = req.getParameter("storeGui").trim();
			String storeGuiReg = "^\\d{8}$";
			if(!storeGui.trim().matches(storeGuiReg)) {
				errorMsgs.add("統一編號格式不符");
			}
			String storeRep = req.getParameter("storeRep").trim();
			String storeRepReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if(!storeRep.trim().matches(storeRepReg)) {
				errorMsgs.add("負責人姓名只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}
			String storeTel = req.getParameter("storeTel").trim();
			String storeTelReg = "^0[1-9]{1}[0-9]{8}$";
			if(!storeTel.trim().matches(storeTelReg)) {
				errorMsgs.add("電話號碼格式錯誤");
			}
			String storeFax = req.getParameter("storeFax").trim();
			String storeFaxReg = "^0[1-9]{1}[0-9]{8}$";
			if(!storeFax.trim().matches(storeFaxReg)) {
				errorMsgs.add("傳真號碼格式錯誤");
			}
			String storeAd = req.getParameter("storeAd").trim();
			String storeCon = req.getParameter("storeCon").trim();
			String storeConTel = req.getParameter("storeConTel").trim();
			String storeConAd = req.getParameter("storeConAd").trim();
			String storeEmail = req.getParameter("storeEmail").trim();
			
			StoreVO storeVO = new StoreVO();
			storeVO.setStoreId(storeId);
			storeVO.setStoreAcc(storeAcc);
			storeVO.setStorePwd(storePwd);
			storeVO.setStoreName(storeName);
			storeVO.setStoreGui(storeGui);
			storeVO.setStoreRep(storeRep);
			storeVO.setStoreTel(storeTel);
			storeVO.setStoreFax(storeFax);
			storeVO.setStoreAd(storeAd);
			storeVO.setStoreCon(storeCon);
			storeVO.setStoreConTel(storeConTel);
			storeVO.setStoreConAd(storeConAd);
			storeVO.setStoreEmail(storeEmail);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("storeVO", storeVO); // 含有輸入格式錯誤的StoreVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/store/addStore.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			StoreService storeSvc = new StoreService();
			storeVO = storeSvc.addStore(storeAcc, storePwd, storeName, storeGui, storeRep, storeTel, storeFax, storeAd, storeCon, storeConTel, storeConAd, storeEmail);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/store/listAllStore.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer storeId = Integer.valueOf(req.getParameter("storeId"));

			/*************************** 2.開始刪除資料 ***************************************/
			StoreService storeSvc = new StoreService();
			storeSvc.deleteStore(storeId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/store/listAllStore.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}
