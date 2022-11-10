package com.refill.controller;

import com.refill.model.RefillService;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.Writer;

@WebServlet(name = "RefillServlet", urlPatterns = {"/RefillServlet", "/refill/refill.do"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class RefillServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        switch (action) {
            case "getToken":
                getToken(request, response);
                break;
        }

    }

    private void getToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject reFillJson = new JSONObject();
        HttpSession session =  request.getSession();
        RefillService refillService = new RefillService();
        Integer memId = (Integer) session.getAttribute("memId");
        Integer memToken =  refillService.getToken(memId);
        reFillJson.put("memToken",memToken);
        Writer out = response.getWriter();
        System.out.println("hi");
        out.write(reFillJson.toString());



    }
}
