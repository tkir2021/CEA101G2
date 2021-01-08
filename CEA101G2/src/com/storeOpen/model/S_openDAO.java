package com.storeOpen.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class S_openDAO implements S_openDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BELLA";
	String passwd = "BELLA";

	private static final String INSERT_STMT = "INSERT INTO STORE_OPEN(time_period,store_no)values(?,?)";
//	private static final String UPDATE ="UPDATE STORE_OPEN SET time_period=? where STORE_NO = ?";
	private static final String DELETE="DELETE FROM STORE_OPEN WHERE STORE_NO=? AND time_period=?";
	private static final String GET_ONE_STMT = "SELECT store_no,time_period FROM STORE_OPEN  where store_no=?";
	private static final String GET_ALL_STMT = "SELECT* FROM STORE_OPEN";

	@Override
	public void insert(S_openVO openVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, openVO.getTimeperiod());
			pstmt.setString(2, openVO.getStoreno());

			pstmt.executeUpdate();
			System.out.println("成功了");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(S_openVO openVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1,openVO.getStoreno());
			pstmt.setString(2,openVO.getTimeperiod());
			pstmt.executeUpdate();
			
			System.out.println("������");
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
	public List<S_openVO> getAll() {
		List<S_openVO> list = new ArrayList<S_openVO>();
		S_openVO openVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				openVO = new S_openVO();
				openVO.setTimeperiod(rs.getString("time_period"));
				openVO.setStoreno(rs.getString("store_no"));
				list.add(openVO);
			}

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

//	@Override
//	public void update(S_openVO openVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//			pstmt.setString(1, openVO.getTimeperiod());
//			pstmt.setString(2, openVO.getStoreno());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}

	@Override
	public List<S_openVO> findByPrimaryKey(String storeno) {
		List<S_openVO> list2 = new ArrayList<S_openVO>();
		S_openVO openVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, storeno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				openVO = new S_openVO();
				openVO.setStoreno(rs.getString("store_no"));
				openVO.setTimeperiod(rs.getString("time_period"));
				list2.add(openVO);
			}
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
		return list2;
	}

//	public static void main(String[] args) {
//		S_openDAO dao = new S_openDAO();

//		// insert �憓�
//		S_openVO open1 = new S_openVO();
//		open1.setTimeperiod("OP00000001");
//		open1.setStoreno("SM0000001");
//		dao.insert(open1);

//		//update 靽格
//		S_openVO open2 = new S_openVO();
//		open2.setTimeperiod("OP00000003");
//		open2.setStoreno("SM00000002");
//		dao.update(open2);
		
//		//delete ��
//		S_openVO openVO = new S_openVO();
//		openVO.setStoreno("SM00000001");
//		openVO.setTimeperiod("OP00000002");
//		dao.delete(openVO);
		
		
		// findByPrimaryKey �閰Ｖ�蝑�
//		List<S_openVO>list2= dao.findByPrimaryKey("SM00000002");
//		System.out.println("SM00000002"+",");
//		for(S_openVO open3:list2) 
//		System.out.println(open3.getTimeperiod());
		
//		// getALL �閰Ｗ�
//		List<S_openVO> list = dao.getAll();
//		for (S_openVO open4 : list) {
//			System.out.println(open4.getStoreno());
//			System.out.println(open4.getTimeperiod());
//			System.out.println();
//		}
//	}

	

}
