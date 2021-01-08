package com.store.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class Store_ScaleJDBCDAO implements Store_Scale_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	
	String userid = "PROJECT";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO Store_Scale (food_no,food_scale) VALUES ( ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT food_no, food_scale  FROM store_Scale order by food_no";
		private static final String GET_ONE_STMT = 
			"SELECT food_no, food_scale FROM store_Scale where food_no = ?";
		private static final String DELETE = 
			"DELETE FROM store_Scale where food_no = ?";
		private static final String UPDATE = 
			"UPDATE store_Scale set  food_scale=?  where food_no = ?";
		
		@Override
		public void insert(Store_ScaleVO store_ScaleVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, store_ScaleVO.getFood_no());
				pstmt.setString(2, store_ScaleVO.getFood_scale());
				
				
				
				pstmt.executeUpdate();
				System.out.println("dao���\�F");
				
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
		public void update(Store_ScaleVO store_ScaleVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setString(1, store_ScaleVO.getFood_no());
				pstmt.setString(2, store_ScaleVO.getFood_scale());
	
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
		public List<Store_ScaleVO> findByPrimaryKey(String food_no) {

			List<Store_ScaleVO> list = new ArrayList<Store_ScaleVO>();
			Store_ScaleVO store_ScaleVO = null;
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
					// empVo �]�٬� Domain objects
					store_ScaleVO = new Store_ScaleVO();
					store_ScaleVO.setFood_no(rs.getString("food_no"));
					store_ScaleVO.setFood_scale(rs.getString("food_scale"));
	
					list.add(store_ScaleVO);
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
		public List<Store_ScaleVO> getAll() {
			List<Store_ScaleVO> list = new ArrayList<Store_ScaleVO>();
			Store_ScaleVO store_ScaleVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo �]�٬� Domain objects
					store_ScaleVO = new Store_ScaleVO();
					store_ScaleVO.setFood_no(rs.getString("food_no"));
					store_ScaleVO.setFood_scale(rs.getString("food_scale"));

					list.add(store_ScaleVO);
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

			Store_ScaleJDBCDAO dao = new Store_ScaleJDBCDAO();
			
			// �s�W
//			Store_ScaleVO store_ScaleVO1 = new Store_ScaleVO();
//			store_ScaleVO1.setFood_no("FD00000003");
//			store_ScaleVO1.setFood_scale("FS00000001");
//			dao.insert(store_ScaleVO1);

		
//			dao.update(store_ScaleVO2);

			// �R��
//			dao.delete("FD00000003");

			// �d��
//			List<Store_ScaleVO> list = dao.findByPrimaryKey("FD00000003");
//			for (Store_ScaleVO astore_Scale : list) {
//				System.out.print(astore_Scale.getFood_no() + ",");
//				System.out.print(astore_Scale.getFood_scale());
//				System.out.println("---------------------");
//			}
			
			// �d��
//			List<Store_ScaleVO> list = dao.getAll();
//			for (Store_ScaleVO astore_Scale1 : list) {
//				System.out.print(astore_Scale1.getFood_no() + ",");
//				System.out.print(astore_Scale1.getFood_scale());
//
//				System.out.println("---------------------");
//			}
		}
	}		

