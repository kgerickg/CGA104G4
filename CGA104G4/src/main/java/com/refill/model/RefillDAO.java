package com.refill.model;

import org.hibernate.query.Query;

import java.util.List;

public class RefillDAO implements RefillDAO_interface{

    public List<RefillVO> findByFK(Integer memId){
        List<RefillVO> list;
        try {
            beginTransaction();
            Query<RefillVO> query = getSession().createQuery("FROM RefillVO WHERE memId = :memId ", RefillVO.class);
            list = query.setParameter("memId",memId).list();
            commit();
            return list;
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void insert(RefillVO VO) {

    }

    @Override
    public void update(RefillVO VO) {

    }

    @Override
    public void delete(Integer tableId) {

    }

    @Override
    public RefillVO findByPrimaryKey(Integer tableId) {
        return null;
    }


    @Override
    public List<RefillVO> getAll() {
        return null;
    }

}
