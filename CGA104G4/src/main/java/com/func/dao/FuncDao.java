package com.func.dao;

import com.func.entity.Func;
import core.dao.CoreDao;

import java.util.List;

public interface FuncDao extends CoreDao<Func, Integer>{

    Func selectByFuncId(Integer funcId);

}
