package com.prod.model;

import java.util.*;

public interface ProdDAO_interface {
          public void insert(ProdVO prodVO);
          public void update(ProdVO prodVO);
          public void delete(Integer prodId);
          public ProdVO findByPrimaryKey(Integer prodId);
          public List<ProdVO> getAll();
}
