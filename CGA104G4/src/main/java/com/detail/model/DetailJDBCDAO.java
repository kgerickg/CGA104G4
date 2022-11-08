package com.detail.model;

import java.util.*;
import java.sql.*;

public class DetailJDBCDAO implements DetailDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/FOOD?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = 
		"insert into DETAIL (PROD_QTY, ORD_ID) values (?, ?)";
	private static final String GET_ALL_STMT = 
		"select PROD_ID, PROD_QTY, ORD_ID from DETAIL order by PROD_ID";
	private static final String GET_ONE_STMT = 
		"select PROD_ID, PROD_QTY, ORD_ID from DETAIL where PROD_ID = ?";
	private static final String DELETE = 
		"delete from DETAIL where PROD_ID = ?";
	private static final String UPDATE = 
		"update DETAIL set PROD_QTY=?, ORD_ID=? where PROD_ID = ?";

	@Override
	public void insert(DetailVO detailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, detailVO.getProdQty());
			pstmt.setInt(2, detailVO.getOrdId());

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
	public void update(DetailVO detailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, detailVO.getProdQty());
			pstmt.setInt(2, detailVO.getOrdId());
			pstmt.setInt(3, detailVO.getProdId());

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
	public DetailVO findByPrimaryKey(Integer prodId) {

		DetailVO detailVO = null;
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
				// detailVO 也稱為 Domain objects
				detailVO = new DetailVO();
				detailVO.setProdId(rs.getInt("prodId"));
				detailVO.setProdQty(rs.getInt("prodQty"));
				detailVO.setOrdId(rs.getInt("ordId"));
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
		return detailVO;
	}

	@Override
	public List<DetailVO> getAll() {
		List<DetailVO> list = new ArrayList<DetailVO>();
		DetailVO detailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// detailVO 也稱為 Domain objects
				detailVO = new DetailVO();
				detailVO.setProdId(rs.getInt("prodId"));
				detailVO.setProdQty(rs.getInt("prodQty"));
				detailVO.setOrdId(rs.getInt("ordId"));
				list.add(detailVO); // Store the row in the list
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

		DetailJDBCDAO dao = new DetailJDBCDAO();

		// 新增
		DetailVO detailVO1 = new DetailVO();
		detailVO1.setProdQty(1);
		detailVO1.setOrdId(1);
		dao.insert(detailVO1);

		// 修改
		DetailVO detailVO2 = new DetailVO();
		detailVO2.setProdId(1);
		detailVO2.setProdQty(1);
		detailVO2.setOrdId(1);
		dao.update(detailVO2);

		// 刪除
		dao.delete(7014);

		// 查詢
		DetailVO detailVO3 = dao.findByPrimaryKey(1);
		System.out.print(detailVO3.getProdId() + ",");
		System.out.print(detailVO3.getProdQty() + ",");
		System.out.print(detailVO3.getOrdId() + ",");
		System.out.println("---------------------");

		// 查詢
		List<DetailVO> list = dao.getAll();
		for (DetailVO aDetail : list) {
			System.out.print(aDetail.getProdId() + ",");
			System.out.print(aDetail.getProdQty() + ",");
			System.out.print(aDetail.getOrdId() + ",\t");
			System.out.print(aDetail.getProdVO()); // join ProdVO
			System.out.println();
		}
	}
}