package com.lucky.model;
import java.io.Serializable;
import java.sql.Date;

public class LuckyVO implements Serializable{
	
	private Integer luckyId;
	private Integer storeId;
	private Integer lkStat;
	private String lkName;
	private String lkCont;
	private Integer lkPrc;
	private Date lkTime;
	
	public Integer getLuckyId() {
		return luckyId;
	}
	public void setLuckyId(Integer luckyId) {
		this.luckyId = luckyId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getLkStat() {
		return lkStat;
	}
	public void setLkStat(Integer lkStat) {
		this.lkStat = lkStat;
	}
	public String getLkName() {
		return lkName;
	}
	public void setLkName(String lkName) {
		this.lkName = lkName;
	}
	public String getLkCont() {
		return lkCont;
	}
	public void setLkCont(String lkCont) {
		this.lkCont = lkCont;
	}
	public Integer getLkPrc() {
		return lkPrc;
	}
	public void setLkPrc(Integer lkPrc) {
		this.lkPrc = lkPrc;
	}
	public Date getLkTime() {
		return lkTime;
	}
	public void setLkTime(Date lkTime) {
		this.lkTime = lkTime;
	}

}
