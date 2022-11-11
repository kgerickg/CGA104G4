package com.lkcompl.model;

import java.util.List;

public interface LkComplService {
	
	public void insert(LkComplVO lkComplVO);	// 新增不成功就回傳布林值
	
	public void update(LkComplVO lkComplVO);
	
	LkComplVO selectByLkCcId(Integer lkCcId);
	
	List<LkComplVO> selectAll();

}
