package com.mem.model;

import java.util.*;
import java.sql.*;

public class Mem_DataJDBCDAO implements Mem_DataDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G2";
	String passwd = "CEA101G2";

	private static final String INSERT_STMT = "INSERT INTO MEM_DATA (MEM_NO,MEM_GRADE,MEM_ACCT,MEM_PWD,MEM_NAME,MEN_PHONE,MEM_MAIL,MEM_IMG,MEM_AUTH,REPORT_COUNT,CONSUME_TIMES,DEPOSIT) VALUES (('MM' || LPAD(EMP_SEQ.NEXTVAL, 8, '0')), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT MEM_NO,MEM_GRADE,MEM_ACCT,MEM_PWD,MEM_NAME,MEN_PHONE,MEM_MAIL,MEM_IMG,MEM_AUTH,REPORT_COUNT,CONSUME_TIMES,DEPOSIT FROM MEM_DATA ORDER BY MEM_NO";
	private static final String GET_ONE_STMT = "SELECT MEM_NO,MEM_GRADE,MEM_ACCT,MEM_PWD,MEM_NAME,MEN_PHONE,MEM_MAIL,MEM_IMG,MEM_AUTH,REPORT_COUNT,CONSUME_TIMES,DEPOSIT FROM MEM_DATA WHERE MEM_NO = ?";
	private static final String GET_ONE_ACCT = "SELECT MEM_NO,MEM_GRADE,MEM_ACCT,MEM_PWD,MEM_NAME,MEN_PHONE,MEM_MAIL,MEM_IMG,MEM_AUTH,REPORT_COUNT,CONSUME_TIMES,DEPOSIT FROM MEM_DATA WHERE MEM_ACCT = ?";
	private static final String GET_ONE_ACC_NOIMG = "SELECT MEM_NO,MEM_GRADE,MEM_ACCT,MEM_PWD,MEM_NAME,MEN_PHONE,MEM_MAIL,MEM_AUTH,REPORT_COUNT,CONSUME_TIMES,DEPOSIT FROM MEM_DATA WHERE MEM_ACCT = ?";
	private static final String DELETE = "DELETE FROM MEM_DATA WHERE MEM_NO = ?";
	private static final String UPDATE = "UPDATE MEM_DATA SET MEM_PWD=?, MEM_NAME=?, MEN_PHONE=?, MEM_MAIL=?,MEM_IMG=? WHERE MEM_NO = ?";
	private static final String UP_STATUS = "UPDATE MEM_DATA SET MEM_AUTH=? WHERE MEM_ACCT= ?";
	private static final String UPDATE_AUTH = "UPDATE MEM_DATA set MEM_AUTH=? where MEM_NO = ?";

	@Override
	public void insert(Mem_DataVO mem_dataVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, mem_dataVO.getMem_grade());
			pstmt.setString(2, mem_dataVO.getMem_acct());
			pstmt.setString(3, mem_dataVO.getMem_pwd());
			pstmt.setString(4, mem_dataVO.getMem_name());
			pstmt.setString(5, mem_dataVO.getMen_phone());
			pstmt.setString(6, mem_dataVO.getMem_mail());
			pstmt.setBytes(7, mem_dataVO.getMem_img());
			pstmt.setInt(8, mem_dataVO.getMem_auth());
			pstmt.setInt(9, mem_dataVO.getReport_count());
			pstmt.setInt(10, mem_dataVO.getConsume_times());
			pstmt.setInt(11, mem_dataVO.getDeposit());
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
	public void update(Mem_DataVO mem_dataVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

//			System.out.println(mem_dataVO.getMem_no());
//			System.out.println(mem_dataVO.getMem_grade());
//			System.out.println(mem_dataVO.getMem_acct());
//			System.out.println(mem_dataVO.getMem_pwd());
//			System.out.println(mem_dataVO.getMem_name());
//			System.out.println(mem_dataVO.getMen_phone());
//			System.out.println(mem_dataVO.getMem_mail());
//			System.out.println(mem_dataVO.getMem_img());
//			System.out.println(mem_dataVO.getMem_auth());
//			System.out.println(mem_dataVO.getReport_count());
//			System.out.println(mem_dataVO.getConsume_times());
//			System.out.println(mem_dataVO.getDeposit());	

			pstmt.setString(1, mem_dataVO.getMem_pwd());
			pstmt.setString(2, mem_dataVO.getMem_name());
			pstmt.setString(3, mem_dataVO.getMen_phone());
			pstmt.setString(4, mem_dataVO.getMem_mail());
			pstmt.setBytes(5, mem_dataVO.getMem_img());
			pstmt.setString(6, mem_dataVO.getMem_no());

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
	public void updateStatus(String mem_acct, Integer mem_auth) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UP_STATUS);

			pstmt.setInt(1, mem_auth);
			pstmt.setString(2, mem_acct);

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
	public void updateAuth(Mem_DataVO mem_dataVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_AUTH);

			pstmt.setInt(1, mem_dataVO.getMem_auth());
			pstmt.setString(2, mem_dataVO.getMem_no());

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
	public void delete(String MEM_NO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, MEM_NO);

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
	public Mem_DataVO findByPrimaryKey(String mem_no) {

		Mem_DataVO mem_dataVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// Mem_DataVO 也稱為 Domain objects
				mem_dataVO = new Mem_DataVO();
				mem_dataVO.setMem_no(rs.getString("mem_no"));
				mem_dataVO.setMem_grade(rs.getInt("mem_grade"));
				mem_dataVO.setMem_acct(rs.getString("mem_acct"));
				mem_dataVO.setMem_pwd(rs.getString("mem_pwd"));
				mem_dataVO.setMem_name(rs.getString("mem_name"));
				mem_dataVO.setMen_phone(rs.getString("men_phone"));
				mem_dataVO.setMem_mail(rs.getString("mem_mail"));
				mem_dataVO.setMem_img(rs.getBytes("mem_img"));
				mem_dataVO.setMem_auth(rs.getInt("mem_auth"));
				mem_dataVO.setReport_count(rs.getInt("report_count"));
				mem_dataVO.setConsume_times(rs.getInt("consume_times"));
				mem_dataVO.setDeposit(rs.getInt("deposit"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (Exception se) {
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
		return mem_dataVO;
	}

	@Override
	public Mem_DataVO findByAccount(String mem_acct) {
		Mem_DataVO mem_dataVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_ACCT);

			pstmt.setString(1, mem_acct);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// Mem_DataVO 也稱為 Domain objects
				mem_dataVO = new Mem_DataVO();
				mem_dataVO.setMem_no(rs.getString("mem_no"));
				mem_dataVO.setMem_grade(rs.getInt("mem_grade"));
				mem_dataVO.setMem_acct(rs.getString("mem_acct"));
				mem_dataVO.setMem_pwd(rs.getString("mem_pwd"));
				mem_dataVO.setMem_name(rs.getString("mem_name"));
				mem_dataVO.setMen_phone(rs.getString("men_phone"));
				mem_dataVO.setMem_mail(rs.getString("mem_mail"));
				mem_dataVO.setMem_img(rs.getBytes("mem_img"));
				mem_dataVO.setMem_auth(rs.getInt("mem_auth"));
				mem_dataVO.setReport_count(rs.getInt("report_count"));
				mem_dataVO.setConsume_times(rs.getInt("consume_times"));
				mem_dataVO.setDeposit(rs.getInt("deposit"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (Exception se) {
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
		return mem_dataVO;
	}

	@Override
	public Mem_DataVO findByAccNoImg(String mem_acct) {

		Mem_DataVO mem_dataVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_ACC_NOIMG);

			pstmt.setString(1, mem_acct);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// Mem_DataVO 也稱為 Domain objects
				mem_dataVO = new Mem_DataVO();
				mem_dataVO.setMem_no(rs.getString("mem_no"));
				mem_dataVO.setMem_grade(rs.getInt("mem_grade"));
				mem_dataVO.setMem_acct(rs.getString("mem_acct"));
				mem_dataVO.setMem_pwd(rs.getString("mem_pwd"));
				mem_dataVO.setMem_name(rs.getString("mem_name"));
				mem_dataVO.setMen_phone(rs.getString("men_phone"));
				mem_dataVO.setMem_mail(rs.getString("mem_mail"));
				mem_dataVO.setMem_auth(rs.getInt("mem_auth"));
				mem_dataVO.setReport_count(rs.getInt("report_count"));
				mem_dataVO.setConsume_times(rs.getInt("consume_times"));
				mem_dataVO.setDeposit(rs.getInt("deposit"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (Exception se) {
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
		return mem_dataVO;
	}

	@Override
	public List<Mem_DataVO> getAll() {
		List<Mem_DataVO> list = new ArrayList<Mem_DataVO>();
		Mem_DataVO mem_dataVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// Mem_DataVO 也稱為 Domain objects
				mem_dataVO = new Mem_DataVO();
				mem_dataVO.setMem_no(rs.getString("mem_no"));
				mem_dataVO.setMem_grade(rs.getInt("mem_grade"));
				mem_dataVO.setMem_acct(rs.getString("mem_acct"));
				mem_dataVO.setMem_pwd(rs.getString("mem_pwd"));
				mem_dataVO.setMem_name(rs.getString("mem_name"));
				mem_dataVO.setMen_phone(rs.getString("men_phone"));
				mem_dataVO.setMem_mail(rs.getString("mem_mail"));
				mem_dataVO.setMem_img(rs.getBytes("mem_img"));
				mem_dataVO.setMem_auth(rs.getInt("mem_auth"));
				mem_dataVO.setReport_count(rs.getInt("report_count"));
				mem_dataVO.setConsume_times(rs.getInt("consume_times"));
				mem_dataVO.setDeposit(rs.getInt("deposit"));
				list.add(mem_dataVO); // Store the row in the list
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

	public static void main(String[] args) {

		Mem_DataJDBCDAO dao = new Mem_DataJDBCDAO();

		// 新增
//		Mem_DataVO mem_dataVO1 = new Mem_DataVO();
//		mem_dataVO1.setMem_grade(11);
//		mem_dataVO1.setMem_acct("DAVID");
//		mem_dataVO1.setMem_pwd("12345");
//		mem_dataVO1.setMem_name("JAVA之神11");
//		mem_dataVO1.setMen_phone("0912345678");
//		mem_dataVO1.setMem_mail("alesayanson@gmail.com");
//		mem_dataVO1.setMem_img(null);
//		mem_dataVO1.setMem_auth(2);
//		mem_dataVO1.setReport_count(0);
//		mem_dataVO1.setConsume_times(10000);
//		mem_dataVO1.setDeposit(100000);
//		dao.insert(mem_dataVO1); 

		// 修改
		Mem_DataVO Mem_DataVO2 = new Mem_DataVO();
		Mem_DataVO2.setMem_no("MM00000019");
		Mem_DataVO2.setMem_grade(2);
		Mem_DataVO2.setMem_acct("吳永志");
		Mem_DataVO2.setMem_pwd("8888");
		Mem_DataVO2.setMem_name("java之神");
		Mem_DataVO2.setMen_phone("0988888888");
		Mem_DataVO2.setMem_mail("8888888@yahoo.com.tw");
		Mem_DataVO2.setMem_img(null);
		Mem_DataVO2.setMem_auth(2);
		Mem_DataVO2.setReport_count(0);
		Mem_DataVO2.setConsume_times(100);
		Mem_DataVO2.setDeposit(1000);
		dao.update(Mem_DataVO2);

		// 刪除
//		dao.delete("MM00000041");

//		 //查詢
//		Mem_DataVO Mem_DataVO3 = dao.findByPrimaryKey("MM00000019");
//		
//		if(Mem_DataVO3 == null) {
//			System.out.println("No Data");
//			return;
//		}
//		System.out.print(Mem_DataVO3.getMem_no() + ",");
//		System.out.print(Mem_DataVO3.getMem_grade() + ",");
//		System.out.print(Mem_DataVO3.getMem_acct() + ",");
//		System.out.print(Mem_DataVO3.getMem_pwd() + ",");
//		System.out.print(Mem_DataVO3.getMem_name() + ",");
//		System.out.print(Mem_DataVO3.getMen_phone() + ",");
//		System.out.print(Mem_DataVO3.getMem_mail()+ ",");
//		System.out.print(Mem_DataVO3.getMem_img()+ ",");
//		System.out.print(Mem_DataVO3.getMem_auth()+ ",");
//		System.out.print(Mem_DataVO3.getReport_count()+ ",");
//		System.out.print(Mem_DataVO3.getConsume_times()+ ",");
//		System.out.println(Mem_DataVO3.getDeposit());
//		System.out.println("---------------------");

		// 查詢NoImg
		Mem_DataVO Mem_DataVO3 = dao.findByAccNoImg("CEA101C2");

		if (Mem_DataVO3 == null) {
			System.out.println("No Data");
			return;
		}
		System.out.print(Mem_DataVO3.getMem_no() + ",");
		System.out.print(Mem_DataVO3.getMem_grade() + ",");
		System.out.print(Mem_DataVO3.getMem_acct() + ",");
		System.out.print(Mem_DataVO3.getMem_pwd() + ",");
		System.out.print(Mem_DataVO3.getMem_name() + ",");
		System.out.print(Mem_DataVO3.getMen_phone() + ",");
		System.out.print(Mem_DataVO3.getMem_mail() + ",");
		System.out.print(Mem_DataVO3.getMem_auth() + ",");
		System.out.print(Mem_DataVO3.getReport_count() + ",");
		System.out.print(Mem_DataVO3.getConsume_times() + ",");
		System.out.println(Mem_DataVO3.getDeposit());
		System.out.println("---------------------");

//		// 查詢
//		List<Mem_DataVO> list = dao.getAll();
//		for (Mem_DataVO aMem : list) {
//			System.out.print(aMem.getMem_no() + ",");
//			System.out.print(aMem.getMem_grade() + ",");
//			System.out.print(aMem.getMem_acct() + ",");
//			System.out.print(aMem.getMem_pwd() + ",");
//			System.out.print(aMem.getMem_name() + ",");
//			System.out.print(aMem.getMen_phone() + ",");
//			System.out.print(aMem.getMem_mail()+ ",");
//			System.out.print(aMem.getMem_img()+ ",");
//			System.out.print(aMem.getMem_auth()+ ",");
//			System.out.print(aMem.getReport_count()+ ",");
//			System.out.print(aMem.getConsume_times()+ ",");
//			System.out.print(aMem.getDeposit());
//			System.out.println();
//      }
	}

}