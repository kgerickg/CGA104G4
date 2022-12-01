package com.func.dao.impl;

import com.func.dao.FuncDao;
import com.func.entity.Func;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class FuncDaoImpl implements FuncDao {

    @PersistenceContext
    private Session session;

    @Override
    public Func selectByFuncId(Integer funcId) {
        return null;
    }

    @Override
    public int insert(Func pojo) {
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public int update(Func pojo) {
        return 0;
    }

    @Override
    public Func selectById(Integer id) {
        return null;
    }

    @Override
    public List<Func> selectAll() {
        return null;
    }
}
