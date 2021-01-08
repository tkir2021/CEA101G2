package com.group.model;

import java.util.*;
import java.sql.*;

public class Group_JDBCDAO implements Group_DAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G2";
	String passwd = "CEA101G2";
	
	private static final String INSERT_STMT = 
		"INSERT INTO GROUP_ (GROUP_NO, MEM_NO, STORE_NO, BOOKING_NO, GP_KIND, MEM_LEAST, MEM_LIMIT, DINING_DATE, START_DATE, END_DATE, GP_INFO, GP_STATUS) VALUES ('GP' || LPAD(GROUP_SEQ.NEXTVAL, 8, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT GROUP_NO, MEM_NO, STORE_NO, BOOKING_NO, GP_KIND, MEM_LEAST, MEM_LIMIT, DINING_DATE, START_DATE, END_DATE, GP_INFO, GP_STATUS FROM GROUP_ ORDER BY GROUP_NO";
	private static final String GET_ONE_STMT = 
		"SELECT GROUP_NO, MEM_NO, STORE_NO, BOOKING_NO, GP_KIND, MEM_LEAST, MEM_LIMIT, DINING_DATE, START_DATE, END_DATE, GP_INFO, GP_STATUS FROM GROUP_ WHERE GROUP_NO = ?";
	private static final String GET_ONE_GPSTA =
		"SELECT GROUP_NO FROM GROUP_ WHERE GP_STATUS = ?";
	private static final String DELETE = 
		"DELETE FROM GROUP_ WHERE GROUP_NO = ?";
	private static final String UPDATE = 
		"UPDATE GROUP_ SET MEM_NO=?, STORE_NO=?, BOOKING_NO=?, GP_KIND=?, MEM_LEAST=?, MEM_LIMIT=?, DINING_DATE=?, START_DATE=?, END_DATE=?, GP_INFO=?, GP_STATUS=? WHERE GROUP_NO = ?";
	
	
	@Override
	public void insert(Group_VO group_VO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, group_VO.getMem_no());
			pstmt.setString(2, group_VO.getStore_no());
			pstmt.setString(3, group_VO.getBooking_no());
			pstmt.setInt(4, group_VO.getGp_kind());
			pstmt.setInt(5, group_VO.getMem_least());
			pstmt.setInt(6, group_VO.getMem_limit());
			pstmt.setDate(7, group_VO.getDining_date());
			pstmt.setDate(8, group_VO.getStart_date());
			pstmt.setDate(9, group_VO.getEnd_date());
			pstmt.setString(10, group_VO.getGp_info());
			pstmt.setInt(11, group_VO.getGp_status());
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch (ClassNotFoundException e){
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
	public void update(Group_VO group_VO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, group_VO.getMem_no());
			pstmt.setString(2, group_VO.getStore_no());
			pstmt.setString(3, group_VO.getBooking_no());
			pstmt.setInt(4, group_VO.getGp_kind());
			pstmt.setInt(5, group_VO.getMem_least());
			pstmt.setInt(6, group_VO.getMem_limit());
			pstmt.setDate(7, group_VO.getDining_date());
			pstmt.setDate(8, group_VO.getStart_date());
			pstmt.setDate(9, group_VO.getEnd_date());
			pstmt.setString(10, group_VO.getGp_info());
			pstmt.setInt(11, group_VO.getGp_status());
			pstmt.setString(12, group_VO.getGroup_no());
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException se) {
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
	public void delete (String group_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, group_no);
			
			pstmt.executeUpdate();
			
			// Handle any errors
		} catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public Group_VO findByPrimaryKey(String group_no) {
		
		Group_VO group_VO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, group_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// group_VO 也稱為 Domain objects
				group_VO = new Group_VO();				
				group_VO.setGroup_no(rs.getString("GROUP_NO"));
				group_VO.setMem_no(rs.getString("MEM_NO"));
				group_VO.setStore_no(rs.getString("STORE_NO"));
				group_VO.setBooking_no(rs.getString("BOOKING_NO"));
				group_VO.setGp_kind(rs.getInt("GP_KIND"));
				group_VO.setMem_least(rs.getInt("MEM_LEAST"));
				group_VO.setMem_limit(rs.getInt("MEM_LIMIT"));
				group_VO.setDining_date(rs.getDate("DINING_DATE"));
				group_VO.setStart_date(rs.getDate("START_DATE"));
				group_VO.setEnd_date(rs.getDate("END_DATE"));
				group_VO.setGp_info(rs.getString("GP_INFO"));
				group_VO.setGp_status(rs.getInt("GP_STATUS"));
			}
			
			// Handle any errors
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
				} catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return group_VO;
	}
	
	@Override
	public Group_VO getOneGpSta(Integer gp_status) {
//		Group_VO group_VO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ONE_GPSTA);
//			
//			pstmt.setInt(1, gp_status);
//			
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				// group_VO 也稱為 Domain objects
//				group_VO = new Group_VO();				
//				group_VO.setGroup_no(rs.getString("GROUP_NO"));
//				group_VO.setMem_no(rs.getString("MEM_NO"));
//				group_VO.setStore_no(rs.getString("STORE_NO"));
//				group_VO.setBooking_no(rs.getString("BOOKING_NO"));
//				group_VO.setGp_kind(rs.getInt("GP_KIND"));
//				group_VO.setMem_least(rs.getInt("MEM_LEAST"));
//				group_VO.setMem_limit(rs.getInt("MEM_LIMIT"));
//				group_VO.setDining_date(rs.getDate("DINING_DATE"));
//				group_VO.setStart_date(rs.getDate("START_DATE"));
//				group_VO.setEnd_date(rs.getDate("END_DATE"));
//				group_VO.setGp_info(rs.getString("GP_INFO"));
//				group_VO.setGp_status(rs.getInt("GP_STATUS"));
//			}
//			
//			// Handle any errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch(SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch(SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch(Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
		return null;
		
	}
	
	@Override
	public List<Group_VO> getAll() {
		List<Group_VO> list = new ArrayList<Group_VO>();
		Group_VO group_VO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// group_VO 也稱為 Domain objects
				group_VO = new Group_VO();				
				group_VO.setGroup_no(rs.getString("GROUP_NO"));
				group_VO.setMem_no(rs.getString("MEM_NO"));
				group_VO.setStore_no(rs.getString("STORE_NO"));
				group_VO.setBooking_no(rs.getString("BOOKING_NO"));
				group_VO.setGp_kind(rs.getInt("GP_KIND"));
				group_VO.setMem_least(rs.getInt("MEM_LEAST"));
				group_VO.setMem_limit(rs.getInt("MEM_LIMIT"));
				group_VO.setDining_date(rs.getDate("DINING_DATE"));
				group_VO.setStart_date(rs.getDate("START_DATE"));
				group_VO.setEnd_date(rs.getDate("END_DATE"));
				group_VO.setGp_info(rs.getString("GP_INFO"));
				group_VO.setGp_status(rs.getInt("GP_STATUS"));
				list.add(group_VO); // Store the row in the list
			}
			
			// Handle any driver errors
		} catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch(SQLException se) {
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
		
		
		Group_JDBCDAO dao = new Group_JDBCDAO();
		//新增
//		Group_VO group_VO1 = new Group_VO();
//		group_VO1.setMem_no("MM00000001");
//		group_VO1.setStore_no("SM00000001");
//		group_VO1.setBooking_no("BO00000001");
//		group_VO1.setGp_kind(0);
//		group_VO1.setMem_least(5);
//		group_VO1.setMem_limit(10);
//		group_VO1.setDining_date(java.sql.Date.valueOf("2020-12-6"));
//		group_VO1.setStart_date(java.sql.Date.valueOf("2020-12-1"));
//		group_VO1.setEnd_date(java.sql.Date.valueOf("2020-12-05"));
//		group_VO1.setGp_info("XXX");
//		group_VO1.setGp_status(0);
//		dao.insert(group_VO1);
		
//		//修改
//		Group_VO group_VO2 = new Group_VO();
//		group_VO2.setGroup_no("GP00000001");
//		group_VO2.setMem_no("MM00000001");
//		group_VO2.setStore_no("SM00000002");
//		group_VO2.setBooking_no("BO00000002");
//		group_VO2.setGp_kind(1);
//		group_VO2.setMem_least(10);
//		group_VO2.setMem_limit(20);
//		group_VO2.setDining_date(java.sql.Date.valueOf("2020-11-6"));
//		group_VO2.setStart_date(java.sql.Date.valueOf("2020-11-1"));
//		group_VO2.setEnd_date(java.sql.Date.valueOf("2020-11-05"));
//		group_VO2.setGp_info("YYY");
//		group_VO2.setGp_status(1);
//		dao.update(group_VO2);
		
//		// 刪除
//		dao.delete("GP00000001");
//		
//		// 查詢
//		Group_VO group_VO3 = dao.findByPrimaryKey("GP00000001");
//		System.out.println(group_VO3.getGroup_no() + ",");
//		System.out.println(group_VO3.getMem_no() + ",");
//		System.out.println(group_VO3.getStore_no() + ",");
//		System.out.println(group_VO3.getBooking_no() + ",");
//		System.out.println(group_VO3.getGp_kind() + ",");
//		System.out.println(group_VO3.getMem_least() + ",");
//		System.out.println(group_VO3.getMem_limit()+ ",");
//		System.out.println(group_VO3.getDining_date() + ",");
//		System.out.println(group_VO3.getStart_date() + ",");
//		System.out.println(group_VO3.getEnd_date() + ",");
//		System.out.println(group_VO3.getGp_info() + ",");
//		System.out.println(group_VO3.getGp_status());
//		System.out.println("---------------------");
//				
//		// 查詢
		List<Group_VO> list = dao.getAll();
		for (Group_VO aGroup : list) {
			System.out.println(aGroup.getGroup_no() + ",");
			System.out.println(aGroup.getMem_no() + ",");
			System.out.println(aGroup.getStore_no() + ",");
			System.out.println(aGroup.getBooking_no() + ",");
			System.out.println(aGroup.getGp_kind() + ",");
			System.out.println(aGroup.getMem_least() + ",");
			System.out.println(aGroup.getMem_limit()+ ",");
			System.out.println(aGroup.getDining_date() + ",");
			System.out.println(aGroup.getStart_date() + ",");
			System.out.println(aGroup.getEnd_date() + ",");
			System.out.println(aGroup.getGp_info() + ",");
			System.out.println(aGroup.getGp_status());
			System.out.println("---------------------");
		}
	}
	
}
