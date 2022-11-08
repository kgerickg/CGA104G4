package com.prod.model;

import java.util.*;

import com.detail.model.DetailVO;

public interface ProdDAO_interface {
          public void insert(ProdVO prodVO);
          public void update(ProdVO ProdVO);
          public void delete(Integer prodId);
          public ProdVO findByPrimaryKey(Integer prodId);
          public List<ProdVO> getAll();
          public Set<DetailVO> getDetailsByProdId(Integer prodId);
    
}