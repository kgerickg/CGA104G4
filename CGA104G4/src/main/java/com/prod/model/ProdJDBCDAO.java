package com.prod.model;

import java.util.*;
import java.sql.*;

public class ProdJDBCDAO implements ProdDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/FOOD?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = 
		"insert into PROD (STORE_ID, PROD_TYPE_ID, PROD_STAT, PROD_NAME, PROD_CONT, PROD_PRC, PROD_TIME) values (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"select PROD_ID, STORE_ID, PROD_TYPE_ID, PROD_STAT, PROD_NAME, PROD_CONT, PROD_PRC, PROD_TIME from PROD order by PROD_ID";
	private static final String GET_ONE_STMT = 
		"select PROD_ID, STORE_ID, PROD_TYPE_ID, PROD_STAT, PROD_NAME, PROD_CONT, PROD_PRC, PROD_TIME from PROD where PROD_ID = ?";
	private static final String DELETE = 
		"delete from PROD where PROD_ID = ?";
	private static final String UPDATE = 
		"update PROD set STORE_ID = ?, PROD_TYPE_ID = ?, PROD_STAT = ?, PROD_NAME = ?, PROD_CONT = ?, PROD_PRC = ?, PROD_TIME = ? from PROD where PROD_ID = ?";

	@Override
	public void insert(ProdVO ProdVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, ProdVO.getProdTypeId());
			pstmt.setInt(2, ProdVO.getStoreId());
			pstmt.setString(3, ProdVO.getProdName());
			pstmt.setString(4, ProdVO.getProdCont());
			pstmt.setInt(5, ProdVO.getProdStat());
			pstmt.setDate(6, ProdVO.getProdTime());

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
	public void update(ProdVO ProdVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, ProdVO.getProdTypeId());
			pstmt.setInt(2, ProdVO.getStoreId());
			pstmt.setString(3, ProdVO.getProdName());
			pstmt.setString(4, ProdVO.getProdCont());
			pstmt.setInt(5, ProdVO.getProdStat());
			pstmt.setDate(6, ProdVO.getProdTime());
			pstmt.setInt(7, ProdVO.getProdId());

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
	public void delete(Integer prodId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, prodId);

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
	public ProdVO findByPrimaryKey(Integer prodId) {

		ProdVO ProdVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, prodId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// ProdVO 也稱為 Domain objects
				ProdVO = new ProdVO();
				ProdVO.setProdId(rs.getInt("PROD_ID"));
				ProdVO.setProdTypeId(rs.getInt("PROD_TYPE_ID"));
				ProdVO.setStoreId(rs.getInt("STORE_ID"));
				ProdVO.setProdName(rs.getString("PROD_NAME"));
				ProdVO.setProdCont(rs.getString("PROD_CONT"));
				ProdVO.setProdStat(rs.getInt("PROD_STAT"));
				ProdVO.setProdTime(rs.getDate("PROD_TIME"));
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
		return ProdVO;
	}

	@Override
	public List<ProdVO> getAll() {
		List<ProdVO> list = new ArrayList<ProdVO>();
		ProdVO ProdVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// ProdVO 也稱為 Domain objects
				ProdVO = new ProdVO();
				ProdVO.setProdId(rs.getInt("PROD_ID"));
				ProdVO.setProdTypeId(rs.getInt("PROD_TYPE_ID"));
				ProdVO.setStoreId(rs.getInt("STORE_ID"));
				ProdVO.setProdName(rs.getString("PROD_NAME"));
				ProdVO.setProdCont(rs.getString("PROD_CONT"));
				ProdVO.setProdStat(rs.getInt("PROD_STAT"));
				ProdVO.setProdTime(rs.getDate("PROD_TIME"));
				list.add(ProdVO); // Store the row in the list
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
}