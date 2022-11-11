package com.lkorder.model;

import java.io.Serializable;
import java.sql.Date;

public class LkOrderVO implements Serializable {
	protected static final long serialVersionUID = 1L;
	protected Integer lkOrderId;
	protected Integer lkId;
	protected Integer memId;
	protected Integer lkTodayId;
	protected Integer lkOrdAmt;
	protected Integer lkOrdStat;
	protected Date lkOrdTimeS;
	protected Date lkOrdTaketime;
	protected Date lkOrdTimeE;

	public Integer getLkOrderId() {
		return lkOrderId;
	}

	public void setLkOrderId(Integer lkOrderId) {
		this.lkOrderId = lkOrderId;
	}

	public Integer getLkId() {
		return lkId;
	}

	public void setLkId(Integer lkId) {
		this.lkId = lkId;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public Integer getLkTodayId() {
		return lkTodayId;
	}

	public void setLkTodayId(Integer lkTodayId) {
		this.lkTodayId = lkTodayId;
	}

	public Integer getLkOrdAmt() {
		return lkOrdAmt;
	}

	public void setLkOrdAmt(Integer lkOrdAmt) {
		this.lkOrdAmt = lkOrdAmt;
	}

	public Integer getLkOrdStat() {
		return lkOrdStat;
	}

	public void setLkOrdStat(Integer lkOrdStat) {
		this.lkOrdStat = lkOrdStat;
	}

	public Date getLkOrdTimeS() {
		return lkOrdTimeS;
	}

	public void setLkOrdTimeS(Date lkOrdTimeS) {
		this.lkOrdTimeS = lkOrdTimeS;
	}

	public Date getLkOrdTaketime() {
		return lkOrdTaketime;
	}

	public void setLkOrdTaketime(Date lkOrdTaketime) {
		this.lkOrdTaketime = lkOrdTaketime;
	}

	public Date getLkOrdTimeE() {
		return lkOrdTimeE;
	}

	public void setLkOrdTimeE(Date lkOrdTimeE) {
		this.lkOrdTimeE = lkOrdTimeE;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public LkOrderVO() {
	}

	public LkOrderVO(Integer lkOrderId, Integer lkId, Integer memId, Integer lkTodayId, Integer lkOrdAmt,
			Integer lkOrdStat, Date lkOrdTimeS, Date lkOrdTaketime, Date lkOrdTimeE) {
		this.lkOrderId = lkOrderId;
		this.lkId = lkId;
		this.memId = memId;
		this.lkTodayId = lkTodayId;
		this.lkOrdAmt = lkOrdAmt;
		this.lkOrdStat = lkOrdStat;
		this.lkOrdTimeS = lkOrdTimeS;
		this.lkOrdTaketime = lkOrdTaketime;
		this.lkOrdTimeE = lkOrdTimeE;
	}

	
	

}
