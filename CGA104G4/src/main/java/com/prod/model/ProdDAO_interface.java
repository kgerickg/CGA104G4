package com.prod.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.prodtype.model.ProdTypeVO;

public interface ProdDAO_interface {
	public void insert(ProdVO prodVO);

	public void update(ProdVO prodVO);

	public void delete(Integer prodId);

	public ProdVO findByPrimaryKey(Integer prodId);

	public List<ProdVO> getAll();

	public Set<ProdVO> getProdsByProdTypeId(Integer prodTypeId);

	public Set<ProdVO> getProdTypeIdsByStoreId(Integer storeId);
	
	//萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<ProdVO> getAll(Map<String, String[]> map); 
}