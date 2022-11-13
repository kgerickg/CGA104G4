package com.member.model;

import com.utils.MailService;
import com.utils.RandomPassword;



import java.util.List;

public class MemberService {

    private MemberDAO_interface dao;

    public MemberService() {
        this.dao = new MemberDAO();
    }

    public List<MemberVO> getAll() {
        List<MemberVO> members;
        try {
            dao.beginTransaction();
            members = dao.getAll();
            dao.commit();
            return members;
        } catch (Exception e) {
            dao.rollback();
            return null;
        }

    }

    public MemberVO getOneMember(Integer memId) {
        try {
            dao.beginTransaction();
            MemberVO memberVO = dao.findByMemId(memId);
            dao.commit();
            return memberVO;
        } catch (Exception e) {
            dao.rollback();
            return null;
        }
    }

    public List<Integer> getMemId() {

        try {
            dao.beginTransaction();
            List<Integer> list = dao.getMemId();
            dao.commit();
            return list;
        } catch (Exception e) {
            dao.rollback();
            e.printStackTrace();
            return null;
        }

    }

    public void update(MemberVO memberVO) {
        try {
            dao.beginTransaction();
            dao.update(memberVO);
            dao.commit();
        } catch (Exception e) {
            dao.rollback();
            e.printStackTrace();
        }


    }

    public MemberVO findByMemId(Integer memId) {
        try {
            dao.beginTransaction();
            MemberVO memberVO = dao.findByMemId(memId);
            dao.commit();
            return memberVO;
        } catch (Exception e) {
            dao.rollback();
            return null;
        }

    }

    public Integer insertWithReturn(String memEmail, String memPwd, String memName, String memMobile, String memCity,
                                    String memDist, String memAdr) {

        MemberVO memberVO = new MemberVO();
        memberVO.setMemEmail(memEmail);
        memberVO.setMemPwd(memPwd);
        memberVO.setMemName(memName);
        memberVO.setMemMobile(memMobile);
        memberVO.setMemCity(memCity);
        memberVO.setMemDist(memDist);
        memberVO.setMemAdr(memAdr);

        try {
            dao.beginTransaction();
            Integer memId = dao.insertWithReturn(memberVO);
            dao.commit();
            return memId;
        } catch (Exception e) {
            dao.rollback();
            e.printStackTrace();
            return null;
        }
    }

    public MemberVO login(String memEmail, String memPwd) {
        try {
            dao.beginTransaction();
            MemberVO memberVO = dao.login(memEmail, memPwd);
            dao.commit();
            return memberVO;
        } catch (Exception e) {
            dao.rollback();
            e.printStackTrace();
            return null;
        }

    }

    public void updatePwd(MemberVO memberVO) {
        try {
            dao.beginTransaction();
            dao.updatePwd(memberVO);
            dao.commit();
        } catch (Exception e) {
            dao.rollback();
            e.printStackTrace();
        }

    }

    public Integer selectByMemEmail(String memEmail) {

        try {
            dao.beginTransaction();
            Integer memId = dao.selectByMemEmail(memEmail);
            dao.commit();
            return memId;
        } catch (Exception e) {
            dao.rollback();
            e.printStackTrace();
            return null;
        }
    }

    public boolean forgetPwd(String memEmail) {

        try {
            dao.beginTransaction();
            Integer memId = dao.selectByMemEmail(memEmail);
            if (memId == null) {
                dao.commit();
                return false;
            }
            String randomPwd = new RandomPassword().genRandomPassword();
            MemberVO memberVO = new MemberVO();
            memberVO.setMemId(memId);
            memberVO.setMemPwd(randomPwd);
            dao.updatePwd(memberVO);

            String title = "您的吉食響樂新密碼";
            String message = "您的新密碼為:" + randomPwd + "，請盡速登入並進行密碼變更。";
            new MailService().sendMail(memEmail, title, message);

            dao.commit();
            return true;
        } catch (Exception e) {
            dao.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public void updateAccState(MemberVO memberVO) {
        try {
            dao.beginTransaction();
            dao.updateAccState(memberVO);
            dao.commit();
        } catch (Exception e) {
            dao.rollback();
            e.printStackTrace();
        }

    }
}
