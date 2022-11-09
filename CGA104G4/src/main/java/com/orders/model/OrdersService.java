package com.orders.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.detail.model.DetailVO;

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
	
	public Set<DetailVO> getDetailsByOrdId(Integer ordId) {
		return dao.getDetailsByOrdId(ordId);
	}
	
	public Set<OrdersVO> getOrdersByMemId(Integer memId) {
		return dao.getOrdersByMemId(memId);
	}
	
	public Set<OrdersVO> getOrdersByStoreId(Integer storeId) {
		return dao.getOrdersByStoreId(storeId);
	}

	public void deleteOrders(Integer ordId) {
		dao.delete(ordId);
	}
}

