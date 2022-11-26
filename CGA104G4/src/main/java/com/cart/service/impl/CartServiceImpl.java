package com.cart.service.impl;

import com.cart.cache.CartCache;
import com.cart.cache.impl.CartCacheImpl;
import com.cart.pojo.Cart;
import com.cart.pojo.Item;
import com.cart.service.CartService;
import com.prod.model.ProdDAO_interface;
import com.prod.model.ProdJDBCDAO;
import com.prod.model.ProdVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CartServiceImpl implements CartService {
    private final CartCache cache = new CartCacheImpl();

    private final ProdDAO_interface prodDao = new ProdJDBCDAO();

    @Override
    public void put(Cart cart) {
        AtomicInteger totalPrice = new AtomicInteger();
        cart.getItemMap().forEach((prodId, item) -> {
            ProdVO product = prodDao.findByPrimaryKey(prodId);
            item.setProdName(product.getProdName());
            item.setProdPrc(product.getProdPrc());
            item.setProdTotalPrc(product.getProdPrc() * item.getProdQty());
            totalPrice.addAndGet(item.getProdTotalPrc());
        });
        cart.setTotalPrc(totalPrice.get());
        cache.put(cart);
    }

    @Override
    public Cart get(String userId) {
        Cart cart = cache.get(userId);
        return cart;
    }

    @Override
    public void clear(String userId) {
        cache.clear(userId);
    }

    @Override
    public void reduceQty(String userId, Integer prodId) {
        Cart cart = get(userId);
        Item item = cart.getItemMap().get(prodId);
        Integer itemQty = item.getProdQty()-1;
        if(itemQty == 0){
            cart.getItemMap().remove(item.getProdId());
        }else {
            item.setProdQty(itemQty);
        }

        put(cart);
    }

    @Override
    public void addQty(String userId, Integer prodId) {
        Cart cart = get(userId);
        Item item = cart.getItemMap().get(prodId);
        item.setProdQty(item.getProdQty() + 1);
        put(cart);
    }


    public static void main(String[] args) {
        CartServiceImpl service = new CartServiceImpl();
        service.clear("1");
        Cart cart = new Cart();
        cart.setUserId("1");
        Item item = new Item();
        item.setProdId(1);
        item.setProdQty(9);

        Item item2 = new Item();
        item2.setProdId(2);
        item2.setProdQty(1);

        cart.getItemMap().put(1, item);
        cart.getItemMap().put(2, item2);
        service.put(cart);
        service.addQty("1",1);
        service.reduceQty("1",2);

        Cart test = service.get("1");
        System.out.print(test);
    }

}
