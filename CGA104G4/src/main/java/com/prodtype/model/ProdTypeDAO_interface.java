package com.prodtype.model;

import java.util.List;

import com.basicDAO.BasicDAO_interface;
import com.prod.model.ProdVO;
import com.refill.model.RefillVO;

public interface ProdTypeDAO_interface extends BasicDAO_interface<ProdTypeVO> {

    public List<Integer> getProdTypeId();

	public List<ProdVO> getProdsByProdTypeId(Integer prodTypeId);
}
