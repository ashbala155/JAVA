package com.apex;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/training","root","Supriya@1505");
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Statement statement1 = connection.createStatement();
			ResultSet resultSet = statement1.executeQuery("select * from user");
			while(resultSet.next()) {
				if(username.equals(resultSet.getString(3))) {
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("registerPage.jsp");
					requestDispatcher.forward(request, response); 
				}
			}
			if(username!=null && password!=null && firstname!=null && lastname!=null) {
				if(connection!=null) {
					PreparedStatement statement = connection.prepareStatement("insert into user values(?,?,?,?)");
					statement.setString(1, firstname);
					statement.setString(2, lastname);
					statement.setString(3, username); 
					statement.setString(4, password);
					int insert = statement.executeUpdate();
					if(insert==1) {
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("regsuccessPage.jsp");
						requestDispatcher.forward(request, response);
					}
				}
			} else {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("registerPage.jsp");
				requestDispatcher.forward(request, response);
			}
			
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
}
