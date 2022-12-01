package com.admin.dao.impl;

import com.admin.dao.AdminDao;
import com.admin.entity.Admin;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class AdminDaoImpl implements AdminDao {
    @PersistenceContext
    private Session session;

    @Override
    public int insert(Admin admin) {
        session.merge(admin);
        return 1;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override

    public int update(Admin admin) {
        Admin adminorignal = session.get(Admin.class, admin.getAdmId());

        adminorignal.setAdmPwd(admin.getAdmPwd());
        adminorignal.setAdmName(admin.getAdmName());
        adminorignal.setAdmPic(admin.getAdmPic());
        adminorignal.setAdmAcc(admin.getAdmAcc());
        if (admin.getAdmRoleMem() != null) {
            adminorignal.setAdmRoleMem(admin.getAdmRoleMem());
        }
        if (admin.getAdmRoleEmp() != null) {
            adminorignal.setAdmRoleEmp(admin.getAdmRoleEmp());
        }
        if (admin.getAdmRoleRef() != null) {
            adminorignal.setAdmRoleRef(admin.getAdmRoleRef());
        }
        if (admin.getAdmPic() != null) {
            adminorignal.setAdmPic(admin.getAdmPic());
        }
        if (admin.getAdmStat() != null) {
            adminorignal.setAdmStat(admin.getAdmStat());
        }
        if (admin.getAdmAccStat() != null) {
            adminorignal.setAdmAccStat(admin.getAdmAccStat());
        }
        return session.merge(adminorignal) != null ? 1 : 0;
    }


    @Override
    public Admin selectById(Integer admId) {
        return session.get(Admin.class, admId);
    }

    @Override
    public List<Admin> selectAll() {
        final String hql = "from Admin order by ADM_ID";
        return session
                .createQuery(hql, Admin.class)
                .list();
    }

    @Override
    public Admin selectByAdmAcc(String admAcc) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Admin> criteriaQuery = criteriaBuilder.createQuery(Admin.class);
        Root<Admin> root = criteriaQuery.from(Admin.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("admAcc"), admAcc));
        return session.createQuery(criteriaQuery).uniqueResult();
    }


    @Override
    public Admin selectForLogin(String admAcc, String admPwd) {
        final String sql = "select * from ADMIN left join " +
                "ADM_FUNCTION on ADMIN.ADM_ID = ADM_FUNCTION.ADM_ID "
                + "where ADMIN.ADM_ACC = :ADM_ACC and ADMIN.ADM_PWD = :ADM_PWD";
        return session.createNativeQuery(sql, Admin.class)
                .setParameter("ADM_ACC", admAcc)
                .setParameter("ADM_PWD", admPwd)
                .uniqueResult();
    }
}
