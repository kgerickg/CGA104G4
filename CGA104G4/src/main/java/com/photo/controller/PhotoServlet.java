package com.photo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.photo.model.PhotoService;
import com.photo.model.PhotoVO;

@MultipartConfig(fileSizeThreshold = 0 * 1024 * 1024, maxFileSize = 1 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024)
public class PhotoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("photoId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("photoId", "請輸入商品照片編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-prod/storeMenu.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer photoId = null;
			try {
				photoId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("photoId", "商品照片編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-prod/storeMenu.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			PhotoService photoSvc = new PhotoService();
			PhotoVO photoVO = photoSvc.getOnePhoto(photoId);
			if (photoVO == null) {
				errorMsgs.put("photoId", "查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-prod/storeMenu.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("photoVO", photoVO); // 資料庫取出的photoVO物件,存入req
			req.setAttribute("getOne_For_Display", "true"); // 旗標getOne_For_Display見select_page.jsp的第122行 -->
			String url = "/front-prod/storeMenu.jsp"; // 成功轉交 /front-prod/storeMenu.jsp
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自/front-prod/storeMenu.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer photoId = Integer.valueOf(req.getParameter("photoId"));

			/*************************** 2.開始查詢資料 ****************************************/
			PhotoService photoSvc = new PhotoService();
			PhotoVO photoVO = photoSvc.getOnePhoto(photoId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			String param = "?photoId="  +photoVO.getPhotoId()+
						   "&prodId="   +photoVO.getProdId()+
						   "&photoStat="+photoVO.getPhotoStat();
			String url = "/front-prod/storeMenu.jsp"+param;
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自/front-prod/storeMenu.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer photoId = Integer.valueOf(req.getParameter("photoId").trim());
			Integer prodId = Integer.valueOf(req.getParameter("prodId").trim());

			Integer photoStat = null;
			try {
				photoStat = Integer.valueOf(req.getParameter("photoStat").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("prodStat", "商品照片狀態請輸入數字");
			}
			
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
				errorMsgs.put("Exception","修改資料失敗");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-prod/storeMenu.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			PhotoService photoSvc = new PhotoService();
			PhotoVO photoVO = photoSvc.updatePhoto(photoId, prodId, photoStat, photoPic);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("success", "- (修改成功)");
			req.setAttribute("photoVO", photoVO); // 資料庫update成功後,正確的的photoVO物件,存入req
			String url = "/front-prod/storeProdUpdate.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交/front-prod/storeMenu.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addPhoto.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			HttpSession session = req.getSession();
			Integer prodId = (Integer) session.getAttribute("prodId");

			Integer photoStat = null;
			try {
				photoStat = Integer.valueOf(req.getParameter("photoStat").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("prodStat", "商品照片狀態請輸入數字");
			}
			
			//照片
			InputStream in = req.getPart("photoPic").getInputStream(); //從javax.servlet.http.Part物件取得上傳檔案的InputStream
			byte[] photoPic = null;
			if(in.available()!=0){
				photoPic = new byte[in.available()];
				in.read(photoPic);
				in.close();
			}  else errorMsgs.put("photoPic","商品照片: 請上傳照片"); 

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-prod/storeMenu.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始新增資料 ***************************************/
			PhotoService photoSvc = new PhotoService();
			photoSvc.addPhoto(prodId, photoStat, photoPic);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			req.setAttribute("success", "- (新增成功)");
			String url = "/front-prod/storeMenu.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交/front-prod/listAllPhotos.jsp
			successView.forward(req, res);	
		}

		if ("delete".equals(action)) { // 來自/front-prod/storeMenu.jsp

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer photoId = Integer.valueOf(req.getParameter("photoId"));

			/*************************** 2.開始刪除資料 ***************************************/
			PhotoService photoSvc = new PhotoService();
			photoSvc.deletePhoto(photoId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			req.setAttribute("success", "- (刪除成功)");
			String url = "/front-prod/storeMenu.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}	
	}
}