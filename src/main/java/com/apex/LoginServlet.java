package com.apex;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/training","root","Supriya@1505");
			String firstname="";
			String username = request.getParameter("username");
			String password = request.getParameter("password"); 
			if( null!=username && null!=password) {
				if(connection!=null) {
				PreparedStatement statement = connection.prepareStatement("select * from user where username=? and password=?");
				statement.setString(1, username); 
				statement.setString(2, password);
				ResultSet resultSet = statement.executeQuery();
				while(resultSet.next()) {
					firstname = resultSet.getString(1);
					}

				}	
				
			}
			HttpSession session = request.getSession();
			if(firstname.length()!= 0) {
			session.setAttribute("firstname", firstname);
			session.setAttribute("username", username);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("successPage.jsp");
			requestDispatcher.forward(request, response);
			} else {
				session.setAttribute("error", "Invalid Login");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("loginPage.jsp");
				requestDispatcher.forward(request, response); 
			}
		}  catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
