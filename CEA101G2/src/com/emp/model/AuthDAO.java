package com.emp.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AuthDAO implements AuthDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CEA101G2");
		} catch (NamingException e) {
		e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO AUTH_CATEGORY (AUTH_NO, AUTH_NAME) VALUES (('AU' || LPAD(AUTH_SEQ.NEXTVAL, 6, '0')),?)";
	private static final String UPDATE = "UPDATE AUTH_CATEGORY SET AUTH_NAME=? WHERE AUTH_NO = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM AUTH_CATEGORY ORDER BY AUTH_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM AUTH_CATEGORY WHERE AUTH_NO = ?";

	@Override
	public void insert(AuthVO authVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, authVO.getAuth_name());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.getMessage();
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
	public void update(AuthVO authVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, authVO.getAuth_name());
			pstmt.setString(2, authVO.getAuth_no());

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
	public AuthVO findByPK(String auth_no) {
		AuthVO authVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, auth_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				authVO = new AuthVO();
				authVO.setAuth_no(rs.getString("auth_no"));
				authVO.setAuth_name(rs.getString("auth_name"));

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
		return authVO;
	}

	@Override
	public List<AuthVO> getAll() {
		List<AuthVO> list = new ArrayList<AuthVO>();
		AuthVO authVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				authVO = new AuthVO();
				authVO.setAuth_no(rs.getString("auth_no"));
				authVO.setAuth_name(rs.getString("auth_name"));
				list.add(authVO);
			}
		} catch (SQLException se) {
			se.getMessage();
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
		AuthDAO dao = new AuthDAO();

		// 新增
//		AuthVO authVO1 = new AuthVO();
//		authVO1.setAuth_name("揪團");
//		dao.insert(authVO1);
//		
		// 更新
//		AuthVO authVO2 = new AuthVO();
//		authVO2.setAuth_no("AU000001");
//		authVO2.setAuth_name("揪團管理");
//		dao.update(authVO2);

		// getall
//		List<AuthVO> list = dao.getAll();
//		for(AuthVO auth :list) {
//			System.out.print(auth.getAuth_no() + ",");
//			System.out.print(auth.getAuth_name() + ",");
//		}
		
		//findPK
		AuthVO authVO3 = dao.findByPK("AU000002");
		System.out.println(authVO3.getAuth_no() + ",");
		System.out.println(authVO3.getAuth_name());
		

	}
}