package com.prod.controller;

import java.io.*;
import java.util.*;
import java.sql.Timestamp;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.photo.model.PhotoService;
import com.photo.model.PhotoVO;
import com.prod.model.ProdService;
import com.prod.model.ProdVO;

@WebServlet("/prod/prod.do")
@MultipartConfig
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
				errorMsgs.put("prodId", "請輸入商品編號！");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-prod/storeMenu.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer prodId = null;
			try {
				prodId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("prodId", "商品編號格式不正確！");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-prod/storeMenu.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ProdService prodSvc = new ProdService();
			ProdVO prodVO = prodSvc.getOneProd(prodId);
			if (prodVO == null) {
				errorMsgs.put("prodId", "查無資料！");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-prod/storeMenu.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("prodVO", prodVO); // 資料庫取出的prodVO物件,存入req
			String url = "/front-prod/listOneProd.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneProd.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自/front-prod/storeMenu.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer prodId = Integer.valueOf(req.getParameter("prodId"));

			/*************************** 2.開始查詢資料 ****************************************/
			ProdService prodSvc = new ProdService();
			ProdVO prodVO = prodSvc.getOneProd(prodId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			String param = "?prodId=" 	  + prodVO.getProdId()+
						   "&prodTypeId=" + prodVO.getProdTypeId()+
						   "&storeId="    + prodVO.getStoreId()+
						   "&prodName="   + prodVO.getProdName()+
						   "&prodCont="   + prodVO.getProdCont()+
						   "&prodPrc=" 	  + prodVO.getProdPrc()+
						   "&prodStat="	  + prodVO.getProdStat()+
						   "&prodTime="	  + prodVO.getProdTime();
			String url = "/front-prod/storeProdUpdate.jsp"+param;
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 /front-prod/storeProdUpdate.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自/front-prod/storeProdUpdate.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer prodId = Integer.valueOf(req.getParameter("prodId").trim());
			Integer prodTypeId = Integer.valueOf(req.getParameter("prodTypeId").trim());
			Integer storeId = Integer.valueOf(req.getParameter("storeId").trim());
			String prodName = req.getParameter("prodName");
			String prodNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,100}$";
			if (prodName == null || prodName.trim().length() == 0) {
				errorMsgs.put("prodName", "商品名稱請勿空白！");
			} else if (!prodName.trim().matches(prodNameReg)) {
				errorMsgs.put("prodName", "商品名稱只能是中文字、英文字母、數字和＿，且長度必需在1到100之間！");
			}

			String prodCont = req.getParameter("prodCont");
//			String prodContReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,500}$";
			if (prodCont == null || prodCont.trim().length() == 0) {
				errorMsgs.put("prodCont", "商品介紹請勿空白！");
			}
//			} else if (!prodCont.trim().matches(prodContReg)) {
//				errorMsgs.put("prodCont", "商品介紹只能是中文字、英文字母、數字和＿，且長度必需在1到500之間！");
//			}

			Integer prodPrc = null;
			try {
				prodPrc = Integer.valueOf(req.getParameter("prodPrc").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("prodPrc", "商品價格請輸入數字！");
			}

			Integer prodStat = null;
			try {
				prodStat = Integer.valueOf(req.getParameter("prodStat").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("prodStat", "商品狀態請輸入數字");
			}
			
			Timestamp prodTime = null;
			try {
				prodTime = Timestamp.valueOf(req.getParameter("prodTime").trim());
			} catch (IllegalArgumentException e) {
				errorMsgs.put("prodTime", "請輸入日期");
			}

			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-prod/storeProdUpdate.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			
			Integer photoId = Integer.valueOf(req.getParameter("photoId").trim());
			
			//照片
			InputStream in = req.getPart("photoPic").getInputStream(); //從javax.servlet.http.Part物件取得上傳檔案的InputStream
			byte[] photoPic = null;
			if(in.available()!=0){
				photoPic = new byte[in.available()];
				in.read(photoPic);
				in.close();
			}  else {
				PhotoService photoSvc = new PhotoService();
				photoPic = photoSvc.getOnePhoto(photoId).getPhotoPic();
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-prod/storeNewProd.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			ProdService prodSvc = new ProdService();
			ProdVO prodVO = new ProdVO();
			prodVO.setProdId(prodId);
			prodVO.setProdTypeId(prodTypeId);
			prodVO.setStoreId(storeId);
			prodVO.setProdName(prodName);
			prodVO.setProdCont(prodCont);
			prodVO.setProdPrc(prodPrc);
			prodVO.setProdStat(prodStat);
			prodVO.setProdTime(prodTime);
			PhotoVO photoVO = new PhotoVO();
			photoVO.setPhotoId(photoId);
			photoVO.setPhotoPic(photoPic);
			prodSvc.updateProd(prodVO, photoVO);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			
			String url = "/front-prod/storeProdUpdate.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交storeProdUpdate.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自/front-prod/storeNewProd.jsp請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			HttpSession session = req.getSession();
			Integer storeId = (Integer) session.getAttribute("storeId");
			Integer prodTypeId = Integer.valueOf(req.getParameter("prodTypeId").trim());

			String prodName = req.getParameter("prodName");
			String prodNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,100}$";
			if (prodName == null || prodName.trim().length() == 0) {
				errorMsgs.put("prodName", "商品名稱請勿空白！");
			} else if (!prodName.trim().matches(prodNameReg)) {
				errorMsgs.put("prodName", "商品名稱只能是中文字、英文字母、數字和＿，且長度必需在1到100之間！");
			}

			String prodCont = req.getParameter("prodCont");
//			String prodContReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,500}$";
			if (prodCont == null || prodCont.trim().length() == 0) {
				errorMsgs.put("prodCont", "商品介紹請勿空白！");
			}
//			} else if (!prodCont.trim().matches(prodContReg)) {
//				errorMsgs.put("prodCont", "商品介紹只能是中文字、英文字母、數字和＿，且長度必需在1到500之間！");
//			}

			Integer prodPrc = null;
			try {
				prodPrc = Integer.valueOf(req.getParameter("prodPrc").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("prodPrc", "商品售價請輸入數字！");
			}

			Timestamp prodTime = null;
			try {
				prodTime = Timestamp.valueOf(req.getParameter("prodTime").trim());
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
				RequestDispatcher failureView = req.getRequestDispatcher("/front-prod/storeNewProd.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			
			//照片
			InputStream in = req.getPart("photoPic").getInputStream(); //從javax.servlet.http.Part物件取得上傳檔案的InputStream
			byte[] photoPic = null;
			if(in.available()!=0){
				photoPic = new byte[in.available()];
				in.read(photoPic);
				in.close();
			}  else errorMsgs.put("photoPic","請上傳商品照片！"); 

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-prod/storeNewProd.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			

			/*************************** 2.開始新增資料 ***************************************/
			ProdService prodSvc = new ProdService();
			ProdVO prodVO = new ProdVO();
			prodVO.setProdTypeId(prodTypeId);
			prodVO.setStoreId(storeId);
			prodVO.setProdName(prodName);
			prodVO.setProdCont(prodCont);
			prodVO.setProdPrc(prodPrc);
			prodVO.setProdStat(prodStat);
			prodVO.setProdTime(prodTime);
			PhotoVO photoVO = new PhotoVO();
			photoVO.setPhotoPic(photoPic);
			prodSvc.addProd(prodVO, photoVO);
			
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			
			String url = "/front-prod/storeNewProd.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交/front-prod/storeNewProd.jsp
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
			HttpSession session = req.getSession();
			Integer storeId = (Integer) session.getAttribute("storeId");
		    session.setAttribute("storeId", storeId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/front-prod/storeMenu.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}

		// 來自front-store/storeSearch.html的請求
		if ("listProdTypeIds_ByStoreId_M".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer storeId = Integer.valueOf(req.getParameter("storeId"));
			/*************************** 2.開始查詢資料 ****************************************/
			ProdService prodSvc = new ProdService();
			Set<ProdVO> set = prodSvc.getProdTypeIdsByStoreId(storeId);
			HttpSession session = req.getSession();
		    session.setAttribute("storeId", storeId);
		     
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("listProdTypeIds_ByStoreId", set); // 資料庫取出的set物件,存入request

			String url = "/front-prod/memberMenu.jsp"; // 成功轉交 /front-prod/memberMenu.jsp

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		// 來自index.html的請求
		if ("listProdTypeIds_ByStoreId_S".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			HttpSession session1 = req.getSession();
			Integer storeId = (Integer) session1.getAttribute("storeId");
			/*************************** 2.開始查詢資料 ****************************************/
			ProdService prodSvc = new ProdService();
			Set<ProdVO> set = prodSvc.getProdTypeIdsByStoreId(storeId);
			HttpSession session = req.getSession();
		    session.setAttribute("storeId", storeId);
				     
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("listProdTypeIds_ByStoreId", set); // 資料庫取出的set物件,存入request

			String url = "/front-prod/storeMenu.jsp"; // 成功轉交 /front-prod/storeMenu.jsp

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			}

		// 來自front-prod/memberMenu.jsp的請求
		if ("listProds_ByProdTypeId".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer prodTypeId = Integer.valueOf(req.getParameter("prodTypeId"));
			Integer prodStat = 1;
			/*************************** 2.開始查詢資料 ****************************************/
			ProdService prodSvc = new ProdService();
			Set<ProdVO> set = prodSvc.getProdsByProdTypeId(prodTypeId, prodStat);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("listProds_ByProdTypeId", set); // 資料庫取出的list物件,存入request

			String url = "/front-prod/memberMenu.jsp"; // 成功轉交 /front-prod/memberMenu.jsp

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		// 來自front-prod/memberMenu.jsp的請求
		if ("listProds_ByStoreIdAndProdTypeId".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			HttpSession session = req.getSession();
			Integer storeId = (Integer) session.getAttribute("storeId");
			Integer prodTypeId = Integer.valueOf(req.getParameter("prodTypeId"));
			Integer prodStat = 1;
			/*************************** 2.開始查詢資料 ****************************************/
			ProdService prodSvc = new ProdService();
			Set<ProdVO> set = prodSvc.getProdsByStoreIdAndProdTypeId(storeId, prodTypeId, prodStat);
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("listProds_ByStoreIdAndProdTypeId", set); // 資料庫取出的set物件,存入request

			String url = "/front-prod/memberMenu.jsp"; // 成功轉交 /front-prod/memberMenu.jsp

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		// 來自front-prod/storeMenu.jsp的請求
		if ("listProds_ByStoreIdAndProdTypeId_S".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			HttpSession session1 = req.getSession();
			Integer storeId = (Integer) session1.getAttribute("storeId");
			Integer prodTypeId = Integer.valueOf(req.getParameter("prodTypeId"));
		
			/*************************** 2.開始查詢資料 ****************************************/
			ProdService prodSvc = new ProdService();
			Set<ProdVO> set = prodSvc.getProdsByStoreIdAndProdTypeId_S(storeId, prodTypeId);
				
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("listProds_ByStoreIdAndProdTypeId_S", set); // 資料庫取出的set物件,存入request

			String url = "/front-prod/storeMenu.jsp"; // 成功轉交 /front-prod/memberMenu.jsp

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