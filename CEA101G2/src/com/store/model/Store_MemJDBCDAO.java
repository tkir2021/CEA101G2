package com.store.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Store_MemJDBCDAO implements Store_Mem_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";

	String userid = "CEA101G2";
	String passwd = "CEA101G2";

	private static final String INSERT_STMT = "INSERT INTO store_mem (store_no,store_acct,store_pwd,store_name,addr,open_dates,email,S_category,store_info,upload_status,s_permission,sum_grade,blocked,star_total,star_times,table_limit,rest_img) VALUES ('SM' || LPAD(STOREMEM_SEQ.NEXTVAL, 8, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT store_no,store_acct,store_pwd,store_name,addr,open_dates,email,s_category,store_info,upload_status,S_permission,sum_grade,blocked,star_total,star_times,table_limit,rest_img FROM store_mem order by store_no";
	private static final String GET_ONE_STMT = "SELECT store_no,store_acct,store_pwd,store_name,addr,open_dates,email,s_category,store_info,upload_status,S_permission,sum_grade,blocked,star_total,star_times,table_limit,rest_img FROM store_mem where store_no = ?";
	private static final String DELETE = "DELETE FROM store_mem where STORE_NO = ?";
	private static final String UPDATE = "UPDATE store_mem set store_acct=?, store_pwd=?, store_name=?, addr=?, open_dates=?, email=? ,s_category=?, store_info=?, upload_status=?, S_permission=?, sum_grade=?, blocked=?, star_total=?, star_times=?, table_limit=?, rest_img=? where store_no = ?";
	// ========================更新店家審核上架狀態 by Mike========================
	private static final String UPDATE_Upload_Status = "UPDATE store_mem set upload_status=? where store_no = ?";
	// ========================更新店家平台權限狀態 by Mike========================
	private static final String UPDATE_S_Permission = "UPDATE store_mem set s_permission=? where store_no = ?";

	@Override
	public void insert(Store_MemVO store_memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, store_memVO.getStore_acct());
			pstmt.setString(2, store_memVO.getStore_pwd());
			pstmt.setString(3, store_memVO.getStore_name());
			pstmt.setString(4, store_memVO.getAddr());
			pstmt.setString(5, store_memVO.getOpen_dates());
			pstmt.setString(6, store_memVO.getEmail());
			pstmt.setString(7, store_memVO.getS_category());
			pstmt.setString(8, store_memVO.getStore_info());
			pstmt.setInt(9, store_memVO.getUpload_status());
			pstmt.setInt(10, store_memVO.getS_permission());
			pstmt.setInt(11, store_memVO.getSum_grade());
			pstmt.setInt(12, store_memVO.getBlocked());
			pstmt.setDouble(13, store_memVO.getStar_total());
			pstmt.setInt(14, store_memVO.getStar_times());
			pstmt.setInt(15, store_memVO.getTable_limit());
			pstmt.setBytes(16, store_memVO.getRest_img());

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
	public void update(Store_MemVO store_memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, store_memVO.getStore_acct());
			pstmt.setString(2, store_memVO.getStore_pwd());
			pstmt.setString(3, store_memVO.getStore_name());
			pstmt.setString(4, store_memVO.getAddr());
			pstmt.setString(5, store_memVO.getOpen_dates());
			pstmt.setString(6, store_memVO.getEmail());
			pstmt.setString(7, store_memVO.getS_category());
			pstmt.setString(8, store_memVO.getStore_info());
			pstmt.setInt(9, store_memVO.getUpload_status());
			pstmt.setInt(10, store_memVO.getS_permission());
			pstmt.setInt(11, store_memVO.getSum_grade());
			pstmt.setInt(12, store_memVO.getBlocked());
			pstmt.setDouble(13, store_memVO.getStar_total());
			pstmt.setInt(14, store_memVO.getStar_times());
			pstmt.setInt(15, store_memVO.getTable_limit());
			pstmt.setBytes(16, store_memVO.getRest_img());
			pstmt.setString(17, store_memVO.getStore_no());
			
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

	// ========================更新店家審核上架狀態 by Mike========================
	@Override
	public void updateStatus(String store_no, Integer upload_status) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_Upload_Status);

			pstmt.setInt(1, upload_status);
			pstmt.setString(2, store_no);
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

	// ========================更新店家平台權限狀態 by Mike========================
	@Override
	public void updateStatusPermission(String store_no, Integer s_permission) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_S_Permission);

			pstmt.setInt(1, s_permission);
			pstmt.setString(2, store_no);
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
	public void delete(String store_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, store_no);

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
	public Store_MemVO findByPrimaryKey(String store_no) {

		Store_MemVO store_memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, store_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				store_memVO = new Store_MemVO();

				store_memVO.setStore_no(rs.getString("store_no"));
				store_memVO.setStore_acct(rs.getString("store_acct"));
				store_memVO.setStore_pwd(rs.getString("store_pwd"));
				store_memVO.setStore_name(rs.getString("store_name"));
				store_memVO.setAddr(rs.getString("addr"));
				store_memVO.setOpen_dates(rs.getString("open_dates"));
				store_memVO.setEmail(rs.getString("email"));
				store_memVO.setS_category(rs.getString("s_category"));
				store_memVO.setStore_info(rs.getString("store_info"));
				store_memVO.setUpload_status(rs.getInt("upload_status"));
				store_memVO.setS_permission(rs.getInt("s_permission"));
				store_memVO.setSum_grade(rs.getInt("sum_grade"));
				store_memVO.setBlocked(rs.getInt("blocked"));
				store_memVO.setStar_total(rs.getDouble("star_total"));
				store_memVO.setStar_times(rs.getInt("star_times"));
				store_memVO.setTable_limit(rs.getInt("table_limit"));
				store_memVO.setRest_img(rs.getBytes("rest_img"));

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
		return store_memVO;
	}

	@Override
	public List<Store_MemVO> getAll() {

		List<Store_MemVO> list = new ArrayList<Store_MemVO>();
		Store_MemVO store_memVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				store_memVO = new Store_MemVO();
				store_memVO.setStore_no(rs.getString("store_no"));
				store_memVO.setStore_acct(rs.getString("store_acct"));
				store_memVO.setStore_pwd(rs.getString("store_pwd"));
				store_memVO.setStore_name(rs.getString("store_name"));
				store_memVO.setAddr(rs.getString("addr"));
				store_memVO.setOpen_dates(rs.getString("open_dates"));
				store_memVO.setEmail(rs.getString("email"));
				store_memVO.setS_category(rs.getString("s_category"));
				store_memVO.setStore_info(rs.getString("store_info"));
				store_memVO.setUpload_status(rs.getInt("upload_status"));
				store_memVO.setS_permission(rs.getInt("s_permission"));
				store_memVO.setSum_grade(rs.getInt("sum_grade"));
				store_memVO.setBlocked(rs.getInt("blocked"));
				store_memVO.setStar_total(rs.getDouble("star_total"));
				store_memVO.setStar_times(rs.getInt("star_times"));
				store_memVO.setTable_limit(rs.getInt("table_limit"));
				store_memVO.setRest_img(rs.getBytes("rest_img"));

				list.add(store_memVO); // Store the row in the list

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

	public static byte[] getPicture(String pic) throws IOException {
		FileInputStream fis = new FileInputStream(pic);
		byte[] space = new byte[fis.available()];
		fis.read(space);
		fis.close();
		return space;
	}

	public static void main(String[] args) {

		Store_MemJDBCDAO dao = new Store_MemJDBCDAO();

//	Store_memVO store_memVO1 = new Store_memVO();
//	
//	store_memVO1.setStore_acct("A3334567");
//	store_memVO1.setStore_pwd("A111456");
//	store_memVO1.setStore_name("fopa");
//	store_memVO1.setAddr("Taipei");
//	store_memVO1.setOpen_dates("1100221");
//	store_memVO1.setEmail("Qoo@gmail.com");
//	store_memVO1.setS_category("2");
//	store_memVO1.setStore_info("憟賢������");
//	store_memVO1.setUpload_status(new Integer(4));
//	store_memVO1.setS_permission(new Integer(3));
//	store_memVO1.setSum_grade(new Integer(2));
//	store_memVO1.setBlocked(new Integer(1));
//	store_memVO1.setStar_total(new Double(3.3));
//	store_memVO1.setStar_times(new Integer(13));
//	store_memVO1.setTable_limit(new Integer(50));
//	dao.insert(store_memVO1);

//	store_memVO1.setStore_no("SM00000002");	

//Store_memVO store_memVO2 = new Store_memVO();
//    
//	
//	store_memVO2.setStore_acct("A3334567");
//	store_memVO2.setStore_pwd("A111456");
//	store_memVO2.setStore_name("fyyyy");
//	store_memVO2.setAddr("Taipei");
//	store_memVO2.setOpen_dates("1100221");
//	store_memVO2.setEmail("Qoo@gmail.com");
//	store_memVO2.setS_category("2");
//	store_memVO2.setStore_info("��������");
//	store_memVO2.setUpload_status(new Integer(4));
//	store_memVO2.setS_permission(new Integer(3));
//	store_memVO2.setSum_grade(new Integer(2));
//	store_memVO2.setBlocked(new Integer(1));
//	store_memVO2.setStar_total(new Double(3.3));
//	store_memVO2.setStar_times(new Integer(13));
//	store_memVO2.setTable_limit(new Integer(50));
//	store_memVO2.setStore_no("SM00000005");
//	
//	dao.update(store_memVO2);
//	

		// 刪除
//	dao.delete("SM00000005");

		// 查詢

//	Store_memVO store_memVO3 = dao.findByPrimaryKey("SM00000005");
//	System.out.print(store_memVO3.getStore_no() + ",");
//	System.out.print(store_memVO3.getStore_acct() + ",");
//	System.out.print(store_memVO3.getStore_name() + ",");
//	System.out.print(store_memVO3.getAddr() + ",");
//	System.out.print(store_memVO3.getOpen_dates() + ",");
//	System.out.print(store_memVO3.getEmail() + ",");
//	System.out.print(store_memVO3.getS_category() + ",");
//	System.out.println(store_memVO3.getStore_info() + ",");
//	System.out.println(store_memVO3.getUpload_status() + ",");
//	System.out.println(store_memVO3.getS_permission() + ",");
//	System.out.println(store_memVO3.getSum_grade() + ",");
//	System.out.println(store_memVO3.getBlocked() + ",");
//	System.out.println(store_memVO3.getStar_total() + ",");
//	System.out.println(store_memVO3.getStar_times() + ",");
//	System.out.println(store_memVO3.getTable_limit() + ",");

		// 查詢
		List<Store_MemVO> list = dao.getAll();
		for (Store_MemVO aStore_mem : list) {
			System.out.print(aStore_mem.getStore_no() + ",");
			System.out.print(aStore_mem.getStore_acct() + ",");
			System.out.print(aStore_mem.getStore_name() + ",");
			System.out.print(aStore_mem.getAddr() + ",");
			System.out.print(aStore_mem.getOpen_dates() + ",");
			System.out.print(aStore_mem.getEmail() + ",");
			System.out.print(aStore_mem.getS_category() + ",");
			System.out.print(aStore_mem.getStore_info() + ",");
			System.out.print(aStore_mem.getUpload_status() + ",");
			System.out.print(aStore_mem.getS_permission() + ",");
			System.out.print(aStore_mem.getSum_grade() + ",");
			System.out.print(aStore_mem.getBlocked() + ",");
			System.out.print(aStore_mem.getStar_total() + ",");
			System.out.print(aStore_mem.getStar_times() + ",");
			System.out.print(aStore_mem.getTable_limit() + ",");
			System.out.print(aStore_mem.getRest_img());
			System.out.println();

		}
	}
}