package com.lktoday.model;

import java.sql.Date;
import java.util.List;

import com.lkorder.model.LkOrderVO;

public class LkTodayService {
	private LkTodayDAOinterface dao;

	public LkTodayService() {
		dao = new LkTodayDAO();
	}

	public boolean addLkToday(Integer memId, List<LkTodayVO> list) {
		if (list != null && !list.isEmpty()) {
			list.forEach(vo -> {
				vo.setMemId(memId);
				dao.insert(vo);
			});
		}
		return true;
	}
	
	public void deleteLkToday(Integer lktodayId) {   //會員刪除預約
		dao.delete(lktodayId);
	}
	
	public List<LkTodayVO> findByMem(Integer memId, Date lkTodayTime) {   //會員查詢預約
		return dao.findByMem(memId, lkTodayTime);
	}
	
	public List<TodayLuckyVO> findByStore(Integer storeId, Date lkTodayTime) {   //商家查詢預約
		return dao.findByStore(storeId, lkTodayTime);
	}
	
	public void addLkOrder(LkOrderVO lkOrderVO,Integer storeId) {   //商家接受預約
		dao.orderinsert(lkOrderVO,storeId);
		
	}
	
}
