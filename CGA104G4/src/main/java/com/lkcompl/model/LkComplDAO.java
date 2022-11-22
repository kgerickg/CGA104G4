package com.lkcompl.model;

import java.util.List;

import org.hibernate.query.NativeQuery;

import com.lkorder.model.LkOrderVO;

public class LkComplDAO implements LkComplDAO_interface {




	@Override
	public void insert(LkComplVO lkComplVO) {
		 try {
	            beginTransaction();
	            getSession().persist(lkComplVO);
	            commit();
	        } catch (Exception e) {
	            rollback();
	            e.printStackTrace();
	        }
	}

	@Override
	public void update(LkComplVO lkComplVO) {
		try {
			beginTransaction();
			LkComplVO vo = getSession().load(LkComplVO.class, lkComplVO.getLkCcId());
			vo.setLkCcStat(lkComplVO.getLkCcStat());
			vo.setLkRfdStat(lkComplVO.getLkRfdStat());
			vo.setLkCcId(lkComplVO.getLkCcId());
			vo.setLkOrdId(lkComplVO.getLkOrdId());
			vo.setLkCcCont(lkComplVO.getLkCcCont());
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
	}
	
	@Override
	public void goUpdate(LkComplVO lkComplVO) {
		try {
			beginTransaction();
			LkComplVO vo = getSession().load(LkComplVO.class, lkComplVO.getLkCcId());
			vo.setLkCcStat(lkComplVO.getLkCcStat());
			vo.setLkRfdStat(lkComplVO.getLkRfdStat());
			vo.setLkCcId(lkComplVO.getLkCcId());
			vo.setLkOrdId(lkComplVO.getLkOrdId());
			vo.setLkCcCont(lkComplVO.getLkCcCont());
			commit();
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
	public LkComplVO selectBymemId(Integer memId, Integer lkccId) {
		LkComplVO lkComplVO;
        try {
            beginTransaction();
            final String hql = "select * from LK_COMPL left join LK_ORDER on LK_COMPL.LK_ORD_ID  = LK_ORDER.LK_ORD_ID "
            		+ "where LK_ORDER.MEM_ID = :memid and LK_CC_ID = :ccid";
            lkComplVO = getSession().createNativeQuery(hql, LkComplVO.class)
            		.setParameter("memid", memId)
            		.setParameter("ccid", lkccId)
            		.uniqueResult();
            commit();
            return lkComplVO;
        } catch (Exception e) {
            rollback();
            return null;
        }
	}
	
	@Override
	public List<LkComplVO> selectBymemId(Integer memId) {
        try {
            beginTransaction();
            final String hql = "select * from LK_COMPL left join LK_ORDER on LK_COMPL.LK_ORD_ID  = LK_ORDER.LK_ORD_ID "
            		+ "where LK_ORDER.MEM_ID = :memid";
            List<LkComplVO> list = getSession().createNativeQuery(hql, LkComplVO.class)
            		.setParameter("memid", memId)
            		.list();
            commit();
            return list;
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
	public List<LkOrderVO> selectOrderidBymemId(Integer memId) {
		 try {
	            beginTransaction();
	            final String hql = "select * from LK_ORDER "
	            		+ "where MEM_ID = :memid";
	            List<LkOrderVO> list = getSession().createNativeQuery(hql, LkOrderVO.class)
	            		.setParameter("memid", memId)
	            		.list();
	            commit();
	            return list;
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
