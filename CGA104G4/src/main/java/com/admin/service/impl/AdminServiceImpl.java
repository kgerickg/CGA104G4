package com.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.admin.dao.AdminDao;
import com.admin.entity.Admin;
import com.admin.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao dao;

    @Transactional
    @Override
    public Admin register(Admin admin) {
        if (admin.getAdmAcc() == null) {
            admin.setMessage("帳號未輸入");
            admin.setSuccessful(false);
            return admin;
        }

        if (admin.getAdmPwd() == null) {
            admin.setMessage("密碼未輸入");
            admin.setSuccessful(false);
            return admin;
        }

        if (admin.getAdmName() == null) {
            admin.setMessage("暱稱未輸入");
            admin.setSuccessful(false);
            return admin;
        }

        if (dao.selectByAdmAcc(admin.getAdmAcc()) != null) {
            admin.setMessage("帳號重複");
            admin.setSuccessful(false);
            return admin;
        }
        dao.insert(admin);
        admin.setMessage("新增成功");
        admin.setSuccessful(true);
        return admin;
    }

    @Override
    public Admin login(Admin admin) {
        final String admAcc = admin.getAdmAcc();
        final String admPwd = admin.getAdmPwd();

        if (admAcc == null) {
            admin.setMessage("帳號未輸入");
            admin.setSuccessful(false);
            return admin;
        }

        if (admPwd == null) {
            admin.setMessage("密碼未輸入");
            admin.setSuccessful(false);
            return admin;
        }

        admin = dao.selectForLogin(admAcc, admPwd);
        if (admin == null) {
            admin = new Admin();
            admin.setMessage("帳號或密碼錯誤");
            admin.setSuccessful(false);
            return admin;
        }
        if (admin.getAdmAccStat() == 0) {
            admin.setMessage("你已被停權請聯絡管理員");
            admin.setSuccessful(false);
            return admin;
        }

        admin.setMessage("登入成功");
        admin.setSuccessful(true);
        return admin;
    }

    @Transactional
    @Override
    public Admin edit(Admin admin) {
        final Admin oAdmin = dao.selectByAdmAcc(admin.getAdmAcc());
        admin.setAdmStat(oAdmin.getAdmStat());
        final int resultCount = dao.update(admin);
        admin.setSuccessful(resultCount > 0);
        admin.setMessage(resultCount > 0 ? "更新成功" : "更新失敗");
        return admin;
    }

    @Override
    public List<Admin> findAll() {
        return dao.selectAll();
    }

    @Transactional
    @Override
    public boolean save(Admin admin) {
        return dao.update(admin) > 0;
    }

}
