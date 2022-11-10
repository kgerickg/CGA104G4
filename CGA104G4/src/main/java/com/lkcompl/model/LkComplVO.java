package com.lkcompl.model;

public class LkComplVO {
	private static final long serialVersionUID = 1L;
	private Integer lkCcId;		// 客訴編號PK
	private Integer lkOrdId;	// 福袋訂單編號
	private Integer lkCcStat;	// 處理狀態
	private String lkCcCont;	// 客訴內容
	private Integer lkRfdStat;	// 退款狀態
	
	public Integer getLkCcId() {
		return lkCcId;
	}
	public void setLkCcId(Integer lkCcId) {
		this.lkCcId = lkCcId;
	}
	public Integer getLkOrdId() {
		return lkOrdId;
	}
	public void setLkOrdId(Integer lkOrdId) {
		this.lkOrdId = lkOrdId;
	}
	public Integer getLkCcStat() {
		return lkCcStat;
	}
	public void setLkCcStat(Integer lkCcStat) {
		this.lkCcStat = lkCcStat;
	}
	public String getLkCcCont() {
		return lkCcCont;
	}
	public void setLkCcCont(String lkCcCont) {
		this.lkCcCont = lkCcCont;
	}
	public Integer getLkRfdStat() {
		return lkRfdStat;
	}
	public void setLkRfdStat(Integer lkRfdStat) {
		this.lkRfdStat = lkRfdStat;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public LkComplVO() {

	}
	
	public LkComplVO(Integer lkCcId, Integer lkOrdId, Integer lkCcStat, String lkCcCont, Integer lkRfdStat) {
		super();
		this.lkCcId = lkCcId;
		this.lkOrdId = lkOrdId;
		this.lkCcStat = lkCcStat;
		this.lkCcCont = lkCcCont;
		this.lkRfdStat = lkRfdStat;
	}
	

}
