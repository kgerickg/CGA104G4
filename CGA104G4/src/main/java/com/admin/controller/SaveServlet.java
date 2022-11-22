package com.admin.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.entity.Admin;
import com.admin.service.AdminService;

import core.pojo.Core;
import core.util.CommonUtil;

@WebServlet("/admin/save")
public class SaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService service;

	@Override
	public void init() throws ServletException {
		service = CommonUtil.getBean(getServletContext(), AdminService.class);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		final Admin admin = json2Pojo(request, Admin.class);
		final Core core = new Core();
		if (admin == null) {
			core.setMessage("無會員資訊");
			core.setSuccessful(false);
		} else {
			core.setSuccessful(service.save(admin));
		}
		writePojo2Json(response, core);
	}
}
