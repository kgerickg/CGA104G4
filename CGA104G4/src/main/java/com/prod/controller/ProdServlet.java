//package com.prod.controller;
//
//import java.io.IOException;
//import java.io.Writer;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import com.prod.model.ProdService;
//import com.prod.model.ProdVO;
//
///**
// * Servlet implementation class ProdTypeServlet
// */
//@WebServlet(name="ProdServlet",urlPatterns = {"/ProdTypeServlet", "/prod/prod.do"})
//@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
//public class ProdServlet extends HttpServlet {
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        doPost(request, response);
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setCharacterEncoding("UTF-8");
//        String action = request.getParameter("action");
//
//        switch (action) {
//            case "updateProdStat":
//                updateProdStat(request,response);
//                break;
//            case "getProdsByStoreId":
//            	getProdsByStoreId(request,response);
//                break;
//        }
//    }
//
//    private void updateProdStat(HttpServletRequest request, HttpServletResponse response) {
//       Integer prodStat = Integer.parseInt(request.getParameter("prodStat"));
//       Integer prodId = Integer.parseInt(request.getParameter("prodId"));
//       ProdService prodSvc = new ProdService();
//        ProdVO prodVO = new ProdVO();
//        prodVO.setProdId(prodId);
//        prodVO.setProdStat(prodStat);
//        prodSvc.updateProdStat(prodVO);
//    }
//
//    private void getProdsByStoreId(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        JSONObject prodsJson = new JSONObject();
//    	ProdService prodSvc = new ProdService();
//        Writer out = response.getWriter();
//        Integer storeId = (Integer) request.getSession().getAttribute("storeId");
//        ProdVO prodVO = prodSvc.getProdsByStoreId(storeId);
//        
//        prodsJson.put("prodId", prodVO.getProdId());
//        prodsJson.put("prodTypeId", prodVO.getProdTypeId());
//        prodsJson.put("prodStat", prodVO.getProdStat());
//        prodsJson.put("prodName", prodVO.getProdName());
//        prodsJson.put("prodCont", prodVO.getProdCont());
//        prodsJson.put("prodPrc", prodVO.getProdPrc());
//        prodsJson.put("prodTime", prodVO.getProdTime());
//            
//        out.write(prodsJson.toString());
//    }
//}