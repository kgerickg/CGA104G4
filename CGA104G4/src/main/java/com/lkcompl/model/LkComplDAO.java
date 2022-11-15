package com.lkcompl.model;

import java.util.List;

import org.hibernate.query.NativeQuery;

public class LkComplDAO implements LkComplDAO_interface {




	@Override
	public void insert(LkComplVO lkComplVO) {
		 try {
	            beginTransaction();
	            getSession().persist(lkComplVO);

	        } catch (Exception e) {
	            rollback();
	            e.printStackTrace();
	        }
	}

	@Override
	public void update(LkComplVO LkComplVO) {
		try {
			beginTransaction();
			LkComplVO vo = getSession().load(LkComplVO.getClass(), LkComplVO.getLkCcCont());
			vo.setLkCcStat(LkComplVO.getLkCcStat());
			vo.setLkRfdStat(LkComplVO.getLkRfdStat());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
	}

	@Override
	public LkComplVO selectByLkCcId(Integer lkCcId) {
		LkComplVO lkComplVO;
	        try {
	            beginTransaction();
	            lkComplVO = getSession().get(LkComplVO.class, lkCcId);
	            commit();
	            return lkComplVO;
	        } catch (Exception e) {
	            rollback();
	            return null;
	        }
	}
	
	@Override
	public LkComplVO selectBymemId(Integer memId) {
		LkComplVO lkComplVO;
        try {
            beginTransaction();
            lkComplVO = getSession().get(LkComplVO.class, memId);
            commit();
            return lkComplVO;
        } catch (Exception e) {
            rollback();
            return null;
        }
	}

	@Override
	public List<LkComplVO> selectAll() {
		  List<LkComplVO> lkComplVOs;
	        try {
	            beginTransaction();
	            NativeQuery<LkComplVO> nativeQuery = getSession().createNativeQuery("select * from LK_COMPL", LkComplVO.class);
	            lkComplVOs = nativeQuery.list();
	            commit();
	            return lkComplVOs;
	        } catch (Exception e) {
	            rollback();
	            e.printStackTrace();
	            return null;
	        }
	}
	
	
	
	
	
	@Override
	public List<LkComplVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public LkComplVO findByPrimaryKey(Integer tableId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer tableId) {
		// TODO Auto-generated method stub
		
	}
	

}
