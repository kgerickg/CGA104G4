package com.admin.controller;

import static core.util.CommonUtil.writePojo2Json;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.entity.Admin;

@WebServlet("/admin/getInfo")
public class GetInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin == null) {
			admin = new Admin();
			admin.setMessage("無會員資訊");
			admin.setSuccessful(false);
		} else {
			admin.setSuccessful(true);
		}
		writePojo2Json(response, admin);
	}
}
