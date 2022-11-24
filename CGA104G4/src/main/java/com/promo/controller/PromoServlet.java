package com.promo.controller;

import java.io.IOException;
import java.io.Writer;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.promo.model.PromoService;
import com.promo.model.PromoVO;
import com.utils.SpringUtil;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name="PromoServlet",urlPatterns = {"/PromoServlet","/promo/promo.do"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class PromoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		switch (action){
			case "getAll":
				getAll(request,response);
				break;
			case "delete":
				deletePromo(request,response);
				break;
			case "update":
				update(request,response);
				break;
		}

	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PromoService promoService = SpringUtil.getBean(getServletContext(),PromoService.class);
		Writer out = response.getWriter();
		Integer promoId =Integer.parseInt(request.getParameter("promoId"));
		String promoName = request.getParameter("promoName");
		Integer promoVal = Integer.parseInt(request.getParameter("promoVal"));
		String promoTimSstr =  request.getParameter("promoTimeS");
		String promoTimEstr =  request.getParameter("promoTimeE");
		SimpleDateFormat  df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String promoCont = request.getParameter("promoCont");
		JSONObject MsgsJson = new JSONObject();

		if (promoName == null || promoName.trim().length() == 0) {
			MsgsJson.put("promoNameError", "名稱請勿空白");
		}

		Timestamp promoTimeS=null;
		if(promoTimSstr == null || promoTimSstr.trim().length() == 0){
			MsgsJson.put("promoTimeSError", "開始時間請勿空白");
		}else {
			try {
				promoTimeS = new Timestamp(df.parse(promoTimSstr).getTime());
			}catch (ParseException e){
				e.printStackTrace();
				MsgsJson.put("promoTimeSError", "開始時間格式異常");
			}
		}

		Timestamp promoTimeE=null;
		if(promoTimEstr == null || promoTimEstr.trim().length() == 0){
			MsgsJson.put("promoTimeEError", "結束時間請勿空白");
		}else {
			try {
				promoTimeE = new Timestamp(df.parse(promoTimEstr).getTime());
			}catch (ParseException e){
				e.printStackTrace();
				MsgsJson.put("promoTimeEError", "結束時間格式異常");
			}
		}

		if(promoCont == null ||promoCont.trim().length() == 0){
			MsgsJson.put("promoContError", "活動內容請勿空白");
		}

		if(promoTimeS!=null&promoTimeE!=null){
			boolean[] result = promoService.checkTime(promoTimeS,promoTimeE,promoId);
			if(!result[0]){
				MsgsJson.put("promoTimeSError", "開始時間與其它活動時間重複");
			}
			if(!result[1]){
				MsgsJson.put("promoTimeEError", "結束時間與其它活動時間重複");
			}
		}
		if (!MsgsJson.isEmpty()) {
			MsgsJson.put("state", false);
			out.write(MsgsJson.toString());
			return;
		}


		promoService.update(promoId,promoName,promoVal,promoTimeS,promoTimeE,promoCont);
		MsgsJson.put("state", true);
		out.write(MsgsJson.toString());
	}

	private void deletePromo(HttpServletRequest request, HttpServletResponse response) {
		PromoService promoService = SpringUtil.getBean(getServletContext(),PromoService.class);
		Integer promoId = Integer.parseInt(request.getParameter("promoId"));
		promoService.delete(promoId);
	}

	private void getAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PromoService promoService = SpringUtil.getBean(getServletContext(),PromoService.class);
		List<PromoVO> promoList = promoService.getAll();
		JSONArray jsonArray = new JSONArray(promoList);
		Writer out= response.getWriter();
		out.write(jsonArray.toString());
	}


}
