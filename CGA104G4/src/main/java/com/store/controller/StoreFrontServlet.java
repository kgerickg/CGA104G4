package com.store.controller;


import com.store.model.StoreService;
import com.store.model.StoreVO;
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
                break;
            case "getStoreInfo":
                getStoreInfo(request, response);
                break;
            case "updateData":
                updateData(request, response);
                break;
        }
    }

    private void updateData(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        StoreService service = new StoreService();
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
        storeVO.update(storeVO);

        MsgsJson.put("state", true);
        out.write(MsgsJson.toString());

    }