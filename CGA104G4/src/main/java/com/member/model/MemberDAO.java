package com.member.model;

import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class MemberDAO implements MemberDAO_interface {

    @Override
    public void update(MemberVO memberVO) {

        try {
            beginTransaction();

            MemberVO memberVOorignal = getSession().get(MemberVO.class, memberVO.getMemId());

            memberVOorignal.setMemName(memberVO.getMemName());
            memberVOorignal.setMemMobile(memberVO.getMemMobile());
            memberVOorignal.setMemCity(memberVO.getMemCity());
            memberVOorignal.setMemDist(memberVO.getMemDist());
            memberVOorignal.setMemAdr(memberVO.getMemAdr());
            if(memberVO.getMemPic()!=null){
                memberVOorignal.setMemPic(memberVO.getMemPic());
                getSession().merge(memberVOorignal);
            }
            commit();
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
        }

    }

    @Override
    public List<MemberVO> getAll() {
        List<MemberVO> members;
        try {
            beginTransaction();
            NativeQuery<MemberVO> nativeQuery = getSession().createNativeQuery("select * from Member", MemberVO.class);
            members = nativeQuery.list();
            commit();
            return members;
        } catch (Exception e) {
            rollback();
            return null;
        }
    }

    @Override
    public List<Integer> getMemId() {
        List<Integer> list;
        try {
            beginTransaction();
            Query<Integer> query = getSession().createQuery("SELECT memId FROM MemberVO", Integer.class);
            list = query.list();
            commit();
            return list;
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void insert(MemberVO memberVO) {
        try {
            beginTransaction();
            getSession().persist(memberVO);

        } catch (Exception e) {
            rollback();
            e.printStackTrace();
        }
    }

    //以下是MemberFrontServlet會用到的方法

    @Override
    public MemberVO findByPrimaryKey(Integer memId) {
        MemberVO memberVO;
        try {
            beginTransaction();
            memberVO = getSession().get(MemberVO.class, memId);
            commit();
            return memberVO;
        } catch (Exception e) {
            rollback();
            return null;
        }
    }

    @Override
    public MemberVO login(String memEmail, String memPwd) {
        MemberVO memberVO;
        try {
            beginTransaction();
            NativeQuery<MemberVO> query =
                    getSession().createNativeQuery("select * from MEMBER where MEM_EMAIL = :email and MEM_PWD = :pwd", MemberVO.class);
            query.setParameter("email", memEmail).setParameter("pwd", memPwd);
            memberVO = query.uniqueResult();
            commit();
            return memberVO;
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer selectByMemEmail(String memEmail) {
        Integer memId;
        try {
            beginTransaction();
            Query<Integer> query =
                    getSession().createQuery("SELECT memId FROM MemberVO where memEmail = :email")
                            .setParameter("email", memEmail);
            memId = query.uniqueResult();
            commit();
            return memId;

        } catch (Exception e) {
            rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer insertWithReturn(MemberVO memberVO) {
        try {
            beginTransaction();
            getSession().persist(memberVO);
            Integer memId = (Integer) getSession().getIdentifier(memberVO);
            commit();
            return memId;
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updatePwd(MemberVO memberVO) {
        try {
            beginTransaction();
            MemberVO memberVOorignal = getSession().get(MemberVO.class, memberVO.getMemId());
            memberVOorignal.setMemPwd(memberVO.getMemPwd());
            getSession().merge(memberVOorignal);
            commit();
            return true;
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void updateAccState(MemberVO memberVO) {
        try {
            beginTransaction();
            MemberVO memberVOorignal = getSession().get(MemberVO.class, memberVO.getMemId());
            memberVOorignal.setAccStat(memberVO.getAccStat());
            getSession().merge(memberVOorignal);
            commit();
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Integer memId) {


    }

}
