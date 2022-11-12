package com.lkcompl.model;

import java.util.List;

public class LkComplServiceImpl implements LkComplService {
	
	private LkComplDao dao;
	
	public LkComplServiceImpl() {
		dao = new LkComplDaoImpl();
	}

	@Override
	public boolean insert(LkComplVO lkComplVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(LkComplVO lkComplVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LkComplVO selectByLkCcId(Integer lkCcId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<LkComplVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
