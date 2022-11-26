package com.store.controller;


import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.store.model.StoreService;
import com.store.model.StoreVO;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Base64;
import java.util.List;

@WebServlet(name = "StoreFrontServlet", urlPatterns = {"/StoreFrontServlet", "/store/storeFront.do"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class StoreFrontServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        switch (action) {
            case "login" -> login(request, response);
            case "loginCheck" -> loginCheck(request, response);
            case "logout" -> logout(request, response);
            case "signup" -> signup(request, response);
            case "forgetPwd" -> forgetPwd(request, response);
            case "updatePassword" -> updatePassword(request, response);
            case "getStoreInfo" -> getStoreInfo(request, response);
            case "updateData" -> updateData(request, response);
            case "getAll" -> getAll(request, response);
        }
    }

    private void getAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StoreService storeSvc = new StoreService();
        List<StoreVO> stores = storeSvc.getAll();
        Writer out = response.getWriter();
        JSONArray storeJSONArray = new JSONArray();
        Base64.Encoder encoder = Base64.getEncoder();
        for (StoreVO storeVO : stores) {
            JSONObject storeJsonObject = new JSONObject();
            storeJsonObject.put("storeName", storeVO.getStoreName());
            storeJsonObject.put("storeTel", storeVO.getStoreTel());
            storeJsonObject.put("storeCity", storeVO.getStoreCity());
            storeJsonObject.put("storeDist", storeVO.getStoreDist());
            storeJsonObject.put("storeAdr", storeVO.getStoreAdr());
            if (storeVO.getStorePic() != null) {
                storeJsonObject.put("storePic", encoder.encodeToString(storeVO.getStorePic()));
            }
            storeJSONArray.put(storeJsonObject);
        }
        out.write(storeJSONArray.toString());
    }

    private void updateData(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        StoreService storeService = new StoreService();
        JSONObject MsgsJson = new JSONObject();
        Writer out = response.getWriter();
        Integer storeId = (Integer) request.getSession().getAttribute("storeId");

        // 姓名驗證
        String storeName = request.getParameter("storeName");
        String storeNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,10}$";

        if (storeName == null || storeName.trim().length() == 0) {
            MsgsJson.put("storeNameError", "姓名請勿空白");
        } else if (!storeName.trim().matches(storeNameReg)) {
            MsgsJson.put("storeNameError", "姓名格式異常");
        }

        // 電話驗證
        String storeTel = request.getParameter("storeTel");
        String storeTelReg = "^0[1-9]{1}[0-9]{8}$";

        if (storeTel == null || storeTel.trim().length() == 0) {
            MsgsJson.put("storeTelError", "電話號碼請勿空白");
        } else if (!storeTel.trim().matches(storeTelReg)) {
            MsgsJson.put("storeTelError", "電話號碼格式異常");
        }

        // 地址驗證
        String storeCity = request.getParameter("storeCity");
        String storeDist = request.getParameter("storeDist");
        String storeAdr = request.getParameter("storeAdr");

        if (storeCity.equals("null") || storeDist.equals("null") || storeAdr.trim().length() == 0) {
            storeCity = null;
            storeDist = null;
            storeAdr = null;
        }

        if (!MsgsJson.isEmpty()) {
            MsgsJson.put("state", false);
            out.write(MsgsJson.toString());
            return;
        }

        // 圖片
        byte[] storePic = null;
        InputStream storePics = null;
        Part storePicPart = request.getPart("storePic");

        if (storePicPart != null) {
            try {
                storePics = storePicPart.getInputStream();
                storePic = new byte[storePics.available()];
                storePics.read(storePic);
                request.getSession().setAttribute("storePic", storePic);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                storePics.close();
            }
        }

        StoreVO storeVO = new StoreVO();
        storeVO.setStoreId(storeId);
        storeVO.setStoreName(storeName);
        storeVO.setStoreTel(storeTel);
        storeVO.setStoreCity(storeCity);
        storeVO.setStoreAdr(storeAdr);
        storeVO.setStoreDist(storeDist);
        storeVO.setStorePic(storePic);
        storeService.update(storeVO);
        MsgsJson.put("state", true);
        out.write(MsgsJson.toString());
    }

    private void getStoreInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StoreService storeService = new StoreService();
        Writer out = response.getWriter();
        Integer storeId = (Integer) request.getSession().getAttribute("storeId");
        StoreVO storeVO = storeService.findByStoreId(storeId);
        JSONObject storeJson = new JSONObject(storeVO);
        out.write(storeJson.toString());
    }

    private void updatePassword(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        JSONObject MsgsJson = new JSONObject();
        StoreService storeService = new StoreService();
        Writer out = response.getWriter();
        String oStorePwd = request.getParameter("oStorePwd");
        String nStorePwd = request.getParameter("nStorePwd");
        String confirmPwd = request.getParameter("confirmPwd");
        String storePwdReg = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$";

        if (oStorePwd == null || oStorePwd.trim().length() == 0) {
            MsgsJson.put("oStorePwdError", "舊密碼請勿空白");
        } else if (!oStorePwd.trim().matches(storePwdReg)) {
            MsgsJson.put("oStorePwdError", "舊密碼格式異常");
        }

        if (nStorePwd == null || nStorePwd.trim().length() == 0) {
            MsgsJson.put("nStorePwdError", "新密碼請勿空白");
        } else if (!nStorePwd.trim().matches(storePwdReg)) {
            MsgsJson.put("nStorePwdError", "新密碼格式異常");
        } else if (nStorePwd.equals(oStorePwd)) {
            MsgsJson.put("nStorePwdError", "新密碼請勿與舊密碼相同");
        }

        if (nStorePwd != null) {
            if (!nStorePwd.equals(confirmPwd)) {
                MsgsJson.put("confirmPwdError", "新密碼與確認密碼不相符");
            }
        }

        if (!MsgsJson.isEmpty()) {
            MsgsJson.put("state", false);
            out.write(MsgsJson.toString());
            return;
        }

        Integer storeId = (Integer) request.getSession().getAttribute("storeId");
        StoreVO storeVO = storeService.findByStoreId(storeId);
        String StorePwd = storeVO.getStorePwd();

        if (!StorePwd.equals(oStorePwd)) {
            MsgsJson.put("state", false);
            MsgsJson.put("oStorePwdError", "舊密碼錯誤請重新確認");
            out.write(MsgsJson.toString());
            return;
        }

        storeVO.setStorePwd(nStorePwd);
        storeService.updatePwd(storeVO);
        MsgsJson.put("state", true);
        out.write(MsgsJson.toString());

    }

    private void forgetPwd(HttpServletRequest request, HttpServletResponse response) throws IOException {

        StoreService storeService = new StoreService();
        Writer out = response.getWriter();
        JSONObject MsgsJson = new JSONObject();
        String storeAcc = request.getParameter("storeAcc");
        String emailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";

        if (storeAcc == null || storeAcc.trim().length() == 0) {
            MsgsJson.put("storeAccError", "帳號請勿空白");
        } else if (!storeAcc.trim().matches(emailReg)) {
            MsgsJson.put("storeAccError", "帳號格式異常");
        }

        if (!MsgsJson.isEmpty()) {
            MsgsJson.put("state", false);
            out.write(MsgsJson.toString());
            return;
        }

        boolean state = storeService.forgetPwd(storeAcc);

        if (state) {
            MsgsJson.put("state", state);
            out.write(MsgsJson.toString());
        } else {
            MsgsJson.put("state", state);
            MsgsJson.put("storeAccError", "查無此帳號請重新確認或進行註冊");
            out.write(MsgsJson.toString());
        }

    }

    public void signup(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject MsgsJson = new JSONObject();
        StoreService storeService = new StoreService();
        Writer out = response.getWriter();

        // 信箱帳號驗證與確認
        String storeAcc = request.getParameter("storeAcc");
        String emailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";

        if (storeAcc == null || storeAcc.trim().length() == 0) {
            MsgsJson.put("storeAccError", "帳號請勿空白");
        } else if (!storeAcc.trim().matches(emailReg)) {
            MsgsJson.put("storeAccError", "帳號格式異常");
        } else if (storeService.selectByStoreAcc(storeAcc) != null) {
            MsgsJson.put("storeAccError", "帳號已存在請確認或使用忘記密碼");
        }

        //名稱驗證
        String storeName = request.getParameter("storeName");
        if (storeName == null || storeName.trim().length() == 0) {
            MsgsJson.put("storeNameError", "名稱請勿空白");
        }

        // 密碼驗證
        String storePwd = request.getParameter("storePwd");
        String confirmPwd = request.getParameter("confirmPwd");
        String storePwdReg = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$";

        if (storePwd == null || storePwd.trim().length() == 0) {
            MsgsJson.put("storePwdError", "密碼請勿空白");
        } else if (!storePwd.trim().matches(storePwdReg)) {
            MsgsJson.put("storePwdError", "密碼格式異常");
        } else if (!storePwd.equals(confirmPwd)) {
            MsgsJson.put("storePwdError", "密碼與確認密碼不相符");
        }

        // 電話驗證
        String storeTel = request.getParameter("storeTel");
        String storeTelReg = "^0[1-9]{1}[0-9]{8}$";

        if (storeTel == null || storeTel.trim().length() == 0) {
            MsgsJson.put("storeTelError", "電話號碼請勿空白");
        } else if (!storeTel.trim().matches(storeTelReg)) {
            MsgsJson.put("storeTelError", "電話號碼格式異常");
        }

        // 地址驗證
        String storeCity = request.getParameter("storeCity");
        String storeDist = request.getParameter("storeDist");
        String storeAdr = request.getParameter("storeAdr");

        if (storeCity.equals("null") || storeDist.equals("null") || storeAdr.trim().length() == 0) {
            storeCity = null;
            storeDist = null;
            storeAdr = null;
        }

        if (!MsgsJson.isEmpty()) {
            MsgsJson.put("state", false);
            out.write(MsgsJson.toString());
            return;
        }

        Integer storeId = storeService.insertWithReturn(storeAcc, storePwd, storeName, storeTel, storeCity, storeDist, storeAdr);
        if (storeId != null) {
            HttpSession session = request.getSession();
            session.setAttribute("storeId", storeId);
            session.setAttribute("storeName", storeName);
            MsgsJson.put("state", true);
            out.write(MsgsJson.toString());
            return;
        }

        MsgsJson.put("state", false);
        MsgsJson.put("dataError", "系統忙碌中請稍後在進行作業");

    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().removeAttribute("storeId");
        request.getSession().invalidate();
    }

    public void loginCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        JSONObject msgsJson = new JSONObject();
        Writer out = response.getWriter();
        Object storeIdcheck = session.getAttribute("storeId");

        if (storeIdcheck == null) {
            msgsJson.put("state", false);
            out.write(msgsJson.toString());
            return;
        }

        msgsJson.put("state", true);
        msgsJson.put("storeId", (Integer) storeIdcheck);
        msgsJson.put("storeName", (String) session.getAttribute("storeName"));
        if (session.getAttribute("storePic") != null) {
            Base64.Encoder encoder = Base64.getEncoder();
            String storePic = encoder.encodeToString((byte[]) session.getAttribute("storePic"));
            msgsJson.put("storePic", storePic);
        }
        out.write(msgsJson.toString());
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        JSONObject msgsJson = new JSONObject();
        Writer out = response.getWriter();

        // 信箱帳號驗證與確認
        String storeAcc = request.getParameter("storeAcc");
        String emailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";

        if (storeAcc == null || storeAcc.trim().length() == 0) {
            msgsJson.put("storeAccError", "帳號請勿空白");
        } else if (!storeAcc.trim().matches(emailReg)) {
            msgsJson.put("storeAccError", "帳號格式異常");
        }

        // 密碼驗證
        String storePwd = request.getParameter("storePwd");

        String storePwdReg = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$";

        if (storePwd == null || storePwd.trim().length() == 0) {
            msgsJson.put("storePwdError", "密碼請勿空白");
        } else if (!storePwd.trim().matches(storePwdReg)) {
            msgsJson.put("storePwdError", "密碼格式異常");
        }


        if (!msgsJson.isEmpty()) {
            msgsJson.put("state", false);
            out.write(msgsJson.toString());
            return;
        }

        StoreService storeService = new StoreService();
        StoreVO storeVO = storeService.login(storeAcc, storePwd);


        if (storeVO == null) {
            msgsJson.put("storeError", "帳號或密碼錯誤請重新輸入");
            msgsJson.put("state", false);
            out.write(msgsJson.toString());
            return;
        }

        if (storeVO.getAccStat() == 0) {
            msgsJson.put("storeError", "帳號已被停權，若有疑慮請聯絡客服人員");
            msgsJson.put("state", false);
            out.write(msgsJson.toString());
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("storeId", storeVO.getStoreId());
        session.setAttribute("storeName", storeVO.getStoreName());

        if (storeVO.getStorePic() != null) {
            session.setAttribute("storePic", storeVO.getStorePic());
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