package com.prod.model;

import java.util.List;

import org.hibernate.query.NativeQuery;

public class ProdDAO implements ProdDAO_interface {

    @Override
    public void update(ProdVO prodVO) {

        try {
            beginTransaction();
     
            ProdVO prodVOoriginal = getSession().get(ProdVO.class, prodVO.getProdId());

            prodVOoriginal.setStoreId(prodVO.getStoreId());
            prodVOoriginal.setProdTypeId(prodVO.getProdTypeId());
            prodVOoriginal.setProdStat(prodVO.getProdStat());
            prodVOoriginal.setProdName(prodVO.getProdName());
            prodVOoriginal.setProdCont(prodVO.getProdCont());
            prodVOoriginal.setProdPrc(prodVO.getProdPrc());
            prodVOoriginal.setProdTime(prodVO.getProdTime());
            
            commit();
            
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<ProdVO> getAll() {
        List<ProdVO> prods;
        try {
            beginTransaction();
            NativeQuery<ProdVO> nativeQuery = getSession().createNativeQuery("select * from PROD", ProdVO.class);
            prods = nativeQuery.list();
            commit();
            return prods;
        } catch (Exception e) {
            rollback();
            return null;
        }
    }

    @Override
    public void insert(ProdVO prodVO) {
        try {
            beginTransaction();
            getSession().persist(prodVO);

        } catch (Exception e) {
            rollback();
            e.printStackTrace();
        }
    }

    @Override
    public ProdVO findByPrimaryKey(Integer prodId) {
        ProdVO prodVO;
        try {
            beginTransaction();
            prodVO = getSession().get(ProdVO.class, prodId);
            commit();
            return prodVO;
        } catch (Exception e) {
            rollback();
            return null;
        }
    }

    @Override
    public ProdVO getProdsByStoreId(Integer storeId) {
    	ProdVO prodVO;
        try {
            beginTransaction();
            prodVO =getSession().get(ProdVO.class, storeId);
            commit();
            return prodVO;
        } catch (Exception e) {
            rollback();
            return null;
        }
    }

    @Override
    public void updateProdStat(ProdVO prodVO) {
        try {
            beginTransaction();
            ProdVO prodVOoriginal = getSession().get(ProdVO.class, prodVO.getProdId());
            prodVOoriginal.setProdStat(prodVO.getProdId());
            getSession().merge(prodVOoriginal);
            commit();
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Integer prodId) {
    }
}
