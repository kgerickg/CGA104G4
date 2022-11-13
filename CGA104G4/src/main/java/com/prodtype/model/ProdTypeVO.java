package com.prodtype.model;

import java.util.HashSet;
import java.util.Set;

import com.prod.model.ProdVO;

public class ProdTypeVO implements java.io.Serializable{
	private Integer prodTypeId;
	private Integer prodTypeName;
	private Set<ProdVO> prods = new HashSet<ProdVO>();
	public Integer getProdTypeId() {
		return prodTypeId;
	}
	public void setProdTypeId(Integer prodTypeId) {
		this.prodTypeId = prodTypeId;
	}
	public Integer getProdTypeName() {
		return prodTypeName;
	}
	public void setProdTypeName(Integer prodTypeName) {
		this.prodTypeName = prodTypeName;
	}
	public Set<ProdVO> getProds() {
		return prods;
	}
	public void setProds(Set<ProdVO> prods) {
		this.prods = prods;
	}
}
	