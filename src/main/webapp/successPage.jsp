<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
if(session.getAttribute("firstname")==null){
	RequestDispatcher requestDispatcher = request.getRequestDispatcher("loginPage.jsp");
	requestDispatcher.forward(request, response);
}
%>
<h1> Welcome user <%= session.getAttribute("firstname") %> !</h1> <br>


<form method="post">
<table border="1">
<caption>LIST OF YOUR ORDERS</caption>
<tr>
<td>Order ID</td>
<td>Username</td>
<td>Product Name</td>
<td>Order Date</td>
<td>Amount</td>
</tr>
<%
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
String uname=(String)session.getAttribute("username"); 
String query="select * from orderDetail where username='"+uname+"'";
Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/training","root","Supriya@1505");
Statement stmt=conn.createStatement();
ResultSet rs=stmt.executeQuery(query);
while(rs.next())
{

%>
<tr><td><%=rs.getInt("id") %></td>
<td><%=rs.getString("username") %></td>
<td><%=rs.getString("product_name") %></td>
<td><%=rs.getDate("order_date") %></td>
<td><%=rs.getInt("amount") %></td></tr>
 <%

}
%>
</table>
<%
rs.close();
stmt.close();
conn.close();
}
catch(Exception e)
{
e.printStackTrace();
out.println("<h1> error: "+ e.getMessage()+"</h1>");
}
%>
</form>
</body>
</html>