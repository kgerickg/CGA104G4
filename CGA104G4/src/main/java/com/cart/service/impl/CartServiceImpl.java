package com.cart.service.impl;

import com.cart.cache.CartCache;
import com.cart.cache.impl.CartCacheImpl;
import com.cart.pojo.Cart;
import com.cart.pojo.Item;
import com.cart.service.CartService;
import com.orders.model.OrdersDAO_interface;
import com.orders.model.OrdersJDBCDAO;
import com.orders.model.OrdersVO;
import com.prod.model.ProdDAO_interface;
import com.prod.model.ProdJDBCDAO;
import com.prod.model.ProdVO;
import com.store.model.StoreDAO;
import com.store.model.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CartServiceImpl implements CartService {
    private final CartCache cache = new CartCacheImpl();

    private final ProdDAO_interface prodDao = new ProdJDBCDAO();

    @Override
    public void put(Integer storeId, Cart cart) {
        AtomicInteger storeTotalPrc = new AtomicInteger();
        cart.getStoreMap().get(storeId).forEach((prodId, item) -> {
            ProdVO product = prodDao.findByPrimaryKey(prodId);
            StoreService storeService = new StoreService();
            String storeName = storeService.findByStoreId(storeId).getStoreName();
            item.setStoreName(storeName);
            item.setStoreId(storeId);
            item.setProdName(product.getProdName());
            item.setProdPrc(product.getProdPrc());
            item.setProdTotalPrc(product.getProdPrc() * item.getProdQty());
        });
        cart.getStoreMap().forEach((k,v)->v.forEach((k1,v2)->storeTotalPrc.getAndAdd(v2.getProdTotalPrc())));
        cart.setTotalPrc(storeTotalPrc.get());
        cache.put(cart);
    }

    @Override
    public Cart get(String userId) {
        Cart cart = cache.get(userId);
        return cart;
    }
    @Override
    public String getCart(String userId) {
        String cartJson = cache.getCart(userId);
        return cartJson;
    }

    @Override
    public void clear(String userId) {

        cache.clear(userId);
    }


    @Override
    public void reduceQty(String userId, Integer storeId, Integer prodId) {
        Cart cart = get(userId);
        Item item = cart.getStoreMap().get(storeId).get(prodId);
        Integer itemQty = item.getProdQty()-1;
        if(itemQty == 0){
            cart.getStoreMap().get(storeId).remove(item.getProdId());
        }else {
            item.setProdQty(itemQty);
        }

        put(storeId, cart);
    }

    @Override
    public void addQty(String userId, Integer storeId, Integer prodId) {
        Cart cart = get(userId);
        Item item = cart.getStoreMap().get(storeId).get(prodId);
        item.setProdQty(item.getProdQty() + 1);
        put(storeId, cart);
    }

//    @Override
//    public void putInDb(String userId,Integer storeId) {
//        Cart cart = cache.get(userId);
//         = cart.getStoreMap().get(storeId).get(prodId)
//        orderDao.insert(userId,storeId,);
//    }


//    public static void main(String[] args) {
//        CartServiceImpl service = new CartServiceImpl();
//        service.clear("1");
//        Cart cart = new Cart();
//        cart.setUserId("1");
//        Item item = new Item();
//        item.setProdId(1);
//        item.setProdQty(9);
//
//        Item item2 = new Item();
//        item2.setProdId(2);
//        item2.setProdQty(1);
//
//        cart.getItemMap().put(1, item);
//        cart.getItemMap().put(2, item2);
//        service.put(cart);
//        service.addQty("1",1);
//        service.reduceQty("1",2);
//
//        Cart test = service.get("1");
//        System.out.print(test);
//    }

}
