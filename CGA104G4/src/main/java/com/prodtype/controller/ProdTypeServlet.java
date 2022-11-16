package com.prodtype.controller;

import java.io.IOException;
import java.io.Writer;

import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.prod.model.ProdVO;
import com.prodtype.model.ProdTypeService;
import com.prodtype.model.ProdTypeVO;

/**
 * Servlet implementation class ProdTypeServlet
 */
@WebServlet(name="ProdTypeServlet",urlPatterns = {"/ProdTypeServlet", "/prodType/prodType.do"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ProdTypeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        switch (action) {
            case "getProdTypeId":
                getProdTypeId(request, response);
                break;
            case "selectById":
                selectById(request, response);
                break;
            case "getAll":
                getAll(request, response);
                break;
            case "getProdsByProdTypeId":
            	getProdsByProdTypeId(request,response);
                break;
        }
    }
    private void getProdsByProdTypeId(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	Integer prodTypeId = Integer.parseInt(request.getParameter("prodTypeId"));
    	ProdTypeService prodTypeSvc = new ProdTypeService();
    	List<ProdVO> prods = prodTypeSvc.getProdsByProdTypeId(prodTypeId);
    	Writer out = response.getWriter();
    	JSONArray prodsJSONArray = new JSONArray();
    	
    	for (ProdVO prodVO : prods) {
            JSONObject prodsJsonObject = new JSONObject();
            prodsJsonObject.put("prodId", prodVO.getProdId());
            prodsJsonObject.put("storeId", prodVO.getStoreId());
            prodsJsonObject.put("prodTypeId", prodVO.getProdTypeId());
            prodsJsonObject.put("prodStat", prodVO.getProdStat());
            prodsJsonObject.put("prodName", prodVO.getProdName());
            prodsJsonObject.put("prodCont", prodVO.getProdCont());
            prodsJsonObject.put("prodPrc", prodVO.getProdPrc());
            prodsJsonObject.put("prodTime", prodVO.getProdTime());
           
            prodsJSONArray.put(prodsJsonObject);
        }
        out.write(prodsJSONArray.toString());
    }
    private void getAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProdTypeService prodTypeSvc = new ProdTypeService();
        List<ProdTypeVO> prodTypes = prodTypeSvc.getAll();
        Writer out = response.getWriter();
        JSONArray prodJSONArray = new JSONArray();

        for (ProdTypeVO prodTypeVO : prodTypes) {
            JSONObject prodTypeJsonObject = new JSONObject();
            prodTypeJsonObject.put("prodTypeId", prodTypeVO.getProdTypeId());
            prodTypeJsonObject.put("prodName", prodTypeVO.getProdTypeName());
           
            prodJSONArray.put(prodTypeJsonObject);
        }
        out.write(prodJSONArray.toString());
    }

    private void selectById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer prodTypeId = Integer.parseInt(request.getParameter("prodTypeId"));
        ProdTypeService prodTypeSvc = new ProdTypeService();
        ProdTypeVO prodTypeVO = prodTypeSvc.findByPrimaryKey(prodTypeId);
        Writer out = response.getWriter();
        JSONObject prodTypeJsonObject = new JSONObject();
        JSONArray prodTypeJSONArray = new JSONArray();
   
        prodTypeJsonObject.put("prodTypeId", prodTypeVO.getProdTypeId());
        prodTypeJsonObject.put("prodTypeName", prodTypeVO.getProdTypeName());
        
        prodTypeJSONArray.put(prodTypeJsonObject);
        out.write(prodTypeJSONArray.toString());
    }

    private void getProdTypeId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Writer out = response.getWriter();
        ProdTypeService prodTypeSvc = new ProdTypeService();
        List<Integer> prodTypeIdList = prodTypeSvc.getProdTypeId();
        JSONArray prodTypeIdJosnArray = new JSONArray();
        for (Integer prodTypeId : prodTypeIdList) {
            prodTypeIdJosnArray.put(prodTypeId);
        }
        out.write(prodTypeIdJosnArray.toString());
    }
    
}