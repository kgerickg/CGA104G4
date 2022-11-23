package com.promo.model;

import com.refill.model.RefillVO;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class PromoDAO implements PromoDAO_interface {

	@PersistenceContext
	private Session session;

	@Override
	public void insert(PromoVO promoVO) {

	}

	@Override
	public void update(PromoVO promoVO) {
		PromoVO originalpromo = session.get(PromoVO.class,promoVO.getPromoId());
		originalpromo.setPromoName(promoVO.getPromoName());
		originalpromo.setPromoVal(promoVO.getPromoVal());
		originalpromo.setPromoTimeS(promoVO.getPromoTimeS());
		originalpromo.setPromoTimeE(promoVO.getPromoTimeE());
		originalpromo.setPromoCont(promoVO.getPromoCont());
		session.merge(originalpromo);

	}

	@Override
	public void delete(Integer promoId) {
		PromoVO promoVO = new PromoVO();
		promoVO.setPromoId(promoId);
		session.remove(promoVO);
	}

	@Override
	public PromoVO findByPrimaryKey(Integer promoId) {
		return null;

	}

	@Override
	public List<PromoVO> getAll() {
		NativeQuery<PromoVO> nativeQuery = session.createNativeQuery("select * from Promo", PromoVO.class);
		List<PromoVO> PromoVOs = nativeQuery.list();
		return PromoVOs;
	}

	@Override
	public Integer checkTime(Timestamp promoTime) {
		Query<Integer> query = session.createQuery("SELECT promoId FROM PromoVO WHERE :time BETWEEN promoTimeS AND promoTimeE");
		query.setParameter("time",promoTime);
		Integer promoId = query.uniqueResult();
		return promoId;
	}
}
