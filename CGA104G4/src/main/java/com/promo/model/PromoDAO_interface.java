package com.promo.model;

import com.basicDAO.BasicDAO_interface;

import java.sql.Timestamp;

public interface PromoDAO_interface extends BasicDAO_interface<PromoVO> {

    public Integer checkTime(Timestamp promoTime);


    PromoVO isInPromo(Timestamp today);
}
