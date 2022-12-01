package com.func.service.impl;

import com.admin.entity.Admin;
import com.func.entity.Func;
import core.service.CoreService;

import java.util.List;

public interface FuncService extends CoreService {
    List<Func> findAll();
}