package com.prodtype.model;

import java.util.List;

import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.prod.model.ProdVO;

public class ProdTypeDAO implements ProdTypeDAO_interface {

    @Override
    public void update(ProdTypeVO prodTypeVO) {

        try {
            beginTransaction();

            ProdTypeVO prodTypeVOoriginal = getSession().get(ProdTypeVO.class, prodTypeVO.getProdTypeId());

          
            prodTypeVOoriginal.setProdTypeId(prodTypeVO.getProdTypeId());
            prodTypeVOoriginal.setProdTypeName(prodTypeVO.getProdTypeName());
            
            commit();
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<ProdTypeVO> getAll() {
        List<ProdTypeVO> prodTypes;
        try {
            beginTransaction();
            NativeQuery<ProdTypeVO> nativeQuery = getSession().createNativeQuery("select * from PROD_TYPE", ProdTypeVO.class);
            prodTypes = nativeQuery.list();
            commit();
            return prodTypes;
        } catch (Exception e) {
            rollback();
            return null;
        }
    }

    @Override
    public List<Integer> getProdTypeId() {
        List<Integer> list;
        try {
            beginTransaction();
            Query<Integer> query = getSession().createQuery("SELECT prodTypeId FROM ProdTypeVO", Integer.class);
            list = query.list();
            commit();
            return list;
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void insert(ProdTypeVO prodTypeVO) {
        try {
            beginTransaction();
            getSession().persist(prodTypeVO);

        } catch (Exception e) {
            rollback();
            e.printStackTrace();
        }
    }

    //以下是ProdTypeFrontServlet會用到的方法

    @Override
    public ProdTypeVO findByPrimaryKey(Integer prodTypeId) {
        ProdTypeVO prodTypeVO;
        try {
            beginTransaction();
            prodTypeVO = getSession().get(ProdTypeVO.class, prodTypeId);
            commit();
            return prodTypeVO;
        } catch (Exception e) {
            rollback();
            return null;
        }
    }

    @Override
    public ProdVO getProdsByProdTypeId(Integer prodTypeId) {
    	ProdVO prodVO;
        try {
            beginTransaction();
            prodVO = getSession().get(ProdVO.class, prodTypeId);
            commit();
            return prodVO;
        } catch (Exception e) {
            rollback();
            return null;
        }
    }

    @Override
    public void delete(Integer prodTypeId) {
    }
}