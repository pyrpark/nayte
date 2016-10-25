<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="henkilokunnan_style.css" rel="stylesheet" type="text/css">
<title>Kurun henkilökunta</title>
</head>

<body>

<h1>Kurun henkilökunnan sivut</h1>

<!-- Tämä div antaa ilmoituksia (onnistuiko vai ei). Älä koske. -->
<div id="tapahtuma_ilmoitus" style="color: ${vari};">${ilmoitus}</div>

<h2>Tilatut tuotteet</h2>

<c:set var="count" value="${1}"></c:set>


<c:forEach var="tilaukset" items="${tilaukset}">

<c:if test="${tilaukset.tilaus_id == count && tilaukset.status_id == '2'}" >
	<div id="tilaukset">
	
	
		<b>TILAUS</b>
		<table id="tilaus_tablet">
		<tr><td>TILAUS ID</td> <td>ASIAKAS ID</td> <td>STATUS ID</td><td>TILAUS TYYPPI</td><td>LISÄTIETO</td><td>TILAUS AIKA</td><td>TILAUS HINTA</td></tr>
		<tr><td>"${tilaukset.tilaus_id}"</td> <td>"${tilaukset.asiakas_id}"</td> <td>"${tilaukset.status_id}"</td>
		<td>"${tilaukset.tilaus_tyyppi}"</td> <td>"${tilaukset.lisatieto}"</td> <td>"${tilaukset.tilaus_aika}"</td>
		<td>"${tilaukset.tilaus_hinta}"</td></tr>
		</table>
	
		<b>TOIMITUS</b>
		<table id="tilaus_tablet">
		<tr><td>ETUNIMI</td> <td>SUKUNIMI</td> <td>SAHKOPOSTI</td><td>POSTINUMERO</td><td>OSOITE</td><td>PUHELINNUMERO</td></tr>
		<tr><td>"${tilaukset.etunimi}"</td> <td>"${tilaukset.sukunimi}"</td> <td>"${tilaukset.sahkoposti}"</td>
		<td>"${tilaukset.postinro}"</td> <td>"${tilaukset.osoite}"</td><td>"${tilaukset.puh_nro}"</td></tr>
		</table>
		
	
		<b>PIZZAT</b>
		<table>
			<tr><td>TILAUS ID</td> <td>PIZZA ID</td> <td>PIZZAN NIMI</td><td>KOMMENTTI</td><td>MÄÄRÄ</td></tr>
			<c:forEach var="tilatutPizzat" items="${tilatutPizzat}">
				<c:if test="${tilatutPizzat.tilaus_id == count}" >
					
					<tr><td>"${tilatutPizzat.tilaus_id}"</td><td>"${tilatutPizzat.pizza_id}"</td>
					<td>"${tilatutPizzat.pizza_nimi}"</td><td>"${tilatutPizzat.kommentti}"</td>
					<td>"${tilatutPizzat.maara}"</td></tr>
					
				</c:if>
				
			</c:forEach>
		</table>
		<b>JUOMAT</b>
		<table>
			<tr><td>TILAUS ID</td> <td>JUOMA ID</td> <td>JUOMAN NIMI</td><td>MÄÄRÄ</td></tr>
			<c:forEach var="tilatutJuomat" items="${tilatutJuomat}">
				<c:if test="${tilatutJuomat.tilaus_id == count}" >
					
					<tr><td>"${tilatutJuomat.tilaus_id}"</td><td>"${tilatutJuomat.juoma_id}"</td>
					<td>"${tilatutJuomat.juoma_nimi}"</td><td>"${tilatutJuomat.maara}"</td></tr>
				</c:if>
			</c:forEach>
		</table>
	
	
	
	<button type="submit" value="${tilaukset.tilaus_id}" name="tilaus_id">VALMIS!</button>
	</div>
	</c:if>
	
	<c:set var="count" value="${count+1}"></c:set>

</c:forEach>


<a href="henkilokunnanServlet" id="logout">Takaisin pääsivulle</a>
   
</body>
</html>
