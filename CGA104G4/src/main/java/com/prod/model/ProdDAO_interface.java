package com.prod.model;

import java.util.*;

import com.detail.model.DetailVO;

public interface ProdDAO_interface {
          public void insert(ProdVO prodVO);
          public void update(ProdVO ProdVO);
          public void delete(Integer prodId);
          public ProdVO findByPrimaryKey(Integer prodId);
          public Set<ProdVO> getProdsByStoreId(Integer storeId);
          public List<ProdVO> getAll();
        //萬用複合查詢(傳入參數型態Map)(回傳 List)
          public List<ProdVO> getAll(Map<String, String[]> map);
}