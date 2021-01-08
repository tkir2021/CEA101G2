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

public class Food_ListJDBCDAO implements Food_List_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";

	String userid = "CEA101G2";
	String passwd = "CEA101G2";

	private static final String INSERT_STMT = "INSERT INTO food_List (food_no,store_no,food_name,food_price,limit_,food_info,food_status,food_img) VALUES ('FD' || LPAD(foodlist_seq.NEXTVAL,8, '0'), ?, ?, ?, ?, ?, ?,?)";
	private static final String GET_ALL_STMT = "SELECT food_no,store_no,food_name,food_price,limit_,food_info,food_status,food_img FROM food_List order by food_no";
	private static final String GET_ONE_STMT = "SELECT food_no,store_no,food_name,food_price,limit_,food_info,food_status,food_img FROM food_List where food_no = ?";
	private static final String DELETE = "DELETE FROM food_List where food_no = ?";
	private static final String UPDATE = "UPDATE food_List set store_no=?, food_name=?, food_price=?, limit_=?, food_info=?, food_status=? where food_no = ?";
	 /************************購物車 20210101 by 皓哥*************************/
	private static final String GET_ALL_FOOD_STMT = "SELECT food_no,food_name,food_price,limit_,food_info,food_status,food_img FROM food_List where store_no = ? and food_status = 1 order by food_name";
	 /************************購物車 20210101 by 皓哥*************************/

	@Override
	public void insert(Food_ListVO food_ListVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, food_ListVO.getStore_no());
			pstmt.setString(2, food_ListVO.getFood_name());
			pstmt.setInt(3, food_ListVO.getFood_price());
			pstmt.setInt(4, food_ListVO.getLimit_());
			pstmt.setString(5, food_ListVO.getFood_info());
			pstmt.setInt(6, food_ListVO.getFood_status());
			pstmt.setBytes(7, food_ListVO.getFood_img());
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
	public void update(Food_ListVO food_ListVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, food_ListVO.getStore_no());
			pstmt.setString(2, food_ListVO.getFood_name());
			pstmt.setInt(3, food_ListVO.getFood_price());
			pstmt.setInt(4, food_ListVO.getLimit_());
			pstmt.setString(5, food_ListVO.getFood_info());
			pstmt.setInt(6, food_ListVO.getFood_status());
			pstmt.setString(7, food_ListVO.getFood_no());

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
	public void delete(String food_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, food_no);

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
	public Food_ListVO findByPrimaryKey(String food_no) {

		Food_ListVO food_ListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, food_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects

				food_ListVO = new Food_ListVO();
				food_ListVO.setFood_no(rs.getString("food_no"));
				food_ListVO.setStore_no(rs.getString("store_no"));
				food_ListVO.setFood_name(rs.getString("food_name"));
				food_ListVO.setFood_price(rs.getInt("food_price"));
				food_ListVO.setLimit_(rs.getInt("limit_"));
				food_ListVO.setFood_info(rs.getString("food_info"));
				food_ListVO.setFood_status(rs.getInt("food_status"));
				food_ListVO.setFood_img(rs.getBytes("food_img"));
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
		return food_ListVO;
	}

	@Override
	public List<Food_ListVO> getAll() {
		List<Food_ListVO> list = new ArrayList<Food_ListVO>();
		Food_ListVO food_ListVO = null;

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
				food_ListVO = new Food_ListVO();
				food_ListVO.setFood_no(rs.getString("food_no"));
				food_ListVO.setStore_no(rs.getString("store_no"));
				food_ListVO.setFood_name(rs.getString("food_name"));
				food_ListVO.setFood_price(rs.getInt("food_price"));
				food_ListVO.setLimit_(rs.getInt("limit_"));
				food_ListVO.setFood_info(rs.getString("food_info"));
				food_ListVO.setFood_status(rs.getInt("food_status"));
				food_ListVO.setFood_img(rs.getBytes("food_img"));
				list.add(food_ListVO); // Store the row in the list
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
	
	
	/************************購物車 20210101 by 皓哥*************************/
	public List<Food_ListVO> getAllFood(String store_no) {
		List<Food_ListVO> list = new ArrayList<Food_ListVO>();
		Food_ListVO food_ListVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_FOOD_STMT);
			pstmt.setString(1, store_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				food_ListVO = new Food_ListVO();
				food_ListVO.setFood_no(rs.getString("food_no"));
				food_ListVO.setFood_name(rs.getString("food_name"));
				food_ListVO.setFood_price(rs.getInt("food_price"));
				food_ListVO.setLimit_(rs.getInt("limit_"));
				food_ListVO.setFood_info(rs.getString("food_info"));
			//	food_ListVO.setFood_status(rs.getInt("food_status"));
			//	food_ListVO.setFood_img(rs.getBytes("food_img"));
				list.add(food_ListVO); // Store the row in the list
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
	/************************購物車 20210101 by 皓哥*************************/
	
//
//	public static void main(String[] args) {
//		try {
//			Food_ListJDBCDAO dao = new Food_ListJDBCDAO();

			// 新增
//			Food_ListVO food_ListVO1 = new Food_ListVO();
//			food_ListVO1.setStore_no("SM00000004");
//			food_ListVO1.setFood_name("pizza");
//			food_ListVO1.setFood_price(new Integer(100));
//			food_ListVO1.setLimit_(new Integer(50));
//			food_ListVO1.setFood_info("難吃的菜");
//			food_ListVO1.setFood_status(new Integer(1));
//			byte[] pic;
//			pic = getPicture("C:\\Users\\CJ02002\\Desktop\\店家\\01福德涼麵\\01.png");
//			food_ListVO1.setFood_img(pic);
//			dao.insert(food_ListVO1);

			// 修改
//			Food_ListVO food_ListVO2 = new Food_ListVO();

//			food_ListVO2.setStore_no("SM00000004");
//			food_ListVO2.setFood_name("xizza");
//			food_ListVO2.setFood_price(new Integer(100));
//			food_ListVO2.setLimit_(new Integer(50));
//			food_ListVO2.setFood_info("好吃的菜");
//			food_ListVO2.setFood_status(new Integer(1));
//			food_ListVO2.setFood_no("FD00000001");
//			dao.update(food_ListVO2);

			// 刪除
//			dao.delete("FD00000001");

			// 查詢
//			Food_ListVO food_ListVO3 = dao.findByPrimaryKey("FD00000002");
//			
//			System.out.print(food_ListVO3.getFood_no() + ",");
//			System.out.print(food_ListVO3.getFood_name() + ",");
//			System.out.print(food_ListVO3.getStore_no() + ",");
//			System.out.print(food_ListVO3.getFood_price() + ",");
//			System.out.print(food_ListVO3.getLimit_() + ",");
//			System.out.print(food_ListVO3.getFood_info() + ",");
//			System.out.print(food_ListVO3.getFood_status() + ",");
//			
//			System.out.println("---------------------");

			// 查詢
//			List<Food_ListVO> list = dao.getAll();
//			for (Food_ListVO aFood_List : list) {
//				System.out.print(aFood_List.getFood_no() + ",");
//				System.out.print(aFood_List.getFood_name() + ",");
//				System.out.print(aFood_List.getStore_no() + ",");
//				System.out.print(aFood_List.getFood_price() + ",");
//				System.out.print(aFood_List.getLimit_() + ",");
//				System.out.print(aFood_List.getFood_info() + ",");
//				System.out.print(aFood_List.getFood_status());
//				System.out.println();
//
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
