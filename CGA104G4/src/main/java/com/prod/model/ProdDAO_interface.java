package com.prod.model;

import java.util.*;

public interface ProdDAO_interface {
	public void insert(ProdVO prodVO);
	public void update(ProdVO prodVO);
	public void delete(Integer prodId);
	public ProdVO findByPrimaryKey(Integer prodId);
	public List<ProdVO> getAll();
	
	//查詢某類型的商品(一對多)(回傳 Set)
	public Set<ProdVO> getProdTypeIdsByStoreId(Integer storeId);
	public Set<ProdVO> getProdsByProdTypeId(Integer prodTypeId, Integer prodStat);
	public Set<ProdVO> getProdsByStoreIdAndProdTypeId(Integer storeId, Integer prodTypeId, Integer prodStat);
	public Set<ProdVO> getProdsByStoreIdAndProdTypeId_S(Integer storeId, Integer prodTypeId);
	
	//萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<ProdVO> getAll(Map<String, String[]> map); 
}