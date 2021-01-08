package com.storereport.model;

import java.sql.*;
import java.util.*;

import com.memreport.model.MemReportVO;

public class StoreReportJDBCDAO implements StoreReportDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G2";
	String passwd = "CEA101G2";

	private static final String INSERT_STMT = "INSERT INTO STORE_REPORT (S_REPORT_NO, ORDER_NO, BOOKING_NO, REPORTED_STORE, REPROT_MEM, EMP_NO, REPORT_REASON, REPORT_TIME) VALUES (('SR' || LPAD(AUTH_SEQ.NEXTVAL, 8, '0')),?,?,?,?,?,?,?)";
	// UPDATE--將審核狀況為0(未審核)的更新(嚴重評分|是否成立|審核狀態)
	private static final String UPDATE = "UPDATE STORE_REPORT SET REPORT_GRADE=?, REPORT_STATUS=?, COMMIT_STATUS=? WHERE S_REPORT_no=?";
	// GETREPORT_BYSTORED--查詢某一店家會員所有的檢舉列表
	private static final String GETREPORT_BYSTROENO = "SELECT S_REPORT_NO,ORDER_NO,BOOKING_NO,REPORTED_STORE,REPORT_MEM,EMP_NO,REPORT_REASON,REPORT_TIME,REPORT_GRADE,REPORT_STATUS,COMMIT_STATUS FROM STORE_REPORT WHERE REPORTED_STORE= ?ORDER BY S_REPORT_NO";
	private static final String GETREPORT_BYSRNO = "SELECT S_REPORT_NO,ORDER_NO,BOOKING_NO,REPORTED_STORE,REPORT_MEM,EMP_NO,REPORT_REASON,REPORT_TIME,REPORT_GRADE,REPORT_STATUS,COMMIT_STATUS FROM STORE_REPORT WHERE S_REPORT_NO= ?";
	private static final String GET_ALL_STMT = "SELECT * FROM STORE_REPORT";

	@Override
	public void insert(StoreReportVO srVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, srVO.getOrder_no());
			pstmt.setString(2, srVO.getBooking_no());
			pstmt.setString(3, srVO.getReported_store());
			pstmt.setString(4, srVO.getReport_mem());
			pstmt.setString(5, srVO.getEmp_no());
			pstmt.setString(6, srVO.getReport_reason());
			pstmt.setDate(7, srVO.getReport_time());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
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
	public void update(StoreReportVO srVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, srVO.getReport_grade());
			pstmt.setInt(2, srVO.getReport_status());
			pstmt.setInt(3, srVO.getCommit_status());
			pstmt.setString(4, srVO.getS_report_no());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
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
	public Set<StoreReportVO> findByStoreno(String store_no) {
		Set<StoreReportVO> set = new LinkedHashSet<StoreReportVO>();
		StoreReportVO srVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GETREPORT_BYSTROENO);
			pstmt.setString(1, store_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				srVO = new StoreReportVO();
				srVO.setS_report_no(rs.getString("s_report_no"));
				srVO.setOrder_no(rs.getString("order_no"));
				srVO.setBooking_no(rs.getString("booking_no"));
				srVO.setReported_store(rs.getString("reported_store"));
				srVO.setReport_mem(rs.getString("report_mem"));
				srVO.setEmp_no(rs.getString("emp_no"));
				srVO.setReport_reason(rs.getString("report_reason"));
				srVO.setReport_time(rs.getDate("report_time"));
				srVO.setReport_grade(rs.getInt("report_grade"));
				srVO.setReport_status(rs.getInt("report_status"));
				srVO.setCommit_status(rs.getInt("commit_status"));
				set.add(srVO);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
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
		return set;
	}

	@Override
	public List<StoreReportVO> getAll() {
		List<StoreReportVO> list = new ArrayList<StoreReportVO>();
		StoreReportVO srVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				srVO = new StoreReportVO();
				srVO.setS_report_no(rs.getString("s_report_no"));
				srVO.setOrder_no(rs.getString("order_no"));
				srVO.setBooking_no(rs.getString("booking_no"));
				srVO.setReported_store(rs.getString("reported_store"));
				srVO.setReport_mem(rs.getString("report_mem"));
				srVO.setEmp_no(rs.getString("emp_no"));
				srVO.setReport_reason(rs.getString("report_reason"));
				srVO.setReport_time(rs.getDate("report_time"));
				srVO.setReport_grade(rs.getInt("report_grade"));
				srVO.setReport_status(rs.getInt("report_status"));
				srVO.setCommit_status(rs.getInt("commit_status"));
				list.add(srVO);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
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

	
	public StoreReportVO findBySRno(String s_report_no) {
		StoreReportVO srVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GETREPORT_BYSRNO);

			pstmt.setString(1, s_report_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				srVO = new StoreReportVO();
				srVO.setS_report_no(rs.getString("s_report_no"));
				srVO.setOrder_no(rs.getString("order_no"));
				srVO.setBooking_no(rs.getString("booking_no"));
				srVO.setReported_store(rs.getString("reported_store"));
				srVO.setReport_mem(rs.getString("report_mem"));
				srVO.setEmp_no(rs.getString("emp_no"));
				srVO.setReport_reason(rs.getString("report_reason"));
				srVO.setReport_time(rs.getDate("report_time"));
				srVO.setReport_grade(rs.getInt("report_grade"));
				srVO.setReport_status(rs.getInt("report_status"));
				srVO.setCommit_status(rs.getInt("commit_status"));
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
		return srVO;
	}
	public static void main(String[] args) {
		StoreReportJDBCDAO dao = new StoreReportJDBCDAO();
		
		//insert
//		StoreReportVO srVO1 = new StoreReportVO();
//		srVO1.setOrder_no("OM00000001");
//		srVO1.setBooking_no("BO00000001");
//		srVO1.setReported_store("SM00000001");
//		srVO1.setReport_men("");
//		srVO1.setEmp_no("");
//		srVO1.setReport_reason("");
//		srVO1.setReport_time(java.sql.Date.valueOf("2020-12-02"));
//		srVO1.setReport_grade(10);
//		srVO1.setReport_status(0);
//		srVO1.setCommit_status(0);
//		dao.insert(srVO1);
		
		
		//更新
		StoreReportVO srVO2 = new StoreReportVO();
		srVO2.setS_report_no("SR00000001");
		srVO2.setReport_grade(10);
		srVO2.setReport_status(1);
		srVO2.setCommit_status(1);
		dao.update(srVO2);
		
		//getall
//		List<StoreReportVO> list = dao.getAll();
//		for(StoreReportVO aStore : list) {
//			System.out.print(aStore.getS_report_no()+",");
//			System.out.print(aStore.getBooking_no()+",");
//			System.out.print(aStore.getOrder_no()+",");
//			System.out.print(aStore.getReported_store()+",");
//			System.out.print(aStore.getReport_mem()+",");
//			System.out.print(aStore.getEmp_no()+",");
//			System.out.print(aStore.getReport_reason()+",");
//			System.out.print(aStore.getReport_time()+",");
//			System.out.print(aStore.getReport_grade()+",");
//			System.out.print(aStore.getReport_status()+",");
//			System.out.println(aStore.getCommit_status());
//		}
		
		//findByStoreno
//		Set<StoreReportVO> set = dao.findByStoreno("SM00000001");
//		for(StoreReportVO aStore :set) {
//			System.out.print(aStore.getS_report_no()+",");
//			System.out.print(aStore.getBooking_no()+",");
//			System.out.print(aStore.getOrder_no()+",");
//			System.out.print(aStore.getReported_store()+",");
//			System.out.print(aStore.getReport_mem()+",");
//			System.out.print(aStore.getEmp_no()+",");
//			System.out.print(aStore.getReport_reason()+",");
//			System.out.print(aStore.getReport_time()+",");
//			System.out.print(aStore.getReport_grade()+",");
//			System.out.print(aStore.getReport_status()+",");
//			System.out.print(aStore.getCommit_status());
//			System.out.println();
//		}
//		
		
		
//		findByS_report_no
//		StoreReportVO srVO = dao.findBySRno("SR00000001");
//			System.out.print(srVO.getS_report_no()+",");
//			System.out.print(srVO.getBooking_no()+",");
//			System.out.print(srVO.getOrder_no()+",");
//			System.out.print(srVO.getReported_store()+",");
//			System.out.print(srVO.getReport_mem()+",");
//			System.out.print(srVO.getEmp_no()+",");
//			System.out.print(srVO.getReport_reason()+",");
//			System.out.print(srVO.getReport_time()+",");
//			System.out.print(srVO.getReport_grade()+",");
//			System.out.print(srVO.getReport_status()+",");
//			System.out.print(srVO.getCommit_status());
//			System.out.println();
		}
	}

