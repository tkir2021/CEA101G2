package com.store.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	
	String userid = "CEA101G2";
	String passwd = "CEA101G2";
	public void loadDriver(String Driver)
	{
		try {
			Class.forName(Driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


public Connection getConnection()
{
	Connection con = null;
	try {
		con = DriverManager.getConnection(url, userid, passwd);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return con;
}

public boolean validate(LoginBean loginBean)
{
	boolean status = false;
	ResultSet rs =null;
	
	loadDriver(driver);
	Connection con = getConnection();
	
	String sql = "select store_acct from store_mem where store_acct = ? and store_pwd =?";
	PreparedStatement ps=null;
	try {
	ps = con.prepareStatement(sql);
	ps.setString(1, loginBean.getUsername());
	ps.setString(2, loginBean.getPassword());
	 rs = ps.executeQuery();
	status = rs.next();
	System.out.println("status"+status);
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (ps != null) {
			try {
				ps.close();
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
	return status;
}
}

