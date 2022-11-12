package com.lkorder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LkOrderJDBCDAO implements LkOrderinterface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/FOOD?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	@Override
	public void insert(LkOrderVO lkOrderVO) {
		String sql = "INSERT INTO LK_ORDER(LK_ID, MEM_ID, LK_TODAY_ID, LK_ORD_AMT, LK_ORD_STAT, LK_ORD_TIME_S, LK_ORD_TAKETIME, LK_ORD_TIME_E) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		try (
			Connection conn = DriverManager.getConnection(url, userid, passwd);
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setInt(1, lkOrderVO.getLkId());
			pstmt.setInt(2, lkOrderVO.getMemId());
			pstmt.setInt(3, lkOrderVO.getLkTodayId());
			pstmt.setInt(4, lkOrderVO.getLkOrdAmt());
			pstmt.setInt(5, lkOrderVO.getLkOrdStat());
			pstmt.setDate(6, lkOrderVO.getLkOrdTimeS());
			pstmt.setDate(7, lkOrderVO.getLkOrdTaketime());
			pstmt.setDate(8, lkOrderVO.getLkOrdTimeE());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void update(LkOrderVO lkOrderVO) {
		String sql = "update LK_ORDER set LK_ID = ?, LK_ORD_AMT = ?, LK_ORD_STAT = ?, LK_ORD_TAKETIME = ?, LK_ORD_TIME_E = ? where LK_ORD_ID = ?";
		try (
			Connection conn = DriverManager.getConnection(url, userid, passwd);
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setInt(1, lkOrderVO.getLkId());
			pstmt.setInt(2, lkOrderVO.getLkOrdAmt());
			pstmt.setInt(3, lkOrderVO.getLkOrdStat());
			pstmt.setDate(4, lkOrderVO.getLkOrdTaketime());
			pstmt.setDate(5, lkOrderVO.getLkOrdTimeE());
			pstmt.setInt(6, lkOrderVO.getLkOrderId());	// 修改要抓取PK當條件
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void delete(Integer lkordId) {
		String sql = "delete from LK_ORDER where LK_ORD_ID = ?";
		try (
			Connection conn = DriverManager.getConnection(url, userid, passwd);
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setInt(1, lkordId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override				// 以編號查詢，查詢單筆
	public LkOrderSelectVO findByPrimaryKey(Integer lkordId) {
		String sql = "select LK_ORD_ID, LKO.LK_ID, LK_NAME, MEM.MEM_ID, MEM_NAME, LK_TODAY_ID , LK_ORD_AMT, LK_ORD_STAT, LK_ORD_TIME_S, LK_ORD_TAKETIME, LK_ORD_TIME_E "
				+"from LUCKY LK "
					+"join LK_ORDER LKO "
						+"on LKO.LK_ID = LK.LK_ID "
					+"join MEMBER MEM "
						+"on LKO.MEM_ID = MEM.MEM_ID "
				+ "where LK_ORD_ID = ?";
		try (
			Connection conn = DriverManager.getConnection(url, userid, passwd);
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setInt(1, lkordId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					LkOrderSelectVO vo = new LkOrderSelectVO();			
					vo.setLkOrderId(rs.getInt(1));	// 後面的數字對應到SQL的欄位 (可以下SQL的欄位名，也可以下數字)
					vo.setLkId(rs.getInt(2));
					vo.setLkName(rs.getString(3));
					vo.setMemId(rs.getInt(4));
					vo.setMemName(rs.getString(5));
					vo.setLkTodayId(rs.getInt(6));
					vo.setLkOrdAmt(rs.getInt(7));
					vo.setLkOrdStat(rs.getInt(8));
					vo.setLkOrdTimeS(rs.getDate(9));
					vo.setLkOrdTaketime(rs.getDate(10));
					vo.setLkOrdTimeE(rs.getDate(11));
					return vo;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override								// 查詢以會員當PK
	public List<LkOrderSelectVO> selectAll(Integer memId) {
		String sql = "select LK_ORD_ID, LK_NAME, MEM_NAME, LK_TODAY_ID , LK_ORD_AMT, LK_ORD_STAT, LK_ORD_TIME_S, LK_ORD_TAKETIME, LK_ORD_TIME_E "
						+"from LUCKY LK "
							+"join LK_ORDER LKO "
								+"on LKO.LK_ID = LK.LK_ID "
							+"join MEMBER MEM "
								+"on LKO.MEM_ID = MEM.MEM_ID "
					+ "where MEM.MEM_ID = ?";
		try (
			Connection conn = DriverManager.getConnection(url, userid, passwd);
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setInt(1, memId);
			try (ResultSet rs = pstmt.executeQuery()) {
				List<LkOrderSelectVO> list = new ArrayList<>();		
				while (rs.next()) {								
					LkOrderSelectVO vo = new LkOrderSelectVO();			
					vo.setLkOrderId(rs.getInt(1));	// 後面的數字對應到SQL的欄位 (可以下SQL的欄位名，也可以下數字)
					vo.setLkName(rs.getString(2));
					vo.setMemName(rs.getString(3));
					vo.setLkTodayId(rs.getInt(4));
					vo.setLkOrdAmt(rs.getInt(5));
					vo.setLkOrdStat(rs.getInt(6));
					vo.setLkOrdTimeS(rs.getDate(7));
					vo.setLkOrdTaketime(rs.getDate(8));
					vo.setLkOrdTimeE(rs.getDate(9));
					list.add(vo);
				}
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<LkOrderVO> getAll(){
		return null;
	}
	
	public List<LkOrderSelectVO> findAll(){
		String sql = "select LK_ORD_ID, LKO.LK_ID, LK_NAME, MEM.MEM_ID, MEM_NAME, LK_TODAY_ID , LK_ORD_AMT, LK_ORD_STAT, LK_ORD_TIME_S, LK_ORD_TAKETIME, LK_ORD_TIME_E "
				+"from LUCKY LK "
				+"join LK_ORDER LKO "
					+"on LKO.LK_ID = LK.LK_ID "
				+"join MEMBER MEM "
					+"on LKO.MEM_ID = MEM.MEM_ID ";
		try (
			Connection conn = DriverManager.getConnection(url, userid, passwd);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()
		) {
			List<LkOrderSelectVO> list = new ArrayList<>();		
			while (rs.next()) {								
				LkOrderSelectVO vo = new LkOrderSelectVO();			
				vo.setLkOrderId(rs.getInt(1));	// 後面的數字對應到SQL的欄位 (可以下SQL的欄位名，也可以下數字)
				vo.setLkId(rs.getInt(2));
				vo.setLkName(rs.getString(3));
				vo.setMemId(rs.getInt(4));
				vo.setMemName(rs.getString(5));
				vo.setLkTodayId(rs.getInt(6));
				vo.setLkOrdAmt(rs.getInt(7));
				vo.setLkOrdStat(rs.getInt(8));
				vo.setLkOrdTimeS(rs.getDate(9));
				vo.setLkOrdTaketime(rs.getDate(10));
				vo.setLkOrdTimeE(rs.getDate(11));
				list.add(vo);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
//	@Override		【備份原本】
//	public List<LkOrderVO> getAll() {
//		String sql = "select * from LK_ORDER";
//		try (
//				Connection conn = DriverManager.getConnection(url, userid, passwd);
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				ResultSet rs = pstmt.executeQuery()
//				) {
//			List<LkOrderVO> list = new ArrayList<>();		
//			while (rs.next()) {								
//				LkOrderVO vo = new LkOrderVO();			
//				vo.setLkOrderId(rs.getInt(1));	// 後面的數字對應到SQL的欄位 (可以下SQL的欄位名，也可以下數字)
//				vo.setLkId(rs.getInt(2));
//				vo.setMemId(rs.getInt(3));
//				vo.setLkTodayId(rs.getInt(4));
//				vo.setLkOrdAmt(rs.getInt(5));
//				vo.setLkOrdStat(rs.getInt(6));
//				vo.setLkOrdTimeS(rs.getDate(7));
//				vo.setLkOrdTaketime(rs.getDate(8));
//				vo.setLkOrdTimeE(rs.getDate(9));
//				list.add(vo);
//			}
//			return list;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	
	@Override
	public LkOrderVO findByMemId(Integer memId) {
		String sql = "select * from LK_ORDER group by MEM_ID = ?";
		try (
			Connection conn = DriverManager.getConnection(url, userid, passwd);
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setInt(1, memId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					LkOrderVO vo = new LkOrderVO();
					vo.setLkOrderId(rs.getInt("LK_ORD_ID"));
					vo.setLkId(rs.getInt("LK_ID"));
					vo.setMemId(rs.getInt("MEM_ID"));
					vo.setLkTodayId(rs.getInt("LK_TODAY_ID"));
					vo.setLkOrdAmt(rs.getInt("LK_ORD_AMT"));
					vo.setLkOrdStat(rs.getInt("LK_ORD_STAT"));
					vo.setLkOrdTimeS(rs.getDate("LK_ORD_TIME_S"));
					vo.setLkOrdTaketime(rs.getDate("LK_ORD_TAKETIME"));
					vo.setLkOrdTimeE(rs.getDate("LK_ORD_TIME_E"));
					return vo;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	
	
	
	
	
	
	
	
	
	
//	public static void main(String[] args) {
//
//		LkOrderJDBCDAO dao = new LkOrderJDBCDAO();

		
		
		// 新增
//		LkOrderVO LkOrderVO1 = new LkOrderVO();
//		LkOrderVO1.setLkOrderId(1);
//		LkOrderVO1.setLkId(1);
//		LkOrderVO1.setMemId(1);
//		LkOrderVO1.setLkTodayId(8);
//		LkOrderVO1.setLkOrdAmt(1);
//		LkOrderVO1.setLkOrdStat(1);
//		LkOrderVO1.setLkOrdTimeS(java.sql.Date.valueOf("2005-01-01"));
//		LkOrderVO1.setLkOrdTaketime(java.sql.Date.valueOf("2005-01-01"));
//		LkOrderVO1.setLkOrdTimeE(java.sql.Date.valueOf("2005-01-01"));
//		dao.insert(LkOrderVO1);

		// 修改
//		LkOrderVO LkOrderVO2 = new LkOrderVO();
//		LkOrderVO2.setLkOrderId(6);
//		LkOrderVO2.setLkId(1);
//		LkOrderVO2.setMemId(1);
//		LkOrderVO2.setLkTodayId(8);
//		LkOrderVO2.setLkOrdAmt(1);
//		LkOrderVO2.setLkOrdStat(1);
//		LkOrderVO2.setLkOrdTimeS(java.sql.Date.valueOf("2002-01-01"));
//		LkOrderVO2.setLkOrdTaketime(java.sql.Date.valueOf("2022-01-01"));
//		LkOrderVO2.setLkOrdTimeE(java.sql.Date.valueOf("2002-01-01"));
//		dao.update(LkOrderVO2);

		// 刪除
//		dao.delete(15);
//
//		// 查詢
//		LkOrderVO LkOrderVO3 = dao.findByPrimaryKey(8);
//		System.out.print(LkOrderVO3.getLkOrderId() + ",");
//		System.out.print(LkOrderVO3.getLkId() + ",");
//		System.out.print(LkOrderVO3.getMemId() + ",");
//		System.out.print(LkOrderVO3.getLkTodayId() + ",");
//		System.out.print(LkOrderVO3.getLkOrdAmt() + ",");
//		System.out.print(LkOrderVO3.getLkOrdStat() + ",");
//		System.out.println(LkOrderVO3.getLkOrdTimeS());
//		System.out.println(LkOrderVO3.getLkOrdTaketime());
//		System.out.println(LkOrderVO3.getLkOrdTimeE());
//		System.out.println("---------------------");
//
//		// 查詢
//		List<LkOrderVO> list = dao.getAll();
//		for (LkOrderVO LkOrderVO : list) {
//			System.out.print(LkOrderVO.getLkOrderId() + ",");
//			System.out.print(LkOrderVO.getLkId() + ",");
//			System.out.print(LkOrderVO.getMemId() + ",");
//			System.out.print(LkOrderVO.getLkTodayId() + ",");
//			System.out.print(LkOrderVO.getLkOrdAmt() + ",");
//			System.out.print(LkOrderVO.getLkOrdStat() + ",");
//			System.out.print(LkOrderVO.getLkOrdTimeS());
//			System.out.print(LkOrderVO.getLkOrdTaketime());
//			System.out.print(LkOrderVO.getLkOrdTimeE());
//			System.out.println();
//		}
//	}
	
}