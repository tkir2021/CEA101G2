package com.food_list.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Food_ListDAO implements Food_List_interface {
	
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

	private static final String INSERT_STMT = "INSERT INTO food_List (food_no,store_no,food_name,food_price,limit_,food_info,food_status,food_img) VALUES ('FD' || LPAD(foodlist_seq.NEXTVAL,8, '0'), ?, ?, ?, ?, ?, ?,?)";
	private static final String GET_ALL_STMT = "SELECT food_no,store_no,food_name,food_price,limit_,food_info,food_status,food_img FROM food_List order by food_no";
	private static final String GET_ONE_STMT = "SELECT food_no,store_no,food_name,food_price,limit_,food_info,food_status,food_img FROM food_List where food_no = ?";
	private static final String DELETE = "DELETE FROM food_List where food_no = ?";
	private static final String UPDATE = "UPDATE food_List set store_no=?, food_name=?, food_price=?, limit_=?, food_info=?, food_status=? ,food_img=? where food_no = ?";
//	========================更新餐點上架狀態 by Mike========================
	private static final String UPDATE_Food_Status = "UPDATE food_List set food_status=? where food_no = ?";
//	========================ListAll餐點上架狀態order by審核與否 by Mike========================
	private static final String GET_ALL_STMT2 = "SELECT food_no,store_no,food_name,food_price,limit_,food_info,food_status,food_img FROM food_List order by food_status, food_no desc";
	
	
	/************************ 購物車：取得所有食物列表 by Sheng *************************/
	private static final String GET_ALL_FOOD_STMT = "SELECT food_no,food_name,food_price,limit_,food_info,food_status,food_img FROM food_List where store_no = ? AND food_status = 1 ORDER BY food_name";
	/************************ 購物車：取得所有食物列表 by Sheng *************************/
	// 20210110By 零零
	private static final String GET_ONE_STORE_STMT = "SELECT food_no,store_no,food_name,food_price,limit_,food_info,food_status,food_img FROM food_List where store_no = ?";

	@Override
	public void insert(Food_ListVO food_ListVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
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

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, food_ListVO.getStore_no());
			pstmt.setString(2, food_ListVO.getFood_name());
			pstmt.setInt(3, food_ListVO.getFood_price());
			pstmt.setInt(4, food_ListVO.getLimit_());
			pstmt.setString(5, food_ListVO.getFood_info());
			pstmt.setInt(6, food_ListVO.getFood_status());
			pstmt.setBytes(7, food_ListVO.getFood_img());
			pstmt.setString(8, food_ListVO.getFood_no());

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
	public void delete(String food_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, food_no);

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
	public Food_ListVO findByPrimaryKey(String food_no) {

		Food_ListVO food_ListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, food_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects

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

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
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

	/************************ 購物車：取得所有食物列表 by Sheng *************************/
	public List<Food_ListVO> getAllFood(String store_no) {
		List<Food_ListVO> list = new ArrayList<Food_ListVO>();
		Food_ListVO food_ListVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_FOOD_STMT);
			pstmt.setString(1, store_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// food_ListVO 也稱為 Domain objects
				food_ListVO = new Food_ListVO();
				food_ListVO.setFood_no(rs.getString("food_no"));
				food_ListVO.setFood_name(rs.getString("food_name"));
				food_ListVO.setFood_price(rs.getInt("food_price"));
				food_ListVO.setLimit_(rs.getInt("limit_"));
				food_ListVO.setFood_info(rs.getString("food_info"));
				food_ListVO.setFood_status(rs.getInt("food_status"));
				food_ListVO.setFood_img(rs.getBytes("food_img"));
				list.add(food_ListVO); // Store the row in the list
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

	/************************ 購物車：取得所有食物列表 by Sheng *************************/

	@Override
	public List<Food_ListVO> getAll(String store_no) {
		List<Food_ListVO> list = new ArrayList<Food_ListVO>();
		Food_ListVO food_ListVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STORE_STMT);
			pstmt.setString(1, store_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
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

//	========================更新餐點上架狀態 by Mike========================
	@Override
	public void updateStatus(String food_no, Integer food_status) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_Food_Status);

			pstmt.setInt(1, food_status);
			pstmt.setString(2, food_no);
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
	
//	========================ListAll餐點上架狀態order by審核與否 by Mike========================
	@Override
	public List<Food_ListVO> getAll2() {
		List<Food_ListVO> list = new ArrayList<Food_ListVO>();
		Food_ListVO food_ListVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT2);
			rs = pstmt.executeQuery();

			while (rs.next()) {
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
//
//	public static void main(String[] args) {
//		try {
//			Food_ListJDBCDAO dao = new Food_ListJDBCDAO();

	// �s�W
//			Food_ListVO food_ListVO1 = new Food_ListVO();
//			food_ListVO1.setStore_no("SM00000004");
//			food_ListVO1.setFood_name("pizza");
//			food_ListVO1.setFood_price(new Integer(100));
//			food_ListVO1.setLimit_(new Integer(50));
//			food_ListVO1.setFood_info("���Y����");
//			food_ListVO1.setFood_status(new Integer(1));
//			byte[] pic;
//			pic = getPicture("C:\\Users\\CJ02002\\Desktop\\���a\\01�ּw�D��\\01.png");
//			food_ListVO1.setFood_img(pic);
//			dao.insert(food_ListVO1);

	// �ק�
//			Food_ListVO food_ListVO2 = new Food_ListVO();

//			food_ListVO2.setStore_no("SM00000004");
//			food_ListVO2.setFood_name("xizza");
//			food_ListVO2.setFood_price(new Integer(100));
//			food_ListVO2.setLimit_(new Integer(50));
//			food_ListVO2.setFood_info("�n�Y����");
//			food_ListVO2.setFood_status(new Integer(1));
//			food_ListVO2.setFood_no("FD00000001");
//			dao.update(food_ListVO2);

	// �R��
//			dao.delete("FD00000001");

	// �d��
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

	// �d��
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
