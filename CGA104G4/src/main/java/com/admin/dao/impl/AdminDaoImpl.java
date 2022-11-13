package com.admin.dao.impl;

import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.admin.dao.AdminDao;
import com.admin.entity.Admin;
@Repository
public class AdminDaoImpl implements AdminDao {
	@PersistenceContext
	private Session session;
	
	@Override
	public int insert(Admin admin) {
		session.persist(admin);
		return 1;
	}

	@Override
	public int deleteById(Integer id) {
		return 0;
	}
	@Override
	public int update(Admin admin) {
		final StringBuilder hql = new StringBuilder()
				.append("UPDATE Admin SET ");
		final String admPwd = admin.getAdmPwd();
		if (admPwd != null && !admPwd.isEmpty()) {
			hql.append("ADM_PWD = :ADM_PWD,");
		}
		hql.append("ADM_ACC_STAT = :ADM_ACC_STAT,")
			.append("ADM_STAT = :ADM_STAT,")
			.append("ADM_PIC = :ADM_PIC,")
			.append("ADM_NAME = :ADM_NAME,")
		    .append("where ADM_ACC = :ADM_ACC");
		Query query = session.createQuery(hql.toString());
		if (admPwd != null && !admPwd.isEmpty()) {
			query.setParameter("ADM_PWD", admin.getAdmPwd());
		}
		return query.setParameter("ADM_ACC_STAT", admin.getAdmAccStat())
				.setParameter("ADM_STAT", admin.getAdmStat())
				.setParameter("ADM_PIC", admin.getAdmPic())
				.setParameter("ADM_NAME", admin.getAdmName())
				.setParameter("ADM_ACC", admin.getAdmAcc())
				.executeUpdate();
	
	}
	@Override
	public Admin selectById(Integer admId) {
		return session.get(Admin.class, admId);
	}
	@Override
	public List<Admin> selectAll() {
		final String hql = "from Admin order by ADM_ID";
		return session
				.createQuery(hql, Admin.class)
				.list();
	}
	@Override
	public Admin selectByAdmAcc(String admAcc) {
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Admin> criteriaQuery = criteriaBuilder.createQuery(Admin.class);
		Root<Admin> root = criteriaQuery.from(Admin.class);
		criteriaQuery.where(criteriaBuilder.equal(root.get("admAcc"), admAcc));
		return session.createQuery(criteriaQuery).uniqueResult();
	}
	@Override
	public Admin selectForLogin(String admAcc, String admPwd) {
		final String sql = "select * from ADMIN "
				+ "where ADM_ACC = :ADM_ACC and ADM_PWD = :ADM_PWD";
		return session.createNativeQuery(sql, Admin.class)
				.setParameter("ADM_ACC", admAcc)
				.setParameter("ADM_PWD", admPwd)
				.uniqueResult();
	}
}
