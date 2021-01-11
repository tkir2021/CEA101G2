package com.memGrade.model;

import java.util.*;
import java.sql.*;

public class Mem_GradeJDBCDAO implements Mem_GradeDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G2";
	String passwd = "CEA101G2";

	private static final String INSERT_STMT = 
		"INSERT INTO MEM_GRADE (GRADE_NO,CONSUME_LEVEL,DISCOUNT) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT GRADE_NO,CONSUME_LEVEL,DISCOUNT FROM MEM_GRADE order by GRADE_NO";
	private static final String GET_ONE_STMT = 
		"SELECT GRADE_NO,CONSUME_LEVEL,DISCOUNT FROM MEM_GRADE where GRADE_NO = ?";
	private static final String DELETE = 
		"DELETE FROM MEM_GRADE where GRADE_NO = ?";
	private static final String UPDATE = 
		"UPDATE MEM_GRADE set CONSUME_LEVEL=?, DISCOUNT=? where GRADE_NO = ?";

	@Override
	public void insert(Mem_GradeVO mem_gradeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, mem_gradeVO.getGrade_no());
			pstmt.setInt(2, mem_gradeVO.getConsume_level());
			pstmt.setDouble(3, mem_gradeVO.getDiscount());
			


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
	public void update(Mem_GradeVO mem_gradeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, mem_gradeVO.getConsume_level());
			pstmt.setDouble(2, mem_gradeVO.getDiscount());
			pstmt.setInt(3, mem_gradeVO.getGrade_no());
			
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
	public void delete(Integer grade_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, grade_no);

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
	public Mem_GradeVO findByPrimaryKey(Integer grade_no) {

		Mem_GradeVO mem_gradeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, grade_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// GradeVo 也稱為 Domain objects
				mem_gradeVO = new Mem_GradeVO();
				mem_gradeVO.setGrade_no(rs.getInt("GRADE_NO"));
				mem_gradeVO.setConsume_level(rs.getInt("CONSUME_LEVEL"));
				mem_gradeVO.setDiscount(rs.getDouble("DISCOUNT"));
				
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
		return mem_gradeVO;
	}

	@Override
	public List<Mem_GradeVO> getAll() {
		List<Mem_GradeVO> list = new ArrayList<Mem_GradeVO>();
		Mem_GradeVO mem_gradeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// GradeVO 也稱為 Domain objects
				mem_gradeVO = new Mem_GradeVO();
				mem_gradeVO.setGrade_no(rs.getInt("GRADE_NO"));
				mem_gradeVO.setConsume_level(rs.getInt("CONSUME_LEVEL"));
				mem_gradeVO.setDiscount(rs.getDouble("DISCOUNT"));
				
				list.add(mem_gradeVO); // Store the row in the list
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

		Mem_GradeJDBCDAO dao = new Mem_GradeJDBCDAO();

		// 新增
//		Mem_GradeVO mem_gradeVO1 = new Mem_GradeVO();
//		mem_gradeVO1.setGrade_no(6);
//		mem_gradeVO1.setConsume_level(10000000);
//		mem_gradeVO1.setDiscount(new Double(0.4));
//		dao.insert(mem_gradeVO1);

		// 修改
		Mem_GradeVO mem_gradeVO2 = new Mem_GradeVO();
		mem_gradeVO2.setGrade_no(5);
		mem_gradeVO2.setConsume_level(200000);
		mem_gradeVO2.setDiscount(new Double(0.5));
		dao.update(mem_gradeVO2);

		// 刪除
//		dao.delete(5);

		// 查詢
//		Mem_GradeVO mem_gradeVO3 = dao.findByPrimaryKey(2);
//		System.out.print(mem_gradeVO3.getGrade_no() + ",");
//		System.out.print(mem_gradeVO3.getConsume_level() + ",");
//		System.out.println(mem_gradeVO3.getDiscount());
//		System.out.println("---------------------");

		// 查詢
		List<Mem_GradeVO> list = dao.getAll();
		for (Mem_GradeVO aGrade : list) {
			System.out.print(aGrade.getGrade_no() + ",");
			System.out.print(aGrade.getConsume_level() + ",");
			System.out.print(aGrade.getDiscount());
			System.out.println();
		}
	}
}