package com.group_mem.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Group_memDAO implements Group_memDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CEA101G2");
		} catch (NamingException e) {
		e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
		"INSERT INTO group_mem (group_no, mem_no) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT group_no, mem_no FROM group_mem ORDER BY group_no, mem_no";
	private static final String GET_ONE_STMT = 
		"SELECT group_no, mem_no FROM group_mem WHERE group_no = ?";
	private static final String DELETE = 
		"DELETE FROM group_mem WHERE group_no = ? AND mem_no = ?";	
	
	@Override
	public void insert(Group_memVO group_memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, group_memVO.getGroup_no());
			pstmt.setString(2, group_memVO.getMem_no());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(String group_no, String mem_no) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, group_no);
			pstmt.setString(2, mem_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public List<Group_memVO> findByPrimaryKey(String group_no) {
		List<Group_memVO> oneGroupList = new ArrayList<Group_memVO>();
		Group_memVO group_memVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, group_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// group_memVO 也稱為 Domain objects
				group_memVO = new Group_memVO();
				group_memVO.setGroup_no(rs.getString("group_no"));
				group_memVO.setMem_no(rs.getString("mem_no"));
				oneGroupList.add(group_memVO);
				
//				System.out.println(rs.getString("group_no"));
//				System.out.println(rs.getString("mem_no"));
			}
			
			
			
			// Handle any driver errors
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
		return oneGroupList;
	
	}

	@Override
	public List<Group_memVO> getAll() {
		List<Group_memVO> list = new ArrayList<Group_memVO>();
		Group_memVO group_memVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// group_memVO 也稱為 Domain objects
				group_memVO = new Group_memVO();
				group_memVO.setGroup_no(rs.getString("group_no"));
				group_memVO.setMem_no(rs.getString("mem_no"));				
				list.add(group_memVO); // Store the row in the list
			}

			// Handle any driver errors
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
//		Group_memDAO dao = new Group_memDAO();
		
//		// 新增
//		Group_memVO group_memVO1 = new Group_memVO();
//		group_memVO1.setGroup_no("GP00000001");
//		group_memVO1.setMem_no("MM00000002");
//		dao.insert(group_memVO1);
		
//		//刪除
//		dao.delete("GP00000001", "MM00000002");
//		
//		// 查詢
//		Group_memVO group_memVO2 = dao.findByPrimaryKey("GP00000001");
//		System.out.print(group_memVO2.getGroup_no() + ",");
//		System.out.println(group_memVO2.getMem_no());
//		System.out.println("---------------------");
//		
//		// 查詢
//		List<Group_memVO> list = dao.getAll();
//		for(Group_memVO aGroup_mem : list) {
//			System.out.print(aGroup_mem.getGroup_no() + ",");
//			System.out.println(aGroup_mem.getMem_no());
//			System.out.println("---------------------");
//		}
	}

}
