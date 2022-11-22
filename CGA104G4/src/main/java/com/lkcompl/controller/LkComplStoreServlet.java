package com.lkcompl.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToUrl;

import com.comment.model.CommentService;
import com.comment.model.CommentVO;
import com.lkcompl.model.LkComplService;
import com.lkcompl.model.LkComplVO;

// 轉發 true跳到成功頁，false跳到失敗頁(舉例)

@WebServlet(urlPatterns = { "/store-lkcompl/LkComplServlet", "/store-lkcompl/lkcompl.do" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class LkComplStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("hiGET");
		doPost(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("hiPOST");
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		switch (action) {
		case "insert":
			insert(request, response);
			break;
		case "goUpdate":
			System.out.println("go~update");
			goUpdate(request, response);
			break;
		case "update":
			update(request, response);
			System.out.println("修改~case");
			break;
		case "selectAll":
			selectAll(request, response);
			break;
		case "selectByLkCcId":
			selectByLkCcId(request, response);
			System.out.println("hi~");
			break;
		}

	}



	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LkComplVO lkComplVO = new LkComplVO();
		List<String> errorMsgs = new LinkedList<String>();
		request.setAttribute("errorMsgs", errorMsgs);

		Integer lkCcStat = Integer.parseInt(request.getParameter("lkCcStat"));
		Integer lkRfdStat = Integer.parseInt(request.getParameter("lkRfdStat"));
		Integer lkCcId = Integer.parseInt(request.getParameter("lkCcId"));
		Integer lkOrdId = Integer.parseInt(request.getParameter("lkOrdId"));
		String lkCcCont = request.getParameter("lkCcCont");

		if (lkCcCont == null || (lkCcCont.trim()).length() == 0) {
			errorMsgs.add("請輸入評論");
		}

		lkComplVO.setLkCcStat(lkCcStat);
		lkComplVO.setLkRfdStat(lkRfdStat);
		lkComplVO.setLkCcId(lkCcId);
		lkComplVO.setLkOrdId(lkOrdId);
		lkComplVO.setLkCcCont(lkCcCont);

		if (!errorMsgs.isEmpty()) {
			request.setAttribute("lkcomplVO", lkComplVO);
			RequestDispatcher failureView = request.getRequestDispatcher("/front-lkcompl/FrontAddlkcompl.jsp");
			failureView.forward(request, response);
			return;// 程式中斷
		}
		LkComplService lkcomplSvc = new LkComplService();
		lkcomplSvc.insert(lkComplVO);
		selectAll(request, response);

	}
	

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("我是修改 servlet");
		LkComplVO lkComplVO = new LkComplVO();
		List<String> errorMsgs = new LinkedList<String>();
		request.setAttribute("errorMsgs", errorMsgs);
		
		request.setAttribute("lkComplVO", lkComplVO);
		
		
		System.out.println(request.getParameter("lkCcId") + "servlet");
		
		Integer lkCcStat = Integer.parseInt(request.getParameter("lkCcStat"));
		Integer lkRfdStat = Integer.parseInt(request.getParameter("lkRfdStat"));
		Integer lkCcId = Integer.parseInt(request.getParameter("lkCcId"));
		Integer lkOrdId = Integer.parseInt(request.getParameter("lkOrdId"));
		String lkCcCont = request.getParameter("lkCcCont");

		lkComplVO.setLkCcStat(lkCcStat);
		lkComplVO.setLkRfdStat(lkRfdStat);
		lkComplVO.setLkCcId(lkCcId);
		lkComplVO.setLkOrdId(lkOrdId);
		lkComplVO.setLkCcCont(lkCcCont);

		if (!errorMsgs.isEmpty()) {
			request.setAttribute("lkcomplVO", lkComplVO);
			RequestDispatcher failureView = request.getRequestDispatcher("/front-lkcompl/StoreUpdateLkCompl.jsp");
			failureView.forward(request, response);
			return;// 程式中斷
		}
		
		LkComplService lkcomplSvc = new LkComplService();
		lkcomplSvc.update(lkComplVO);
		String url = "/front-lkcompl/StoreListOneLkCompl.jsp";	// 成功後會跳轉哪頁
		RequestDispatcher successView = request.getRequestDispatcher(url);
		successView.forward(request, response);
	}
	
	private void goUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("goto修改頁面");
		LkComplService lkcomplSvc = new LkComplService();
		LkComplVO lkComplVO = new LkComplVO();
		

		request.setAttribute("lkComplVO", lkComplVO);
		
		Integer lkCcStat = Integer.parseInt(request.getParameter("lkCcStat"));
		Integer lkRfdStat = Integer.parseInt(request.getParameter("lkRfdStat"));
		Integer lkCcId = Integer.parseInt(request.getParameter("lkCcId"));
		Integer lkOrdId = Integer.parseInt(request.getParameter("lkOrdId"));
		String lkCcCont = request.getParameter("lkCcCont");

		lkComplVO.setLkCcStat(lkCcStat);
		lkComplVO.setLkRfdStat(lkRfdStat);
		lkComplVO.setLkCcId(lkCcId);
		lkComplVO.setLkOrdId(lkOrdId);
		lkComplVO.setLkCcCont(lkCcCont);
		
		lkcomplSvc.goUpdate(lkComplVO);

		String url = "/front-lkcompl/StoreUpdateLkCompl.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url);
		successView.forward(request, response);
	}
	
	

	private void selectAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LkComplService lkcomplSvc = new LkComplService();
		List<LkComplVO> list = lkcomplSvc.selectAll();

		request.setAttribute("lkcomplVOlist", list);
		String url = "/front-lkcompl/StoreListAllLkCompl.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url);
		successView.forward(request, response);
	}

	private void selectByLkCcId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("byccid");
		LkComplService lkcomplSvc = new LkComplService();
		Integer lkCcId = Integer.parseInt(request.getParameter("lkCcId"));

		LkComplVO lkComplVO = lkcomplSvc.selectByLkCcId(lkCcId);
		
		request.setAttribute("lkComplVO", lkComplVO);
		String url = "/front-lkcompl/StoreListOneLkCompl.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url);
		successView.forward(request, response);
	}
	
	

}
