package com.admin.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static core.util.Constants.GSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.entity.Admin;

import core.pojo.Core;

@WebServlet("/admin/checkPassword")
public class CheckPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		final String admPwd = GSON.fromJson(request.getReader(), Admin.class).getAdmPwd();

		final Admin admin = (Admin) request.getSession().getAttribute("admin");
		final Core core = new Core();
		if (admin == null) {
			core.setMessage("無會員資訊");
			core.setSuccessful(false);
		} else {
			final String currentPassword = admin.getAdmPwd();
			if (Objects.equals(admPwd, currentPassword)) {
				core.setSuccessful(true);
			} else {
				core.setMessage("舊密碼錯誤");
				core.setSuccessful(false);
			}
		}
		writePojo2Json(response, core);
	}
}
