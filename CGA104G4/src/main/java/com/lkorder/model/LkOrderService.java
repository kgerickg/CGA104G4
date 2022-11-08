package com.lkorder.model;

import java.sql.Date;
import java.util.List;


public class LkOrderService {
	private LkOrderinterface dao;
	
	public LkOrderService() {
		dao = new LkOrderJDBCDAO();
	}
	
//	public void insert(LkOrderVO lkorderVO) {
//		dao.insert(lkorderVO);
//	}
//	
//	public void update(LkOrderVO lkorderVO) {
//		dao.update(lkorderVO);
//	}
	
	public LkOrderVO insert(Integer lkId, Integer memId, Integer lkTodayId, Integer lkOrdAmt,
			Integer lkOrdStat, java.sql.Date lkOrdTimeS, Date lkOrdTaketime, Date lkOrdTimeE) {

		LkOrderVO lkorderVO = new LkOrderVO();

		lkorderVO.setLkId(lkId);
		lkorderVO.setMemId(memId);
		lkorderVO.setLkTodayId(lkTodayId);
		lkorderVO.setLkOrdAmt(lkOrdAmt);
		lkorderVO.setLkOrdStat(lkOrdStat);
		lkorderVO.setLkOrdTimeS(lkOrdTimeS);
		lkorderVO.setLkOrdTaketime(lkOrdTaketime);
		lkorderVO.setLkOrdTimeE(lkOrdTimeE);
		dao.insert(lkorderVO);

		return lkorderVO;
	}

	public LkOrderVO update(Integer lkOrderId, Integer lkId, Integer memId, Integer lkTodayId, Integer lkOrdAmt,
			Integer lkOrdStat, java.sql.Date lkOrdTimeS, Date lkOrdTaketime, Date lkOrdTimeE) {

		LkOrderVO lkorderVO = new LkOrderVO();

		lkorderVO.setLkOrderId(lkOrderId);
		lkorderVO.setLkId(lkId);
		lkorderVO.setMemId(memId);
		lkorderVO.setLkTodayId(lkTodayId);
		lkorderVO.setLkOrdAmt(lkOrdAmt);
		lkorderVO.setLkOrdStat(lkOrdStat);
		lkorderVO.setLkOrdTimeS(lkOrdTimeS);
		lkorderVO.setLkOrdTaketime(lkOrdTaketime);
		lkorderVO.setLkOrdTimeE(lkOrdTimeE);
		dao.update(lkorderVO);

		return lkorderVO;
	}
	
	public void delete(Integer lkordId) {
		dao.delete(lkordId);
	}
	
	public LkOrderVO lkorderVO(Integer lkordId) {
		return dao.findByPrimaryKey(lkordId);
	}
	
	public LkOrderVO lkordermemidVO(Integer memId) {
		return dao.findByMemId(memId);
	}
	
	public List<LkOrderVO> getAll(){
		return dao.getAll();
	}
	
	public List<LkOrderSelectVO> selectAll(Integer memId){
		return dao.selectAll(memId);
	}
	
	public List<LkOrderSelectVO> findAll(){
		return dao.findAll();
	}
}
