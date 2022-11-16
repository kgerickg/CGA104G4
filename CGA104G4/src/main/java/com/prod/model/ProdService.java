package com.prod.model;

import java.sql.Date;
import java.util.List;

public class ProdService {

    private ProdDAO_interface dao;

    public ProdService() {
        this.dao = new ProdDAO();
    }

    public List<ProdVO> getAll() {
        return dao.getAll();
    }
    
    public ProdVO getOneProd(Integer prodId) {
		return dao.findByPrimaryKey(prodId);
	}

    public void update(ProdVO prodVO) {
        dao.update(prodVO);
    }

    public ProdVO findByPrimaryKey(Integer prodId) {
        return dao.findByPrimaryKey(prodId);

    }

    public ProdVO getProdsByStoreId(Integer storeId){
        return dao.getProdsByStoreId(storeId);
    }

    public void updateProdStat(ProdVO prodVO) {
        dao.updateProdStat(prodVO);
    }
}