package com.prodtype.model;

import java.sql.Date;
import java.util.List;

public class ProdTypeService {

	private ProdTypeDAO_interface dao;

	public ProdTypeService() {
		dao = new ProdTypeJDBCDAO();
	}

	public ProdTypeVO addProdType(String prodTypeName) {

		ProdTypeVO ProdTypeVO = new ProdTypeVO();

		ProdTypeVO.setProdTypeName(prodTypeName);

		dao.insert(ProdTypeVO);

		return ProdTypeVO;
	}

	//預留給 Struts 2 或 Spring MVC 用
	public void addProdType(ProdTypeVO ProdTypeVO) {
		dao.insert(ProdTypeVO);
	}
	
	public ProdTypeVO updateProdType(Integer prodTypeId, String prodTypeName) {

		ProdTypeVO ProdTypeVO = new ProdTypeVO();

		ProdTypeVO.setProdTypeId(prodTypeId);
		ProdTypeVO.setProdTypeName(prodTypeName);
		
		dao.update(ProdTypeVO);

		return dao.findByPrimaryKey(prodTypeId);
	}
	
	//預留給 Struts 2 用的
	public void updateProdType(ProdTypeVO ProdTypeVO) {
		dao.update(ProdTypeVO);
	}

	public void deleteProdType(Integer prodTypeId) {
		dao.delete(prodTypeId);
	}

	public ProdTypeVO getOneProdType(Integer prodTypeId) {
		return dao.findByPrimaryKey(prodTypeId);
	}

	public List<ProdTypeVO> getAll() {
		return dao.getAll();
	}
}