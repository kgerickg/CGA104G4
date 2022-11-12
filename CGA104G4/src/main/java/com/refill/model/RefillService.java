package com.refill.model;

import com.member.model.MemberDAO;
import com.member.model.MemberDAO_interface;

import com.member.model.MemberVO;

import java.util.List;

public class RefillService {
        MemberDAO_interface memdao = new MemberDAO();
        RefillDAO refillDAO = new RefillDAO();

    public Integer getToken(Integer memId) {

        MemberVO memberVO = memdao.findByPrimaryKey(memId);

        Integer memToken = memberVO.getMemToken();

       return memToken;
    }

    public List<RefillVO> getRefillRecord(Integer memId) {

            return refillDAO.findByFK(memId);
    }
}
