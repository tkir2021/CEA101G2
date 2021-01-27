package com.deposit.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class  DepositDAO implements DepositDAO_interface {
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
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
		"INSERT INTO DEPOSIT (DEPOSIT_NO,MEM_NO,CHARGE) VALUES (('D' || LPAD(DEPOSIT_SEQ.NEXTVAL, 9, '0')), ?, ?)";
	private static final String GET_ALL_STMT = "SELECT DEPOSIT_NO,MEM_NO,CHARGE, CHARGE_TIME FROM DEPOSIT order by DEPOSIT_NO";
	private static final String GET_ONE_STMT = "SELECT DEPOSIT_NO,MEM_NO,CHARGE, CHARGE_TIME FROM DEPOSIT where Deposit_no = ?";
	/****************取得會員儲存金記錄 by Sheng*********************/
	private static final String GET_MEM_ONE_STMT = "SELECT DEPOSIT_NO,MEM_NO,CHARGE, CHARGE_TIME FROM DEPOSIT WHERE MEM_NO = ?";
	/****************取得會員儲存金記錄 by Sheng*********************/
	private static final String DELETE = 
		"DELETE FROM DEPOSIT where DEPOSIT_NO = ?";
	private static final String UPDATE = 
		"UPDATE DEPOSIT set MEM_NO=?, CHARGE=? where DEPOSIT_NO = ?";

	@Override
	public void insert(DepositVO depositVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, depositVO.getMem_no());
			pstmt.setInt(2, depositVO.getCharge());
			
			
			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public void update(DepositVO depositVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, depositVO.getMem_no());
			pstmt.setInt(2, depositVO.getCharge());
			pstmt.setString(3, depositVO.getDeposit_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public void delete(String Deposit_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, Deposit_no);

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
	public DepositVO findByPrimaryKey(String Deposit_no) {

		DepositVO depositVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, Deposit_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// DepositVo 也稱為 Domain objects
				depositVO = new DepositVO();
				depositVO.setDeposit_no(rs.getString("DEPOSIT_NO"));
				depositVO.setMem_no(rs.getString("MEM_NO"));
				depositVO.setCharge(rs.getInt("CHARGE"));
				depositVO.setCharge_time(rs.getTimestamp("CHARGE_TIME"));
				
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
		return depositVO;
	}
	
	/****************取得會員儲存金記錄 by Sheng*********************/
	@Override
	public List<DepositVO> findByMem_no(String mem_no) {
		List<DepositVO> list = new ArrayList<DepositVO>();
		DepositVO depositVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MEM_ONE_STMT);

			pstmt.setString(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// DepositVo 也稱為 Domain objects
				depositVO = new DepositVO();
				depositVO.setDeposit_no(rs.getString("DEPOSIT_NO"));
				depositVO.setMem_no(rs.getString("MEM_NO"));
				depositVO.setCharge(rs.getInt("CHARGE"));
				depositVO.setCharge_time(rs.getTimestamp("CHARGE_TIME"));
				list.add(depositVO);
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
	/****************取得會員儲存金記錄 by Sheng*********************/
	

	@Override
	public List<DepositVO> getAll() {
		List<DepositVO> list = new ArrayList<DepositVO>();
		DepositVO depositVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// DepositVO 也稱為 Domain objects
				depositVO = new DepositVO();
				depositVO.setDeposit_no(rs.getString("DEPOSIT_NO"));
				depositVO.setMem_no(rs.getString("MEM_NO"));
				depositVO.setCharge(rs.getInt("CHARGE"));
				depositVO.setCharge_time(rs.getTimestamp("CHARGE_TIME"));
				list.add(depositVO); // Store the row in the list
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

		DepositDAO dao = new DepositDAO();

		// 新增
//		DepositVO depositVO1 = new DepositVO();
//		depositVO1.setMem_no("MM00000001");
//		depositVO1.setCharge(2000);
//		dao.insert(depositVO1);


		// 修改
//		DepositVO depositVO2 = new DepositVO();
//		depositVO2.setDeposit_no("D00000006");
//		depositVO2.setMem_no("MM00000001");
//		depositVO2.setCharge(10000);
//	
//		dao.update(depositVO2);

		// 刪除
		dao.delete("D00000006");

		// 查詢
//		DepositVO depositVO3 = dao.findByPrimaryKey("D00000030");
//		System.out.print(depositVO3.getDeposit_no() + ",");
//		System.out.print(depositVO3.getMem_no() + ",");
//		System.out.print(depositVO3.getCharge() + ",");
//		System.out.print(depositVO3.getCharge_time() );
//		System.out.println("---------------------");

		// 查詢
		List<DepositVO> list = dao.getAll();
		for (DepositVO aDeposit : list) {
			System.out.print(aDeposit.getDeposit_no() + ",");
			System.out.print(aDeposit.getMem_no() + ",");
			System.out.print(aDeposit.getCharge() + ",");
			System.out.print(aDeposit.getCharge_time() );
			System.out.println();
		}
	}

	
}