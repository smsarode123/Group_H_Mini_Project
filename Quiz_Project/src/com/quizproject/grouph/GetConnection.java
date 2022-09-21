package com.quizproject.grouph;

import java.sql.*;

public class GetConnection {

<<<<<<< HEAD
	Connection con=null;
	//creating connection
	public Connection getConnectionDetails() throws SQLException {
		System.out.println("Connecting....");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3308/example", "root", "Sagar@142143");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
=======
  Connection con=null;
  //creating connection
  public Connection getConnectionDetails() throws SQLException {
    System.out.println("Connecting....");
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      con=DriverManager.getConnection("jdbc:mysql://localhost:3308/example", "root", "Sagar@142143");
    }catch(Exception e) {
      e.printStackTrace();
    }
    return con;
  }
}


>>>>>>> FB-Tejaswini
