package com.refill.model;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class RefillDAO implements RefillDAO_interface{

    @PersistenceContext
    private Session session;

    public List<RefillVO> findByFK(Integer memId) {
        Query<RefillVO> query = session.createQuery("FROM RefillVO WHERE memId = :memId ", RefillVO.class);
        return query.setParameter("memId", memId).list();
    }


    @Override
    public void insert(RefillVO refillVO) {
        session.merge(refillVO);

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
