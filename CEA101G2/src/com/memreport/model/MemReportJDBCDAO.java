package com.memreport.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.emp.model.EmpVO;

public class MemReportJDBCDAO implements MemReportDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G2";
	String passwd = "CEA101G2";

	private static final String INSERT_STMT = "INSERT INTO MEM_REPORT (M_REPORT_NO,GROUP_NO,REPORTED_MEM,REPORT_MEM,REPORT_REASON) VALUES ('MR' || LPAD(M_REPORT_SEQ.NEXTVAL, 8, '0'),?,?,?,?)";
	// UPDATE--將審核狀況為0(未審核)的更新(是否成立|審核狀態)
	private static final String UPDATE = "UPDATE MEM_REPORT SET REPORT_STATUS=?, COMMIT_STATUS=? WHERE M_REPORT_NO=?";
	// GETREPORT_BYSTORED--查詢某一會員所有的檢舉列表
	private static final String GETREPORT_BYMEMNO = "SELECT M_REPORT_NO,GROUP_NO,REPORTED_MEM,REPORT_MEM,EMP_NO,REPORT_REASON,REPORT_TIME,REPORT_STATUS,COMMIT_STATUS FROM MEM_REPORT WHERE REPORTED_MEM = ? ORDER BY M_REPORT_NO";
	// GETREPORT_BYSTORED--查詢某一筆檢舉記錄
	private static final String GETREPORT_BYMRNO = "SELECT M_REPORT_NO,GROUP_NO,REPORTED_MEM,REPORT_MEM,EMP_NO,REPORT_REASON,REPORT_TIME,REPORT_STATUS,COMMIT_STATUS FROM MEM_REPORT WHERE M_REPORT_NO = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM MEM_REPORT";

	@Override
	public void insert(MemReportVO mrVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, mrVO.getGroup_no());
			pstmt.setString(2, mrVO.getReported_mem());
			pstmt.setString(3, mrVO.getReport_mem());
			pstmt.setString(4, mrVO.getReport_reason());
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
	public void update(MemReportVO mrVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, mrVO.getReport_status());
			pstmt.setInt(2, mrVO.getCommit_status());
			pstmt.setString(3, mrVO.getM_report_no());

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
	public Set<MemReportVO> findByMemno(String mem_no) {
		Set<MemReportVO> set = new LinkedHashSet<MemReportVO>();
		MemReportVO mrVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GETREPORT_BYMEMNO);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mrVO = new MemReportVO();
				mrVO.setM_report_no(rs.getString("m_report_no"));
				mrVO.setGroup_no(rs.getString("group_no"));
				mrVO.setReported_mem(rs.getString("reported_mem"));
				mrVO.setReport_mem(rs.getString("report_mem"));
				mrVO.setEmp_no(rs.getString("emp_no"));
				mrVO.setReport_reason(rs.getString("report_reason"));
				mrVO.setReport_time(rs.getDate("report_time"));
				mrVO.setReport_status(rs.getInt("report_status"));
				mrVO.setCommit_status(rs.getInt("commit_status"));
				set.add(mrVO);
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
	public List<MemReportVO> getAll() {
		List<MemReportVO> list = new ArrayList<MemReportVO>();
		MemReportVO srVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				srVO = new MemReportVO();
				srVO.setM_report_no(rs.getString("m_report_no"));
				srVO.setGroup_no(rs.getString("group_no"));
				srVO.setReported_mem(rs.getString("reported_mem"));
				srVO.setReport_mem(rs.getString("report_mem"));
				srVO.setEmp_no(rs.getString("emp_no"));
				srVO.setReport_reason(rs.getString("report_reason"));
				srVO.setReport_time(rs.getDate("report_time"));
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

	public MemReportVO findByMRno(String m_report_no) {
		MemReportVO mrVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GETREPORT_BYMRNO);

			pstmt.setString(1, m_report_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mrVO = new MemReportVO();
				mrVO.setM_report_no(rs.getString("m_report_no"));
				mrVO.setGroup_no(rs.getString("group_no"));
				mrVO.setReported_mem(rs.getString("reported_mem"));
				mrVO.setReport_mem(rs.getString("report_mem"));
				mrVO.setEmp_no(rs.getString("emp_no"));
				mrVO.setReport_reason(rs.getString("report_reason"));
				mrVO.setReport_time(rs.getDate("report_time"));
				mrVO.setReport_status(rs.getInt("report_status"));
				mrVO.setCommit_status(rs.getInt("commit_status"));
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
		return mrVO;
	}

	public static void main(String[] args) {
		MemReportJDBCDAO dao = new MemReportJDBCDAO();
		// insert
		MemReportVO mrVO1 = new MemReportVO();
		mrVO1.setGroup_no("GP00000001");
		mrVO1.setReported_mem("MM00000001");
		mrVO1.setReport_mem("MM00000002");
		mrVO1.setReport_reason("揪的什麼團 主題與現實不符阿。");
		
		dao.insert(mrVO1);

		// 更新
//		MemReportVO mrVO2 = new MemReportVO();
//		mrVO2.setReport_status(1);
//		mrVO2.setCommit_status(1);
//		dao.update(mrVO2);
//		
		// getall
//		List<MemReportVO> list = dao.getAll();
//		for(MemReportVO aMem : list) {
//			System.out.print(aMem.getM_report_no()+",");
//			System.out.print(aMem.getGroup_no()+",");
//			System.out.print(aMem.getReported_mem()+",");
//			System.out.print(aMem.getReport_mem()+",");
//			System.out.print(aMem.getEmp_no()+",");
//			System.out.print(aMem.getReport_reason()+",");
//			System.out.print(aMem.getReport_time()+",");
//			System.out.print(aMem.getReport_status()+",");
//			System.out.println(aMem.getCommit_status());
//		}

		// findByStoreno
//		Set<MemReportVO> set = dao.findByMemno("MM00000002");
//		for (MemReportVO aMem : set) {
//			System.out.print(aMem.getM_report_no() + ",");
//			System.out.print(aMem.getGroup_no() + ",");
//			System.out.print(aMem.getReported_mem() + ",");
//			System.out.print(aMem.getReport_mem() + ",");
//			System.out.print(aMem.getEmp_no() + ",");
//			System.out.print(aMem.getReport_reason() + ",");
//			System.out.print(aMem.getReport_time() + ",");
//			System.out.print(aMem.getReport_status() + ",");
//			System.out.print(aMem.getCommit_status());
//			System.out.println();
		}
	}

