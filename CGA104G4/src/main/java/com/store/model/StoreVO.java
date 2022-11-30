package com.store.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="STORE")
public class StoreVO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STORE_ID")
    private Integer storeId;
    @Column(name = "STORE_ACC")
    private String storeAcc;
    @Column(name = "STORE_PWD")
    private String storePwd;
    @Column(name = "ACC_STAT",insertable = false)
    private Integer accStat;
    @Column(name = "STORE_NAME")
    private String storeName;
    @Column(name = "STORE_TEL")
    private String storeTel;
    @Column(name = "STORE_CITY")
    private String storeCity;
    @Column(name = "STORE_DIST")
    private String storeDist;
    @Column(name = "STORE_ADR")
    private String storeAdr;
    @Column(name = "STORE_REG_TIME",insertable = false)
    private Date storeRegTime;
    @Column(name = "STORE_PIC")

    private byte[] storePic;
    @Column(name = "STORE_DATA",insertable = false)
    private byte[] storeData;
    @Column(name = "COMT_SCORE",insertable = false)
    private Integer comtScore;
    @Column(name = "COMT_QTY",insertable = false)
    private Integer comtQty;
    @Column(name = "STORE_LAT",insertable = false)
    private Double storeLat;
    @Column(name = "STORE_LNG",insertable = false)
    private Double storeLng;

}
