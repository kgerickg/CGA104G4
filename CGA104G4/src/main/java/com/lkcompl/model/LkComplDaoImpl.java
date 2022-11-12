package com.lkcompl.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LkComplDaoImpl implements LkComplDao {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/FOOD?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	

	@Override // 右鍵創造實作介面方法
	public Integer insert(LkComplVO lkComplVO) {
		final String SQL = "insert LK_COMPL(LK_CC_ID, LK_ORD_ID, LK_CC_STAT, LK_CC_CONT, LK_RFD_STAT) values(?, ?, ?, ?, ?)";
		try (Connection conn = DriverManager.getConnection(url, userid, passwd); // 打開Workbench連線
				PreparedStatement pstmt = conn.prepareStatement(SQL); // 把SQL有?的值填上
		) {
			pstmt.setInt(1, lkComplVO.getLkCcId());
			pstmt.setInt(2, lkComplVO.getLkOrdId());
			pstmt.setInt(3, lkComplVO.getLkCcStat());
			pstmt.setString(4, lkComplVO.getLkCcCont());
			pstmt.setInt(5, lkComplVO.getLkRfdStat());
			return pstmt.executeUpdate();				// 上面幾筆新增成功後會回傳1
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;										// 失敗及回傳-1
	}


	@Override
	public LkComplVO update(LkComplVO lkComplVO) {
		final String SQL = "update LK_COMPL " + "set LK_CC_STAT = ?, LK_RFD_STAT " + " where LK_CC_ID = ? ";
		try (Connection conn = DriverManager.getConnection(url, userid, passwd); // 打開Workbench連線
				PreparedStatement pstmt = conn.prepareStatement(SQL); // 把SQL有?的值填上
		) {
			pstmt.setInt(1, lkComplVO.getLkCcStat());
			pstmt.setInt(2, lkComplVO.getLkRfdStat());
			pstmt.setInt(3, lkComplVO.getLkCcId()); // 三個問號所以set三次，分別對應到39行SQL
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public LkComplVO selectByLkCcId(Integer lkCcId) {
		final String SQL = "select * from LK_COMPL where LK_CC_ID = ? ";
		try (Connection conn = DriverManager.getConnection(url, userid, passwd); 
				PreparedStatement pstmt = conn.prepareStatement(SQL);) {
			pstmt.setInt(1, lkCcId); // 只有一個?，所以只set一次
			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					LkComplVO lkCompl = new LkComplVO(); // new一個lkCompl容器來裝下面所取得的值
					lkCompl.setLkCcId(rs.getInt("LK_CC_ID"));
					lkCompl.setLkOrdId(rs.getInt("LK_ORD_ID"));
					lkCompl.setLkCcStat(rs.getInt("LK_CC_STAT"));
					lkCompl.setLkCcCont(rs.getString("LK_CC_CONT"));
					lkCompl.setLkRfdStat(rs.getInt("LK_RFD_STAT"));
					return lkCompl;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<LkComplVO> selectAll() {
		final String SQL = "select * from LK_COMPL ";
		try (Connection conn = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				ResultSet rs = pstmt.executeQuery()
		) {
			List<LkComplVO> list = new ArrayList<>();
				while (rs.next()) {
					LkComplVO lkCompl = new LkComplVO(); // new一個lkCompl容器來裝下面所取得的值
					lkCompl.setLkCcId(rs.getInt("LK_CC_ID"));
					lkCompl.setLkOrdId(rs.getInt("LK_ORD_ID"));
					lkCompl.setLkCcStat(rs.getInt("LK_CC_STAT"));
					lkCompl.setLkCcCont(rs.getString("LK_CC_CONT"));
					lkCompl.setLkRfdStat(rs.getInt("LK_RFD_STAT"));
					list.add(lkCompl);
				}
				return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {

		LkComplDaoImpl dao = new LkComplDaoImpl();

		// 新增
//		LkComplVO lkComplVO = new LkComplVO();
//		lkComplVO.setLkCcId(7);
//		lkComplVO.setLkOrdId(8);
//		lkComplVO.setLkCcStat(1);
//		lkComplVO.setLkCcCont("餐點太鹹");
//		lkComplVO.setLkRfdStat(0);
//		dao.insert(lkComplVO);

	
		
		// 修改
//		LkComplVO lkComplVO2 = new LkComplVO();
//		lkComplVO2.setLkCcStat(1);
//		lkComplVO2.setLkRfdStat(1);
//		lkComplVO2.setLkCcId(7);
//		dao.update(lkComplVO2);

		
		
		// 查詢
//		LkComplVO lkComplVO3 = dao.selectByLkCcId(5);
//		System.out.print(lkComplVO3.getLkCcId() + ",");
//		System.out.print(lkComplVO3.getLkOrdId() + ",");
//		System.out.print(lkComplVO3.getLkCcStat() + ",");
//		System.out.print(lkComplVO3.getLkCcCont() + ",");
//		System.out.print(lkComplVO3.getLkRfdStat());
//		System.out.println("---------------------");

		
		
		// 查詢
//		List<LkComplVO> list = dao.selectAll();
//		for (LkComplVO lkComplVO : list) {
//		System.out.print(lkComplVO.getLkCcId() + ",");
//		System.out.print(lkComplVO.getLkOrdId() + ",");
//		System.out.print(lkComplVO.getLkCcStat() + ",");
//		System.out.print(lkComplVO.getLkCcCont() + ",");
//		System.out.print(lkComplVO.getLkRfdStat());
//		System.out.println("---------------------");
//		}
		
		
		
		
	}
	
}




