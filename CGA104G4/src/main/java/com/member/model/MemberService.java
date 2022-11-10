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

        return dao.getAll();
    }
    
    public MemberVO getOneMember(Integer memId) {
		return dao.findByPrimaryKey(memId);
	}

    public List<Integer> getMemId() {

        return dao.getMemId();
    }

    public void update(MemberVO memberVO) {

        dao.update(memberVO);

    }

    public MemberVO findByPrimaryKey(Integer memId) {

        return dao.findByPrimaryKey(memId);

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

        return dao.insertWithReturn(memberVO);
    }

    public MemberVO login(String memEmail, String memPwd) {
        return dao.login(memEmail, memPwd);

    }

    public void updatePwd(MemberVO memberVO){
        dao.updatePwd(memberVO);
    }

    public Integer selectByMemEmail(String memEmail){
        return dao.selectByMemEmail(memEmail);
    }

    public boolean forgetPwd(String memEmail) {
        Integer memId = dao.selectByMemEmail(memEmail);

        if(memId==null){
        return false;
        }

        String randomPwd = new RandomPassword().genRandomPassword();

        MemberVO memberVO = new MemberVO();
        memberVO.setMemId(memId);
        memberVO.setMemPwd(randomPwd);

        boolean updateresult = dao.updatePwd(memberVO);
        if(updateresult){

            String title = "您的吉食響樂新密碼";
            String message = "您的新密碼為:"+ randomPwd+"，請盡速登入並進行密碼變更。";
            new MailService().sendMail(memEmail,title,message);
            return true;
        }

        return false;

    }

    public void updateAccState(MemberVO memberVO) {
        dao.updateAccState(memberVO);
    }
}
