package com.store.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StoreJDBCDAO implements StoreDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/food?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "insert into `STORE`"
			+ "	(`STORE_ACC`,`STORE_PWD`,`STORE_NAME`,`STORE_GUI`,`STORE_REP`,`STORE_TEL`,`STORE_FAX`,`STORE_AD`,`STORE_CON`,`STORE_CON_TEL`,`STORE_CON_AD`,"
			+ "	`STORE_EMAIL`)" + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "select `STORE_ID`,`STORE_ACC`,`STORE_PWD`,`ACC_STAT`,`STORE_NAME`,`STORE_GUI`,`STORE_REP`,`STORE_TEL`,`STORE_FAX`,`STORE_AD`,`STORE_CON`,`STORE_CON_TEL`,`STORE_CON_AD`,`STORE_EMAIL`"
			+ "from `STORE` order by `STORE_ID`";
	private static final String GET_ONE_STMT = "select `STORE_ID`,`STORE_ACC`,`STORE_PWD`,`ACC_STAT`,`STORE_NAME`,`STORE_GUI`,`STORE_REP`,`STORE_TEL`,`STORE_FAX`,`STORE_AD`,`STORE_CON`,`STORE_CON_TEL`,`STORE_CON_AD`,`STORE_EMAIL`"
			+ "from `STORE` where `STORE_ID` = ?";
	private static final String DELETE = "delete from `STORE` where `STORE_ID` = ?";
	private static final String UPDATE = "update `STORE` set `STORE_ACC`=?, `STORE_PWD`=?, `STORE_NAME`=?, `STORE_GUI`=? ,`STORE_REP`=? ,`STORE_TEL`=? ,`STORE_FAX`=?, `STORE_AD`=?, `STORE_CON`=?, `STORE_CON_TEL`=?, `STORE_CON_AD`=?,"
			+ " `STORE_EMAIL`=? where `STORE_ID` = ? ";

	@Override
	public void insert(StoreVO storeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, storeVO.getStoreAcc());
			pstmt.setString(2, storeVO.getStorePwd());
			pstmt.setString(3, storeVO.getStoreName());
			pstmt.setString(4, storeVO.getStoreGui());
			pstmt.setString(5, storeVO.getStoreRep());
			pstmt.setString(6, storeVO.getStoreTel());
			pstmt.setString(7, storeVO.getStoreFax());
			pstmt.setString(8, storeVO.getStoreAd());
			pstmt.setString(9, storeVO.getStoreCon());
			pstmt.setString(10, storeVO.getStoreConTel());
			pstmt.setString(11, storeVO.getStoreConAd());
			pstmt.setString(12, storeVO.getStoreEmail());
			Statement stmt=	con.createStatement();
			stmt.executeUpdate("set auto_increment_offset=1;");
			stmt.executeUpdate("alter table `STORE` auto_increment = 1;");
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void update(StoreVO storeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, storeVO.getStoreAcc());
			pstmt.setString(2, storeVO.getStorePwd());
			pstmt.setString(3, storeVO.getStoreName());
			pstmt.setString(4, storeVO.getStoreGui());
			pstmt.setString(5, storeVO.getStoreRep());
			pstmt.setString(6, storeVO.getStoreTel());
			pstmt.setString(7, storeVO.getStoreFax());
			pstmt.setString(8, storeVO.getStoreAd());
			pstmt.setString(9, storeVO.getStoreCon());
			pstmt.setString(10, storeVO.getStoreConTel());
			pstmt.setString(11, storeVO.getStoreConAd());
			pstmt.setString(12, storeVO.getStoreEmail());
			pstmt.setInt(13, storeVO.getStoreId());
			Statement stmt=	con.createStatement();
			stmt.executeUpdate("set auto_increment_offset=1;");
			stmt.executeUpdate("alter table `STORE` auto_increment = 1;");
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void delete(Integer storeId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, storeId);
			Statement stmt=	con.createStatement();
			stmt.executeUpdate("alter table `STORE` auto_increment = 1;");
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public StoreVO findByPrimaryKey(Integer storeId) {

		StoreVO storeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, storeId);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				storeVO = new StoreVO();
				storeVO.setStoreId(rs.getInt("STORE_ID"));
				storeVO.setStoreAcc(rs.getString("STORE_ACC"));
				storeVO.setStorePwd(rs.getString("STORE_PWD"));
				storeVO.setAccStat(rs.getInt("ACC_STAT"));
				storeVO.setStoreName(rs.getString("STORE_NAME"));
				storeVO.setStoreGui(rs.getString("STORE_GUI"));
				storeVO.setStoreRep(rs.getString("STORE_REP"));
				storeVO.setStoreTel(rs.getString("STORE_TEL"));
				storeVO.setStoreFax(rs.getString("STORE_FAX"));
				storeVO.setStoreAd(rs.getString("STORE_AD"));
				storeVO.setStoreCon(rs.getString("STORE_CON"));
				storeVO.setStoreConTel(rs.getString("STORE_CON_TEL"));
				storeVO.setStoreConAd(rs.getString("STORE_CON_AD"));
				storeVO.setStoreEmail(rs.getString("STORE_EMAIL"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
		return storeVO;
	}

	@Override
	public List<StoreVO> getAll() {
		List<StoreVO> list = new ArrayList<StoreVO>();
		StoreVO storeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				storeVO = new StoreVO();
				storeVO.setStoreId(rs.getInt("STORE_ID"));
				storeVO.setStoreAcc(rs.getString("STORE_ACC"));
				storeVO.setStorePwd(rs.getString("STORE_PWD"));
				storeVO.setAccStat(rs.getInt("ACC_STAT"));
				storeVO.setStoreName(rs.getString("STORE_NAME"));
				storeVO.setStoreGui(rs.getString("STORE_GUI"));
				storeVO.setStoreRep(rs.getString("STORE_REP"));
				storeVO.setStoreTel(rs.getString("STORE_TEL"));
				storeVO.setStoreFax(rs.getString("STORE_FAX"));
				storeVO.setStoreAd(rs.getString("STORE_AD"));
				storeVO.setStoreCon(rs.getString("STORE_CON"));
				storeVO.setStoreConTel(rs.getString("STORE_CON_TEL"));
				storeVO.setStoreConAd(rs.getString("STORE_CON_AD"));
				storeVO.setStoreEmail(rs.getString("STORE_EMAIL"));
				list.add(storeVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
		return list;
	}

	public static void main(String[] args) {

		StoreJDBCDAO dao = new StoreJDBCDAO();

		//新增
		StoreVO storeVO1 = new StoreVO();
		storeVO1.setStoreAcc("kgerickg2");
		storeVO1.setStorePwd("password");
		storeVO1.setStoreName("eric");
		storeVO1.setStoreGui("87897463");
		storeVO1.setStoreRep("王曉明");
		storeVO1.setStoreTel("037289934");
		storeVO1.setStoreFax("037289956");
		storeVO1.setStoreAd("桃園市中壢區中山東路二段37號");
		storeVO1.setStoreCon("王大明");
		storeVO1.setStoreConTel("0372896543");
		storeVO1.setStoreConAd("桃園市中壢區中山東路二段35號");
		storeVO1.setStoreEmail("a1234567@gmail.com");
		dao.insert(storeVO1);

	// 修改
//		StoreVO storeVO2 = new StoreVO();
//		storeVO2.setStoreId(10);
//		storeVO2.setStoreAcc("kgerickg3");
//		storeVO2.setStorePwd("password2");
//		storeVO2.setStoreName("eric2");
//		storeVO2.setStoreGui("87897463");
//		storeVO2.setStoreRep("王曉明");
//		storeVO2.setStoreTel("037289934");
//		storeVO2.setStoreFax("037289956");
//		storeVO2.setStoreAd("桃園市中壢區中山東路二段37號");
//		storeVO2.setStoreCon("王大明");
//		storeVO2.setStoreConTel("0372896543");
//		storeVO2.setStoreConAd("桃園市中壢區中山東路二段35號");
//		storeVO2.setStoreEmail("a1234567@gmail.com");
//		dao.update(storeVO2);
//
	// 刪除
//		dao.delete(11);
//
//		//查詢
//		StoreVO storeVO3 = dao.findByPrimaryKey(1);
//		System.out.print(storeVO3.getStoreAcc() + ",");
//		System.out.print(storeVO3.getStorePwd() + ",");
//		System.out.print(storeVO3.getAccStat() + ",");
//		System.out.print(storeVO3.getStoreName() + ",");
//		System.out.print(storeVO3.getStoreGui() + ",");
//		System.out.print(storeVO3.getStoreRep() + ",");
//		System.out.print(storeVO3.getStoreTel() + ",");
//		System.out.print(storeVO3.getStoreFax() + ",");
//		System.out.print(storeVO3.getStoreAd() + ",");
//		System.out.print(storeVO3.getStoreCon() + ",");
//		System.out.print(storeVO3.getStoreConTel() + ",");
//		System.out.print(storeVO3.getStoreConAd());
//		System.out.print(storeVO3.getStoreTel() + ",");
//		System.out.println(storeVO3.getStoreTel() + ",");
//		System.out.println("---------------------");

//	 查詢
//		List<StoreVO> list = dao.getAll();
//		for (StoreVO aStore : list) {
//			System.out.print(aStore.getStoreAcc() + ",");
//			System.out.print(aStore.getStorePwd() + ",");
//			System.out.print(aStore.getAccStat() + ",");
//			System.out.print(aStore.getStoreName() + ",");
//			System.out.print(aStore.getStoreGui() + ",");
//			System.out.print(aStore.getStoreRep() + ",");
//			System.out.print(aStore.getStoreTel() + ",");
//			System.out.print(aStore.getStoreFax() + ",");
//			System.out.print(aStore.getStoreAd() + ",");
//			System.out.print(aStore.getStoreCon() + ",");
//			System.out.print(aStore.getStoreConTel() + ",");
//			System.out.print(aStore.getStoreConAd() + ",");
//			System.out.println(aStore.getStoreTel());
//			System.out.println("---------------------");
//		}
	}

}
