package com.emp.model;
import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class EmpDAO implements EmpDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CEA101G2");
		} catch (NamingException e) {
		e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO EMP_DATA (EMP_NO, EMP_PWD, EMP_NAME, EMP_DATE, EMAIL) VALUES (('EM' || LPAD(EMP_SEQ.NEXTVAL, 8, '0')),?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE EMP_DATA SET EMP_NAME=?, EMP_DATE=?, EMAIL=?, EMP_STATUS=? WHERE EMP_NO = ?";//, EMP_IMG=?
	private static final String GET_ONE_STMT = "SELECT EMP_NO,EMP_NAME, EMP_DATE, EMAIL, EMP_STATUS FROM EMP_DATA WHERE EMP_NO = ?";
	private static final String GET_ALL_STMT = "SELECT EMP_NO,EMP_NAME, EMP_DATE, EMAIL, EMP_STATUS FROM EMP_DATA ORDER BY EMP_NO";
	//員工登入用
	private static final String SEARCHEMP = "SELECT * FROM EMP_DATA WHERE EMP_NO = ? AND EMP_PWD=?";
	
	@Override
	public void insert(EmpVO empVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
		
			pstmt.setString(1,empVO.getEmp_pwd());
			pstmt.setString(2, empVO.getEmp_name());
			pstmt.setDate(3, empVO.getEmp_date());
			pstmt.setString(4, empVO.getEmp_mail());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.getMessage();
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
	public void update(EmpVO empVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, empVO.getEmp_name());
			pstmt.setDate(2, empVO.getEmp_date());
			pstmt.setString(3, empVO.getEmp_mail());
			pstmt.setInt(4, empVO.getEmp_status());
			pstmt.setString(5, empVO.getEmp_no());

			pstmt.executeUpdate();

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
	public EmpVO findByPK(String emp_no) {
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, emp_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				empVO = new EmpVO();
				empVO.setEmp_no(rs.getString("emp_no"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setEmp_mail(rs.getString("email"));
				empVO.setEmp_date(rs.getDate("emp_date"));
				empVO.setEmp_status(rs.getInt("emp_status"));
			}

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
		return empVO;
	}

	@Override
	public List<EmpVO> getAll() {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				empVO = new EmpVO();
				empVO.setEmp_no(rs.getString("emp_no"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setEmp_mail(rs.getString("email"));
				empVO.setEmp_date(rs.getDate("emp_date"));
				//empVO.setEmp_img((BLOB) rs.getBlob("emp_img"));
				empVO.setEmp_status(rs.getInt("emp_status"));
				list.add(empVO);
			}
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

	//新增for員工登入用
	public boolean searchEmp(String emp_no, String emp_pwd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(SEARCHEMP);
			pstmt.setString(1,emp_no);
			pstmt.setString(2, emp_pwd);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
		}  catch(SQLException se) {
			
		}finally {
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
		return false;		
	}
	
	public static void main(String[] args) {
		EmpDAO dao = new EmpDAO();
//		 add()
//		EmpVO empVO1 = new EmpVO();
//		empVO1.setEmp_name("Allen");
//		empVO1.setEmp_pwd(empVO1.getEmp_pwd());
//		empVO1.setEmp_date(java.sql.Date.valueOf("2020-01-01"));
//		empVO1.setEmp_img(null);
//		empVO1.setEmp_mail("i74524@gmail.com");
//
//		dao.insert(empVO1);

//		// getall()
//		List<EmpVO> list = dao.getAll();
//		for (EmpVO aEmp : list) {
//			System.out.print(aEmp.getEmp_no() + ",");
//			System.out.print(aEmp.getEmp_pwd() + ",");
//			System.out.print(aEmp.getEmp_name() + ",");
//			System.out.print(aEmp.getEmp_date() + ",");
//			//System.out.print(aEmp.getEmp_img() + ",");
//			System.out.print(aEmp.getEmp_status() + ",");
//		}

		// update()
//		EmpVO empVO2 = new EmpVO();
//		empVO2.setEmp_no("EM00000003");
//		empVO2.setEmp_pwd("12345678");
//		empVO2.setEmp_name("���j��");
//		empVO2.setEmp_date(java.sql.Date.valueOf("2020-02-28"));
//		//empVO2.setEmp_img(null);
//		empVO2.setEmp_status(0);
//		dao.update(empVO2);

//		// findPK()
//		EmpVO aEmp = dao.findByPK("EM00000001");
//		System.out.print(aEmp.getEmp_no() + ",");
//		System.out.print(aEmp.getEmp_name() + ",");
//		System.out.print(aEmp.getEmp_date() + ",");
//		//System.out.print(aEmp.getEmp_img() + ",");
//		System.out.print(aEmp.getEmp_status());
//		System.out.println();
		
		//searchemp
		boolean sEmp = dao.searchEmp("em00000001", "pw000000");
		System.out.println(sEmp);
	}
}
