package com.lkcompl.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lkcompl.model.LkComplService;
import com.lkcompl.model.LkComplServiceImpl;

// 轉發 true跳到成功頁，false跳到失敗頁(舉例)

@WebServlet(name = "LkComplServlet", urlPatterns = {"/LkComplServlet", "/store-compl/lkcompl.do"})
public class LkComplServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private LkComplService service;		// 把service的方法宣告出來
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);

    }
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	}
	
}
