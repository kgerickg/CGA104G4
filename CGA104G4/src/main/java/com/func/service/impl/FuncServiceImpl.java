package com.func.service.impl;

import com.func.dao.FuncDao;
import com.func.entity.Func;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FuncServiceImpl implements FuncService {
    @Autowired
    private FuncDao dao;
    @Override
    public List<Func> findAll() {
        return null;
    }
}
