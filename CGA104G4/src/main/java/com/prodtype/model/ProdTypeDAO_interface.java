package com.prodtype.model;

import java.util.*;

public interface ProdTypeDAO_interface {
          public void insert(ProdTypeVO prodTypeVO);
          public void update(ProdTypeVO prodTypeVO);
          public void delete(Integer prodTypeId);
          public ProdTypeVO findByPrimaryKey(Integer prodTypeId);
          public List<ProdTypeVO> getAll();
}
