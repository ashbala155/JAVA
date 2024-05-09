<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1><%
String error = (String) session.getAttribute("error");
if(error!=null) {
	out.println(error);
	session.setAttribute("error",null);
} 
if(session.getAttribute("firstname")!=null){
	RequestDispatcher requestDispatcher = request.getRequestDispatcher("successPage.jsp");
	requestDispatcher.forward(request, response);
}
%></h1>
<form action="LoginServlet" method="POST">
UserName : <input type="text" name="username"><br>
Password : <input type="text" name="password"><br>
<input type="submit">
</form>
</body>
</html>