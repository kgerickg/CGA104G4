package com.store.controller;


import com.store.model.StoreService;
import com.store.model.StoreVO;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Base64;
import java.util.List;

@WebServlet(name = "StoreBackServlet", urlPatterns = {"/StoreBackServlet", "/store/storeBack.do"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class StoreBackServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        switch (action) {
            case "getStoreId" -> getStoreId(response);
            case "selectById" -> selectById(request, response);
            case "getAll" -> getAll(response);
            case "updateAccStat" -> updateAccStat(request, response);
        }
    }

    private void getStoreId(HttpServletResponse response) throws IOException {
        Writer out = response.getWriter();
        StoreService storeSvc = new StoreService();
        List<Integer> storeIdList = storeSvc.getStoreId();
        JSONArray storeIdJsonArray = new JSONArray();
        for (Integer storeId : storeIdList) {
            storeIdJsonArray.put(storeId);
        }
        out.write(storeIdJsonArray.toString());

    }

    private void selectById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StoreService storeService = new StoreService();
        Integer storeId = Integer.parseInt(request.getParameter("storeId"));
        JSONObject storeJSONObject = new JSONObject();
        Writer out = response.getWriter();
        StoreVO storeVO = storeService.findByStoreId(storeId);
        JSONArray storeJSONArray = new JSONArray();
        Base64.Encoder encoder = Base64.getEncoder();
        storeJSONObject.put("storeId", storeVO.getStoreId());
        storeJSONObject.put("storeAcc", storeVO.getStoreAcc());
        storeJSONObject.put("storeName", storeVO.getStoreName());
        storeJSONObject.put("storeTel", storeVO.getStoreTel());
        storeJSONObject.put("storeCity", storeVO.getStoreCity());
        storeJSONObject.put("storeDist", storeVO.getStoreDist());
        storeJSONObject.put("storeAdr", storeVO.getStoreAdr());
        storeJSONObject.put("accStat", storeVO.getAccStat());
        storeJSONObject.put("storeRegTime", storeVO.getStoreRegTime());
        if (storeVO.getStorePic() != null) {
            storeJSONObject.put("storePic", encoder.encodeToString(storeVO.getStorePic()));
        }
        storeJSONArray.put(storeJSONObject);
        out.write(storeJSONArray.toString());
    }

    private void getAll(HttpServletResponse response) throws IOException {
        StoreService storeSvc = new StoreService();
        List<StoreVO> stores = storeSvc.getAll();
        Writer out = response.getWriter();
        JSONArray storeJSONArray = new JSONArray();
        Base64.Encoder encoder = Base64.getEncoder();
        for (StoreVO storeVO : stores) {
            JSONObject storeJSONObject = new JSONObject();
            storeJSONObject.put("storeId", storeVO.getStoreId());
            storeJSONObject.put("storeAcc", storeVO.getStoreAcc());
            storeJSONObject.put("storeName", storeVO.getStoreName());
            storeJSONObject.put("storeTel", storeVO.getStoreTel());
            storeJSONObject.put("storeCity", storeVO.getStoreCity());
            storeJSONObject.put("storeDist", storeVO.getStoreDist());
            storeJSONObject.put("storeAdr", storeVO.getStoreAdr());
            storeJSONObject.put("accStat", storeVO.getAccStat());
            storeJSONObject.put("storeRegTime", storeVO.getStoreRegTime());
            if (storeVO.getStorePic() != null) {
                storeJSONObject.put("storePic", encoder.encodeToString(storeVO.getStorePic()));
            }
            storeJSONArray.put(storeJSONObject);
        }
        out.write(storeJSONArray.toString());
    }

    private void updateAccStat(HttpServletRequest request, HttpServletResponse response) {
        Integer accStat = Integer.parseInt(request.getParameter("accStat"));
        Integer storeId = Integer.parseInt(request.getParameter("storeId"));
        StoreService storeSvc = new StoreService();
        StoreVO storeVO = new StoreVO();
        storeVO.setStoreId(storeId);
        storeVO.setAccStat(accStat);
        storeSvc.updateAccStat(storeVO);

    }


}