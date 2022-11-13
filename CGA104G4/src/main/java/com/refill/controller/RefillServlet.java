package com.refill.controller;


import com.utils.SpringUtil;
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
        doPost(request, response);

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
                getRefillRecord(request, response);
                break;
            case "BuyToken":
                buyToken(request, response);
                break;
        }

    }

    private void buyToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer refillToken = Integer.valueOf(request.getParameter("refillToken"));
        Writer out = response.getWriter();
        RefillService refillService = SpringUtil.getBean(getServletContext(), RefillService.class);
        JSONObject JsonMsg = new JSONObject();
        StringBuffer sb = new StringBuffer();
        String url = sb.append(request.getScheme()).append("://")
                .append(request.getServerName()).append(":")
                .append(request.getServerPort())
                .append(request.getContextPath()).append("/RefillResultServlet").toString();
        try {
            String result = refillService.buyToken(refillToken, url);
            if (result == null) {
                JsonMsg.put("erroMsg", "綠界支付連接異常，請稍後在試");
                out.write(JsonMsg.toString());
                return;
            }
            JsonMsg.put("result", result);
            out.write(JsonMsg.toString());

        } catch (Exception e) {
            e.printStackTrace();
            JsonMsg.put("erroMsg", "綠界支付連接異常，請稍後在試");
            out.write(JsonMsg.toString());
        }

    }

    private void getRefillRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        Writer out = response.getWriter();
        RefillService refillService = SpringUtil.getBean(getServletContext(), RefillService.class);
        Integer memId = (Integer) session.getAttribute("memId");

        List<RefillVO> refillVOs = refillService.getRefillRecord(memId);

        if (refillVOs != null) {
            JSONArray refillArray = new JSONArray();
            for (RefillVO refillVO : refillVOs) {
                JSONObject reFillJson = new JSONObject();
                reFillJson.put("RefillToken", refillVO.getRefillToken());
                reFillJson.put("RefillAmt", refillVO.getRefillAmt());
                SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                String refillTime = ft.format(refillVO.getRefillTime());
                reFillJson.put("refillTime", refillTime);
                refillArray.put(reFillJson);
            }
            out.write(refillArray.toString());
            return;
        }

        JSONObject JsonMsg = new JSONObject();
        JsonMsg.put("state", true);
        out.write(JsonMsg.toString());

    }

    private void getToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject reFillJson = new JSONObject();
        HttpSession session = request.getSession();
        RefillService refillService = SpringUtil.getBean(getServletContext(), RefillService.class);
        Integer memId = (Integer) session.getAttribute("memId");
        Integer memToken = refillService.getToken(memId);
        reFillJson.put("memToken", memToken);
        Writer out = response.getWriter();
        out.write(reFillJson.toString());
    }
}
