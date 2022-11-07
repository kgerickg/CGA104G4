package com.detail.model;
import java.sql.Date;

public class DetailVO implements java.io.Serializable{
	
	private Integer prodId;
	private Integer prodQty;
	private Integer ordId;
	
	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public Integer getProdQty() {
		return prodQty;
	}

	public void setProdQty(Integer prodQty) {
		this.prodQty = prodQty;
	}

	public Integer getOrdId() {
		return ordId;
	}

	public void setOrdId(Integer ordId) {
		this.ordId = ordId;
	}
	
    // for join dname from deptno
//    public com.dept.model.DeptVO getDeptVO() {
//	    com.dept.model.DeptService deptSvc = new com.dept.model.DeptService();
//	    com.dept.model.DeptVO deptVO = deptSvc.getOneDept(deptno);
//	    return deptVO;
//    }
}