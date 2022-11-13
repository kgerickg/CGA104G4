package com.prod.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.prodtype.model.ProdTypeVO;
import com.store.model.StoreVO;

public class ProdVO implements java.io.Serializable{
	private Integer prodId;
	private Integer prodStat;
	private String prodName;
	private String prodCont;
	private Integer prodPrc;
	private Date prodTime;
	private StoreVO storeVO;
	private ProdTypeVO prodTypeVO;
	private Set<ProdVO> prods = new HashSet<ProdVO>();
	
	public Integer getProdId() {
		return prodId;
	}
	public void setProdId(Integer prodId) {
		this.prodId = prodId;
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
	public StoreVO getStoreVO() {
		return storeVO;
	}
	public void setStoreVO(StoreVO storeVO) {
		this.storeVO = storeVO;
	}
	public ProdTypeVO getProdTypeVO() {
		return prodTypeVO;
	}
	public void setProdTypeVO(ProdTypeVO prodTypeVO) {
		this.prodTypeVO = prodTypeVO;
	}
	public Set<ProdVO> getProds() {
		return prods;
	}
	public void setProds(Set<ProdVO> prods) {
		this.prods = prods;
	}
	
}	