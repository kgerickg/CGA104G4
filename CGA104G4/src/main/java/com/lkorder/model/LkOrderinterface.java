package com.lkorder.model;

import java.util.List;

public interface LkOrderinterface {
	public void insert(LkOrderVO lkOrderVO);

	public void update(LkOrderVO lkOrderVO);

	public void delete(Integer lkOrderVO);

	public LkOrderSelectVO findByPrimaryKey(Integer lkOrderVO);

	public LkOrderVO findByMemId(Integer lkOrderVO);

	public List<LkOrderVO> getAll();

	public List<LkOrderSelectVO> selectAll(Integer memId);

	public List<LkOrderSelectVO> findAll();
}
