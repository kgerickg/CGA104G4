package com.prod.model;

import java.sql.*;
import java.util.*;
import CompositeQuery.jdbcUtil_CompositeQuery_PROD;

public class ProdJDBCDAO implements ProdDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/FOOD?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "insert into PROD (STORE_ID, PROD_TYPE_ID, PROD_STAT, PROD_NAME, PROD_CONT, PROD_PRC, PROD_TIME) values (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "select PROD_ID, STORE_ID, PROD_TYPE_ID, PROD_STAT, PROD_NAME, PROD_CONT, PROD_PRC, PROD_TIME from PROD order by PROD_ID";
	private static final String GET_ONE_STMT = "select PROD_ID, STORE_ID, PROD_TYPE_ID, PROD_STAT, PROD_NAME, PROD_CONT, PROD_PRC, PROD_TIME from PROD where PROD_ID = ?";
	private static final String DELETE = "delete from PROD where PROD_ID = ?";
	private static final String UPDATE = "update PROD set STORE_ID = ?, PROD_TYPE_ID = ?, PROD_STAT = ?, PROD_NAME = ?, PROD_CONT = ?, PROD_PRC = ?, PROD_TIME = ? from PROD where PROD_ID = ?";
	private static final String GET_ProdTypeIds_ByStoreId_STMT = "select PROD_TYPE_ID from PROD where STORE_ID = ? order by PROD_TYPE_ID";
	private static final String GET_Prods_ByProdTypeId_STMT = "select * from PROD where PROD_TYPE_ID = ? order by PROD_ID";
	private static final String GET_Prods_ByStoreIdAndProdTypeId_STMT = "select * from PROD where STORE_ID = ? and PROD_TYPE_ID = ? order by PROD_ID";

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
			pstmt.setInt(5, ProdVO.getProdPrc());
			pstmt.setInt(6, ProdVO.getProdStat());
			pstmt.setDate(7, ProdVO.getProdTime());

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
			pstmt.setInt(5, ProdVO.getProdPrc());
			pstmt.setInt(6, ProdVO.getProdStat());
			pstmt.setDate(7, ProdVO.getProdTime());

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
				// ProdTypeVO 也稱為 Domain objects
				ProdVO = new ProdVO();
				ProdVO.setProdId(rs.getInt("PROD_ID"));
				ProdVO.setProdTypeId(rs.getInt("PROD_TYPE_ID"));
				ProdVO.setStoreId(rs.getInt("STORE_ID"));
				ProdVO.setProdName(rs.getString("PROD_NAME"));
				ProdVO.setProdCont(rs.getString("PROD_CONT"));
				ProdVO.setProdPrc(rs.getInt("PROD_PRC"));
				ProdVO.setProdStat(rs.getInt("PROD_STAT"));
				ProdVO.setProdTime(rs.getDate("PROD_TIME"));
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
				// ProdTypeVO 也稱為 Domain objects
				ProdVO = new ProdVO();
				ProdVO.setProdId(rs.getInt("PROD_ID"));
				ProdVO.setProdTypeId(rs.getInt("PROD_TYPE_ID"));
				ProdVO.setStoreId(rs.getInt("STORE_ID"));
				ProdVO.setProdName(rs.getString("PROD_NAME"));
				ProdVO.setProdCont(rs.getString("PROD_CONT"));
				ProdVO.setProdPrc(rs.getInt("PROD_PRC"));
				ProdVO.setProdStat(rs.getInt("PROD_STAT"));
				ProdVO.setProdTime(rs.getDate("PROD_TIME"));
				list.add(ProdVO); // Store the row in the list
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

	@Override
	public Set<ProdVO> getProdTypeIdsByStoreId(Integer storeId) {

		Set<ProdVO> set = new LinkedHashSet<ProdVO>();
		ProdVO prodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ProdTypeIds_ByStoreId_STMT);
			pstmt.setInt(1, storeId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prodVO = new ProdVO();
				prodVO.setProdTypeId(rs.getInt("PROD_TYPE_ID"));

				set.add(prodVO); // Store the row in the vector
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public Set<ProdVO> getProdsByProdTypeId(Integer prodTypeId) {
		Set<ProdVO> set = new LinkedHashSet<ProdVO>();
		ProdVO prodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Prods_ByProdTypeId_STMT);
			pstmt.setInt(1, prodTypeId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prodVO = new ProdVO();
				prodVO.setProdId(rs.getInt("PROD_ID"));
				prodVO.setProdTypeId(rs.getInt("PROD_TYPE_ID"));
				prodVO.setStoreId(rs.getInt("STORE_ID"));
				prodVO.setProdName(rs.getString("PROD_NAME"));
				prodVO.setProdCont(rs.getString("PROD_CONT"));
				prodVO.setProdPrc(rs.getInt("PROD_PRC"));
				prodVO.setProdStat(rs.getInt("PROD_STAT"));
				prodVO.setProdTime(rs.getDate("PROD_TIME"));

				set.add(prodVO); // Store the row in the vector
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public Set<ProdVO> getProdsByStoreIdAndProdTypeId(Integer storeId, Integer prodTypeId) {
		Set<ProdVO> set = new LinkedHashSet<ProdVO>();
		ProdVO prodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Prods_ByStoreIdAndProdTypeId_STMT);
			pstmt.setInt(1, storeId);
			pstmt.setInt(2, prodTypeId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prodVO = new ProdVO();
				prodVO.setProdId(rs.getInt("PROD_ID"));
				prodVO.setProdTypeId(rs.getInt("PROD_TYPE_ID"));
				prodVO.setStoreId(rs.getInt("STORE_ID"));
				prodVO.setProdName(rs.getString("PROD_NAME"));
				prodVO.setProdCont(rs.getString("PROD_CONT"));
				prodVO.setProdPrc(rs.getInt("PROD_PRC"));
				prodVO.setProdStat(rs.getInt("PROD_STAT"));
				prodVO.setProdTime(rs.getDate("PROD_TIME"));

				set.add(prodVO); // Store the row in the vector
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<ProdVO> getAll(Map<String, String[]> map) {
		List<ProdVO> list = new ArrayList<ProdVO>();
		ProdVO prodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String finalSQL = "select * from PROD " + jdbcUtil_CompositeQuery_PROD.get_WhereCondition(map)
					+ "order by PROD_ID";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = " + finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prodVO = new ProdVO();
				prodVO.setProdId(rs.getInt("PROD_ID"));
				prodVO.setProdTypeId(rs.getInt("PROD_TYPE_ID"));
				prodVO.setStoreId(rs.getInt("STORE_ID"));
				prodVO.setProdName(rs.getString("PROD_NAME"));
				prodVO.setProdCont(rs.getString("PROD_CONT"));
				prodVO.setProdPrc(rs.getInt("PROD_PRC"));
				prodVO.setProdStat(rs.getInt("PROD_STAT"));
				prodVO.setProdTime(rs.getDate("PROD_TIME"));
				list.add(prodVO); // Store the row in the List
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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