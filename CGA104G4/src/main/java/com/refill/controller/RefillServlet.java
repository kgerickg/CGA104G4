package com.refill.controller;


import org.json.*;
import com.refill.model.RefillService;
import com.refill.model.RefillVO;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.List;

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
            case "getRefillRecord":
                getRefillRecord(request,response);
                break;
        }

    }

    private void getRefillRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session =  request.getSession();
        Writer out = response.getWriter();
        RefillService refillService = new RefillService();
        Integer memId = (Integer) session.getAttribute("memId");

        List<RefillVO> refillVOs = refillService.getRefillRecord(memId);

        if(refillVOs!=null){
            JSONArray refillArray = new JSONArray();
            for(RefillVO refillVO:refillVOs){
                JSONObject reFillJson = new JSONObject();
                reFillJson.put("RefillToken",refillVO.getRefillToken());
                reFillJson.put("RefillAmt",refillVO.getRefillAmt());
                SimpleDateFormat ft = new SimpleDateFormat ("yyyy/MM/dd hh:mm:ss");
                String refillTime = ft.format(refillVO.getRefillTime());
                reFillJson.put("refillTime",refillTime);
               refillArray.put(reFillJson);
            }
            System.out.println("hi");
            out.write(refillArray.toString());
            return;
        }

        JSONObject JsonMsg = new JSONObject();
        JsonMsg.put("state",true);
        System.out.println("hi2");
        out.write(JsonMsg.toString());

    }

    private void getToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject reFillJson = new JSONObject();
        HttpSession session =  request.getSession();
        RefillService refillService = new RefillService();
        Integer memId = (Integer) session.getAttribute("memId");
        Integer memToken =  refillService.getToken(memId);
        reFillJson.put("memToken",memToken);
        Writer out = response.getWriter();
        out.write(reFillJson.toString());
    }
}
