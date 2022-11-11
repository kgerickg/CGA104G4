package com.lkcompl.model;

import java.util.List;

import com.member.model.MemberVO;
import com.utils.MailService;
import com.utils.RandomPassword;

public class LkComplServiceImpl implements LkComplService {
	
	private LkComplDao dao;
	
	public LkComplServiceImpl() {
		this.dao = new LkComplDaoImpl();
	}

	@Override
	public void insert(LkComplVO lkComplVO) {
		dao.insert(lkComplVO);
	}

	@Override
	public void update(LkComplVO lkComplVO) {
		dao.update(lkComplVO);
	}

	@Override
	public LkComplVO selectByLkCcId(Integer lkCcId) {
		return dao.selectByLkCcId(lkCcId);
	}

	@Override
	public List<LkComplVO> selectAll() {
		return dao.selectAll();
	}

	

	
	
	

}
