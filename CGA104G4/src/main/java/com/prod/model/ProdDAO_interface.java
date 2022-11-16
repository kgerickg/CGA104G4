package com.prod.model;

import com.basicDAO.BasicDAO_interface;

public interface ProdDAO_interface extends BasicDAO_interface<ProdVO> {
	
	public ProdVO getProdsByStoreId(Integer storeId);
    public void updateProdStat(ProdVO prodVO);
}