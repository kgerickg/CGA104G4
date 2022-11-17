package com.prod.model;

import java.sql.Date;

public class ProdVO implements java.io.Serializable{
	
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
	
}