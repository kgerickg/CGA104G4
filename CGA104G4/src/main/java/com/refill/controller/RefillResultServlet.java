package com.refill.controller;

import com.refill.model.RefillService;
import com.utils.SpringUtil;
import org.hibernate.Session;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(name = "RefillResultServlet", value = "/RefillResultServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class RefillResultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String MemberData = request.getParameter("CustomField1");
        String[] split = MemberData.split(",");
        Integer memId = Integer.parseInt(split[0]);
        Integer refillToken = Integer.parseInt(split[1]);
        Integer refillAmt = Integer.parseInt(request.getParameter("TradeAmt"));
        System.out.println(""+memId+","+refillToken+","+refillAmt);

        RefillService refillService = SpringUtil.getBean(getServletContext(), RefillService.class);
        refillService.insert(memId,refillToken,refillAmt);


        ServletContext sc = this.getServletContext();
        String contextPath = sc.getContextPath();
        String url = contextPath+"/front-refill/refill.html";
        response.sendRedirect(url);
    }
}
