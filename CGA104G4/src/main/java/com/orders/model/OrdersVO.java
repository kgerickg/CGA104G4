package com.orders.model;

import java.sql.Date;

import com.detail.model.DetailService;
import com.detail.model.DetailVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.store.model.StoreService;
import com.store.model.StoreVO;

public class OrdersVO implements java.io.Serializable{
	private Integer ordId;
	private Integer memId;
	private Integer storeId;
	private Integer ordAmt;
	private Integer ordStat;
	private Date ordTime;
	
	public Integer getOrdId() {
		return ordId;
	}
	public void setOrdId(Integer ordId) {
		this.ordId = ordId;
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
	public Integer getOrdAmt() {
		return ordAmt;
	}
	public void setOrdAmt(Integer ordAmt) {
		this.ordAmt = ordAmt;
	}
	public Integer getOrdStat() {
		return ordStat;
	}
	public void setOrdStat(Integer ordStat) {
		this.ordStat = ordStat;
	}
	public Date getOrdTime() {
		return ordTime;
	}
	public void setOrdTime(Date ordTime) {
		this.ordTime = ordTime;
		
	}
	// for join storeName, from storeId
    public StoreVO getStoreVO() {
    	StoreService storeSvc = new StoreService();
	    StoreVO storeVO = storeSvc.getOneStore(storeId);
	    return storeVO;
    }
    // for join memName, from memId
    public MemberVO getMemberVO() {
    	MemberService memberSvc = new MemberService();
    	MemberVO memberVO = memberSvc.getOneMember(memId);
    	return memberVO;
    }
}