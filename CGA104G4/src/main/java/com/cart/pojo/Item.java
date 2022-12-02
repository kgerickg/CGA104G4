package com.cart.pojo;

import lombok.Data;

@Data
public class Item {
    private Integer storeId;
    private Integer prodId;
    private Integer prodQty;
    private String prodName;
    private String storeName;
    private Integer prodTotalPrc;
    private Integer prodPrc;

}