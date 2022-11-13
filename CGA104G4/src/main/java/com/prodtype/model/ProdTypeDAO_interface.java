package com.prodtype.model;

import java.util.*;

import com.detail.model.DetailVO;
import com.prod.model.ProdVO;

public interface ProdTypeDAO_interface {
          public void insert(ProdTypeVO prodTypeVO);
          public void update(ProdTypeVO ProdTypeVO);
          public void delete(Integer prodId);
          public ProdTypeVO findByPrimaryKey(Integer prodTypeId);
          public List<ProdTypeVO> getAll();
        //查詢某商品類型的商品(一對多)(回傳 Set)
          public Set<ProdVO> getProdsByProdTypeId(Integer prodTypeId);
}