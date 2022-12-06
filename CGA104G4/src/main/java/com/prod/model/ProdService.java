package com.prod.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.photo.model.PhotoDAO_interface;
import com.photo.model.PhotoJDBCDAO;
import com.photo.model.PhotoVO;

public class ProdService {

	private ProdDAO_interface dao;
	private PhotoDAO_interface dao2;

	public ProdService() {
		dao = new ProdJDBCDAO();
		dao2 = new PhotoJDBCDAO();
	}

	public ProdVO addProd(ProdVO prodVO, PhotoVO photoVO) {
		dao.insert(prodVO);
		photoVO.setProdId(prodVO.getProdId());
		dao2.insert(photoVO);
		return prodVO;
	}

	//預留給 Struts 2 或 Spring MVC 用
	public void addProd(ProdVO ProdVO) {
		dao.insert(ProdVO);
	}
	
	public ProdVO updateProd(ProdVO prodVO, PhotoVO photoVO) {
		dao.update(prodVO);
		photoVO.setProdId(prodVO.getProdId());
		dao2.update(photoVO);
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
	
	public Set<ProdVO> getProdTypeIdsByStoreId(Integer storeId) {
		return dao.getProdTypeIdsByStoreId(storeId);
	}
	
	public Set<ProdVO> getProdsByProdTypeId(Integer prodTypeId, Integer prodStat) {
		return dao.getProdsByProdTypeId(prodTypeId, prodStat);
	}
	
	public Set<ProdVO> getProdsByStoreIdAndProdTypeId(Integer storeId, Integer prodTypeId, Integer prodStat) {
		return dao.getProdsByStoreIdAndProdTypeId(storeId, prodTypeId, prodStat);
	}
	public Set<ProdVO> getProdsByStoreIdAndProdTypeId_S(Integer storeId, Integer prodTypeId) {
		return dao.getProdsByStoreIdAndProdTypeId_S(storeId, prodTypeId);
	}
	
}