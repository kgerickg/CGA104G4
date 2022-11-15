package com.member.controller;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet(name="MemberServlet",urlPatterns = {"/MemberServlet", "/member/member.do"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemberServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");


        switch (action) {
            case "getMemId":
                getMemId(request, response);
                break;
            case "selectById":
                selectById(request, response);
                break;
            case "getAll":
                getAll(request, response);
                break;
            case "updateAccState":
                updateAccState(request,response);
                break;
        }
    }

    private void updateAccState(HttpServletRequest request, HttpServletResponse response) {
       Integer accState = Integer.parseInt(request.getParameter("accState"));
       Integer memId = Integer.parseInt(request.getParameter("memId"));
       MemberService memSvc = new MemberService();
        MemberVO memberVO = new MemberVO();
        memberVO.setMemId(memId);
        memberVO.setAccStat(accState);

        memSvc.updateAccState(memberVO);

    }


    private void getAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MemberService memSvc = new MemberService();
        List<MemberVO> members = memSvc.getAll();
        Writer out = response.getWriter();
        JSONArray memberJSONArray = new JSONArray();
        Encoder encoder = Base64.getEncoder();
        for (MemberVO memberVO : members) {
            JSONObject memberJsonObject = new JSONObject();
            memberJsonObject.put("memId", memberVO.getMemId());
            memberJsonObject.put("memEmail", memberVO.getMemEmail());
            memberJsonObject.put("accStat", memberVO.getAccStat());
            memberJsonObject.put("memName", memberVO.getMemName());
            memberJsonObject.put("memMobile", memberVO.getMemMobile());
            memberJsonObject.put("memCity", memberVO.getMemCity());
            memberJsonObject.put("memDist", memberVO.getMemDist());
            memberJsonObject.put("memAdr", memberVO.getMemAdr());
            memberJsonObject.put("memRegTime", memberVO.getMemRegTime().getTime());
            memberJsonObject.put("memToken", memberVO.getMemToken());
            if (memberVO.getMemPic() != null) {
                memberJsonObject.put("memPic", encoder.encodeToString(memberVO.getMemPic()));
            }
            memberJSONArray.put(memberJsonObject);
        }
        out.write(memberJSONArray.toString());

    }

    private void selectById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer memId = Integer.parseInt(request.getParameter("memId"));
        MemberService memSvc = new MemberService();
        MemberVO memberVO = memSvc.findByMemId(memId);
        Writer out = response.getWriter();
        JSONObject memberJsonObject = new JSONObject();
        JSONArray memberJSONArray = new JSONArray();
        Encoder encoder = Base64.getEncoder();
        memberJsonObject.put("memId", memberVO.getMemId());
        memberJsonObject.put("memEmail", memberVO.getMemEmail());
        memberJsonObject.put("accStat", memberVO.getAccStat());
        memberJsonObject.put("memName", memberVO.getMemName());
        memberJsonObject.put("memMobile", memberVO.getMemMobile());
        memberJsonObject.put("memCity", memberVO.getMemCity());
        memberJsonObject.put("memDist", memberVO.getMemDist());
        memberJsonObject.put("memAdr", memberVO.getMemAdr());
        memberJsonObject.put("memRegTime", memberVO.getMemRegTime().getTime());
        memberJsonObject.put("memToken", memberVO.getMemToken());
        if (memberVO.getMemPic() != null) {
            memberJsonObject.put("memPic", encoder.encodeToString(memberVO.getMemPic()));
        }
        memberJSONArray.put(memberJsonObject);
        out.write(memberJSONArray.toString());
    }

    private void getMemId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Writer out = response.getWriter();
        MemberService memSvc = new MemberService();
        List<Integer> memIdList = memSvc.getMemId();
        JSONArray memIdJosnArray = new JSONArray();
        for (Integer memId : memIdList) {
            memIdJosnArray.put(memId);
        }
        out.write(memIdJosnArray.toString());

    }

}
