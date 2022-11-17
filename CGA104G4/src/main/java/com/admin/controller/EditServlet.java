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

@WebServlet("/admin/edit")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService service;
	@Override
	public void init() throws ServletException{
		service = CommonUtil.getBean(getServletContext(), AdminService.class);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		final HttpSession session = request.getSession();
		final String admAcc = ((Admin) session.getAttribute("admin")).getAdmAcc();
		Admin admin = json2Pojo(request, Admin.class);
		admin.setAdmAcc(admAcc);
		writePojo2Json(response, service.edit(admin));
	}
}
