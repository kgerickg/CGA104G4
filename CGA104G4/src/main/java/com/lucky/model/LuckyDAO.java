package com.lucky.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class LuckyDAO implements LuckyDAOinterface {


	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/FOOD");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO LUCKY (STORE_ID,LK_STAT,LK_NAME,LK_CONT,LK_PRC,LK_TIME) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT LK_ID,STORE_ID,LK_STAT,LK_NAME,LK_CONT,LK_PRC,LK_TIME FROM LUCKY order by LK_ID";
	private static final String GET_ONE_STMT = "SELECT LK_ID,STORE_ID,LK_STAT,LK_NAME,LK_CONT,LK_PRC,LK_TIME FROM LUCKY where LK_ID = ?";
	private static final String DELETE = "DELETE FROM LUCKY where LK_ID = ?";
	private static final String UPDATE = "UPDATE LUCKY set STORE_ID=?, LK_STAT=?, LK_NAME=?, LK_CONT=?, LK_PRC=?, LK_TIME=? where LK_ID = ?";

	@Override
	public void insert(LuckyVO luckyVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, luckyVO.getStoreId());
			pstmt.setInt(2, luckyVO.getLkStat());
			pstmt.setString(3, luckyVO.getLkName());
			pstmt.setString(4, luckyVO.getLkCont());
			pstmt.setInt(5, luckyVO.getLkPrc());
			pstmt.setDate(6, luckyVO.getLkTime());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

			con = ds.getConnection();
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
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, luckyId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, luckyId);

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
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		try (
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT);
			ResultSet rs = pstmt.executeQuery();
		) {
			List<LuckyVO> list = new ArrayList<LuckyVO>();
			while (rs.next()) {
				LuckyVO luckyVO = new LuckyVO();
				luckyVO.setLuckyId(rs.getInt("LK_ID"));
				luckyVO.setStoreId(rs.getInt("STORE_ID"));
				luckyVO.setLkStat(rs.getInt("LK_STAT"));
				luckyVO.setLkName(rs.getString("LK_NAME"));
				luckyVO.setLkCont(rs.getString("LK_CONT"));
				luckyVO.setLkPrc(rs.getInt("LK_PRC"));
				luckyVO.setLkTime(rs.getDate("LK_TIME"));
				list.add(luckyVO);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public byte[] getImgById(Integer luckyId) {
		try (
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement("select LK_IMG from LUCKY where LK_ID = ?");
		) {
			pstmt.setInt(1, luckyId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getBytes(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}