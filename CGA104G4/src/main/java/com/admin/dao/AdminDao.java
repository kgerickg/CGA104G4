package com.admin.dao;

import com.admin.entity.*;

import core.dao.CoreDao;

public interface AdminDao extends CoreDao<Admin, Integer> {
	Admin selectByAdmAcc(String admAcc);
	
	Admin selectForLogin(String admName, String admPwd);



}
