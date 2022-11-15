package com.orders.model;

import java.util.*;

import com.detail.model.DetailVO;

public interface OrdersDAO_interface {
          public void insert(OrdersVO ordersVO);
          public void updateOrdStat(OrdersVO OrdersVO);
          public OrdersVO findByPrimaryKey(Integer ordId);
          public List<OrdersVO> getAll();
          public Set<DetailVO> getDetailsByOrdId(Integer ordId);
          public Set<OrdersVO> getOrdersByMemId(Integer memId);
          public Set<OrdersVO> getOrdersByStoreId(Integer storeId);
}