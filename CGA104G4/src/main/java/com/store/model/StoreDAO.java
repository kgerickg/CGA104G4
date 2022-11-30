package com.store.model;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class StoreDAO implements StoreDAO_interface {
    @PersistenceContext
    private Session session;

    @Override
    public void update(StoreVO storeVO) {

        StoreVO oStoreVO = getSession().get(StoreVO.class, storeVO.getStoreId());

        oStoreVO.setStoreName(storeVO.getStoreName());
        oStoreVO.setStoreTel(storeVO.getStoreTel());
        oStoreVO.setStoreCity(storeVO.getStoreCity());
        oStoreVO.setStoreDist(storeVO.getStoreDist());
        oStoreVO.setStoreAdr(storeVO.getStoreAdr());
        if (storeVO.getStorePic() != null) {
            oStoreVO.setStorePic(storeVO.getStorePic());
            getSession().merge(oStoreVO);
        }

    }

    @Override
    public List<StoreVO> getAll() {
        NativeQuery<StoreVO> nativeQuery = getSession().createNativeQuery("select * from Store", StoreVO.class);
        List<StoreVO> stores = nativeQuery.list();
        return stores;
    }

    @Override
    public List<Integer> getStoreId() {

        Query<Integer> query = getSession().createQuery("SELECT storeId FROM StoreVO ", Integer.class);
        List<Integer> list = query.list();
        return list;
    }

    @Override
    public void insert(StoreVO storeVO) {

    }

    //以下是StoreFrontServlet會用到的方法
    @Override
    public StoreVO findByStoreId(Integer storeId) {
        StoreVO storeVO = getSession().get(StoreVO.class, storeId);
        return storeVO;
    }

    @Override
    public StoreVO findByStoreName(String storeName) {
        NativeQuery<StoreVO> query =
                getSession().createNativeQuery("select * from STORE where STORE_NAME = :storeName", StoreVO.class);
        query.setParameter("storeName", storeName);
        StoreVO storeVO = query.uniqueResult();
        return storeVO;
    }
    @Override
    public List<StoreVO> findByStoreCity(String storeCity) {
        NativeQuery<StoreVO> query = getSession().createNativeQuery("select * from Store where STORE_CITY = :storeCity", StoreVO.class);
        query.setParameter("storeCity", storeCity);
        List<StoreVO> stores = query.list();
        return stores;
    }

    @Override
    public List<String> getStoreName() {
        Query<String> query = getSession().createQuery("SELECT storeName FROM StoreVO", String.class);
        List<String> list = query.list();
        return list;
    }
    @Override
    public List<String> getStoreCity() {
        Query<String> query = getSession().createQuery("SELECT distinct storeCity FROM StoreVO", String.class);
        List<String> list = query.list();
        return list;
    }

    @Override
    public List<StoreVO> selectStoreAcc(String storeAcc) {
        StringBuilder sb = new StringBuilder();

        if (storeAcc.length() >= 2) {
            for (int i = 0; i < storeAcc.length(); i++) {
                if (i == 0) {
                    sb.append("'%").append(storeAcc.charAt(i)).append("%");
                } else if (i == storeAcc.length() - 1) {
                    sb.append(storeAcc.charAt(i)).append("%'");
                } else {
                    sb.append(storeAcc.charAt(i)).append("%");
                }
            }
        } else {
            sb.append("'%").append(storeAcc).append("%'");
        }
        String selectSQL = "select * from Store where STORE_ACC like" + sb;
        NativeQuery<StoreVO> nativeQuery =
                session.createNativeQuery(selectSQL, StoreVO.class);
        List<StoreVO> stores = nativeQuery.list();
        return stores;
    }

    @Override
    public StoreVO findByPrimaryKey(Integer storeId) {
        StoreVO storeVO = session.get(StoreVO.class, storeId);
        return storeVO;
    }

    @Override
    public StoreVO login(String storeAcc, String storePwd) {
        NativeQuery<StoreVO> query =
                getSession().createNativeQuery("select * from STORE where STORE_ACC = :storeAcc and STORE_PWD = :storePwd", StoreVO.class);
        query.setParameter("storeAcc", storeAcc).setParameter("storePwd", storePwd);
        StoreVO storeVO = query.uniqueResult();
        return storeVO;

    }

    @Override
    public Integer selectByStoreAcc(String storeAcc) {
        Query<Integer> query =
                getSession().createQuery("SELECT storeId FROM StoreVO where storeAcc = :storeAcc")
                        .setParameter("storeAcc", storeAcc);
        Integer storeId = query.uniqueResult();

        return storeId;
    }

    @Override
    public Integer insertWithReturn(StoreVO storeVO) {
        getSession().persist(storeVO);
        Integer storeId = (Integer) getSession().getIdentifier(storeVO);
        return storeId;
    }

    @Override
    public void updatePwd(StoreVO storeVO) {

        StoreVO oStoreVO = getSession().get(StoreVO.class, storeVO.getStoreId());
        oStoreVO.setStorePwd(storeVO.getStorePwd());
        getSession().merge(oStoreVO);

    }

    @Override
    public void updateAccStat(StoreVO storeVO) {

        StoreVO oStoreVO = getSession().get(StoreVO.class, storeVO.getStoreId());
        oStoreVO.setAccStat(storeVO.getAccStat());
        getSession().merge(oStoreVO);

    }

    @Override
    public String getOneStoreNameById(Integer storeId) {
        StoreVO storeVO = getSession().get(StoreVO.class, storeId);
        return storeVO.getStoreName();
    }


    @Override
    public void delete(Integer storeId) {
    }
}
