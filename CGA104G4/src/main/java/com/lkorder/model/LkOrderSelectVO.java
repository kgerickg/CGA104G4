package com.lkorder.model;

import java.io.Serializable;
import java.sql.Date;

public class LkOrderSelectVO extends LkOrderVO implements Serializable  {
	protected static final long serialVersionUID = 1L;

	protected String lkName;
	protected String memName;
	
	public String getLkName() {
		return lkName;
	}


	public void setLkName(String lkName) {
		this.lkName = lkName;
	}


	public String getMemName() {
		return memName;
	}


	public void setMemName(String memName) {
		this.memName = memName;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public LkOrderSelectVO() {
		
	}
	
	
	public LkOrderSelectVO(Integer lkOrderId, Integer lkId, Integer memId, Integer lkTodayId, Integer lkOrdAmt,
			Integer lkOrdStat, Date lkOrdTimeS, Date lkOrdTaketime, Date lkOrdTimeE, String lkName, String memName) {
		this.lkOrderId = lkOrderId;
		this.lkId = lkId;
		this.memId = memId;
		this.lkTodayId = lkTodayId;
		this.lkOrdAmt = lkOrdAmt;
		this.lkOrdStat = lkOrdStat;
		this.lkOrdTimeS = lkOrdTimeS;
		this.lkOrdTaketime = lkOrdTaketime;
		this.lkOrdTimeE = lkOrdTimeE;
		this.lkName = lkName;
		this.memName = memName;

	}

}
