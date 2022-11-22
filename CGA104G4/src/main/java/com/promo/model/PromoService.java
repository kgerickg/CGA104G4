package com.promo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromoService {
	@Autowired
	PromoDAO_interface dao;


	public List<PromoVO> getAll() {
		return dao.getAll();
	}
}
