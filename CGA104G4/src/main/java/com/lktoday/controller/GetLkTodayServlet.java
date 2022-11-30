package com.lktoday.controller;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lkorder.model.LkOrderVO;
import com.lktoday.model.LkTodayDAO;
import com.lktoday.model.LkTodayService;
import com.lktoday.model.LkTodayVO;
import com.lucky.model.LuckyService;
import com.lucky.model.LuckyVO;


@WebServlet({"/GetLkTodayServlet", "/lktoday/getlktoday.do"})
public class GetLkTodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Type type = new TypeToken<List<LkTodayVO>>(){}.getType();

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		Integer memId = (Integer) req.getSession().getAttribute("memId");	  //取得會員id
//		Integer storeId = (Integer) req.getSession().getAttribute("storeId"); //取得商家id
		Integer storeId = 2;  //先寫死，測試用，正式用↑上面那行↑*******************************************************這裡要改！
		java.util.Date utilDate = new java.util.Date();                       //java系統時間
	    java.sql.Date lkTodayTime = new java.sql.Date(utilDate.getTime());    //sql時間 = java系統時間
	    
		LkTodayDAO dao = new LkTodayDAO();
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("findAllForStore".equals(action)) {    //店家找被約的福袋		
			req.setAttribute("list", dao.findByStore(storeId, lkTodayTime));
			req.getRequestDispatcher("/front-lktoday/listAllToday.jsp").forward(req, res);			
			return;
		}
		
		if ("findAllForMember".equals(action)) {   //會員找預約的福袋
			req.setAttribute("list", dao.findByMem(memId, lkTodayTime));
			req.getRequestDispatcher("/front-lktoday/listAllToday.jsp").forward(req, res);
			return;
		}		
		       
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數***************************************/
				Integer lktodayId = Integer.valueOf(req.getParameter("lktodayId"));
				
				/***************************2.開始刪除資料***************************************/
				LkTodayService LkTodaySvc = new LkTodayService();
				LkTodaySvc.deleteLkToday(lktodayId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/				
				String url = "/front-lktoday/listAllToday.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
		}
		
        if ("insertlkorder".equals(action)) {  
			Integer lkTodayId = Integer.parseInt(req.getParameter("lkTodayId")) ;
			Integer lkId= Integer.parseInt(req.getParameter("lkId"));
			Integer memId2= Integer.parseInt(req.getParameter("memId"));
			Integer lkPrc= Integer.parseInt(req.getParameter("lkPrc"));
			LkTodayService LkTodaySvc = new LkTodayService();
			Date date = new Date();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			
			LkOrderVO lkOrderVO  = new LkOrderVO();
			lkOrderVO.setLkId(lkId);
			lkOrderVO.setLkTodayId(lkTodayId);
			lkOrderVO.setMemId(memId2);
			lkOrderVO.setLkOrdAmt(lkPrc);
			lkOrderVO.setLkOrdTimeS(lkTodayTime);
			LkTodaySvc.addLkOrder(lkOrderVO,storeId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
//				req.setAttribute("lktodayVO", lktodayVO);
				String url = "/front-lktoday/listAllToday.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);			
		}
		
	}
	
}
