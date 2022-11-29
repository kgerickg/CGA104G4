package com.lktoday.model;

import java.sql.Date;
import java.util.List;

import com.lkorder.model.LkOrderVO;
import com.lktoday.model.LkTodayVO;

public interface LkTodayDAOinterface {

	public void insert(LkTodayVO lkTodayVO);
	
	public void delete(Integer lktodatId);
	
	public List<LkTodayVO> findByStore(Integer storeId, Date lkTodayTime);
	
	public List<LkTodayVO> findByMem(Integer memId, Date lkTodayTime);

	public void orderinsert(LkTodayVO lkTodayVO);
	

}
