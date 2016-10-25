<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="UTF-8">
<meta name="description" content="Kurun pizzerian internetsivut">
<meta name="author" content="">
<meta name="keywords" content="kurun pizzeria, pizza, kuru">
<meta name="viewport" content="width=device-width, intial-scale 1.0">

<link href="style.css" rel="stylesheet" type="text/css">
<link href="style_mobile.css" rel="stylesheet" type="text/css"
media="only screen and (max-width: 849px)">
<title>Kurun pizzeria</title>

<script src="functions.js"></script>

</head>
<body>

<div id="login_ja_cart">
<c:out value="${tuotteetYhteensa}"/>
<a id="ostoskori" href="ostoskori.jsp"><img src="kuvat/cart1.png" width="30px" height="30px"></a>
<img src="kuvat/login.png"  height="30px" onClick="showLogin()">
<a href="LogoutServlet" id="logout">LogOut</a>
<br>
<c:out value="${kirjautunut}"/>
</div>

<div id="login">
<form action="AsiakasLogin" method="post">
<p>Käyttäjätunnus:</p> <input type="text" name="user" placeholder="Käyttäjätunnus">
<p>Salasana:</p> <input type="password" name="password" placeholder="Salasana">
<input id="kirjautbutton" type="submit" value="Kirjaudu">
</form>
<p>Uusi asiakas? Rekisteröidy<a href="rekisterointi.jsp" id="rekis_linkki">täällä.</a></p>
</div>

<div id="cart">

</div>

<header>
<img id="logo" src="kuvat/logo2.png"></img>
<h1 id="otsikko">Kurun pizzeria</h1>
</header>

<nav id="navigointi">
<ul>
<li><a href="AukioloServlet" id="nav_linkki">Etusivu</a></li>
<li><a href="MenuServlet" id="nav_linkki">Menu</a></li>
<li><a href="NaytaOstoskori" id="nav_linkki">Ostoskori</a></li>
<li><a href="YhteystiedotAukiolo" id="nav_linkki">Yhteystiedot</a></li>
</ul>
</nav>




<nav id="mobiili_navi">

<a id="mobiili__btn"><img src="kuvat/menuicon.png" style="width: 79px; "></a>

<ul>
<li><a href="AukioloServlet" id="nav_linkki">Etusivu</a></li>
<li><a href="MenuServlet" id="nav_linkki">Menu</a></li>
<li><a href="NaytaOstoskori" id="nav_linkki">Ostoskori</a></li>
<li><a href="YhteystiedotAukiolo" id="nav_linkki">Yhteystiedot</a></li>
</ul>
</nav>

<article id=ostoskorin_tuotteet>

<%-- <c:set var="maara" value=0"></c:set> --%>
<!-- TEHTÄVÄ VIELÄ HINTA COUNTTERI YHTEENSÄ HINTA -->

<form action="PoistaPizzaOstoskorista" method="post">
<table id=ostoskorin_table>
	<tr id=ostoskorin_header><td>Pizza nro</td> <td>nimi</td> <td>hinta/kpl</td> <td>määrä</td><tr>
<c:forEach items="${keratyt_pizzat}" var="o">
    <tr><td>${o.key}</td> <td>${o.value.pizza_nimi}</td> <td>${o.value.hinta}e</td> <td>${o.value.maara} kpl</td>
    <td><button id="poistapizzabut" type="submit" value="${o.key}" name="pizza_id">Poista pizza</button></td><tr>   
</c:forEach>
</table>
</form>

<form action="PoistaJuomaOstoskorista" method="post">
<table id=ostoskorin_table>
	<tr id=ostoskorin_header><td>Juoma nro</td> <td>nimi</td> <td>hinta/kpl</td> <td>määrä</td><tr>
<c:forEach items="${keratyt_juomat}" var="o">
    <tr><td>${o.key}</td> <td>${o.value.nimi}</td> <td>${o.value.hinta}e</td> <td>${o.value.maara} kpl</td>
    <td><button id="poistajuomabut" type="submit" value="${o.key}" name="juoma_id">Poista juoma</button></td><tr>   
</c:forEach>
</table>
</form>
<p>Hinta yhteensä: <c:out value="${hinta}"/> euroa. </p>
<br>
<p>Lisätietoja:</p>
<form  action="TilausServlet" method="get">
<textarea id="lisatietoja"  name="lisatietoja"></textarea><br>
<input TYPE=radio name=tilaustyyppi VALUE=1> Kuljetus 
<input TYPE=radio name=tilaustyyppi VALUE=2> Nouto
<br> 
<button id="valinta" type="submit" name="submit">Tilaa</button>
</form>

<c:out value="${Tyhja}"/>

</article>


<footer id="ft">
<b>Puhelin:</b>
<p>(01) 234 5678</p>
<br>
<b>Osoite</b>
<p>Kurunkatu 1</p>
<p>03280 Kuru</p>
</footer>
</body>

</html>
