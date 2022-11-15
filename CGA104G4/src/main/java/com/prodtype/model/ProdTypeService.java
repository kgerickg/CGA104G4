package com.prodtype.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.service.spi.Stoppable;

import com.detail.model.DetailVO;
import com.prod.model.ProdVO;
import com.store.model.StoreVO;

public class ProdTypeService {

	private ProdTypeDAO_interface dao;

	public ProdTypeService() {
		dao = new ProdTypeHibernateDAO();
	}
	
	public List<ProdTypeVO> getAll() {
		return dao.getAll();
	}
	
	public ProdTypeVO getOneProdType(Integer prodTypeId) {
		return dao.findByPrimaryKey(prodTypeId);
	}
	
	public Set<ProdVO> getProdsByProdTypeId(Integer prodTypeId) {
		return dao.getProdsByProdTypeId(prodTypeId);
	}

	public void deleteProdType(Integer prodTypeId) {
		dao.delete(prodTypeId);
	}
}