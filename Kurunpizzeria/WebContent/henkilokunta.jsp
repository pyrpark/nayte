<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="henkilokunnan_style.css" rel="stylesheet" type="text/css">
<title>Kurun henkilökunta</title>
</head>

<body>

<c:out value="${kirjautunut}"/>
<a href="LogoutAdminServlet" >LogOut</a>

<h1>Kurun henkilökunnan sivut</h1>

<!-- Tämä div antaa ilmoituksia (onnistuiko vai ei). Älä koske. -->
<div id="tapahtuma_ilmoitus" style="color: ${vari};">${ilmoitus}</div>

<h2>Tilatut tuotteet</h2>

<p>Tilaustyyppi 1 = kuljetus </p>
<p>Tilaustyyppi 2 = nouto </p>
<c:set var="count" value="${1}"></c:set>

<form action="MuutaPizzanStatus" method="post">
<c:forEach var="tilaukset" items="${tilaukset}">
<%-- <c:if test="${tilaukset.status_id == '1'}" > --%>
<c:if test="${tilaukset.tilaus_id == count && tilaukset.status_id == '1'}" >
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
<%-- 	</c:if> --%>
</c:forEach>
</form>

<a href="VanhatTilaukset" id="logout">Vanhat tilaukset</a>

<h2>Päivitä menu</h2>


<p><b>Ruokalista</b>
<br>

<c:forEach var="columnHeader" items="${pizzat}">
<c:set var="string1" value="${columnHeader} }"></c:set>
<c:set var="index" value="${fn:indexOf(string1,' ')}"></c:set>
<c:set var="string2" value="${fn:substring(string1, 0, index-1)}"></c:set>
<c:out value="${columnHeader}"/><br>
</c:forEach>



<form action="Pizzojen_muokkausServlet" id="menu_uusipizza" method="post">
<fieldset id="menu_paivitys">
<legend><b>Uusi pizza</b></legend>
  	<p>Kaikkien kenttien täyttäminen on pakollista.</p><br>
  	Pizzan id:<br>
    <input type="text" name="uusi_pizzaid"><br><br>
    Pizzan nimi:<br>
    <input type="text" name="uusi_nimi"><br><br>
    Pizzan koko:<br>
    <input type="radio" name="pizzauusi_paivitys" value="normaali">Normaali<br>
    <input type="radio" name="pizzauusi_paivitys" value="perhe">Perhe<br><br>
    Täytteet:<br>
    <input type="checkbox" name="tayte1" value="1">Ananas<br>
    <input type="checkbox" name="tayte2" value="2">Aurajuusto<br>
    <input type="checkbox" name="tayte3" value="3">Fetajuusto<br>
    <input type="checkbox" name="tayte4" value="4">Herkkusieni<br>
    <input type="checkbox" name="tayte5" value="5">Jauheliha<br>
    <input type="checkbox" name="tayte6" value="6">Katkarapu<br>
    <input type="checkbox" name="tayte7" value="7">Kebabliha<br>
    <input type="checkbox" name="tayte8" value="8">Kinkku<br>
    <input type="checkbox" name="tayte9" value="9">Oliivi<br>
    <input type="checkbox" name="tayte10" value="10">Paprika<br>
    <input type="checkbox" name="tayte11" value="11">Salami<br>
    <input type="checkbox" name="tayte12" value="12">Sipuli<br>
    <input type="checkbox" name="tayte13" value="13">Tomaatti<br>
    <input type="checkbox" name="tayte14" value="14">Tuplajuusto<br><br>
    Pizzan hinta:<br>
    <input type="text" name="pizza_hinta" min="0" max="999" step="0.05"><br><br>
    <input type="submit" value="Lähetä">
    <input type="reset" value="Tyhjennä">
</fieldset>
</form>
 
