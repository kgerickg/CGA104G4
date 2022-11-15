package com.prod.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import com.utils.HibernateUtil;

import CompositeQuery.HibernateUtil_CompositeQuery_PROD;

public class ProdHibernateDAO implements ProdDAO_interface {
	
	private static final String GET_ALL_STMT = "from ProdTypeVO order by prodId";

	@Override
	public void insert(ProdVO prodVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(prodVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(ProdVO prodVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(prodVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}
	
	@Override
	public void delete(Integer prodId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			ProdVO prodVO = new ProdVO();
			prodVO.setProdId(prodId);
			session.delete(prodVO);
			
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public ProdVO findByPrimaryKey(Integer prodId) {
		ProdVO prodVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			prodVO = (ProdVO) session.get(ProdVO.class, prodId);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return prodVO;
	}

	@Override
	public List<ProdVO> getAll() {
		List<ProdVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<ProdVO> query = session.createQuery(GET_ALL_STMT, ProdVO.class);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	
	@Override
	public List<ProdVO> getAll(Map<String, String[]> map) {
		List<ProdVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			list = HibernateUtil_CompositeQuery_PROD.getAllC(map);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@Override
	public Set<ProdVO> getProdsByStoreId(Integer storeId) {
		Set<ProdVO> set = findByPrimaryKey(storeId).getProds();
		return set;
	}
}