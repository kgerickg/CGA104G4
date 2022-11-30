package com.lktoday.model;

import java.util.Objects;

public class TodayLuckyVO {
	private Integer lkId;
	private String lkName;
	private Integer lkTodayId;
	private Integer memId;
	private Integer storeId;
	private Integer lkPrc;

	public Integer getLkId() {
		return lkId;
	}

	public void setLkId(Integer lkId) {
		this.lkId = lkId;
	}

	public String getLkName() {
		return lkName;
	}

	public void setLkName(String lkName) {
		this.lkName = lkName;
	}

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

	public Integer getLkPrc() {
		return lkPrc;
	}

	public void setLkPrc(Integer lkPrc) {
		this.lkPrc = lkPrc;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		TodayLuckyVO obj = (TodayLuckyVO) o;
		return Objects.equals(lkId, obj.lkId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lkId);
	}
}
