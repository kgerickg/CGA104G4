package com.orders.model;

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

public class OrdersJDBCDAO implements OrdersDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/FOOD?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "insert into ORDERS (MEM_ID, STORE_ID, ORD_AMT, ORD_STAT, ORD_TIME) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "select ORD_ID, MEM_ID, STORE_ID, ORD_AMT, ORD_STAT, ORD_TIME from ORDERS";
	private static final String GET_ONE_STMT = "select ORD_ID, MEM_ID, STORE_ID, ORD_AMT, ORD_STAT, ORD_TIME from ORDERS where ORD_ID = ?";
	private static final String GET_Details_ByOrdId_STMT = "select PROD_ID, PROD_QTY, ORD_ID from DETAIL where ORD_ID = ? order by PROD_ID";
	private static final String GET_Orders_ByMemId_STMT = "select ORD_ID, MEM_ID, STORE_ID, ORD_AMT, ORD_STAT, ORD_TIME from ORDERS where MEM_ID = ? order by ORD_ID";
	private static final String GET_Orders_ByStoreId_STMT = "select ORD_ID, MEM_ID, STORE_ID, ORD_AMT, ORD_STAT, ORD_TIME from ORDERS where STORE_ID = ? order by ORD_ID";
	
	private static final String DELETE_DETAILs = "delete from DETAIL where ORD_ID = ?";
	private static final String DELETE_ORDERS = "delete from ORDERS where ORD_ID = ?";
	
	private static final String UPDATE = "update ORDERS set MEM_ID=?, STORE_ID=?, ORD_AMT=?, ORD_STAT=?, ORD_TIME=?, where ORD_ID = ?";

	@Override
	public void insert(OrdersVO ordersVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, ordersVO.getMemId());
			pstmt.setInt(2, ordersVO.getStoreId());
			pstmt.setInt(3, ordersVO.getOrdAmt());
			pstmt.setInt(4, ordersVO.getOrdStat());
			pstmt.setDate(5, ordersVO.getOrdTime());

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
	public void update(OrdersVO ordersVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, ordersVO.getMemId());
			pstmt.setInt(2, ordersVO.getStoreId());
			pstmt.setInt(3, ordersVO.getOrdAmt());
			pstmt.setInt(4, ordersVO.getOrdStat());
			pstmt.setDate(5, ordersVO.getOrdTime());
			pstmt.setInt(6, ordersVO.getOrdId());

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
	public void delete(Integer ordId) {
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
			pstmt.setInt(1, ordId);
			pstmt.executeUpdate();
			// 再刪除訂單
			pstmt = con.prepareStatement(DELETE_ORDERS);
			pstmt.setInt(1, ordId);
			pstmt.executeUpdate();
			
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除訂單編號" + ordId + "時,共有明細" + updateCount_DETAILs
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
	public OrdersVO findByPrimaryKey(Integer ordId) {

		OrdersVO ordersVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, ordId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// ordersVo 也稱為 Domain objects
				ordersVO = new OrdersVO();
				ordersVO.setOrdId(rs.getInt("ORD_ID"));
				ordersVO.setMemId(rs.getInt("MEM_ID"));
				ordersVO.setStoreId(rs.getInt("STORE_ID"));
				ordersVO.setOrdAmt(rs.getInt("ORD_AMT"));
				ordersVO.setOrdStat(rs.getInt("ORD_STAT"));
				ordersVO.setOrdTime(rs.getDate("ORD_TIME"));
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
		return ordersVO;
	}

	@Override
	public List<OrdersVO> getAll() {
		List<OrdersVO> list = new ArrayList<OrdersVO>();
		OrdersVO ordersVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ordersVO = new OrdersVO();
				ordersVO.setOrdId(rs.getInt("ORD_ID"));
				ordersVO.setMemId(rs.getInt("MEM_ID"));
				ordersVO.setStoreId(rs.getInt("STORE_ID"));
				ordersVO.setOrdAmt(rs.getInt("ORD_AMT"));
				ordersVO.setOrdStat(rs.getInt("ORD_STAT"));
				ordersVO.setOrdTime(rs.getDate("ORD_TIME"));
				list.add(ordersVO); // Store the row in the list
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
	public Set<DetailVO> getDetailsByOrdId(Integer ordId) {
		Set<DetailVO> set = new LinkedHashSet<DetailVO>();
		DetailVO detailVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Details_ByOrdId_STMT);
			pstmt.setInt(1, ordId);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				detailVO = new DetailVO();
				detailVO.setProdId(rs.getInt("PROD_ID"));
				detailVO.setProdQty(rs.getInt("PROD_QTY"));
				detailVO.setOrdId(rs.getInt("ORD_ID"));
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
	
	@Override
	public Set<OrdersVO> getOrdersByMemId(Integer memId) {
		Set<OrdersVO> set = new LinkedHashSet<OrdersVO>();
		OrdersVO ordersVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Orders_ByMemId_STMT);
			pstmt.setInt(1, memId);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				ordersVO = new OrdersVO();
				ordersVO.setOrdId(rs.getInt("ORD_ID"));
				ordersVO.setMemId(rs.getInt("MEM_ID"));
				ordersVO.setStoreId(rs.getInt("STORE_ID"));
				ordersVO.setOrdAmt(rs.getInt("ORD_AMT"));
				ordersVO.setOrdStat(rs.getInt("ORD_STAT"));
				ordersVO.setOrdTime(rs.getDate("ORD_TIME"));
				set.add(ordersVO); // Store the row in the vector
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
	
	@Override
	public Set<OrdersVO> getOrdersByStoreId(Integer storeId) {
		Set<OrdersVO> set = new LinkedHashSet<OrdersVO>();
		OrdersVO ordersVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Orders_ByStoreId_STMT);
			pstmt.setInt(1, storeId);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				ordersVO = new OrdersVO();
				ordersVO.setOrdId(rs.getInt("ORD_ID"));
				ordersVO.setMemId(rs.getInt("MEM_ID"));
				ordersVO.setStoreId(rs.getInt("STORE_ID"));
				ordersVO.setOrdAmt(rs.getInt("ORD_AMT"));
				ordersVO.setOrdStat(rs.getInt("ORD_STAT"));
				ordersVO.setOrdTime(rs.getDate("ORD_TIME"));
				set.add(ordersVO); // Store the row in the vector
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
}