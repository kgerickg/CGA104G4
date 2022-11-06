package com.member.controller;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.Writer;
import java.util.Base64;


@WebServlet(name = "MemberFrontServlet", urlPatterns = {"/MemberFrontServlet", "/member/memberfront.do"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemberFrontServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");


        switch (action) {
            case "login":
                login(request, response);
                break;
            case "loginCheck":
                loginCheck(request, response);
                break;
            case "logout":
                logout(request, response);
                break;
            case "signup":
                signup(request, response);
                break;
            case "forgetPwd":
                forgetPwd(request, response);
                break;
            case "updatePassword":
                updatePassword(request, response);
            case "memberInfoUpdate":
                memberInfoUpdate(request,response);

        }
    }

    private void memberInfoUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject memberJson = new JSONObject();
        MemberService memSvc = new MemberService();
        Writer out = response.getWriter();

        Integer memId =(Integer) request.getSession().getAttribute("memId");


       MemberVO memberVO = memSvc.findByPrimaryKey(memId);

       memberJson.put("memEmail",memberVO.getMemEmail());
       memberJson.put("memName",memberVO.getMemName());
       memberJson.put("memMobile",memberVO.getMemMobile());
       memberJson.put("memCity",memberVO.getMemCity());
       memberJson.put("memDist",memberVO.getMemDist());
       memberJson.put("memAdr",memberVO.getMemAdr());

       out.write(memberJson.toString());


    }

    private void updatePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject MsgsJson = new JSONObject();
        MemberService memSvc = new MemberService();
        Writer out = response.getWriter();

        String memOldPwd = request.getParameter("memOldPwd");
        String memNewPwd = request.getParameter("memNewPwd");
        String memcfNewPwd = request.getParameter("memcfNewPwd");

        String memPwdReg = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
        if (memOldPwd == null || memOldPwd.trim().length() == 0) {
            MsgsJson.put("memOldPwderror", "舊密碼請勿空白");
        } else if (!memOldPwd.trim().matches(memPwdReg)) {
            MsgsJson.put("memOldPwderror", "舊密碼格式異常");
        }

        if (memNewPwd == null || memNewPwd.trim().length() == 0) {
            MsgsJson.put("memNewPwderror", "新密碼請勿空白");
        } else if (!memOldPwd.trim().matches(memPwdReg)) {
            MsgsJson.put("memNewPwderror", "新密碼格式異常");
        } else if (memNewPwd.equals(memOldPwd)) {
            MsgsJson.put("memNewPwderror", "新密碼請勿與舊密碼相同");
        }

        if (memNewPwd != null) {
            if (!memNewPwd.equals(memcfNewPwd)) {
                MsgsJson.put("memcfNewPwderror", "新密碼與確認密碼不相符");
            }
        }

        if (!MsgsJson.isEmpty()) {
            MsgsJson.put("state", false);
            out.write(MsgsJson.toString());
            return;
        }

        Integer memId = (Integer) request.getSession().getAttribute("memId");
        MemberVO memberVO = memSvc.findByPrimaryKey(memId);
        String MemPwd = memberVO.getMemPwd();
        if (!MemPwd.equals(memOldPwd)) {
            MsgsJson.put("state", false);
            MsgsJson.put("memOldPwderror", "舊密碼錯誤請重新確認");
            out.write(MsgsJson.toString());
            return;
        }

        memberVO.setMemPwd(memNewPwd);
        memSvc.updatePwd(memberVO);
        MsgsJson.put("state", true);
        out.write(MsgsJson.toString());

    }

    private void forgetPwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MemberService memSvc = new MemberService();
        Writer out = response.getWriter();
        JSONObject MsgsJson = new JSONObject();

        String memEmail = request.getParameter("memEmail");

        String emailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
        if (memEmail == null || memEmail.trim().length() == 0) {
            MsgsJson.put("memEmailerror", "帳號請勿空白");
        } else if (!memEmail.trim().matches(emailReg)) {
            MsgsJson.put("memEmailerror", "帳號格式異常");
        }

        if (!MsgsJson.isEmpty()) {
            MsgsJson.put("state", false);
            out.write(MsgsJson.toString());
            return;
        }

        boolean state = memSvc.forgetPwd(memEmail);

        if (state) {
            MsgsJson.put("state", state);
            out.write(MsgsJson.toString());
        } else {
            MsgsJson.put("state", state);
            MsgsJson.put("memEmailerror", "查無此帳號請重新確認或進行註冊");
            out.write(MsgsJson.toString());
        }


    }

    public void signup(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject MsgsJson = new JSONObject();
        MemberService memSvc = new MemberService();
        Writer out = response.getWriter();

        // 信箱帳號驗證與確認
        String memEmail = request.getParameter("memEmail");

        String emailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
        if (memEmail == null || memEmail.trim().length() == 0) {
            MsgsJson.put("memEmailerror", "帳號請勿空白");
        } else if (!memEmail.trim().matches(emailReg)) {
            MsgsJson.put("memEmailerror", "帳號格式異常");
        } else if (memSvc.selectByMemEmail(memEmail) != null) {
            MsgsJson.put("memEmailerror", "帳號已存在請確認或使用忘記密碼");
        }


        // 密碼驗證
        String memPwd = request.getParameter("memPwd");
        String memcfPwd = request.getParameter("memcfPwd");
        String memPwdReg = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
        if (memPwd == null || memPwd.trim().length() == 0) {
            MsgsJson.put("memPwderror", "密碼請勿空白");
        } else if (!memPwd.trim().matches(memPwdReg)) {
            MsgsJson.put("memPwderror", "密碼格式異常");
        } else if (!memPwd.equals("memcfPwd")) {
            MsgsJson.put("memPwderror", "密碼與確認密碼不相符");
        }


        // 姓名驗證
        String memName = request.getParameter("memName");

        String memNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,10}$";
        if (memName == null || memName.trim().length() == 0) {
            MsgsJson.put("memNameerror", "姓名請勿空白");
        } else if (!memName.trim().matches(memNameReg)) {
            MsgsJson.put("memNameerror", "姓名格式異常");
        }

        // 手機驗證
        String memMobile = request.getParameter("memMobile");

        String memMobileReg = "^[0][9]\\d{8}$";
        if (memMobile == null || memMobile.trim().length() == 0) {
            MsgsJson.put("memMobileerror", "手機號碼請勿空白");
        } else if (!memMobile.trim().matches(memMobileReg)) {
            MsgsJson.put("memMobileerror", "手機號碼格式異常");
        }

        // 地址驗證
        String memCity = request.getParameter("memCity");
        String memDist = request.getParameter("memDist");
        String memAdr = request.getParameter("memAdr");


        if (memCity.equals("null") || memDist.equals("null") || memAdr.trim().length() == 0) {
            memCity = null;
            memDist = null;
            memAdr = null;
        }

        if (!MsgsJson.isEmpty()) {
            MsgsJson.put("state", false);
            out.write(MsgsJson.toString());
            return;
        }

        Integer memId = memSvc.insertWithReturn(memEmail, memPwd, memName, memMobile, memCity, memDist, memAdr);
        if (memId != null) {

            HttpSession session = request.getSession();
            session.setAttribute("memId", memId);
            session.setAttribute("memName", memName);
            MsgsJson.put("state", true);
            out.write(MsgsJson.toString());
            return;
        }
        MsgsJson.put("state", false);
        MsgsJson.put("dataError", "系統忙碌中請稍後在進行作業");


    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("memId");
        request.getSession().invalidate();
    }

    public void loginCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        JSONObject msgsJson = new JSONObject();
        Writer out = response.getWriter();

        Object memIdcheck = session.getAttribute("memId");

        if (memIdcheck == null) {
            msgsJson.put("state", false);
            out.write(msgsJson.toString());
            return;
        }

        msgsJson.put("state", true);
        msgsJson.put("memId", (Integer) memIdcheck);
        msgsJson.put("memName", (String) session.getAttribute("memName"));
        if (session.getAttribute("memPic") != null) {
            Base64.Encoder encoder = Base64.getEncoder();
            String memPic = encoder.encodeToString((byte[]) session.getAttribute("memPic"));
            msgsJson.put("memPic", memPic);
        }

        out.write(msgsJson.toString());
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject msgsJson = new JSONObject();
        Writer out = response.getWriter();

        // 信箱帳號驗證與確認
        String memEmail = request.getParameter("memEmail");

        String emailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
        if (memEmail == null || memEmail.trim().length() == 0) {
            msgsJson.put("memEmailerror", "帳號請勿空白");
        } else if (!memEmail.trim().matches(emailReg)) {
            msgsJson.put("memEmailerror", "帳號格式異常");
        }

        // 密碼驗證
        String memPwd = request.getParameter("memPwd");

        String memPwdReg = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
        if (memPwd == null || memPwd.trim().length() == 0) {
            msgsJson.put("memPwderror", "密碼請勿空白");
        } else if (!memPwd.trim().matches(memPwdReg)) {
            msgsJson.put("memPwderror", "密碼格式異常");
        }


        if (!msgsJson.isEmpty()) {
            msgsJson.put("state", false);
            out.write(msgsJson.toString());
            return;
        }

        MemberService memSvc = new MemberService();
        MemberVO memberVO = memSvc.login(memEmail, memPwd);


        if (memberVO == null) {
            System.out.println("hi2");
            msgsJson.put("memError", "帳號或密碼錯誤請重新輸入");
            msgsJson.put("state", false);
            out.write(msgsJson.toString());
            return;
        }

        if (memberVO.getAccStat() == 1) {
            System.out.println("hi");
            msgsJson.put("memError", "帳號已被停權，若有疑慮請聯絡客服人員");
            msgsJson.put("state", false);
            out.write(msgsJson.toString());
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("memId", memberVO.getMemId());
        session.setAttribute("memName", memberVO.getMemName());
        if (memberVO.getMemPic() != null) {
            session.setAttribute("memPic", memberVO.getMemPic());
        }


        Object location = session.getAttribute("location");

        if (location != null) {
            session.removeAttribute("location");
            msgsJson.put("location", (String) location);
        }

        msgsJson.put("state", true);
        out.write(msgsJson.toString());
    }


}
