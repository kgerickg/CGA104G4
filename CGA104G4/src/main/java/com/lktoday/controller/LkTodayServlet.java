package com.lktoday.controller;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lktoday.model.LkTodayService;
import com.lktoday.model.LkTodayVO;

@WebServlet("/lucky/todayAdd")
public class LkTodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Type type = new TypeToken<List<LkTodayVO>>(){}.getType();

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Gson gson = new Gson();
		LkTodayService service = new LkTodayService();
		Writer out = res.getWriter();
		try {
			List<LkTodayVO> list = gson.fromJson(req.getReader(),type);
			HttpSession session = req.getSession();
			Integer memId = (Integer) session.getAttribute("memId");
//			Integer memId = 1;    //先寫死，正式使用要改↑上面程式****************************************************這裡要改！
			service.addLkToday(memId, list);
			out.write("{\"successful\": true}");
		} catch (Exception e) {
			e.printStackTrace();
			out.write("{\"successful\": false}");
		}
	}
}
