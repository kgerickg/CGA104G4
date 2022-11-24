//package com.cart.service.impl;
//
//import com.cart.cache.CartCache;
//import com.cart.cache.impl.CartCacheImpl;
//import com.cart.pojo.Cart;
//import com.cart.pojo.Item;
//import com.cart.service.CartService;
//import com.prod.model.ProdDAO_interface;
//import com.prod.model.ProdJDBCDAO;
//import com.prod.model.ProdVO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.function.BiConsumer;
//
//@Service
//public class CartServiceImpl implements CartService {
//    @Autowired
//    private CartCache cache;
//
//    private final ProdDAO_interface prodDao = new ProdJDBCDAO();
//
//    @Override
//    public void put(Cart cart) {
//        AtomicInteger totalPrice = new AtomicInteger();
//
//        cart.getCartMap().forEach(prodId,item)-> {
//            Integer pid = item.getProdId();
//            ProdVO product = prodDao.findByPrimaryKey(pid);
//            item.setProdName(product.getProdName());
//            item.setProdPrc(product.getProdPrc());
//            item.setProdTotalPrc(product.getProdPrc() * item.getProdQty());
//            totalPrice.addAndGet(item.getProdTotalPrc());
//        });
//        Integer pid = item.getProdId();
//        ProdVO product = prodDao.findByPrimaryKey(pid);
//        item.setProdName(product.getProdName());
//        item.setProdPrc(product.getProdPrc());
//        item.setProdTotalPrc(product.getProdPrc() * item.getProdQty());
//        totalPrice.addAndGet(item.getProdTotalPrc());
//        cart.setTotalPrc(totalPrice.get());
//        new CartCacheImpl().put(cart);
////        cache.put(userId, cart);
//    }
//
//    @Override
//    public Cart get(String userId) {
//        Cart cart = new CartCacheImpl().get(userId);
////        Cart cart = cache.get(userId);
//        return cart;
//    }
//    public static void main(String[] args) {
//        CartServiceImpl service = new CartServiceImpl();
//        Cart cart = new Cart();
//        Item item = new Item();
//        item.setProdId(1);
//        item.setProdQty(9);
//
//        Item item2 = new Item();
//        item2.setProdId(2);
//        item2.setProdQty(7);
//
//        cart.getCartList().add(item);
//        cart.getCartList().add(item2);
//        service.put(cart);
//
//        Cart test = service.get("1");
//        System.out.print(test);
//    }
//}
