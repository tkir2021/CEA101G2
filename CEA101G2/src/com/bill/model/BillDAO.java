package com.bill.model;

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

public class BillDAO implements BillDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CEA101G2");
		} catch (NamingException e) {
			e.printStackTrace();
		} 
	}

	private static final String INSERT_STMT = "INSERT INTO bill (bill_no, store_no, bill_price, bill_period) VALUES ('BL' || LPAD(BILL_SEQ.NEXTVAL, 8, '0'),?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT bill_no, store_no, bill_price, bill_date, bill_period FROM bill ORDER BY bill_no";
	private static final String GET_ONE_STMT = "SELECT bill_no, store_no, bill_price, bill_date, bill_period FROM bill WHERE bill_no = ?";
	private static final String DELETE = "DELETE FROM bill WHERE bill_no = ?";
	private static final String UPDATE = "UPDATE bill SET store_no=?, bill_price=?, bill_period=? WHERE bill_no=?";

	@Override
	public void insert(BillVO billVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, billVO.getStore_no());
			pstmt.setInt(2, billVO.getBill_price());
			pstmt.setString(3, billVO.getBill_period());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void update(BillVO billVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, billVO.getStore_no());
			pstmt.setInt(2, billVO.getBill_price());
			pstmt.setString(3, billVO.getBill_period());
			pstmt.setString(4, billVO.getBill_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
	public void delete(String bill_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, bill_no);

			pstmt.executeUpdate();

			// Handle any errors
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
	public BillVO findByPrimaryKey(String bill_no) {
		BillVO billVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, bill_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// billVO �]�٬� Domain objects
				billVO = new BillVO();
				billVO.setBill_no(rs.getString("bill_no"));
				billVO.setStore_no(rs.getString("store_no"));
				billVO.setBill_price(rs.getInt("bill_price"));
				billVO.setBill_date(rs.getTimestamp("bill_date"));
				billVO.setBill_period(rs.getString("bill_period"));
			}

			// Handle any errors
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
		return billVO;
	}

	@Override
	public List<BillVO> getAll() {
		List<BillVO> list = new ArrayList<BillVO>();
		BillVO billVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// BillVO �]�٬� Domain objects
				billVO = new BillVO();
				billVO.setBill_no(rs.getString("bill_no"));
				billVO.setStore_no(rs.getString("store_no"));
				billVO.setBill_price(rs.getInt("bill_price"));
				billVO.setBill_date(rs.getTimestamp("bill_date"));
				billVO.setBill_period(rs.getString("bill_period"));
				list.add(billVO); // Store the row in the list
			}

			// Handle any driver errors
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

		BillDAO dao = new BillDAO();
//		// �s�W
//		BillVO billVO1 = new BillVO();
//		billVO1.setStore_no("SM00000001");
//		billVO1.setBill_price(10000);
//		billVO1.setBill_period("10901");
//		dao.insert(billVO1);
//		
//		//�ק�
//		BillVO billVO2 = new BillVO();
//		billVO2.setBill_no("BL00000022");
//		billVO2.setStore_no("SM00000002");
//		billVO2.setBill_price(87000);
//		billVO2.setBill_period("10902");
//		dao.update(billVO2);

		// �R��
//		dao.delete("BL00000012");

//		//�d��
//		BillVO billVO3 = dao.findByPrimaryKey("BL00000013");
//		System.out.println(billVO3.getBill_no()+ ",");
//		System.out.println(billVO3.getStore_no()+ ",");
//		System.out.println(billVO3.getBill_price()+ ",");
//		System.out.println(billVO3.getBill_date()+ ",");
//		System.out.println(billVO3.getBill_period()+ ",");
//		System.out.println("---------------------");

//		// �d��
		List<BillVO> list = dao.getAll();
		for (BillVO aBill : list) {
			System.out.println(aBill.getBill_no() + ",");
			System.out.println(aBill.getStore_no() + ",");
			System.out.println(aBill.getBill_price() + ",");
			System.out.println(aBill.getBill_date() + ",");
			System.out.println(aBill.getBill_period() + ",");
			System.out.println("---------------------");
		}
	}

}
