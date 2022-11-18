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
            case "selectByMemId":
                selectByMemId(request, response);
                break;
            case "selectByRefillId":
                selectByRefillId(request, response);
                break;
            case "selectAll":
                selectAll(request, response);
                break;
            case "selectByMemEmail":
                selectByMemEmail(request, response);
                break;
        }

    }

    private void selectByMemEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RefillService refillService = SpringUtil.getBean(getServletContext(), RefillService.class);
        String memEmail = request.getParameter("memEmail");
        JSONObject MsgsJson = new JSONObject();
        Writer out = response.getWriter();

        if (memEmail == null || memEmail.trim().length() == 0){
            MsgsJson.put("state", false);
        }


        String memEmailReg = "^[\\w@]{0,63}$";
        if (!memEmail.matches(memEmailReg))
            MsgsJson.put("refillerror", "輸入格式錯誤");

        if (!MsgsJson.isEmpty()) {
            MsgsJson.put("state", false);
            out.write(MsgsJson.toString());
            return;
        }

        JSONArray refillArray = refillService.selectByMemEmail(memEmail);


        if(refillArray.length()!=0){
            MsgsJson.put("state", true);
            MsgsJson.put("refills", refillArray);
            out.write(MsgsJson.toString());
            return;
        }
        MsgsJson.put("state", false);
        out.write(MsgsJson.toString());


    }

    private void selectAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RefillService refillService = SpringUtil.getBean(getServletContext(), RefillService.class);
        List<RefillVO> refills = refillService.getAll();
        JSONObject MsgsJson = new JSONObject();
        Writer out = response.getWriter();

        if (refills.size() != 0) {
            JSONArray refillArray = new JSONArray();
            for (RefillVO refillVO : refills) {
                JSONObject reFillJson = new JSONObject();
                reFillJson.put("RefillId", refillVO.getRefillId());
                reFillJson.put("memId", refillVO.getMemId());
                reFillJson.put("memEmail", refillVO.getMemberVO().getMemEmail());
                reFillJson.put("memName", refillVO.getMemberVO().getMemName());
                reFillJson.put("RefillToken", refillVO.getRefillToken());
                reFillJson.put("RefillAmt", refillVO.getRefillAmt());
                SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                String refillTime = ft.format(refillVO.getRefillTime());
                reFillJson.put("refillTime", refillTime);
                refillArray.put(reFillJson);
            }
            MsgsJson.put("state", true);
            MsgsJson.put("refills", refillArray);
            out.write(MsgsJson.toString());

        }

    }

    private void selectByRefillId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RefillService refillService = SpringUtil.getBean(getServletContext(), RefillService.class);
        String refillIdStr = request.getParameter("refillId");
        JSONObject MsgsJson = new JSONObject();
        Writer out = response.getWriter();

        if (refillIdStr == null || refillIdStr.trim().length() == 0) {
            MsgsJson.put("refillerror", "儲值編號未輸入");
        }
        if (!MsgsJson.isEmpty()) {
            MsgsJson.put("state", false);
            out.write(MsgsJson.toString());
            return;
        }
        Integer refillId = null;
        try {
            refillId = Integer.parseInt(refillIdStr);
        } catch (NumberFormatException e) {
            MsgsJson.put("refillerror", "請輸入數字");
        }

        if (!MsgsJson.isEmpty()) {
            MsgsJson.put("state", false);
            out.write(MsgsJson.toString());
            return;
        }

        RefillVO refillVO = refillService.findByPrimaryKey(refillId);

        if (refillVO != null) {
            JSONArray refillArray = new JSONArray();
            JSONObject reFillJson = new JSONObject();
            reFillJson.put("RefillId", refillVO.getRefillId());
            reFillJson.put("memId", refillVO.getMemId());
            reFillJson.put("memEmail", refillVO.getMemberVO().getMemEmail());
            reFillJson.put("memName", refillVO.getMemberVO().getMemName());
            reFillJson.put("RefillToken", refillVO.getRefillToken());
            reFillJson.put("RefillAmt", refillVO.getRefillAmt());
            SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
            String refillTime = ft.format(refillVO.getRefillTime());
            reFillJson.put("refillTime", refillTime);

            refillArray.put(reFillJson);

            MsgsJson.put("state", true);
            MsgsJson.put("refills", refillArray);
            out.write(MsgsJson.toString());
            System.out.println(MsgsJson.toString());
            return;
        }

        MsgsJson.put("refillerror", "查無此儲值編號");
        MsgsJson.put("state", false);
        out.write(MsgsJson.toString());


    }

    private void selectByMemId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RefillService refillService = SpringUtil.getBean(getServletContext(), RefillService.class);
        String memIdStr = request.getParameter("memId");
        JSONObject MsgsJson = new JSONObject();
        Writer out = response.getWriter();

        if (memIdStr == null || memIdStr.trim().length() == 0) {
            MsgsJson.put("refillerror", "會員編號未輸入");
        }

        if (!MsgsJson.isEmpty()) {
            MsgsJson.put("state", false);
            out.write(MsgsJson.toString());
            return;
        }


        Integer memId = null;
        try {
            memId = Integer.parseInt(memIdStr);
        } catch (NumberFormatException e) {
            MsgsJson.put("refillerror", "請輸入數字");
        }

        if (!MsgsJson.isEmpty()) {
            MsgsJson.put("state", false);
            out.write(MsgsJson.toString());
            return;
        }

        List<RefillVO> refillVOs = refillService.getRefillRecord(memId);

        if (refillVOs.size() != 0) {
            JSONArray refillArray = new JSONArray();
            for (RefillVO refillVO : refillVOs) {
                JSONObject reFillJson = new JSONObject();
                reFillJson.put("RefillId", refillVO.getRefillId());
                reFillJson.put("memId", refillVO.getMemId());
                reFillJson.put("memEmail", refillVO.getMemberVO().getMemEmail());
                reFillJson.put("memName", refillVO.getMemberVO().getMemName());
                reFillJson.put("RefillToken", refillVO.getRefillToken());
                reFillJson.put("RefillAmt", refillVO.getRefillAmt());
                SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                String refillTime = ft.format(refillVO.getRefillTime());
                reFillJson.put("refillTime", refillTime);
                refillArray.put(reFillJson);
            }
            MsgsJson.put("state", true);
            MsgsJson.put("refills", refillArray);
            out.write(MsgsJson.toString());
            return;
        }

        MsgsJson.put("refillerror", "查無此會員編號或此會員尚無儲值紀錄");
        MsgsJson.put("state", false);
        out.write(MsgsJson.toString());



    }

    private void buyToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer refillToken = Integer.valueOf(request.getParameter("refillToken"));
        Integer memId = (Integer) (request.getSession().getAttribute("memId"));
        Writer out = response.getWriter();
        RefillService refillService = SpringUtil.getBean(getServletContext(), RefillService.class);
        JSONObject JsonMsg = new JSONObject();

        StringBuffer sb = new StringBuffer();
        String url = sb.append(request.getScheme()).append("://")
                .append(request.getServerName()).append(":")
                .append(request.getServerPort())
                .append(request.getContextPath()).toString();
        try {
            String result = refillService.buyToken(refillToken, url, memId);
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

        if (refillVOs.size() != 0) {
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
