package com.refill.model;


import com.member.model.MemberDAO;
import com.member.model.MemberDAO_interface;
import com.member.model.MemberVO;
import com.utils.RandomPassword;
import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class RefillService {
        @Autowired
        private RefillDAO_interface dao;



    public Integer getToken(Integer memId) {
        MemberDAO_interface memdao = new MemberDAO();
        MemberVO memberVO = memdao.findByPrimaryKey(memId);
        Integer memToken = memberVO.getMemToken();
       return memToken;
    }

    @Transactional
    public List<RefillVO> getRefillRecord(Integer memId) {
            List<RefillVO> list = dao.findByFK(memId);
            return list;
    }

    public String buyToken(Integer refillToken, String url) {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String dateString = dateFormat.format(date);
        String itemName = refillToken +"";
        String rdp = new RandomPassword().genRandomPassword();

        AllInOne allInOne = new AllInOne("");
        AioCheckOutALL aco = new AioCheckOutALL();
        aco.setMerchantTradeNo(rdp+"G4FOOD");
        aco.setMerchantTradeDate(dateString);
        aco.setTotalAmount(refillToken.toString());
        aco.setTradeDesc("test");
        aco.setItemName(itemName);
        aco.setClientBackURL(url);
        aco.setReturnURL(url);
        aco.setNeedExtraPaidInfo("N");

        return allInOne.aioCheckOut(aco,null);

    }
}
