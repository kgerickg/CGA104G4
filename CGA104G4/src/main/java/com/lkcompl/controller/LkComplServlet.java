package com.lkcompl.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lkcompl.model.LkComplService;
import com.lkcompl.model.LkComplServiceImpl;

// 轉發 true跳到成功頁，false跳到失敗頁(舉例)

@WebServlet(name = "LkComplServlet", urlPatterns = {"/LkComplServlet", "/back-compl/lkcompl.do"})
public class LkComplServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private LkComplService service;
	
	@Override
	public void init() throws ServletException {	// init為Servlet的生命週期方法
		service = new LkComplServiceImpl();	// 把service new出來
		
	}	// 物件創立後使用的第一個方法26~29行，因23行宣告沒給直，
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String lkCcId = req.getParameter("lkCcId");
		final String lkOrdId = req.getParameter("lkOrdId");
		final String lkCcStat = req.getParameter("lkCcStat");	
		final String lkCcCont = req.getParameter("lkCcCont");
		final String lkRfdStat = req.getParameter("lkRfdStat");
		
		PrintWriter pw = resp.getWriter();		// 回應寫出純文字資料
		// pw.close();   webcontener會幫我們關所以不用寫出來
		// 如果有【透過req請求回應new出來的東西，web會自動幫我們關閉】所以不用寫出關閉
		// 【自己new出來的物件就要手動關閉】
		
		// 38行事宣告PrintWriter，為了在前端顯示53、54行的字，刪掉對code沒影響
		
		
//		if (Objects.equals(cPassword, password)) {		// 只是想用equals方法，equals是Objects裡面的其中一個方法，所以寫成Objects.equals
//			Member member = new Member();	// 宣告一個叫member的容器來存取下面三個值，要用在service.register()方法
//			member.setUsername(username);
//			member.setPassword(password);
//			member.setNickname(nickname);
//			final boolean result = service.register(member);	// 這行要用布林來判斷52的三源運算子
//			pw.append("註冊").append(result ? "成功" : "失敗");
//		}	else {
//			pw.append("密碼與確認密碼不符合");
//		}
		
	}
	
}
