package com.store.model;

import com.member.model.StoreVo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class StoreDAO implements StoreDAO_interface {
    @PersistenceContext
    private Session session;
    @Override
    public void insert(StoreVO VO) {
        StoreVo storeVOorignal = getSession().get(StoreVo.class, memberVO.getMemId());

        storeVOorignal.setMemName(memberVO.getMemName());
        storeVOorignal.setMemMobile(memberVO.getMemMobile());
        storeVOorignal.setMemCity(memberVO.getMemCity());
        storeVOorignal.setMemDist(memberVO.getMemDist());
        storeVOorignal.setMemAdr(memberVO.getMemAdr());
        if (memberVO.getMemPic() != null) {
            memberVOorignal.setMemPic(memberVO.getMemPic());
            getSession().merge(memberVOorignal);
        }
    }

    @Override
    public void update(StoreVO VO) {

    }

    @Override
    public void delete(Integer tableId) {

    }

    @Override
    public StoreVO findByPrimaryKey(Integer tableId) {
        return null;
    }

    @Override
    public List<StoreVO> getAll() {
        return null;
    }

    @Override
    public Session getSession() {
        return StoreDAO_interface.super.getSession();
    }

    @Override
    public Transaction beginTransaction() {
        return StoreDAO_interface.super.beginTransaction();
    }

    @Override
    public void commit() {
        StoreDAO_interface.super.commit();
    }

    @Override
    public void rollback() {
        StoreDAO_interface.super.rollback();
    }
}
