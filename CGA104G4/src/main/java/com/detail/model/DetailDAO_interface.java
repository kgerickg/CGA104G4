package com.detail.model;

import java.util.*;

public interface DetailDAO_interface {
          public void insert(DetailVO detailVO);
          public void update(DetailVO detailVO);
          public void delete(Integer prodId);
          public DetailVO findByPrimaryKey(Integer prodId);
          public List<DetailVO> getAll();
          public Set<DetailVO> getDetailsByOrdId(Integer ordId);
}
