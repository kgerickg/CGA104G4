package com.admin.controller;

import static core.util.CommonUtil.writePojo2Json;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.service.AdminService;

import core.util.CommonUtil;

@WebServlet("/admin/getInfoAll")
public class GetInfoAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService service;

	@Override
	public void init() throws ServletException {
		service = CommonUtil.getBean(getServletContext(), AdminService.class);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		writePojo2Json(response, service.findAll());
	}
}
