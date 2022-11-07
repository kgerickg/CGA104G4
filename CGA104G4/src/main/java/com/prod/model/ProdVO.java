package com.prod.model;

import java.sql.Date;

public class ProdVO implements java.io.Serializable{
	private Integer prodId;
	private Integer storeId;
	private Integer prodTypeId;
	private Integer prodStat;
	private String prodName;
	private String prodCont;
	private Integer prodPrc;
	private Date prodTime;
	
	public Integer getProdId() {
		return prodId;
	}
	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getProdTypeId() {
		return prodTypeId;
	}
	public void setProdTypeId(Integer prodTypeId) {
		this.prodTypeId = prodTypeId;
	}
	public Integer getProdStat() {
		return prodStat;
	}
	public void setProdStat(Integer prodStat) {
		this.prodStat = prodStat;
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
	public Integer getProdPrc() {
		return prodPrc;
	}
	public void setProdPrc(Integer prodPrc) {
		this.prodPrc = prodPrc;
	}
	public Date getProdTime() {
		return prodTime;
	}
	public void setProdTime(Date prodTime) {
		this.prodTime = prodTime;
	}
}	