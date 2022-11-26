package com.orders.model;

import java.util.List;
import java.util.Set;

public class OrdersService {

	private OrdersDAO_interface dao;

	public OrdersService() {
		dao = new OrdersJDBCDAO();
	}

	public List<OrdersVO> getAll() {
		return dao.getAll();
	}
	
	public OrdersVO getOneOrder(Integer ordId) {
		return dao.findByPrimaryKey(ordId);
	}
	
	public Set<OrdersVO> getOrdersByMemId(Integer memId) {
		return dao.getOrdersByMemId(memId);
	}
	
	public Set<OrdersVO> getOrdersByStoreId(Integer storeId) {
		return dao.getOrdersByStoreId(storeId);
	}

	public OrdersVO updateOrdStat(Integer ordId, Integer ordStat) { 
	
		OrdersVO ordersVO = new OrdersVO();
	
		ordersVO.setOrdId(ordId);
		ordersVO.setOrdStat(ordStat);
		dao.updateOrdStat(ordersVO);
	
		return ordersVO;
	}
}