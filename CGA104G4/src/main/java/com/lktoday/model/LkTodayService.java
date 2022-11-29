package com.lktoday.model;

import java.sql.Date;
import java.util.List;

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
	
	public List<LkTodayVO> findByStore(Integer storeId, Date lkTodayTime) {   //商家查詢預約
		return dao.findByStore(storeId, lkTodayTime);
	}
	
	public boolean addLkOrder(Integer storeId, List<LkTodayVO> list) {   //商家接受預約
		if (list != null && !list.isEmpty()) {
			list.forEach(lkTodayVO -> {
				lkTodayVO.setMemId(storeId);
				dao.orderinsert(lkTodayVO);
			});
		}
		return true;
	}
	
}
