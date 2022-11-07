package com.prod.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.detail.model.DetailVO;

public class ProdJDBCDAO implements ProdDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/FOOD?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "insert into PROD (PROD_ID, STORE_ID, PROD_TYPE_ID, PROD_STAT, PROD_NAME, PROD_CONT, PROD_PRC, PROD_TIME) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "select PROD_ID, STORE_ID, PROD_TYPE_ID, PROD_STAT, PROD_NAME, PROD_CONT, PROD_PRC, PROD_TIME from PROD";
	private static final String GET_ONE_STMT = "select PROD_ID, STORE_ID, PROD_TYPE_ID, PROD_STAT, PROD_NAME, PROD_CONT, PROD_PRC, PROD_TIME from PROD where PROD_ID = ?";
	private static final String GET_Details_ByProdId_STMT = "select PROD_ID, PROD_QTY, ORD_ID from DETAIL where PROD_ID = ? order by PROD_ID";
	private static final String DELETE_DETAILs = "delete from DETAIL where PROD_ID = ?";
	private static final String DELETE_PROD = "delete from PROD where PROD_ID = ?";
	private static final String UPDATE = "update PROD set STORE_ID=?, PROD_TYPE_ID=?, PROD_STAT=?, PROD_NAME=?, PROD_CONT=?, PROD_PRC=?, PROD_TIME=?, where PROD_ID = ?";

	@Override
	public void insert(ProdVO prodVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, prodVO.getStoreId());
			pstmt.setInt(2, prodVO.getProdTypeId());
			pstmt.setInt(3, prodVO.getProdStat());
			pstmt.setString(4, prodVO.getProdName());
			pstmt.setString(5, prodVO.getProdCont());
			pstmt.setInt(6, prodVO.getProdPrc());
			pstmt.setDate(6, prodVO.getProdTime());

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
	public void update(ProdVO prodVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, prodVO.getStoreId());
			pstmt.setInt(2, prodVO.getProdTypeId());
			pstmt.setInt(3, prodVO.getProdStat());
			pstmt.setString(4, prodVO.getProdName());
			pstmt.setString(5, prodVO.getProdCont());
			pstmt.setInt(6, prodVO.getProdPrc());
			pstmt.setDate(6, prodVO.getProdTime());
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
		int updateCount_DETAILs = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);
			
			// 先刪除明細
			pstmt = con.prepareStatement(DELETE_DETAILs);
			pstmt.setInt(1, prodId);
			pstmt.executeUpdate();
			// 再刪除訂單
			pstmt = con.prepareStatement(DELETE_PROD);
			pstmt.setInt(1, prodId);
			pstmt.executeUpdate();
			
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除商品編號" + prodId + "時,共有明細" + updateCount_DETAILs
					+ "筆同時被刪除");
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

		ProdVO prodVO = null;
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
				// prodVo 也稱為 Domain objects
				prodVO = new ProdVO();
				prodVO.setProdId(rs.getInt("PROD_ID"));
				prodVO.setStoreId(rs.getInt("STORE_ID"));
				prodVO.setProdTypeId(rs.getInt("PROD_TYPE_ID"));
				prodVO.setProdStat(rs.getInt("PROD_STAT"));
				prodVO.setProdName(rs.getString("PROD_NAME"));
				prodVO.setProdCont(rs.getString("PROD_CONT"));
				prodVO.setProdPrc(rs.getInt("PROD_PRC"));
				prodVO.setProdTime(rs.getDate("PROD_TIME"));
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
		return prodVO;
	}

	@Override
	public List<ProdVO> getAll() {
		List<ProdVO> list = new ArrayList<ProdVO>();
		ProdVO prodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prodVO = new ProdVO();
				prodVO.setProdId(rs.getInt("PROD_ID"));
				prodVO.setStoreId(rs.getInt("STORE_ID"));
				prodVO.setProdTypeId(rs.getInt("PROD_TYPE_ID"));
				prodVO.setProdStat(rs.getInt("PROD_STAT"));
				prodVO.setProdName(rs.getString("PROD_NAME"));
				prodVO.setProdCont(rs.getString("PROD_CONT"));
				prodVO.setProdPrc(rs.getInt("PROD_PRC"));
				prodVO.setProdTime(rs.getDate("PROD_TIME"));
				list.add(prodVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

	@Override
	public Set<DetailVO> getDetailsByProdId(Integer prodId) {
		Set<DetailVO> set = new LinkedHashSet<DetailVO>();
		DetailVO detailVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Details_ByProdId_STMT);
			pstmt.setInt(1, prodId);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				detailVO = new DetailVO();
				detailVO.setProdId(rs.getInt("prodId"));
				detailVO.setProdQty(rs.getInt("prodQty"));
				detailVO.setOrdId(rs.getInt("ordId"));
				set.add(detailVO); // Store the row in the vector
			}
	
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return set;
	}
	
	public static void main(String[] args) {

		ProdJDBCDAO dao = new ProdJDBCDAO();

		// 新增
//		ProdVO ordersVO1 = new ProdVO();
//		ordersVO1.setOrdId(1);
//		ordersVO1.setMemId(1);
//		ordersVO1.setStoreId(1);
//		ordersVO1.setOrdAmt(100);
//		ordersVO1.setOrdStat(0);
//		ordersVO1.setOrdTime(java.sql.Date.valueOf("2022-10-16"));
//		dao.insert(ordersVO1);

		// 修改
//		ProdVO ordersVO2 = new ProdVO();
//		ordersVO2.setOrdId(2);
//		ordersVO2.setMemId(2);
//		ordersVO2.setStoreId(2);
//		ordersVO2.setOrdAmt(110);
//		ordersVO2.setOrdStat(1);
//		ordersVO2.setOrdTime(java.sql.Date.valueOf("2022-10-17"));
//		dao.update(ordersVO2);

		// 刪除
//		dao.delete(2);

		// 查詢
		ProdVO ordersVO3 = dao.findByPrimaryKey(1);
		System.out.print(ordersVO3.getProdId() + ",");
		System.out.print(ordersVO3.getStoreId() + ",");
		System.out.print(ordersVO3.getProdTypeId() + ",");
		System.out.print(ordersVO3.getProdStat() + ",");
		System.out.print(ordersVO3.getProdName() + ",");
		System.out.print(ordersVO3.getProdCont() + ",");
		System.out.print(ordersVO3.getProdPrc() + ",");
		System.out.print(ordersVO3.getProdTime());
		System.out.println("---------------------");

		// 查詢商品
		List<ProdVO> list = dao.getAll();
		for (ProdVO aProd : list) {
			System.out.print(aProd.getProdId() + ",");
			System.out.print(aProd.getStoreId() + ",");
			System.out.print(aProd.getProdTypeId() + ",");
			System.out.print(aProd.getProdStat() + ",");
			System.out.print(aProd.getProdName() + ",");
			System.out.print(aProd.getProdCont() + ",");
			System.out.print(aProd.getProdPrc() + ",");
			System.out.print(aProd.getProdTime());
			System.out.println();
		}
		
		// 查詢某商品的明細
		Set<DetailVO> set = dao.getDetailsByProdId(1);
		for (DetailVO aDetail : set) {
			System.out.print(aDetail.getProdId() + ",");
			System.out.print(aDetail.getProdQty() + ",");
			System.out.print(aDetail.getOrdId() + ",");
			System.out.println();
		}
	}
}