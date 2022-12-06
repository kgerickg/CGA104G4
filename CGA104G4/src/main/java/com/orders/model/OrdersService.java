package com.orders.model;

import com.cart.pojo.Cart;
import com.detail.model.DetailService;
import com.detail.model.DetailVO;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class OrdersService {
    DetailService detailService = new DetailService();

    public void addOrder(Cart cart) {
        cart.getStoreMap().forEach((storeId, itemMap) -> {
            OrdersVO ordersVO = new OrdersVO();
            ordersVO.setMemId(Integer.valueOf(cart.getUserId()));
            ordersVO.setStoreId(Integer.valueOf(storeId));
            AtomicInteger storeTotalPrc = new AtomicInteger();
            itemMap.forEach((prodId, item) -> {
                storeTotalPrc.getAndAdd(item.getProdTotalPrc());
            });
            ordersVO.setOrdAmt(storeTotalPrc.get());
            ordersVO.setOrdTime(new Date(System.currentTimeMillis()));
            ordersVO.setOrdStat(1);
            dao.insert(ordersVO);
            Integer orderId = dao.genOrderId();
            itemMap.forEach((prodId, item) -> {
                detailService.addDetail(DetailVO.of(prodId,item.getProdQty(),orderId));

            });
        });



    }

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