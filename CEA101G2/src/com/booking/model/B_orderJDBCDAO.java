package com.booking.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class B_orderJDBCDAO implements B_orderDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G2"; // DB名稱記得改!!!!!!!!!!!!!!!!!!!!!!
	String passwd = "CEA101G2";

	// 新增資料
	private static final String INSERT_STMT_GROUP = "INSERT INTO BOOKING_ORDER (BOOKING_NO,MEM_NO,STORE_NO,GROUP_NO,BOOKING_DATE,TIME_PERIOD,PEOPLE,BOOKING_STATUS,ATTEND_STATUS) VALUES (('BO' || LPAD(BOOKINGORDER_SEQ.NEXTVAL, 8, '0')),?,?,?,?,?,?,1,0)";
	// 查全部
	private static final String GET_ALL_STMT = "SELECT BOOKING_NO,MEM_NO,STORE_NO,GROUP_NO,to_char(BOOKING_DATE,'yyyy-mm-dd')BOOKING_DATE,TIME_PERIOD,PEOPLE,BOOKING_STATUS,ATTEND_STATUS,GIVE_STAR,ORDER_COMMIT FROM BOOKING_ORDER order by booking_no";
	// 用bookingno查單筆
	private static final String GET_ONE_STMT = "SELECT booking_no, mem_no,store_no,group_no,To_char(booking_date, 'yyyy-mm-dd')BOOKING_DATE,time_period, people,booking_status,attend_status,give_star,ORDER_COMMIT FROM booking_order WHERE booking_no = ?";
	// 用storeno查單筆
	private static final String GET_ONE_STORE = "SELECT booking_no, mem_no,store_no,group_no,To_char(booking_date, 'yyyy-mm-dd')BOOKING_DATE,time_period, people,booking_status,attend_status,give_star,ORDER_COMMIT FROM booking_order WHERE store_no = ? order by BOOKING_DATE DESC";
	// 用memno查單筆
	private static final String GET_ONE_MEMBER = "SELECT booking_no, mem_no,store_no,group_no,To_char(booking_date, 'yyyy-mm-dd')BOOKING_DATE,time_period, people,booking_status,attend_status,give_star,ORDER_COMMIT FROM booking_order WHERE mem_no = ?";
	// 修改
	private static final String UPDATE = "UPDATE BOOKING_ORDER set BOOKING_STATUS=? ,ATTEND_STATUS=? ,GIVE_STAR=? where booking_no = ?";
	/*** 更新評分 by Sheng ***/
	private static final String UP_GIVE_STAR = "UPDATE BOOKING_ORDER SET GIVE_STAR=? WHERE BOOKING_NO = ?";
	/*** 更新出席狀態 by Bella ***/
	private static final String UP_ATTEND_STATUS = "UPDATE BOOKING_ORDER SET ATTEND_STATUS=? WHERE BOOKING_NO = ?";
	/*** 更新評分 by Sheng ***/
	// 用storeno,date,timeperiod查people總和
	private static final String GET_ONE_PEOPLE = "SELECT SUM(people) AS people_SUM FROM booking_order WHERE store_no = ? and BOOKING_DATE=? and time_period=?";

	@Override
	public void insert(B_orderVO b_orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT_GROUP);
			pstmt.setString(1, b_orderVO.getMemno());
			pstmt.setString(2, b_orderVO.getStoreno());
			pstmt.setString(3, b_orderVO.getGroupno());
			pstmt.setDate(4, b_orderVO.getBookingdate());
			pstmt.setString(5, b_orderVO.getTimeperiod());
			pstmt.setInt(6, b_orderVO.getPeople());

			pstmt.executeUpdate();
			System.out.println("成功了");
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
	public void update(B_orderVO b_orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, b_orderVO.getBookingstatus());
			pstmt.setInt(2, b_orderVO.getAttendstatus());
			pstmt.setDouble(3, b_orderVO.getGivestar());
			pstmt.setString(4, b_orderVO.getBookingno());

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

	/************************ 更新評分 by Sheng **************************/
	@Override
	public void upGivestar(String bookingno, double givestar) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UP_GIVE_STAR);

			pstmt.setDouble(1, givestar);
			pstmt.setString(2, bookingno);

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

	/************************ 更新出席狀態 by Bella **************************/
	@Override
	public void upAttendstatus(String bookingno, Integer attendStatus) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UP_ATTEND_STATUS);

			pstmt.setDouble(1, attendStatus);
			pstmt.setString(2, bookingno);

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

	/************************ 更新評分 by Sheng **************************/

	@Override
	public B_orderVO findByPrimaryKey(String bookingno) {
		B_orderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, bookingno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				orderVO = new B_orderVO();
				orderVO.setBookingno(rs.getString("booking_no"));
				orderVO.setMemno(rs.getString("mem_no"));
				orderVO.setStoreno(rs.getString("store_no"));
				orderVO.setGroupno(rs.getString("group_no"));
				orderVO.setBookingdate(rs.getDate("booking_date"));
				orderVO.setTimeperiod(rs.getString("time_period"));
				orderVO.setPeople(rs.getInt("people"));
				orderVO.setBookingstatus(rs.getInt("booking_status"));
				orderVO.setAttendstatus(rs.getInt("attend_status"));
				orderVO.setGivestar(rs.getDouble("give_star"));
				orderVO.setOrdercommit(rs.getTimestamp("order_commit"));
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
		return orderVO;
	}

	@Override
	public B_orderVO findByPrimaryKey3(String storeno, Date bookingdate, String timeperiod) {
		B_orderVO orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_PEOPLE);
			pstmt.setString(1, storeno);
			pstmt.setDate(2, bookingdate);
			pstmt.setString(3, timeperiod);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderVO = new B_orderVO();
				orderVO.setPeople(rs.getInt("people_sum"));
