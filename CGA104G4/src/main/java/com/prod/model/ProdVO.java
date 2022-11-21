package com.prod.model;

import com.prodtype.model.ProdTypeService;
import com.prodtype.model.ProdTypeVO;
import com.store.model.StoreService;
import com.store.model.StoreVO;

import java.sql.Date;

public class ProdVO {

	private static final long serialVersionUID = 1L;

	private Integer prodId;
	private Integer prodTypeId;
	private Integer storeId;
	private String prodName;
	private String prodCont;
	private Integer prodPrc;
	private Date prodTime;
	private Integer prodStat;

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public Integer getProdTypeId() {
		return prodTypeId;
	}

	public void setProdTypeId(Integer prodTypeId) {
		this.prodTypeId = prodTypeId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdCont() {
		return prodCont;
	}

	public void setProdCont(String prodCont) {
		this.prodCont = prodCont;
	}

	public Date getProdTime() {
		return prodTime;
	}

	public void setProdTime(Date prodTime) {
		this.prodTime = prodTime;
	}

	public Integer getProdStat() {
		return prodStat;
	}

	public void setProdStat(Integer prodStat) {
		this.prodStat = prodStat;
	}

	public Integer getProdPrc() {
		return prodPrc;
	}

	public void setProdPrc(Integer prodPrc) {
		this.prodPrc = prodPrc;
	}

	// for join storeName, from storeId
	public StoreVO getStoreVO() {
		StoreService storeSvc = new StoreService();
		StoreVO storeVO = storeSvc.getOneStore(storeId);
		return storeVO;
	}

	// for join prodTypeName from prodTypeId
	public ProdTypeVO getProdTypeVO() {
		ProdTypeService prodTypeSvc = new ProdTypeService();
		ProdTypeVO prodTypeVO = prodTypeSvc.getOneProdType(prodTypeId);
		return prodTypeVO;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}