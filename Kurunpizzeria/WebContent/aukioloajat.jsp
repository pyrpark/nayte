<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="styles/henkilot.css">
<title>Aukiolot</title>
</head>
<body>


<table>
<caption>Aukioloajat</caption>
<thead>
	<tr>
		<td>ID</td>
		<td>paiva</td>
		<td>sisalto</td>
	</tr>
</thead>
<tbody>
<c:forEach items="${aukiolo}" var="a">
	<tr>
		<td><c:out value="${a.id}"/></td>
		<td><c:out value="${a.paiva}"/></td>
		<td><c:out value="${a.sisalto}"/></td>
	</tr>
</c:forEach>
</tbody>
</table>

<form action="lisaa_aukiolo" method="get">
<input type="text" name="ma_pe" placeholder="ma-pe"/>
<input type="text" name="la_su" placeholder="la-su"/>
<input type="text" name="lounas" placeholder="lounas"/>
<button type="submit">Lähetä</button>
</form>

<c:out value="${ma_pe}"/>

</body>
</html>