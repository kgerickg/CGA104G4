package com.store.model;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class StoreVO implements Serializable {

    private String storeAcc;
    private String storePwd;
    private Integer accStat;
    private String storeName;
    private String storeGui;
    private String storeTel;
    private String storeCity;
    private String storeDist;
    private String storeAdr;
    private Date storeRegTime;
    private byte[] storePic;
    private byte[] storeData;
    private Integer comtScore;
    private Integer comtQty;
    private Double storeLat;
    private Double storeLng;
    private Integer storeId;

}
