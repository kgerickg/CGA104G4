package com.admin.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.entity.Admin;
import com.admin.service.AdminService;

import core.util.CommonUtil;

import java.io.IOException;

@WebServlet("/admin/login")
public class LoginServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	private AdminService service;
	@Override
	public void init() throws ServletException{
		service = CommonUtil.getBean(getServletContext(), AdminService.class);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		Admin admin = json2Pojo(request, Admin.class);
		if (admin == null) {
			admin = new Admin();
			admin.setMessage("無會員資訊");
			admin.setSuccessful(false);
			writePojo2Json(response, admin);
			return;
		}
		
		admin = service.login(admin);
		if (admin.isSuccessful()) {
			if (request.getSession(false) != null) {
				request.changeSessionId();
			}
			final HttpSession session = request.getSession();
			session.setAttribute("loggedin", true);
			session.setAttribute("admin", admin);
		}
		writePojo2Json(response, admin);
	}
}
