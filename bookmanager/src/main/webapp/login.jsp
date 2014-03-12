<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login in to the book manager system</title>
</head>
<body>
	<div align="center" >
		<h1 style="color: red" ><%=request.getAttribute("errorMsg")==null ? "" : request.getAttribute("errorMsg") %></h1>
	</div>
	<form action="login.do" method="post" id="loginForm">
	<table align="center">	
		<tr>
			<td>
				<label>Input your user name:</label>
			</td>
			<td>
				<input name="userName" id="userName" type="text" maxlength="15"/>
			</td>
		</tr>
		<tr>
			<td>
				<label>Input your password:</label>
			</td>
			<td>
				<input name="password" id="password" type="password" maxlength="15" >
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input name="Log in" type="submit" >
			</td>
		</tr>
	</table>
	
	</form>
	
</body>
</html>