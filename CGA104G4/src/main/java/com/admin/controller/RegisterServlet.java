package com.admin.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.admin.entity.Admin;
import com.admin.service.AdminService;

import core.util.CommonUtil;

@WebServlet("/admin/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService service;

	@Override
	public void init() throws ServletException {
		service = CommonUtil.getBean(getServletContext(), AdminService.class);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		Admin admin = json2Pojo(request, Admin.class);
		if (admin == null) {
			admin = new Admin();
			admin.setMessage("無會員資訊");
			admin.setSuccessful(false);
			writePojo2Json(response, admin);
			return;
		}

		admin = service.register(admin);
		writePojo2Json(response, admin);
	}
}
