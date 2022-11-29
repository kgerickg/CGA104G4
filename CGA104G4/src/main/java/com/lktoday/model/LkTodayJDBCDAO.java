package com.lktoday.model;

import java.sql.*;
import java.util.List;

import com.lktoday.model.LkTodayVO;

public class LkTodayJDBCDAO implements LkTodayDAOinterface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/FOOD?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT = "INSERT INTO LK_TODAY(MEM_ID, LK_ID, LK_QTY) VALUES (?, ?, ?)";
	private static final String storeGET = "SELECT LK_TODAY_ID, MEM_ID, 2.LK_ID, LK_QTY, STORE_ID FROM LK_TODAY l join LUCKY s on 2.LK_ID = s.LK_ID order by LK_TODAY_ID where STORE_ID = ? and LK_TODAY_TIME = ?";
	private static final String memGET = "SELECT LK_TODAY_ID, MEM_ID, LK_ID, LK_QTY FROM LK_TODAY where MEM_ID = ? and LK_TODAY_TIME = ?";
	private static final String orderINSERT = "INSERT INTO LK_ORDER(SELECT LK_TODAY_ID, MEM_ID, 2.LK_ID, LK_PRC FROM LK_TODAY l join LUCKY s on 2.LK_ID = s.LK_ID order by LK_TODAY_ID where LK_TODAY_TIME = ?)";
	private static final String DELETE = "DELETE FROM LK_TODAY where LK_TODAY_ID = ?";

	@Override
	public void insert(LkTodayVO lkTodayVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setInt(1, lkTodayVO.getMemId());
			pstmt.setInt(2, lkTodayVO.getLuckyId());
			pstmt.setInt(3, lkTodayVO.getLkQty());		

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	
	@Override
	public void delete(Integer lktodayId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, lktodayId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}


	public static void main(String[] args) {

		LkTodayJDBCDAO dao = new LkTodayJDBCDAO();

		// 新增
		LkTodayVO lkTodayVO1 = new LkTodayVO();
		lkTodayVO1.setMemId(1);		
		lkTodayVO1.setLuckyId(1);
		lkTodayVO1.setLkQty(10);

		dao.insert(lkTodayVO1);
	}

	@Override
	public List<LkTodayVO> findByStore(Integer storeId, Date lkTodayTime) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<LkTodayVO> findByMem(Integer memId, Date lkTodayTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void orderinsert(LkTodayVO lkTodayVO) {
		// TODO Auto-generated method stub
		
	}

}