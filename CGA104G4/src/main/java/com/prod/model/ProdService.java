package com.prod.model;

import java.sql.Date;
import java.util.List;

public class ProdService {

	private ProdDAO_interface dao;

	public ProdService() {
		dao = new ProdJDBCDAO();
	}

	public ProdVO addProd(Integer storeId, Integer prodTypeId, String prodName, String prodCont, Integer prodStat, Date prodTime) {

		ProdVO ProdVO = new ProdVO();

		ProdVO.setProdTypeId(prodTypeId);
		ProdVO.setStoreId(storeId);
		ProdVO.setProdName(prodName);
		ProdVO.setProdCont(prodCont);
		ProdVO.setProdStat(prodStat);
		ProdVO.setProdTime(prodTime);

		dao.insert(ProdVO);

		return ProdVO;
	}

	//預留給 Struts 2 或 Spring MVC 用
	public void addProd(ProdVO ProdVO) {
		dao.insert(ProdVO);
	}
	
	public ProdVO updateProd(Integer prodId, Integer storeId, Integer prodTypeId, String prodName, String prodCont, Integer prodStat, Date prodTime) {

		ProdVO ProdVO = new ProdVO();

		ProdVO.setProdId(prodId);
		ProdVO.setProdTypeId(prodTypeId);
		ProdVO.setStoreId(storeId);
		ProdVO.setProdName(prodName);
		ProdVO.setProdCont(prodCont);
		ProdVO.setProdStat(prodStat);
		ProdVO.setProdTime(prodTime);
		dao.update(ProdVO);

		return dao.findByPrimaryKey(prodId);
	}
	
	//預留給 Struts 2 用的
	public void updateProd(ProdVO ProdVO) {
		dao.update(ProdVO);
	}

	public void deleteProd(Integer prodId) {
		dao.delete(prodId);
	}

	public ProdVO getOneProd(Integer prodId) {
		return dao.findByPrimaryKey(prodId);
	}

	public List<ProdVO> getAll() {
		return dao.getAll();
	}
}