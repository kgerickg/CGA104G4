package com.store.model;

import java.util.List;

public interface StoreDAO_interface {

    public void insert(StoreVO storeVO);

    public void update(StoreVO storeVO);

    public void delete(Integer storeId);

    public StoreVO findByPrimaryKey(Integer storeId);

    public List<StoreVO> getAll();


}
