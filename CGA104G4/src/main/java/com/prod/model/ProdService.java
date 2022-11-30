package com.prod.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProdService {

	private ProdDAO_interface dao;

	public ProdService() {
		dao = new ProdJDBCDAO();
	}

	public ProdVO addProd(Integer storeId, Integer prodTypeId, String prodName, String prodCont, Integer prodPrc,  Date prodTime, Integer prodStat) {

		ProdVO ProdVO = new ProdVO();

		ProdVO.setProdTypeId(prodTypeId);
		ProdVO.setStoreId(storeId);
		ProdVO.setProdName(prodName);
		ProdVO.setProdCont(prodCont);
		ProdVO.setProdPrc(prodPrc);
		ProdVO.setProdStat(prodStat);
		ProdVO.setProdTime(prodTime);

		dao.insert(ProdVO);

		return ProdVO;
	}

	//預留給 Struts 2 或 Spring MVC 用
	public void addProd(ProdVO ProdVO) {
		dao.insert(ProdVO);
	}
	
	public ProdVO updateProd(Integer prodId, Integer prodTypeId, Integer storeId, String prodName, String prodCont, Integer prodPrc, Integer prodStat, Date prodTime) {

		ProdVO prodVO = new ProdVO();

		prodVO.setProdId(prodId);
		prodVO.setProdTypeId(prodTypeId);
		prodVO.setStoreId(storeId);
		prodVO.setProdName(prodName);
		prodVO.setProdCont(prodCont);
		prodVO.setProdPrc(prodPrc);
		prodVO.setProdStat(prodStat);
		prodVO.setProdTime(prodTime);
		dao.update(prodVO);
		
		return dao.findByPrimaryKey(prodId);
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
	
	public List<ProdVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
	public Set<ProdVO> getProdTypeIdsByStoreId(Integer storeId) {
		return dao.getProdTypeIdsByStoreId(storeId);
	}
	
	public Set<ProdVO> getProdsByProdTypeId(Integer prodTypeId) {
		return dao.getProdsByProdTypeId(prodTypeId);
	}
	
	public Set<ProdVO> getProdsByStoreIdAndProdTypeId(Integer storeId, Integer prodTypeId) {
		return dao.getProdsByStoreIdAndProdTypeId(storeId, prodTypeId);
	}
	
}