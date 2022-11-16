package com.prodtype.model;

import java.util.List;
import com.prod.model.ProdVO;

public class ProdTypeService {

    private ProdTypeDAO_interface dao;

    public ProdTypeService() {
        this.dao = new ProdTypeDAO();
    }

    public List<ProdTypeVO> getAll() {
        return dao.getAll();
    }
    
    public ProdTypeVO getOneProdType(Integer prodTypeId) {
		return dao.findByPrimaryKey(prodTypeId);
	}

    public List<Integer> getProdTypeId() {
        return dao.getProdTypeId();
    }

    public void update(ProdTypeVO prodTypeVO) {
        dao.update(prodTypeVO);
    }

    public ProdTypeVO findByPrimaryKey(Integer prodTypeId) {
        return dao.findByPrimaryKey(prodTypeId);
    }

    public List<ProdVO> getProdsByProdTypeId(Integer prodTypeId){
        return dao.getProdsByProdTypeId();
    }
}