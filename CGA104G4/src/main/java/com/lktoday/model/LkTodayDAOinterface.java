package com.lktoday.model;

import java.sql.Date;
import java.util.List;

import com.lkorder.model.LkOrderVO;

public interface LkTodayDAOinterface {

	public void insert(LkTodayVO lkTodayVO);
	
	public void delete(Integer lktodatId);
	
	public List<TodayLuckyVO> findByStore(Integer storeId, Date lkTodayTime);
	
	public List<LkTodayVO> findByMem(Integer memId, Date lkTodayTime);

	public void orderinsert(LkOrderVO lkOrderVO);

	public void orderinsert(LkOrderVO lkOrderVO, Integer storeId);

	

}
