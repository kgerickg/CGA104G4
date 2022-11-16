package com.lkcompl.model;

import java.util.List;

public class LkComplService {
	
	private LkComplDAO_interface dao;
	
	public LkComplService() {
		this.dao = new LkComplDAO();
	}
	
	public List<LkComplVO> selectAll() {
		return dao.selectAll();
	}
	
	public LkComplVO selectByLkCcId(Integer lkCcId) {
		System.out.println("~~~");
		return dao.selectByLkCcId(lkCcId);
	}
	
	public LkComplVO selectBymemId(Integer memId) {
		return dao.selectByLkCcId(memId);
	}
	
	public void insert(LkComplVO lkComplVO) {
	dao.insert(lkComplVO);
}
	
	public void update(LkComplVO lkComplVO) {
		System.out.println("修改service");
	dao.update(lkComplVO);
	

}
	
	
//	public void insert(Integer lkCcId, Integer lkOrdId, Integer lkCcStat, String lkCcCont, Integer lkRfdStat) {
//		LkComplVO lkComplVO = new LkComplVO();
//		
//		lkComplVO.setLkCcId(lkCcId);
//		lkComplVO.setLkOrdId(lkOrdId);
//		lkComplVO.setLkCcStat(lkCcStat);
//		lkComplVO.setLkCcCont(lkCcCont);
//		lkComplVO.setLkRfdStat(lkRfdStat);
//		
//		return;
//	}
//	
//	public void update( Integer lkCcStat, Integer lkRfdStat) {
//		LkComplVO lkComplVO = new LkComplVO();
//		
//		lkComplVO.setLkCcStat(lkCcStat);
//		lkComplVO.setLkRfdStat(lkRfdStat);
//		
//		return;
//	}

	
	
}

