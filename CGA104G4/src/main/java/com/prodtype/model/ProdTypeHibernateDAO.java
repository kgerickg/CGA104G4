package com.prodtype.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.*;

import com.detail.model.DetailVO;
import com.prod.model.ProdVO;
import com.utils.HibernateUtil;

public class ProdTypeHibernateDAO implements ProdTypeDAO_interface {
	
	private static final String GET_ALL_STMT = "from ProdTypeVO order by prodId";

	@Override
	public void insert(ProdTypeVO prodTypeVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(prodTypeVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(ProdTypeVO prodTypeVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(prodTypeVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}
	
	@Override
	public void delete(Integer prodTypeId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			ProdTypeVO prodTypeVO = (ProdTypeVO)session.get(ProdTypeVO.class, prodTypeId);
			session.delete(prodTypeVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public ProdTypeVO findByPrimaryKey(Integer prodTypeId) {
		ProdTypeVO prodTypeVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			prodTypeVO = (ProdTypeVO) session.get(ProdTypeVO.class, prodTypeId);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return prodTypeVO;
	}

	@Override
	public List<ProdTypeVO> getAll() {
		List<ProdTypeVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<ProdTypeVO> query = session.createQuery(GET_ALL_STMT, ProdTypeVO.class);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	
	@Override
	public Set<ProdVO> getProdsByProdTypeId(Integer prodTypeId) {
		Set<ProdVO> set = findByPrimaryKey(prodTypeId).getProds();
		return set;
	}
}