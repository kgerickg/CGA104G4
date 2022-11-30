package com.store.model;

import com.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreJDBCDAO implements StoreDAO_interface {
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/food?serverTimezone=Asia/Taipei";
    String userid = "root";
    String passwd = "password";

    private static final String INSERT_SQL = "insert into STORE(STORE_ACC, STORE_PWD, ACC_STAT, STORE_NAME, STORE_TEL, STORE_CITY, STORE_DIST, STORE_ADR, STORE_PIC)values(?,?,?,?,?,?,?,?,?)";

    private static final String GETSTOREID_SQL = "select STORE_ID from STORE";

    private static final String FINDBYPK_SQL = "select * from STORE where STORE_ID = ?";

    private static final String GETALL_SQL = "select * from STORE";

    private static final String DELETE_SQL = "delete from STORE where STORE_ID = ?";

    private static final String UPDATE_SQL = "update STORE set STORE_ACC=?,STORE_PWD=?,ACC_STAT=?,STORE_NAME=?,STORE_TEL=?,STORE_CITY=?,STORE_DIST=?,STORE_ADR=? where STORE_ID =?";

    @Override
    public void insert(StoreVO storeVO) {
        try (Connection conn = JDBCUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {

            ps.setString(1, storeVO.getStoreAcc());
            ps.setString(2, storeVO.getStorePwd());
            ps.setInt(3, storeVO.getAccStat());
            ps.setString(4, storeVO.getStoreName());
            ps.setString(5, storeVO.getStoreTel());
            ps.setString(6, storeVO.getStoreCity());
            ps.setString(7, storeVO.getStoreDist());
            ps.setString(8, storeVO.getStoreAdr());
            ps.setBytes(9, storeVO.getStorePic());

            ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    @Override
    public void update(StoreVO storeVO) {
        try (Connection conn = JDBCUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(UPDATE_SQL);) {

            ps.setString(1, storeVO.getStoreAcc());
            ps.setString(2, storeVO.getStorePwd());
            ps.setInt(3, storeVO.getAccStat());
            ps.setString(4, storeVO.getStoreName());
            ps.setString(5, storeVO.getStoreTel());
            ps.setString(6, storeVO.getStoreCity());
            ps.setString(7, storeVO.getStoreDist());
            ps.setString(8, storeVO.getStoreAdr());
            ps.setInt(10, storeVO.getStoreId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer storeId) {
        try (Connection conn = JDBCUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(DELETE_SQL);) {
            ps.setInt(1, storeId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public StoreVO findByPrimaryKey(Integer storeId) {
        StoreVO storeVO = new StoreVO();
        try (Connection conn = JDBCUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(FINDBYPK_SQL);) {
            ps.setInt(1, storeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                storeVO.setStoreId(rs.getInt(1));
                storeVO.setStoreAcc(rs.getString(2));
                storeVO.setStorePwd(rs.getString(3));
                storeVO.setAccStat(rs.getInt(4));
                storeVO.setStoreName(rs.getString(5));
                storeVO.setStoreTel(rs.getString(6));
                storeVO.setStoreCity(rs.getString(7));
                storeVO.setStoreDist(rs.getString(8));
                storeVO.setStoreAdr(rs.getString(9));
                storeVO.setStoreRegTime(rs.getDate(10));
                storeVO.setStorePic(rs.getBytes(11));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return storeVO;
    }

    @Override
    public List<StoreVO> getAll() {
        List<StoreVO> stores = new ArrayList<StoreVO>();
        try (Connection conn = JDBCUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(GETALL_SQL);) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StoreVO storeVO = new StoreVO();
                storeVO.setStoreId(rs.getInt(1));
                storeVO.setStoreAcc(rs.getString(2));
                storeVO.setStorePwd(rs.getString(3));
                storeVO.setAccStat(rs.getInt(4));
                storeVO.setStoreName(rs.getString(5));
                storeVO.setStoreTel(rs.getString(6));
                storeVO.setStoreCity(rs.getString(7));
                storeVO.setStoreDist(rs.getString(8));
                storeVO.setStoreAdr(rs.getString(9));
                storeVO.setStoreRegTime(rs.getDate(10));
                storeVO.setStorePic(rs.getBytes(11));
                stores.add(storeVO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stores;
    }

    @Override
    public List<Integer> getStoreId() {
        return null;
    }

    @Override
    public StoreVO findByStoreId(Integer storeId) {
        return null;
    }

    @Override
    public StoreVO findByStoreName(String storeName) {
        return null;
    }

    @Override
    public List<StoreVO> findByStoreCity(String storeCity) {
        return null;
    }

    @Override
    public List<String> getStoreName() {
        return null;
    }

    @Override
    public List<String> getStoreCity() {
        return null;
    }

    @Override
    public List<StoreVO> selectStoreAcc(String storeAcc) {
        return null;
    }

    @Override
    public void updatePwd(StoreVO storeVO) {

    }

    @Override
    public Integer selectByStoreAcc(String storeAcc) {
        return null;
    }

    @Override
    public Integer insertWithReturn(StoreVO storeVO) {
        return null;
    }

    @Override
    public StoreVO login(String storeAcc, String storePwd) {
        return null;
    }

    @Override
    public void updateAccStat(StoreVO storeVO) {

    }

    @Override
    public String getOneStoreNameById(Integer storeId) {
        return null;
    }
}
