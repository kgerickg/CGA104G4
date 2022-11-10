package com.refill.model;

import com.member.model.MemberDAO;
import com.member.model.MemberService;
import com.member.model.MemberVO;

public class RefillService {


    public Integer getToken(Integer memId) {
        MemberService memSvc = new MemberService();
        MemberVO memberVO = memSvc.findByPrimaryKey(memId);

        Integer memToken = memberVO.getMemToken();

       return memToken;
    }
}
