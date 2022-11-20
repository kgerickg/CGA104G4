package com.promo.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.promo.model.PromoService;
import com.promo.model.PromoVO;
import com.utils.SpringUtil;
import org.json.JSONArray;

@WebServlet(name="PromoServlet",urlPatterns = {"/PromoServlet","/promo/promo.do"})
public class PromoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		switch (action){
			case "getAll":
				getAll(request,response);
				break;
		}

	}

	private void getAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PromoService promoService = SpringUtil.getBean(getServletContext(),PromoService.class);
		List<PromoVO> promoList = promoService.getAll();
		JSONArray jsonArray = new JSONArray(promoList);
		Writer out= response.getWriter();
		out.write(jsonArray.toString());
	}

}
