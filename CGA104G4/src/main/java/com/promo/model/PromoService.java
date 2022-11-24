package com.promo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PromoService {
	@Autowired
	PromoDAO_interface dao;


	public List<PromoVO> getAll() {
		return dao.getAll();
	}

	@Transactional
    public void delete(Integer promoId) {
		dao.delete(promoId);
    }

    public boolean[] checkTime(Timestamp promoTimeS, Timestamp promoTimeE,Integer promoId) {
		boolean[] result = {false,false};
		Integer checkIdS = dao.checkTime(promoTimeS);
		Integer checkIdE = dao.checkTime(promoTimeE);
		if(checkIdS==null||checkIdS==promoId){
			result[0] = true;
		}
		if(checkIdE==null||checkIdE==promoId){
			result[1] = true;
		}
		return result;
    }

	public boolean[] checkTime(Timestamp promoTimeS, Timestamp promoTimeE) {
		boolean[] result = {false,false};
		Integer checkIdS = dao.checkTime(promoTimeS);
		Integer checkIdE = dao.checkTime(promoTimeE);
		if(checkIdS==null){
			result[0] = true;
		}
		if(checkIdE==null){
			result[1] = true;
		}
		return result;
	}
	@Transactional
	public void update(Integer promoId, String promoName, Integer promoVal, Timestamp promoTimeS, Timestamp promoTimeE, String promoCont) {
		PromoVO promoVO = new PromoVO();
		promoVO.setPromoId(promoId);
		promoVO.setPromoName(promoName);
		promoVO.setPromoVal(promoVal);
		promoVO.setPromoTimeS(promoTimeS);
		promoVO.setPromoTimeE(promoTimeE);
		promoVO.setPromoCont(promoCont);
		dao.update(promoVO);
	}
	@Transactional
	public void insert(String promoName, Integer promoVal, Timestamp promoTimeS, Timestamp promoTimeE, String promoCont) {
		PromoVO promoVO = new PromoVO();
		promoVO.setPromoName(promoName);
		promoVO.setPromoVal(promoVal);
		promoVO.setPromoTimeS(promoTimeS);
		promoVO.setPromoTimeE(promoTimeE);
		promoVO.setPromoCont(promoCont);
		dao.insert(promoVO);
	}
}
