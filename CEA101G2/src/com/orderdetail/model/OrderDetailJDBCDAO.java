package com.orderdetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailJDBCDAO implements OrderDetailDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G2";
	String passwd = "CEA101G2";

	private static final String INSERT_STMT = "INSERT INTO ORDER_DETAIL(order_no, food_no, food_scale, quantity, food_price, total) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE ORDER_DETAIL set food_no=?, food_scale=?, quantity=?, food_price=?, total=? where order_no=?";
	private static final String DELETE = "DELETE FROM ORDER_DETAIL where order_no = ?";
	private static final String GET_ONE_STMT = "SELECT order_no, food_no, food_scale, quantity, food_price, total FROM ORDER_DETAIL where order_no = ?";
	private static final String GET_ALL_STMT = "SELECT order_no, food_no, food_scale, quantity, food_price, total FROM ORDER_DETAIL order by order_no";
	
	@Override
	public void insert(OrderDetailVO orderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, orderDetailVO.getOrder_no());
			pstmt.setString(2, orderDetailVO.getFood_no());
			pstmt.setString(3, orderDetailVO.getFood_scale());
			pstmt.setInt(4, orderDetailVO.getQuantity());
			pstmt.setInt(5, orderDetailVO.getFood_price());
			pstmt.setInt(6, orderDetailVO.getTotal());
			pstmt.executeUpdate();

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
	public void update(OrderDetailVO orderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, orderDetailVO.getFood_no());
			pstmt.setString(2, orderDetailVO.getFood_scale());
			pstmt.setInt(3, orderDetailVO.getQuantity());
			pstmt.setInt(4, orderDetailVO.getFood_price());
			pstmt.setInt(5, orderDetailVO.getTotal());
			pstmt.setString(6, orderDetailVO.getOrder_no());

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
	
	 /************************購物車：新增訂餐明細 by Sheng*************************/
	@Override
	public void updateByShopping(OrderDetailVO orderDetailVO, Connection con) {
		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, orderDetailVO.getOrder_no());
			pstmt.setString(2, orderDetailVO.getFood_no());
			pstmt.setString(3, orderDetailVO.getFood_scale());
			pstmt.setInt(4, orderDetailVO.getQuantity());
			pstmt.setInt(5, orderDetailVO.getFood_price());
			pstmt.setInt(6, orderDetailVO.getTotal());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back from OrderDetail");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "	+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			
		}
		
		
	}
	 /************************購物車：新增訂餐明細 by Sheng*************************/
	
	
	@Override
	public void delete(String order_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, order_no);

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
	public List<OrderDetailVO> findByPrimaryKey(String order_no) {

		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO orderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, order_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrder_no(rs.getString("order_no"));
				orderDetailVO.setFood_no(rs.getString("food_no"));
				orderDetailVO.setFood_scale(rs.getString("food_scale"));
				orderDetailVO.setQuantity(rs.getInt("quantity"));
				orderDetailVO.setFood_price(rs.getInt("food_price"));
				orderDetailVO.setTotal(rs.getInt("total"));
				list.add(orderDetailVO);
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

	@Override
	public List<OrderDetailVO> getAll() {
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO orderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrder_no(rs.getString("order_no"));
				orderDetailVO.setFood_no(rs.getString("food_no"));
				orderDetailVO.setFood_scale(rs.getString("food_scale"));
				orderDetailVO.setQuantity(rs.getInt("quantity"));
				orderDetailVO.setFood_price(rs.getInt("food_price"));
				orderDetailVO.setTotal(rs.getInt("total"));
				list.add(orderDetailVO);
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

		OrderDetailJDBCDAO dao = new OrderDetailJDBCDAO();

		// 新增
//		OrderDetailVO orderDetailVO1 = new OrderDetailVO();
//		orderDetailVO1.setOrder_no("OM00000002");
//		orderDetailVO1.setFood_no("FD00000004");
//		orderDetailVO1.setFood_scale("12342");
//		orderDetailVO1.setQuantity(12134);
//		orderDetailVO1.setFood_price(2134);
//		orderDetailVO1.setTotal(321);
//
//		dao.insert(orderDetailVO1);

		// 修改
//		OrderDetailVO orderDetailVO2 = new OrderDetailVO();
//		orderDetailVO2.setOrder_no("OM00000003");
//		orderDetailVO2.setFood_no("FD00000001");
//		orderDetailVO2.setFood_scale("12345");
//		orderDetailVO2.setQuantity(12134);
//		orderDetailVO2.setFood_price(2134);
//		orderDetailVO2.setTotal(322);
//		
//		dao.update(orderDetailVO2);

		// 刪除
//		dao.delete("OM00000005");

		// 查詢
//		List<OrderDetailVO> list = dao.findByPrimaryKey("OM00000001");
//		for (OrderDetailVO aOrd1 : list) {
//			System.out.print(aOrd1.getOrder_no() + ",");
//			System.out.print(aOrd1.getFood_no() + ",");
//			System.out.print(aOrd1.getFood_scale() + ",");
//			System.out.print(aOrd1.getQuantity() + ",");
//			System.out.print(aOrd1.getFood_price() + ",");
//			System.out.println(aOrd1.getTotal());
//			System.out.println("---------------------");
//		}
		
		// 查詢
//		List<OrderDetailVO> list = dao.getAll();
//		for (OrderDetailVO aOrd1 : list) {
//			System.out.print(aOrd1.getOrder_no() + ",");
//			System.out.print(aOrd1.getFood_no() + ",");
//			System.out.print(aOrd1.getFood_scale() + ",");
//			System.out.print(aOrd1.getQuantity() + ",");
//			System.out.print(aOrd1.getFood_price() + ",");
//			System.out.println(aOrd1.getTotal());
//			System.out.println("---------------------");
//		}
	}

	
}
