<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Henkil�kunta kirjaudu sis��n - Kurun Pizzeria</title>
</head>

<body>

<h1>Kirjaudu sis��n</h1>
<div id="admin_login">
<form action="AdminLogin" method="post">
<p>K�ytt�j�tunnus:</p> <input type="text" name="user" placeholder="K�ytt�j�tunnus">
<p>Salasana:</p> <input type="password" name="password" placeholder="Salasana">
<p>
<c:if test ="${not empty loginError}">
	<c:out value="${loginError}"/>
	</c:if>
</p>
<input type="submit" value="Submit">
</form>
</div>
</body>
</html>