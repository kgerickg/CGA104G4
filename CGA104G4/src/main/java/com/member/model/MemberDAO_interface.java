package com.member.model;

import java.util.List;

import com.basicDAO.BasicDAO_interface;
import com.refill.model.RefillVO;

public interface MemberDAO_interface extends BasicDAO_interface<MemberVO> {

    public List<Integer> getMemId();

    public MemberVO login(String memEmail, String memPwd);

    public Integer selectByMemEmail(String memEmail);

    public Integer insertWithReturn(MemberVO memberVO);

    public void updatePwd(MemberVO memberVO);

    public void updateAccState(MemberVO memberVO);

    public MemberVO findByMemId(Integer memId);

    public void updateToKen(Integer memId, Integer refillToken);
    public void updateToKenBuy(Integer memId, Integer chargeToken);

    public List<MemberVO> selectMemEmail(String memEmail);
}
