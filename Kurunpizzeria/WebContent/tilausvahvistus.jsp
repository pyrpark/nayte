<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="UTF-8">
<meta name="description" content="Kurun pizzerian internetsivut">
<meta name="author" content="">
<meta name="keywords" content="kurun pizzeria, pizza, kuru, yhteystiedot">
<meta name="viewport" content="width=device-width, intial-scale 1.0">

<link href="style.css" rel="stylesheet" type="text/css">
<link href="style_mobile.css" rel="stylesheet" type="text/css"
media="only screen and (max-width: 849px)">

<title>Yhteystiedot - Kurun pizzeria</title>

<script src="functions.js"></script>

</head>
<body>

<div id="login_ja_cart">
<img src="kuvat/login.png"  height="30px" onClick="showLogin()">
<a href="LogoutServlet" id="logout">LogOut</a>
<br>
<c:out value="${kirjautunut}"/>
</div>

<div id="login">
<form action="AsiakasLogin" method="post">
<p>Käyttäjätunnus:</p> <input type="text" name="user" placeholder="Käyttäjätunnus">
<p>Salasana:</p> <input type="password" name="password" placeholder="Salasana">
<input type="submit" value="Kirjaudu">
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

<a id="mobiili__btn"><img src="kuvat/menu_kuvake_mobile.jpg" style="width: 79px; "></a>

<ul>
<li><a href="AukioloServlet" id="nav_linkki">Etusivu</a></li>
<li><a href="MenuServlet" id="nav_linkki">Menu</a></li>
<li><a href="NaytaOstoskori" id="nav_linkki">Ostoskori</a></li>
<li><a href="YhteystiedotAukiolo" id="nav_linkki">Yhteystiedot</a></li>
</ul>
</nav>

<article id="tilausvahvistus">

<br>
<c:out value="${tilausvahvistus}"/>
<br>

<c:if test="${image==true}" >
<br/>
<img id="onnistumis_iconi" src="kuvat/onnistui.png" width="50px" height="50px"></img>
</c:if>

<c:if test="${image==false}" >
<br/>
<img id="onnistumis_iconi" src="kuvat/epaonnistui.png" width="100px" height="100px"></img>
</c:if>
 	
</article>

<footer id="ft">
<b>Puhelin:</b>
<p>(01) 234 5678</p>
<br>
<!-- <b>Osoite</b> -->
<!-- <p>Kurunkatu 1</p> -->
<!-- <p>03280 Kuru</p> -->
</footer>

</body>
</html>