<form id="menu_poistapizza" action="Pizzojen_muokkausServlet" method="post">
<fieldset id="poistapizza_paivitys">
<legend><b>Poista pizza</b></legend>
  	Pizzan id:<br>
    <input type="text" name="poistapizza_id"><br><br>
    <input type="submit" value="Lähetä">
    <input type="reset" value="Tyhjennä">
 </fieldset>
 </form>

	<!-- Juomat saadaan listattua tällä -->
	<div id="juomalista">
			<b>Juomalista</b> <br>
			<table>
			<tr>
				<th>ID</th>
				<th>Nimi</th>
				<th>Hinta (euroa)</th>
			</tr>
			<c:forEach var="columnHeader" items="${juomat}">
				
				<tr>
				<td title="ID"><c:out value="${columnHeader.juoma_id}" /></td>
				<td title="Nimi"><c:out value="${columnHeader.juoma_nimi}" /></td>
				<td title="Hinta (Euroa)"><c:out value="${columnHeader.juoma_hinta}" /></td>
				</tr>
				
			</c:forEach>
			</table>
	</div>


	<form id="menu_uusijuoma" action="lisaa_juoma" method="post">
<fieldset id="uusijuoma_paivitys">
<legend><b>Uusi juoma</b></legend>
    Juoman id:<br>
    <input type="text" name="juoma_id"><br><br>
    Juoman nimi ja koko (l):<br>
    <input type="text" name="juoma_nimi"><br><br>
    Juoman hinta:<br>
    <input type="text" name="juoma_hinta"><br><br>
    <input type="submit" value="Lähetä">
    <input type="reset" value="Tyhjennä">
    <p>
	</p>
</fieldset>
</form>

 
<form id="menu_poistajuoma" action="poista_juoma" method="post">
<fieldset id="poistajuoma_paivitys">
<legend><b>Poista juoma</b></legend>
   	Juoman id:<br>
    <input type="text" name="poistajuoma_id"><br><br>
    <input type="submit" value="Lähetä">
    <input type="reset" value="Tyhjennä">
    <p>
 </p>
</fieldset>
</form>

 
<h2>Päivitä aukioloajat</h2> 
<!--    <p> -->
<%--  <c:if test="${not empty lasuUpdate}"> --%>
<%--    <c:out value="${lasuUpdate}"/> --%>
<%-- </c:if> --%>
<!-- </p> -->
<!-- <p> -->
<%--  <c:if test="${not empty mapeUpdate}"> --%>
<%--    <c:out value="${mapeUpdate}"/> --%>
<%-- </c:if> --%>
<!-- </p> -->
<!-- <p> -->
<%--  <c:if test="${not empty lounasUpdate}"> --%>
<%--    <c:out value="${lounasUpdate}"/> --%>
<%-- </c:if> --%>
<!-- </p> -->
<!--  <table> -->
<%-- <caption></caption> --%>
<!-- <thead> -->
<!-- 	<tr> -->
<!-- 		<td>ID</td> -->
<!-- 		<td>paiva</td> -->
<!-- 		<td>sisalto</td> -->
<!-- 	</tr> -->
<!-- </thead> -->
<!-- <tbody> -->
<c:forEach items="${aukiolo}" var="a">
<!-- 	<tr> -->
<%-- 		<td><c:out value="${a.id}"/></td> --%>
<%-- 		<td><c:out value="${a.paiva}"/></td> --%>
<%-- 		<td><c:out value="${a.sisalto}"/></td> --%>
		
		<c:out value="${a.paiva}"/><br>
		<c:out value="${a.sisalto}"/><br><br>
<!-- 	</tr> -->
</c:forEach>
<!-- </tbody> -->
<!-- </table> -->
<form action="lisaa_aukiolo" method="get">
<input type="text" name="ma_pe" placeholder="ma-pe"/><br>
<input type="text" name="la_su" placeholder="la-su"/><br>
<input type="text" name="lounas" placeholder="lounas"/><br>
<button type="submit">Lähetä</button>
</form>
 

</body>
</html>
