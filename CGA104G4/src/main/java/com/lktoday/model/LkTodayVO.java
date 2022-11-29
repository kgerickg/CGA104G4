package com.lktoday.model;

import java.sql.Date;

public class LkTodayVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer lkTodayId;
	private Integer memId;
	private Integer storeId;
	private Integer luckyId;
	private Date lkTodayTime;
	private Integer lkQty;
	private Integer lkPrc;

	public Integer getLkTodayId() {
		return lkTodayId;
	}

	public void setLkTodayId(Integer lkTodayId) {
		this.lkTodayId = lkTodayId;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getLuckyId() {
		return luckyId;
	}

	public void setLuckyId(Integer luckyId) {
		this.luckyId = luckyId;
	}

	public Date getLkTodayTime() {
		return lkTodayTime;
	}

	public void setLkTodayTime(Date lkTodayTime) {
		this.lkTodayTime = lkTodayTime;
	}

	public Integer getLkQty() {
		return lkQty;
	}

	public void setLkQty(Integer lkQty) {
		this.lkQty = lkQty;
	}
	
	public Integer getLkPrc() {
		return lkQty;
	}

	public void setLkPrc(Integer lkPrc) {
		this.lkPrc = lkPrc;
	}
}
