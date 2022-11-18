package com.prodtype.model;

public class ProdTypeVO {

	private static final long serialVersionUID = 1L;

	private Integer prodTypeId;
	private String prodTypeName;
	
	public Integer getProdTypeId() {
		return prodTypeId;
	}

	public void setProdTypeId(Integer prodTypeId) {
		this.prodTypeId = prodTypeId;
	}

	public String getProdTypeName() {
		return prodTypeName;
	}

	public void setProdTypeName(String prodTypeName) {
		this.prodTypeName = prodTypeName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}