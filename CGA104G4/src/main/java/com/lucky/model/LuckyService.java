package com.lucky.model;

import java.sql.Date;
import java.util.List;

public class LuckyService {

	private LuckyDAOinterface dao;

	public LuckyService() {
		dao = new LuckyDAO();
//		dao = new LuckyJDBCDAO();
	}

	public LuckyVO addLucky(Integer storeId, Integer lkStat, String lkName, String lkCont, Integer lkPrc, Date lkTime) {

		LuckyVO luckyVO = new LuckyVO();

		luckyVO.setStoreId(storeId);
		luckyVO.setLkStat(lkStat);
		luckyVO.setLkName(lkName);
		luckyVO.setLkCont(lkCont);
		luckyVO.setLkPrc(lkPrc);
		luckyVO.setLkTime(lkTime);
		dao.insert(luckyVO);

		return luckyVO;

	}

	public LuckyVO updateLucky(Integer luckyId, Integer storeId, Integer lkStat, String lkName, String lkCont,
			Integer lkPrc, Date lkTime) {

		LuckyVO luckyVO = new LuckyVO();

		luckyVO.setLuckyId(luckyId);
		luckyVO.setStoreId(storeId);
		luckyVO.setLkStat(lkStat);
		luckyVO.setLkName(lkName);
		luckyVO.setLkCont(lkCont);
		luckyVO.setLkPrc(lkPrc);
		luckyVO.setLkTime(lkTime);
		dao.update(luckyVO);

		return luckyVO;

	}

	public LuckyVO updateLucky(LuckyVO luckyVO) {
		dao.update(luckyVO);
		return luckyVO;
	}

	public void deleteLucky(Integer luckyId) {
		dao.delete(luckyId);
	}

	public LuckyVO getOneLucky(Integer luckyId) {
		return dao.findByPrimaryKey(luckyId);
	}

	public List<LuckyVO> getAll() {
		return dao.getAll();
	}
	
	public byte[] getImgById(Integer luckyId) {
		return dao.getImgById(luckyId);
	}
}
