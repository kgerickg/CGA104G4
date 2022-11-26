package com.store.model;

import com.basicDAO.BasicDAO_interface;
import com.member.model.MemberVO;

import java.util.List;

public interface StoreDAO_interface extends BasicDAO_interface<StoreVO> {

    List<Integer> getStoreId();

    public StoreVO findByStoreId(Integer storeId);
    public StoreVO findByStoreName(String storeName);
    public List<String> getStoreName();

    List<StoreVO> selectStoreAcc(String storeAcc);

    public void updatePwd(StoreVO storeVO);

   public Integer selectByStoreAcc(String storeAcc);

    public Integer insertWithReturn(StoreVO storeVO);

    public StoreVO login(String storeAcc, String storePwd);

    public void updateAccStat(StoreVO storeVO);
}
