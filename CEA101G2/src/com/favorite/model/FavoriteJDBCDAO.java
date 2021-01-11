package com.favorite.model;

import java.util.*;
import java.sql.*;

public class FavoriteJDBCDAO implements FavoriteDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G2";
	String passwd = "CEA101G2";

	private static final String INSERT_STMT = 
		"INSERT INTO FAVORITE (STORE_NO,MEM_NO) VALUES ( ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT STORE_NO,MEM_NO FROM FAVORITE order by STORE_NO";
	private static final String GET_ONE_STMT = 
		"SELECT STORE_NO,MEM_NO FROM FAVORITE where STORE_NO = ?";
	private static final String DELETE = 
		"DELETE FROM FAVORITE where STORE_NO = ? AND MEM_NO = ?";
	private static final String UPDATE = 
		"UPDATE FAVORITE set MEM_NO=? where STORE_NO = ?";

	@Override
	public void insert(FavoriteVO favoriteVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, favoriteVO.getStore_no());
			pstmt.setString(2, favoriteVO.getMem_no());
			

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
	public void update(FavoriteVO favoriteVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, favoriteVO.getMem_no());
			pstmt.setString(2, favoriteVO.getStore_no());

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
	public void delete(String Store_no, String mem_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, Store_no);
			pstmt.setString(2, mem_no);
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
	public List<FavoriteVO> findByPrimaryKey(String Store_no) {
		List<FavoriteVO> list = new ArrayList<FavoriteVO>();
		FavoriteVO favoriteVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, Store_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// FavoriteVO 也稱為 Domain objects
				favoriteVO = new FavoriteVO();
				favoriteVO.setStore_no(rs.getString("STORE_NO"));
				favoriteVO.setMem_no(rs.getString("MEM_NO"));
				list.add(favoriteVO);
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

	@Override
	public List<FavoriteVO> getAll() {
		List<FavoriteVO> list2 = new ArrayList<FavoriteVO>();
		FavoriteVO favoriteVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// FavoriteVO 也稱為 Domain objects
				favoriteVO = new FavoriteVO();
				favoriteVO.setStore_no(rs.getString("STORE_NO"));
				favoriteVO.setMem_no(rs.getString("MEM_NO"));
				list2.add(favoriteVO);
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
		return list2;
	}

	public static void main(String[] args) {

		FavoriteJDBCDAO dao = new FavoriteJDBCDAO();

		// 新增
//		FavoriteVO favoriteVO1 = new FavoriteVO();
//		favoriteVO1.setStore_no("SM00000005");
//		favoriteVO1.setMem_no("MM00000005");
//		dao.insert(favoriteVO1);
		System.out.println("---------------------");
		// 修改
//		FavoriteVO favoriteVO2 = new FavoriteVO();
//		favoriteVO2.setStore_no("SM00000005");
//		favoriteVO2.setMem_no("MM00000003");
//	
//		dao.update(favoriteVO2);

		// 刪除
		dao.delete("SM00000001","MM00000004");

		// 查詢
//		List<FavoriteVO> list = dao.findByPrimaryKey("SM00000001");
//		for (FavoriteVO aFavorite : list) {
//		System.out.print(aFavorite.getStore_no() + ",");
//		System.out.println(aFavorite.getMem_no());
//		}  
//		System.out.println("---------------------");
		
		// 查詢
		List<FavoriteVO> list2 = dao.getAll();
		for (FavoriteVO aFavorite : list2) {
			System.out.print(aFavorite.getStore_no() + ",");
			System.out.print(aFavorite.getMem_no() + ",");
			System.out.println();
		}
	}
}