package com.store.model;
import java.io.Serializable;
import java.sql.Date;

public class StoreVO implements Serializable {
    private String storeAcc;
    private String storePwd;
    private Integer accStat;
    private String storeName;
    private String storeGui;
    private String storeRep;
    private String storeTel;
    private String storeFax;
    private String storeAd;
    private String storeCon;
    private String storeConTel;
    private String storeConAd;
    private String storeEmail;
    private Date storeRegTime;
    private Byte[] storeData;
    private Integer comtScore;
    private Integer comtQty;
    private Double storeLat;
    private Double storeLng;
    private Integer storeId;

    public String getStoreAcc() {
        return storeAcc;
    }
    public void setStoreAcc(String storeAcc) {
        this.storeAcc = storeAcc;
    }
    public String getStorePwd() {
        return storePwd;
    }
    public void setStorePwd(String storePwd) {
        this.storePwd = storePwd;
    }
    public Integer getAccStat() {
        return accStat;
    }
    public void setAccStat(Integer accStat) {
        this.accStat = accStat;
    }
    public String getStoreName() {
        return storeName;
    }
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    public String getStoreGui() {
        return storeGui;
    }
    public void setStoreGui(String storeGui) {
        this.storeGui = storeGui;
    }
    public String getStoreRep() {
        return storeRep;
    }
    public void setStoreRep(String storeRep) {
        this.storeRep = storeRep;
    }
    public String getStoreTel() {
        return storeTel;
    }
    public void setStoreTel(String storeTel) {
        this.storeTel = storeTel;
    }
    public String getStoreFax() {
        return storeFax;
    }
    public void setStoreFax(String storeFax) {
        this.storeFax = storeFax;
    }
    public String getStoreAd() {
        return storeAd;
    }
    public void setStoreAd(String storeAd) {
        this.storeAd = storeAd;
    }
    public String getStoreCon() {
        return storeCon;
    }
    public void setStoreCon(String storeCon) {
        this.storeCon = storeCon;
    }
    public String getStoreConTel() {
        return storeConTel;
    }
    public void setStoreConTel(String storeConTel) {
        this.storeConTel = storeConTel;
    }
    public String getStoreConAd() {
        return storeConAd;
    }
    public void setStoreConAd(String storeConAD) {
        this.storeConAd = storeConAD;
    }
    public String getStoreEmail() {
        return storeEmail;
    }
    public void setStoreEmail(String storeEmail) {
        this.storeEmail = storeEmail;
    }
    public Date getStoreRegTime() {
        return storeRegTime;
    }
    public void setStoreRegTime(Date storeRegTime) {
        this.storeRegTime = storeRegTime;
    }
    public Byte[] getStoreData() {
        return storeData;
    }
    public void setStoreData(Byte[] storeData) {
        this.storeData = storeData;
    }
    public Integer getComtScore() {
        return comtScore;
    }
    public void setComtScore(Integer comtScore) {
        this.comtScore = comtScore;
    }
    public Integer getComtQty() {
        return comtQty;
    }
    public void setComtQty(Integer comtQty) {
        this.comtQty = comtQty;
    }
    public Double getStoreLat() {
        return storeLat;
    }
    public void setStoreLat(Double storeLat) {
        this.storeLat = storeLat;
    }
    public Double getStoreLng() {
        return storeLng;
    }
    public void setStoreLng(Double storeLng) {
        this.storeLng = storeLng;
    }
    public Integer getStoreId() {
        return storeId;
    }
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }


}
