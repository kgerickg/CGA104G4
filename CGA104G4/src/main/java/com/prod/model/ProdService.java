package com.prod.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.detail.model.DetailVO;

public class ProdService {

	private ProdDAO_interface dao;

	public ProdService() {
		dao = new ProdJDBCDAO();
	}

	public List<ProdVO> getAll() {
		return dao.getAll();
	}
	
	public ProdVO getOneProd(Integer prodId) {
		return dao.findByPrimaryKey(prodId);
	}
	
	public Set<DetailVO> getDetailsByProdId(Integer prodId) {
		return dao.getDetailsByProdId(prodId);
	}

	public void deleteProd(Integer prodId) {
		dao.delete(prodId);
	}
}