//	    orderVO.setBookingno(rs.getString("booking_no"));
//	    orderVO.setMemno(rs.getString("mem_no"));
//	    orderVO.setStoreno(rs.getString("store_no"));
//	    orderVO.setGroupno(rs.getString("group_no"));
//	    orderVO.setBookingdate(rs.getDate("booking_date"));
//	    orderVO.setTimeperiod(rs.getString("time_period"));
//	    orderVO.setPeople(rs.getInt("people"));
//	    orderVO.setBookingstatus(rs.getInt("booking_status"));
//	    orderVO.setAttendstatus(rs.getInt("attend_status"));
//	    orderVO.setGivestar(rs.getDouble("give_star"));
//	    orderVO.setOrdercommit(rs.getTimestamp("order_commit"));
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

		return orderVO;
	}

	@Override
	public List<B_orderVO> getAll() {
		List<B_orderVO> list = new ArrayList<B_orderVO>();
		B_orderVO orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderVO = new B_orderVO();
				orderVO.setBookingno(rs.getString("booking_no"));
				orderVO.setMemno(rs.getString("mem_no"));
				orderVO.setStoreno(rs.getString("store_no"));
				orderVO.setGroupno(rs.getString("group_no"));
				orderVO.setBookingdate(rs.getDate("booking_date"));
				orderVO.setTimeperiod(rs.getString("time_period"));
				orderVO.setPeople(rs.getInt("people"));
				orderVO.setBookingstatus(rs.getInt("booking_status"));
				orderVO.setAttendstatus(rs.getInt("attend_status"));
				orderVO.setGivestar(rs.getDouble("give_star"));
				orderVO.setOrdercommit(rs.getTimestamp("order_commit"));
				list.add(orderVO);
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
	public List<B_orderVO> findByPrimaryKey2(String number) {
		B_orderVO orderVO = null;
		List<B_orderVO> list = new ArrayList<B_orderVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// 判別首字是S(storeno)還是M(memno)，以區隔查找單筆的方式
//			char firstWord = number.charAt(0);
//			String theFirst = Character.toString(firstWord);

			// 如果以店家查找的訂位
			if (number.charAt(0) == 'S') {
				pstmt = con.prepareStatement(GET_ONE_STORE);
				// 如果以會員查找的訂位
			} else if (number.charAt(0) == 'M') {
				pstmt = con.prepareStatement(GET_ONE_MEMBER);
			}
			pstmt.setString(1, number);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				orderVO = new B_orderVO();
				orderVO.setBookingno(rs.getString("booking_no"));
				orderVO.setMemno(rs.getString("mem_no"));
				orderVO.setStoreno(rs.getString("store_no"));
				orderVO.setGroupno(rs.getString("group_no"));
				orderVO.setBookingdate(rs.getDate("booking_date"));
				orderVO.setTimeperiod(rs.getString("time_period"));
				orderVO.setPeople(rs.getInt("people"));
				orderVO.setBookingstatus(rs.getInt("booking_status"));
				orderVO.setAttendstatus(rs.getInt("attend_status"));
				orderVO.setGivestar(rs.getDouble("give_star"));

				java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String str = df.format(rs.getTimestamp("order_commit"));
//				System.out.println(str);
				orderVO.setOrdercommit(java.sql.Timestamp.valueOf(str));

//				orderVO.setOrdercommit(rs.getTimestamp("order_commit"));

				list.add(orderVO);
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
		B_orderJDBCDAO dao = new B_orderJDBCDAO();
//
//		// insert 新增
//		B_orderVO order1 = new B_orderVO();
//		 java.util.Date rightnow =new  java.util.Date();
//		 long now = rightnow.getTime();
//		//order1.setBookingdate(new java.sql.Date(now));
//		//order1.setBookingdate(java.sql.Date.valueOf("2020-12-07"));
//		order1.setMemno("MM00000001");
//		order1.setStoreno("SM00000001");
//		order1.setGroupno(null);
//		order1.setBookingdate(new java.sql.Date(System.currentTimeMillis()));
//		order1.setTimeperiod("OP00000001");
//		order1.setPeople(60);
//		////order1.setBookingstatus(0);
//		////order1.setAttendstatus(1);
//		////order1.setGivestar(null);
//		dao.insert(order1);

//		// update 修改
//		B_orderVO order2 = new B_orderVO();
//		order2.setBookingno("BO00000004");
//		order2.setBookingstatus(1);
//		order2.setAttendstatus(0);
//		order2.setGivestar(5.0);
//		dao.update(order2);
//
//		// findByPrimaryKey 查詢一筆
//		B_orderVO order3 = dao.findByPrimaryKey("BO00000004");
//		System.out.println(order3.getBookingno() + ",");
//		System.out.println(order3.getMemno() + ",");
//		System.out.println(order3.getStoreno() + ",");
//		System.out.println(order3.getGroupno() + ",");
//		System.out.println(order3.getBookingdate() + ",");
//		System.out.println(order3.getTimeperiod() + ",");
//		System.out.println(order3.getPeople() + ",");
//		System.out.println(order3.getBookingstatus() + ",");
//		System.out.println(order3.getAttendstatus() + ",");
//		System.out.println(order3.getGivestar());
//		System.out.println(order4.getOrdercommit());
//		System.out.println("---------------------");
//
//		
		// getALL 查詢全部
//		List<B_orderVO> list = dao.getAll();
//		for(B_orderVO order4 :list) {
//		System.out.println(order4.getBookingno() + ",");
//		System.out.println(order4.getMemno() + ",");
//		System.out.println(order4.getStoreno() + ",");
//		System.out.println(order4.getGroupno() + ",");
//		System.out.println(order4.getBookingdate() + ",");
//		System.out.println(order4.getTimeperiod() + ",");
//		System.out.println(order4.getPeople() + ",");
//		System.out.println(order4.getBookingstatus() + ",");
//		System.out.println(order4.getAttendstatus() + ",");
//		System.out.println(order4.getGivestar() + ",");
//		System.out.println(order4.getOrdercommit());
//		System.out.println("--------------------------------------------");
//		}

		List<B_orderVO> list = dao.findByPrimaryKey2("SM00000001");
		for (B_orderVO order4 : list) {
			System.out.println(order4.getBookingno() + ",");
			System.out.println(order4.getMemno() + ",");
			System.out.println(order4.getStoreno() + ",");
			System.out.println(order4.getGroupno() + ",");
			System.out.println(order4.getBookingdate() + ",");
			System.out.println(order4.getTimeperiod() + ",");
			System.out.println(order4.getPeople() + ",");
			System.out.println(order4.getBookingstatus() + ",");
			System.out.println(order4.getAttendstatus() + ",");
			System.out.println(order4.getGivestar());
			System.out.println(order4.getOrdercommit());
			System.out.println("-----------------------------------------");
		}
	}

}
