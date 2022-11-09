package com.admin.controller;

import static core.util.Constants.PREFIX_WEB_INF;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.entity.Admin;
import com.admin.service.AdminService;

import core.util.CommonUtil;

@WebServlet("/admin/manage")

public class ManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService service;

	@Override
	public void init() throws ServletException {
		service = CommonUtil.getBean(getServletContext(), AdminService.class);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Admin> adminList = service.findAll();
		request.setAttribute("adminList", adminList);
		request.getRequestDispatcher(PREFIX_WEB_INF + "/admin/admin.jsp").forward(request, response);
	}
}
