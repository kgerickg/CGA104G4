package com.lkcompl.model;

import java.util.List;

public interface LkComplDao {
	public Integer insert(LkComplVO lkComplVO);			// 新增因是新增多筆所以使用LkComplVO
	
	public LkComplVO update(LkComplVO LkComplVO);	// 修改因是修改多筆所以使用LkComplVO
	
	public LkComplVO selectByLkCcId(Integer lkCcId);	// 查詢單筆，以查詢客訴編號(PK)找到
	
	public List<LkComplVO> selectAll();					// 查詢全部，因查詢為查詢多筆所以使用<LkComplVO>
}
