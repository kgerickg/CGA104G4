package com.lkcompl.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.converter.json.GsonBuilderUtils;

import com.google.gson.Gson;
//import com.lkcompl.model.LkComplMemberVO;
import com.lkcompl.model.LkComplService;
import com.lkcompl.model.LkComplVO;
import com.lkorder.model.LkOrderVO;

// 轉發 true跳到成功頁，false跳到失敗頁(舉例)

@WebServlet(urlPatterns = { "/member-lkcompl/LkComplServlet", "/member-lkcompl/lkcompl.do" })
public class LkComplMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		switch (action) {
		case "insert":
			System.out.println("新增case");
			insert(request, response);
			break;
		case "selectAll":
			System.out.println("查所有case");
			selectAll(request, response);
			break;
		case "selectBymemId":
			selectBymemId(request, response);
			break;
		case "selectByLkCcId":
			selectByLkCcId(request, response);
			break;
		case "selectOrderidByMemberid":
			selectOrderidByMemberid(request, response);
			break;
		}

	}

	
	
	
	private void selectOrderidByMemberid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Integer memId = (Integer) session.getAttribute("memId");
		LkComplService lkcomplSvc = new LkComplService();
		List<LkOrderVO> list = lkcomplSvc.selectOrderidBymemId(memId);
		
		Gson gson = new Gson();
		response.getWriter().write(gson.toJson(list));
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LkComplVO lkComplVO = new LkComplVO();
		List<String> errorMsgs = new LinkedList<String>();
		request.setAttribute("errorMsgs", errorMsgs);

		Integer lkOrdId = Integer.parseInt(request.getParameter("mySelect"));
		String lkCcCont = request.getParameter("lkCcCont");

		if (lkCcCont == null || (lkCcCont.trim()).length() == 0) {
			errorMsgs.add("請輸入評論");
		}

		lkComplVO.setLkOrdId(lkOrdId);
		lkComplVO.setLkCcCont(lkCcCont);

		if (!errorMsgs.isEmpty()) {
			request.setAttribute("lkcomplVO", lkComplVO);
			RequestDispatcher failureView = request.getRequestDispatcher("/front-lkcompl/MemberAddLkCompl.jsp");
			failureView.forward(request, response);
			return;// 程式中斷
		}
		LkComplService lkcomplSvc = new LkComplService();
		lkcomplSvc.insert(lkComplVO);
		selectAll(request, response);

	}
	

	private void selectAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer memId = (Integer) session.getAttribute("memId");
		LkComplService lkcomplSvc = new LkComplService();
		List<LkComplVO> list = lkcomplSvc.selectBymemId(memId);

		request.setAttribute("lkcomplVOlist", list);
		String url = "/front-lkcompl/MemberListAllLkCompl.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url);
		successView.forward(request, response);
	}

	private void selectByLkCcId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LkComplService lkcomplSvc = new LkComplService();
		Integer lkCcId = Integer.parseInt(request.getParameter("lkCcId"));

		LkComplVO lkComplVO = lkcomplSvc.selectByLkCcId(lkCcId);

		request.setAttribute("lkComplVO", lkComplVO);
		String url = "/front-lkcompl/MemberListOneLkCompl.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url);
		successView.forward(request, response);
	}
	
	private void selectBymemId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer memId = (Integer) session.getAttribute("memId");
		Integer lkccId = Integer.parseInt(request.getParameter("lkccId"));
		LkComplService lkcomplSvc = new LkComplService();
		LkComplVO lkComplVO = lkcomplSvc.selectBymemId(memId, lkccId);
		request.setAttribute("lkComplVO", lkComplVO);
		String url = "/front-lkcompl/MemberListOneLkCompl.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url);
		successView.forward(request, response);
	}
	
	

}
