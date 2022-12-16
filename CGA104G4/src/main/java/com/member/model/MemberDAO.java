package com.member.model;


import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;


import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberDAO implements MemberDAO_interface {

    @PersistenceContext
    private Session session;

    @Override
    public void update(MemberVO memberVO) {

        MemberVO memberVOorignal = getSession().get(MemberVO.class, memberVO.getMemId());

        memberVOorignal.setMemName(memberVO.getMemName());
        memberVOorignal.setMemMobile(memberVO.getMemMobile());
        memberVOorignal.setMemCity(memberVO.getMemCity());
        memberVOorignal.setMemDist(memberVO.getMemDist());
        memberVOorignal.setMemAdr(memberVO.getMemAdr());
        if (memberVO.getMemPic() != null) {
            memberVOorignal.setMemPic(memberVO.getMemPic());
        }
        getSession().merge(memberVOorignal);
    }

    @Override
    public List<MemberVO> getAll() {
        NativeQuery<MemberVO> nativeQuery = getSession().createNativeQuery("select * from Member", MemberVO.class);
        List<MemberVO> members = nativeQuery.list();
        return members;
    }

    @Override
    public List<Integer> getMemId() {

        Query<Integer> query = getSession().createQuery("SELECT memId FROM MemberVO", Integer.class);
        List<Integer> list = query.list();
        return list;
    }

    @Override
    public void insert(MemberVO memberVO) {

    }

    //以下是MemberFrontServlet會用到的方法
    @Override
    public MemberVO findByMemId(Integer memId) {
        MemberVO memberVO = getSession().get(MemberVO.class, memId);
        return memberVO;
    }

    @Override
    public void updateToKen(Integer memId, Integer refillToken) {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemId(memId);
        MemberVO memberVOorignal = session.get(MemberVO.class, memberVO.getMemId());
        Integer NewToken = memberVOorignal.getMemToken()+refillToken;
        memberVOorignal.setMemToken(NewToken);
        session.merge(memberVOorignal);
    }
    @Override
    public void updateToKenBuy(Integer memId, Integer chargeToken) {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemId(memId);
        MemberVO memberVOorignal = session.get(MemberVO.class, memberVO.getMemId());
        Integer NewToken = memberVOorignal.getMemToken()-chargeToken;
        memberVOorignal.setMemToken(NewToken);
        session.update(memberVOorignal);
    }

    @Override
    public List<MemberVO> selectMemEmail(String memEmail) {
        StringBuilder sb = new StringBuilder();

        if(memEmail.length()>=2){
            for(int i=0 ; i< memEmail.length();i++){
                if(i==0){
                    sb.append("'%").append(memEmail.charAt(i)).append("%");
                }else if(i== memEmail.length()-1){
                    sb.append(memEmail.charAt(i)).append("%'");
                }else {
                    sb.append(memEmail.charAt(i)).append("%");
                }
            }
        }else {
            sb.append("'%").append(memEmail).append("%'");
        }
        String selectSQL = "select * from Member where MEM_EMAIL like"+ sb;
        NativeQuery<MemberVO> nativeQuery =
                session.createNativeQuery(selectSQL, MemberVO.class);
        List<MemberVO> members = nativeQuery.list();
        return members;
    }

    @Override
    public MemberVO findByPrimaryKey(Integer memId) {
        MemberVO memberVO = session.get(MemberVO.class, memId);
        return memberVO;
    }

    @Override
    public MemberVO login(String memEmail, String memPwd) {
        NativeQuery<MemberVO> query =
                getSession().createNativeQuery("select * from MEMBER where MEM_EMAIL = :email and MEM_PWD = :pwd", MemberVO.class);
        query.setParameter("email", memEmail).setParameter("pwd", memPwd);
        MemberVO memberVO = query.uniqueResult();
        return memberVO;

    }

    @Override
    public Integer selectByMemEmail(String memEmail) {
        Query<Integer> query =
                getSession().createQuery("SELECT memId FROM MemberVO where memEmail = :email")
                        .setParameter("email", memEmail);
        Integer memId = query.uniqueResult();

        return memId;
    }

    @Override
    public Integer insertWithReturn(MemberVO memberVO) {
        getSession().persist(memberVO);
        Integer memId = (Integer) getSession().getIdentifier(memberVO);
        return memId;
    }

    @Override
    public void updatePwd(MemberVO memberVO) {

        MemberVO memberVOorignal = getSession().get(MemberVO.class, memberVO.getMemId());
        memberVOorignal.setMemPwd(memberVO.getMemPwd());
        getSession().merge(memberVOorignal);

    }

    @Override
    public void updateAccState(MemberVO memberVO) {

        MemberVO memberVOorignal = getSession().get(MemberVO.class, memberVO.getMemId());
        memberVOorignal.setAccStat(memberVO.getAccStat());
        getSession().merge(memberVOorignal);


    }

    @Override
    public void delete(Integer memId) {
    }

}
