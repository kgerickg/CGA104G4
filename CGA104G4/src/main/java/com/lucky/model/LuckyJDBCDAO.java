package com.lucky.model;

import java.util.*;
import java.sql.*;

public class LuckyJDBCDAO implements LuckyDAOinterface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/FOOD?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = 
		"INSERT INTO LUCKY (STORE_ID,LK_STAT,LK_NAME,LK_CONT,LK_PRC,LK_TIME) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT LK_ID, l.STORE_ID, LK_STAT, LK_NAME, LK_CONT, LK_PRC, LK_TIME, STORE_NAME FROM LUCKY l join STORE s on l.STORE_ID = s.STORE_ID order by LK_ID";
	private static final String GET_ONE_STMT = 
		"SELECT LK_ID,STORE_ID,LK_STAT,LK_NAME,LK_CONT,LK_PRC,LK_TIME FROM LUCKY where LK_ID = ?";
	private static final String DELETE = 
		"DELETE FROM LUCKY where LK_ID = ?";
	private static final String UPDATE = 
		"UPDATE LUCKY set STORE_ID=?, LK_STAT=?, LK_NAME=?, LK_CONT=?, LK_PRC=?, LK_TIME=? where LK_ID = ?";

	@Override
	public void insert(LuckyVO luckyVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, luckyVO.getStoreId());
			pstmt.setInt(2, luckyVO.getLkStat());
			pstmt.setString(3, luckyVO.getLkName());
			pstmt.setString(4, luckyVO.getLkCont());
			pstmt.setInt(5, luckyVO.getLkPrc());
			pstmt.setDate(6, luckyVO.getLkTime());			

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
	public void update(LuckyVO luckyVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, luckyVO.getStoreId());
			pstmt.setInt(2, luckyVO.getLkStat());
			pstmt.setString(3, luckyVO.getLkName());
			pstmt.setString(4, luckyVO.getLkCont());
			pstmt.setInt(5, luckyVO.getLkPrc());
			pstmt.setDate(6, luckyVO.getLkTime());
			pstmt.setInt(7, luckyVO.getLuckyId());			

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
	public void delete(Integer luckyId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, luckyId);

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
	public LuckyVO findByPrimaryKey(Integer luckyId) {

		LuckyVO luckyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			System.out.println(url);
			
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, luckyId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				luckyVO = new LuckyVO();
				luckyVO.setLuckyId(rs.getInt("luckyId"));
				luckyVO.setStoreId(rs.getInt("storeId"));
				luckyVO.setLkStat(rs.getInt("lkStat"));
				luckyVO.setLkName(rs.getString("lkName"));
				luckyVO.setLkCont(rs.getString("lkCont"));
				luckyVO.setLkPrc(rs.getInt("lkPrc"));
				luckyVO.setLkTime(rs.getDate("lkTime"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return luckyVO;
	}

	@Override
	public List<LuckyVO> getAll() {
		List<LuckyVO> list = new ArrayList<LuckyVO>();
		LuckyVO luckyVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				luckyVO = new LuckyVO();
				luckyVO.setLuckyId(rs.getInt("LK_ID"));
				luckyVO.setStoreId(rs.getInt("STORE_ID"));
				luckyVO.setLkStat(rs.getInt("LK_STAT"));
				luckyVO.setLkName(rs.getString("LK_NAME"));
				luckyVO.setLkCont(rs.getString("LK_CONT"));
				luckyVO.setLkPrc(rs.getInt("LK_PRC"));
				luckyVO.setLkTime(rs.getDate("LK_TIME"));
				luckyVO.setStoreName(rs.getString("STORE_NAME"));
				list.add(luckyVO); // Store the row in the list
			}

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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return list;
	}

	public static void main(String[] args) {

		LuckyJDBCDAO dao = new LuckyJDBCDAO();

		// 新增
		LuckyVO luckyVO1 = new LuckyVO();
		luckyVO1.setStoreId(1);
		luckyVO1.setLkStat(1);
		luckyVO1.setLkName("麵包");
		luckyVO1.setLkCont("麵包10個");
		luckyVO1.setLkPrc(100);
		luckyVO1.setLkTime(java.sql.Date.valueOf("2002-01-01")); //
		dao.insert(luckyVO1);

		// 修改
		LuckyVO luckyVO2 = new LuckyVO();
		luckyVO2.setLuckyId(1);		
		luckyVO2.setStoreId(1);
		luckyVO2.setLkStat(1);
		luckyVO2.setLkName("便當");
		luckyVO2.setLkCont("便當2個");
		luckyVO2.setLkPrc(150);
		luckyVO2.setLkTime(java.sql.Date.valueOf("2008-01-01")); //
		dao.update(luckyVO2);

		// 刪除
		dao.delete(1);

		// 查詢
		LuckyVO luckyVO3 = dao.findByPrimaryKey(1);
		System.out.print(luckyVO3.getLuckyId() + ",");
		System.out.print(luckyVO3.getStoreId() + ",");
		System.out.print(luckyVO3.getLkStat() + ",");
		System.out.print(luckyVO3.getLkName() + ",");
		System.out.print(luckyVO3.getLkCont() + ",");
		System.out.print(luckyVO3.getLkPrc() + ",");
		System.out.println(luckyVO3.getLkTime());
		System.out.println("---------------------");

		// 查詢
		List<LuckyVO> list = dao.getAll();
		for (LuckyVO aLucky : list) {
			System.out.print(aLucky.getLuckyId() + ",");
			System.out.print(aLucky.getStoreId() + ",");
			System.out.print(aLucky.getLkStat() + ",");
			System.out.print(aLucky.getLkName() + ",");
			System.out.print(aLucky.getLkCont() + ",");
			System.out.print(aLucky.getLkPrc() + ",");
			System.out.print(aLucky.getLkTime());
			System.out.println();
		}
	}

	@Override
	public byte[] getImgById(Integer luckyId) {
		return null;
	}

	@Override
	public List<LuckyVO> findByStoreId(Integer storeId) {
		// TODO Auto-generated method stub
		return null;
	}

}