package com.lucky.model;

import java.util.*;

import com.lucky.model.LuckyVO;

public interface LuckyDAOinterface {
	
	public void insert(LuckyVO luckyVO);
	public void update(LuckyVO luckyVO);
	public void delete(Integer luckyId);
	public LuckyVO findByPrimaryKey(Integer luckyId);
	public List<LuckyVO> getAll();
	public List<LuckyVO> getAll(Map<String, String[]> map);

}
