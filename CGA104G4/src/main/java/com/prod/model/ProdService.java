package com.prod.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.service.spi.Stoppable;

import com.detail.model.DetailVO;
import com.prodtype.model.ProdTypeHibernateDAO;
import com.prodtype.model.ProdTypeVO;
import com.store.model.StoreVO;

public class ProdService {

	private ProdDAO_interface dao;

	public ProdService() {
		dao = new ProdHibernateDAO();
	}

	public ProdVO addProd(Integer storeId, Integer prodTypeId, Integer prodStat, String prodName, String prodCont,
			Integer prodPrc, Date prodTime) {

		ProdVO prodVO = new ProdVO();
		prodVO.setProdStat(prodStat);
		prodVO.setProdName(prodName);
		prodVO.setProdCont(prodCont);
		prodVO.setProdPrc(prodPrc);
		prodVO.setProdTime(prodTime);

		StoreVO storeVO = new StoreVO();
		storeVO.setStoreId(storeId);
		prodVO.setStoreVO(storeVO);

		ProdTypeVO pTypeVO = new ProdTypeVO();
		pTypeVO.setProdTypeId(prodTypeId);
		prodVO.setProdTypeVO(pTypeVO);

		dao.insert(prodVO);

		return prodVO;
	}

	public ProdVO updateProd(Integer prodId,Integer storeId, Integer prodTypeId, Integer prodStat, String prodName, String prodCont,
			Integer prodPrc, Date prodTime) {

		ProdVO prodVO = new ProdVO();
		prodVO.setProdStat(prodStat);
		prodVO.setProdName(prodName);
		prodVO.setProdCont(prodCont);
		prodVO.setProdPrc(prodPrc);
		prodVO.setProdTime(prodTime);

		StoreVO storeVO = new StoreVO();
		storeVO.setStoreId(storeId);
		prodVO.setStoreVO(storeVO);

		ProdTypeVO prodTypeVO = new ProdTypeVO();
		prodTypeVO.setProdTypeId(prodTypeId);
		prodVO.setProdTypeVO(prodTypeVO);

		dao.update(prodVO);

		return prodVO;
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
	
	public Set<ProdVO> getProdsByStoreId(Integer storeId) {
		return dao.getProdsByStoreId(storeId);
	}
}