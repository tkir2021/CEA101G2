package com.ordermaster.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.orderdetail.model.OrderDetailService;
import com.orderdetail.model.OrderDetailVO;

public class OrderMasterJDBCDAO implements OrderMasterDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G2";
	String passwd = "CEA101G2";

	private static final String INSERT_STMT = "INSERT INTO ORDER_MASTER(order_no, mem_no, store_no, sale_no, order_date, pay_type, order_total, sale_percent, discount, order_status, take_status, give_star) VALUES (('OM' || LPAD(ORDERMASTER_SEQ.NEXTVAL, 8, '0')), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE ORDER_MASTER set mem_no=?, store_no=?, sale_no=?, order_date=?, pay_type=?, order_total=?, sale_percent=?, discount=?, order_status=?, take_status=?, give_star=? where order_no = ?";
	private static final String DELETE = "DELETE FROM ORDER_MASTER where order_no = ?";
	private static final String GET_ONE_STMT = "SELECT order_no, mem_no, store_no, sale_no, order_date, pay_type, order_total, sale_percent, discount, order_status, take_status, give_star FROM ORDER_MASTER where order_no = ?";
	private static final String GET_ALL_STMT = "SELECT order_no, mem_no, store_no, sale_no, order_date, pay_type, order_total, sale_percent, discount, order_status, take_status, give_star FROM ORDER_MASTER order by order_no";
	
	/************************取得訂餐資料 by Sheng*************************/
	private static final String GET_STORE_OM = "SELECT order_no, mem_no, store_no, sale_no, order_date, pay_type, order_total, sale_percent, discount, order_status, take_status, give_star FROM ORDER_MASTER WHERE store_no = ?";
	private static final String GET_MEM_OM = "SELECT order_no, mem_no, store_no, sale_no, order_date, pay_type, order_total, sale_percent, discount, order_status, take_status, give_star FROM ORDER_MASTER WHERE mem_no = ?";
	/************************取得訂餐資料 by Sheng*************************/
	
	/************************更新評分 by Sheng*************************/
	private static final String UP_OM_GIVE_STAR = "UPDATE ORDER_MASTER SET give_star=? WHERE order_no = ?";
	/************************更新評分 by Sheng*************************/
	
	
	
	@Override
	public void insert(OrderMasterVO orderMasterVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, orderMasterVO.getMem_no());
			pstmt.setString(2, orderMasterVO.getStore_no());
			pstmt.setString(3, orderMasterVO.getSale_no());
			pstmt.setDate(4, orderMasterVO.getOrder_date());
			pstmt.setInt(5, orderMasterVO.getPay_type());
			pstmt.setInt(6, orderMasterVO.getOrder_total());
			pstmt.setFloat(7, orderMasterVO.getSale_percent());
			pstmt.setFloat(8, orderMasterVO.getDiscount());
			pstmt.setString(9, orderMasterVO.getOrder_status());
			pstmt.setString(10, orderMasterVO.getTake_status());
			pstmt.setFloat(11, orderMasterVO.getGive_star());

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
	public void update(OrderMasterVO orderMasterVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, orderMasterVO.getMem_no());
			pstmt.setString(2, orderMasterVO.getStore_no());
			pstmt.setString(3, orderMasterVO.getSale_no());
			pstmt.setDate(4, orderMasterVO.getOrder_date());
			pstmt.setInt(5, orderMasterVO.getPay_type());
			pstmt.setInt(6, orderMasterVO.getOrder_total());
			pstmt.setFloat(7, orderMasterVO.getSale_percent());
			pstmt.setFloat(8, orderMasterVO.getDiscount());
			pstmt.setString(9, orderMasterVO.getOrder_status());
			pstmt.setString(10, orderMasterVO.getTake_status());
			pstmt.setFloat(11, orderMasterVO.getGive_star());
			pstmt.setString(12, orderMasterVO.getOrder_no());

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

	
	
	/************************購物車：新增訂餐主檔 by Sheng*************************/
	@Override
	public void updateByShopping(OrderMasterVO orderMasterVO, List<OrderDetailVO> list, Connection con) {
		
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(url, userid, passwd);
			
			// 先新訂餐主檔
			String cols[] = {"ORDER_NO"};			
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, orderMasterVO.getMem_no());
			pstmt.setString(2, orderMasterVO.getStore_no());
			pstmt.setString(3, orderMasterVO.getSale_no());
			pstmt.setDate(4, orderMasterVO.getOrder_date());
			pstmt.setInt(5, orderMasterVO.getPay_type());
			pstmt.setInt(6, orderMasterVO.getOrder_total());
			pstmt.setFloat(7, orderMasterVO.getSale_percent());
			pstmt.setFloat(8, orderMasterVO.getDiscount());
			pstmt.setString(9, orderMasterVO.getOrder_status());
			pstmt.setString(10, orderMasterVO.getTake_status());
			pstmt.setFloat(11, orderMasterVO.getGive_star());
			
			pstmt.executeUpdate();

			
			String next_order_no = null;
			ResultSet rs = pstmt.getGeneratedKeys(); //取出綁定資料庫自增主鍵值	
			if (rs.next()) {
				next_order_no = rs.getString(1);
				System.out.println("自增主鍵值= " + next_order_no +"(剛新增成功的部門編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			
			
			//再同時新增訂餐明細
			OrderDetailService orderDetailSvc = new OrderDetailService();
			for (OrderDetailVO aODVO : list) {
				aODVO.setOrder_no(next_order_no) ;
				orderDetailSvc.updateByShopping(aODVO,con);
			}
			
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back from OrderMaster");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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
			
		}
	}

	/************************購物車：新增訂餐主檔 by Sheng*************************/
	
	
	
	/************************更新評分 by Sheng**************************/
	@Override
	public void upGivestar(String order_no, double om_givestar) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UP_OM_GIVE_STAR);
			
			pstmt.setDouble(1, om_givestar);
			pstmt.setString(2, order_no);

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
	
	/************************更新評分 by Sheng**************************/
	
	
	
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
	public OrderMasterVO findByPrimaryKey(String order_no) {

		OrderMasterVO orderMasterVO = null;
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
				orderMasterVO = new OrderMasterVO();
				orderMasterVO.setOrder_no(rs.getString("order_no"));
				orderMasterVO.setMem_no(rs.getString("mem_no"));
				orderMasterVO.setStore_no(rs.getString("store_no"));
				orderMasterVO.setSale_no(rs.getString("sale_no"));
				orderMasterVO.setOrder_date(rs.getDate("order_date"));
				orderMasterVO.setPay_type(rs.getInt("pay_type"));
				orderMasterVO.setOrder_total(rs.getInt("order_total"));
				orderMasterVO.setSale_percent(rs.getFloat("sale_percent"));
				orderMasterVO.setDiscount(rs.getFloat("discount"));
				orderMasterVO.setOrder_status(rs.getString("order_status"));
				orderMasterVO.setTake_status(rs.getString("take_status"));
				orderMasterVO.setGive_star(rs.getFloat("give_star"));
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
		return orderMasterVO;
	}
	
	/************************取得訂餐資料 by Sheng*************************/
	@Override
	public List<OrderMasterVO> findByNumber(String number) {
		OrderMasterVO orderMasterVO = null;
		List<OrderMasterVO> list = new ArrayList<OrderMasterVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			//以店家搜尋訂餐資料
			if(number.charAt(0) == 'S') {
				pstmt = con.prepareStatement(GET_STORE_OM);
			}
			//以會員搜尋訂餐資料
			else if(number.charAt(0) == 'M') {
				pstmt = con.prepareStatement(GET_MEM_OM);
			}

			pstmt.setString(1, number);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				orderMasterVO = new OrderMasterVO();
				orderMasterVO.setOrder_no(rs.getString("order_no"));
				orderMasterVO.setMem_no(rs.getString("mem_no"));
				orderMasterVO.setStore_no(rs.getString("store_no"));
				orderMasterVO.setSale_no(rs.getString("sale_no"));
				orderMasterVO.setOrder_date(rs.getDate("order_date"));
				orderMasterVO.setPay_type(rs.getInt("pay_type"));
				orderMasterVO.setOrder_total(rs.getInt("order_total"));
				orderMasterVO.setSale_percent(rs.getFloat("sale_percent"));
				orderMasterVO.setDiscount(rs.getFloat("discount"));
				orderMasterVO.setOrder_status(rs.getString("order_status"));
				orderMasterVO.setTake_status(rs.getString("take_status"));
				orderMasterVO.setGive_star(rs.getFloat("give_star"));
				
				list.add(orderMasterVO);
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
	/************************取得訂餐資料 by Sheng*************************/
	
	@Override
	public List<OrderMasterVO> getAll() {
		List<OrderMasterVO> list = new ArrayList<OrderMasterVO>();
		OrderMasterVO orderMasterVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				orderMasterVO = new OrderMasterVO();
				orderMasterVO.setOrder_no(rs.getString("order_no"));
				orderMasterVO.setMem_no(rs.getString("mem_no"));
				orderMasterVO.setStore_no(rs.getString("store_no"));
				orderMasterVO.setSale_no(rs.getString("sale_no"));
				orderMasterVO.setOrder_date(rs.getDate("order_date"));
				orderMasterVO.setPay_type(rs.getInt("pay_type"));
				orderMasterVO.setOrder_total(rs.getInt("order_total"));
				orderMasterVO.setSale_percent(rs.getFloat("sale_percent"));
				orderMasterVO.setDiscount(rs.getFloat("discount"));
				orderMasterVO.setOrder_status(rs.getString("order_status"));
				orderMasterVO.setTake_status(rs.getString("take_status"));
				orderMasterVO.setGive_star(rs.getFloat("give_star"));
				list.add(orderMasterVO); // Store the row in the list
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

		OrderMasterJDBCDAO dao = new OrderMasterJDBCDAO();

		// 新增
		OrderMasterVO orderMasterVO1 = new OrderMasterVO();
		orderMasterVO1.setMem_no("MM00000001");
		orderMasterVO1.setStore_no("SM00000001");
		orderMasterVO1.setSale_no("SA00000001");
		orderMasterVO1.setOrder_date(java.sql.Date.valueOf("2020-12-05"));
		orderMasterVO1.setPay_type(1);
		orderMasterVO1.setOrder_total(10000);
		orderMasterVO1.setSale_percent(new Float(1.00));
		orderMasterVO1.setDiscount(new Float(1.00));
		orderMasterVO1.setOrder_status("1");
		orderMasterVO1.setTake_status("1");
		orderMasterVO1.setGive_star(new Float(2.0));

		dao.insert(orderMasterVO1);

		// 修改
//		OrderMasterVO orderMasterVO2 = new OrderMasterVO();
//		
//		orderMasterVO2.setOrder_no("OM00000001");
//		orderMasterVO2.setMem_no("MM00000001");
//		orderMasterVO2.setStore_no("SM00000001");
//		orderMasterVO2.setSale_no("SA00000001");
//		orderMasterVO2.setOrder_date(java.sql.Date.valueOf("2002-01-12"));
//		orderMasterVO2.setPay_type(1);
//		orderMasterVO2.setOrder_total(10000);
//		orderMasterVO2.setSale_percent(new Float(1.00));
//		orderMasterVO2.setDiscount(new Float(1.00));
//		orderMasterVO2.setOrder_status("1");
//		orderMasterVO2.setTake_status("1");
//		orderMasterVO2.setGive_star(new Float(3.0));
//		
//		dao.update(orderMasterVO2);

		// 刪除
//		dao.delete("OM00000001");

		// 查詢
//		OrderMasterVO orderMasterVO3 = dao.findByPrimaryKey("OM00000002");
//		System.out.print(orderMasterVO3.getOrder_no() + ",");
//		System.out.print(orderMasterVO3.getMem_no() + ",");
//		System.out.print(orderMasterVO3.getStore_no() + ",");
//		System.out.print(orderMasterVO3.getSale_no() + ",");
//		System.out.print(orderMasterVO3.getOrder_date() + ",");
//		System.out.print(orderMasterVO3.getPay_type() + ",");
//		System.out.println(orderMasterVO3.getOrder_total() + ",");
//		System.out.println(orderMasterVO3.getSale_percent() + ",");
//		System.out.println(orderMasterVO3.getDiscount() + ",");
//		System.out.println(orderMasterVO3.getOrder_status() + ",");
//		System.out.println(orderMasterVO3.getTake_status() + ",");
//		System.out.println(orderMasterVO3.getGive_star());
//		System.out.println("---------------------");

		// 查詢
//		List<OrderMasterVO> list = dao.getAll();
//		for (OrderMasterVO aOr : list) {
//			System.out.print(aOr.getOrder_no() + ",");
//			System.out.print(aOr.getMem_no() + ",");
//			System.out.print(aOr.getStore_no() + ",");
//			System.out.print(aOr.getSale_no() + ",");
//			System.out.print(aOr.getOrder_date() + ",");
//			System.out.print(aOr.getPay_type() + ",");
//			System.out.println(aOr.getOrder_total() + ",");
//			System.out.println(aOr.getSale_percent() + ",");
//			System.out.println(aOr.getDiscount() + ",");
//			System.out.println(aOr.getOrder_status() + ",");
//			System.out.println(aOr.getTake_status() + ",");
//			System.out.println();
//		}

	}

	
}
