package com.lktoday.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lkorder.model.LkOrderVO;

public class LkTodayDAO implements LkTodayDAOinterface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/FOOD");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT = "INSERT INTO LK_TODAY(MEM_ID, LK_ID, LK_QTY) VALUES (?, ?, ?)";
//	private static final String storeGET = "SELECT l.LK_ID, s.LK_NAME, l.LK_TODAY_ID, l.MEM_ID, s.STORE_ID, s.LK_PRC "
//			+ "FROM LK_TODAY l join LUCKY s on l.LK_ID = s.LK_ID "
//			+ "where s.STORE_ID = 3 and DATE(LK_TODAY_TIME) = ? "
//			+ "order by LK_TODAY_ID";
	private static final String storeGET = "SELECT l.LK_ID, s.LK_NAME, l.LK_TODAY_ID, l.MEM_ID, s.STORE_ID, s.LK_PRC "
			+ "FROM LK_TODAY l join LUCKY s on l.LK_ID = s.LK_ID "
			+ "where s.STORE_ID = ? "
			+ "order by LK_TODAY_ID";
	
	private static final String memGET = "SELECT LK_TODAY_ID, MEM_ID, LK_ID, LK_QTY FROM LK_TODAY where MEM_ID = ? and LK_TODAY_TIME = ?";
	private static final String DELETE = "DELETE FROM LK_TODAY where LK_TODAY_ID = ?";
	private static final String orderINSERT = "INSERT INTO LK_ORDER(LK_ID, MEM_ID, LK_TODAY_ID, LK_ORD_AMT, LK_ORD_TIME_S) "
			+ "SELECT LK_TODAY_ID, MEM_ID, l.LK_ID, LK_PRC, NOW() "
			+ "FROM LK_TODAY l join LUCKY s on l.LK_ID = s.LK_ID "
			+ "where LK_TODAY_TIME = ? and STORE_ID = ?"
			+ "order by LK_TODAY_ID";
	private static final String orderINSERT2 = "INSERT INTO LK_ORDER(LK_ID, MEM_ID, LK_TODAY_ID, LK_ORD_AMT, LK_ORD_TIME_S)"
			+ "VALUES(?,?,?,?,?)";
	
	@Override
	public void orderinsert(LkOrderVO lkOrderVO,Integer storeId) {
		try (
				Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(orderINSERT2)
				) {
			pstmt.setInt(1, lkOrderVO.getLkId());
			pstmt.setInt(2, lkOrderVO.getMemId());
			pstmt.setInt(3, lkOrderVO.getLkTodayId());
			pstmt.setInt(4, lkOrderVO.getLkOrdAmt());
			pstmt.setDate(5, lkOrderVO.getLkOrdTimeS());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void insert(LkTodayVO lkTodayVO) {

		try (
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(INSERT)
		) {
			
			pstmt.setInt(1, lkTodayVO.getMemId());
			pstmt.setInt(2, lkTodayVO.getLuckyId());
			pstmt.setInt(3, lkTodayVO.getLkQty());	
			
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void delete(Integer lktodayId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, lktodayId);

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
	public List<TodayLuckyVO> findByStore(Integer storeId, Date lkTodayTime) {
		try (
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(storeGET);
		) {
			pstmt.setInt(1, storeId);
//			pstmt.setString(2, lkTodayTime);
			try (ResultSet rs = pstmt.executeQuery()) {
				List<TodayLuckyVO> list = new ArrayList<>();
				while (rs.next()) {
					TodayLuckyVO vo = new TodayLuckyVO();
					vo.setLkId(rs.getInt("LK_ID"));
					vo.setLkName(rs.getString("LK_NAME"));
					vo.setLkTodayId(rs.getInt("LK_TODAY_ID"));
					vo.setMemId(rs.getInt("MEM_ID"));
					vo.setStoreId(rs.getInt("STORE_ID"));
					vo.setLkPrc(rs.getInt("LK_PRC"));
					list.add(vo);
				}
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<LkTodayVO> findByMem(Integer memId, Date lkTodayTime) {
		try (
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(memGET);
		) {
			pstmt.setInt(1, memId);
			pstmt.setDate(2, lkTodayTime);
			try (ResultSet rs = pstmt.executeQuery()) {
				List<LkTodayVO> list = new ArrayList<>();
				while (rs.next()) {
					LkTodayVO lktodayVO = new LkTodayVO();
					lktodayVO.setLkTodayId(rs.getInt("LK_TODAY_ID"));
					lktodayVO.setMemId(rs.getInt("MEM_ID"));
					lktodayVO.setLuckyId(rs.getInt("LK_ID"));
					lktodayVO.setLkTodayTime(rs.getDate("LK_TODAY_TIME"));
					lktodayVO.setLkQty(rs.getInt("LK_QTY"));
					lktodayVO.setStoreId(rs.getInt("STORE_ID"));
					list.add(lktodayVO);
				}
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public void orderinsert(LkOrderVO lkOrderVO) {
		// TODO Auto-generated method stub
		
	}	
	
}