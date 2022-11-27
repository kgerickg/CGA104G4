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

public class OrdersJDBCDAO implements OrdersDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/FOOD?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "insert into ORDERS (MEM_ID, STORE_ID, ORD_AMT, ORD_STAT, ORD_TIME) VALUES (?, ?, ?, ?, ?)";
	
	private static final String GET_ALL_STMT = "select ORD_ID, MEM_ID, STORE_ID, ORD_AMT, ORD_STAT, ORD_TIME from ORDERS";
	private static final String GET_ONE_STMT = "select ORD_ID, MEM_ID, STORE_ID, ORD_AMT, ORD_STAT, ORD_TIME from ORDERS where ORD_ID = ?";
	
	private static final String GET_Orders_ByMemId_STMT = "select * from ORDERS where MEM_ID=? order by ORD_ID desc";
	private static final String GET_Orders_ByStoreId_STMT = "select * from ORDERS where STORE_ID=? order by ORD_ID desc";
	
	private static final String UPDATE_OrdStat = "update ORDERS set ORD_STAT=? where ORD_ID = ?";

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
	public void updateOrdStat(OrdersVO ordersVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_OrdStat);

			pstmt.setInt(1, ordersVO.getOrdStat());
			pstmt.setInt(2, ordersVO.getOrdId());

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