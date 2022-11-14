package com.refill.model;


import com.member.model.MemberDAO_interface;
import com.member.model.MemberVO;
import com.utils.RandomPassword;
import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class RefillService {
        @Autowired
        private RefillDAO_interface refillDao;
        @Autowired
        private MemberDAO_interface memDao;

    @Transactional
    public Integer getToken(Integer memId) {
        MemberVO memberVO = memDao.findByPrimaryKey(memId);
        Integer memToken = memberVO.getMemToken();
        return memToken;
    }

    public List<RefillVO> getRefillRecord(Integer memId) {
            List<RefillVO> list = refillDao.findByFK(memId);
            return list;
    }

    public String buyToken(Integer refillToken, String url,Integer memId) {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String dateString = dateFormat.format(date);
        String itemName = "吉食響樂點數:"+refillToken +"點";
        String rdp = new RandomPassword().genRandomPassword();
        String indexUrl = url+"/front-index/index.html";
        String getDataUrl = url +"/RefillResultServlet";
        String customField1 = ""+memId+","+refillToken;

        AllInOne allInOne = new AllInOne("");
        AioCheckOutALL aco = new AioCheckOutALL();
        aco.setMerchantTradeNo(rdp+"G4FOOD");
        aco.setMerchantTradeDate(dateString);
        aco.setTotalAmount(refillToken.toString());
        aco.setTradeDesc("test");
        aco.setItemName(itemName);
        aco.setReturnURL(indexUrl);
        aco.setClientBackURL(indexUrl);
        aco.setOrderResultURL(getDataUrl);
        aco.setCustomField1(customField1);
        aco.setNeedExtraPaidInfo("N");


        return allInOne.aioCheckOut(aco,null);

    }
    @Transactional
    public void insert(Integer memId, Integer refillToken, Integer refillAmt) {
        RefillVO refillVO = new RefillVO();
        refillVO.setMemId(memId);
        refillVO.setRefillToken(refillToken);
        refillVO.setRefillAmt(refillAmt);
        refillDao.insert(refillVO);
        memDao.updateToKen(memId,refillToken);

    }

    public RefillVO findByPrimaryKey(Integer refillId) {
        return refillDao.findByPrimaryKey(refillId);
    }

    public List<RefillVO> getAll() {
       return refillDao.getAll();
    }
    @Transactional
    public JSONArray selectByMemEmail(String memEmail) {
        List<MemberVO> members = memDao.selectMemEmail(memEmail);
        JSONArray refillArray = new JSONArray();

        for(MemberVO member : members){
            List<RefillVO> refills = member.getRefillVOs();
            System.out.println(refills.size()==0);
            if(refills.size()!=0){
                for (RefillVO refillVO : refills) {
                    JSONObject reFillJson = new JSONObject();
                    reFillJson.put("RefillId", refillVO.getRefillId());
                    reFillJson.put("memId", member.getMemId());
                    reFillJson.put("memEmail", member.getMemEmail());
                    reFillJson.put("memName", member.getMemName());
                    reFillJson.put("RefillToken", refillVO.getRefillToken());
                    reFillJson.put("RefillAmt", refillVO.getRefillAmt());
                    SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                    String refillTime = ft.format(refillVO.getRefillTime());
                    reFillJson.put("refillTime", refillTime);
                    refillArray.put(reFillJson);
                }
            }
        }


        return refillArray;
    }
}
