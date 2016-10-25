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

<!-- <img src="menu/menu.jpg" id="menu"></img> -->

<div id="pizzalista">
<p><b>RUOKALISTA</b>
<br>

<c:forEach var="columnHeader" items="${valmiitpizzat}">
<c:set var="string1" value="${columnHeader} }"></c:set>
<c:set var="index" value="${fn:indexOf(string1,' ')}"></c:set>
<c:set var="string2" value="${fn:substring(string1, 0, index-1)}"></c:set>
<br>

<form action="OstoskoriServlet" method="post">
<pre id ="pizzablock">
<c:out value="${columnHeader}"/>
<c:if test="${string2 == '15'}" >

<select name="tayte1">
		<option value=""><c:out value="" /></option>
    <c:forEach items="${Taytelista}" var="tayte">
        <option value="${tayte.nimi}"><c:out value="${tayte.nimi}" /></option>
    </c:forEach>
</select>

<select name="tayte2">
		<option value=""><c:out value="" /></option>
    <c:forEach items="${Taytelista}" var="tayte">
        <option value="${tayte.nimi}"><c:out value="${tayte.nimi}" /></option>
    </c:forEach>
</select>

<select name="tayte3">
		<option value=""><c:out value="" /></option>
    <c:forEach items="${Taytelista}" var="tayte">
        <option value="${tayte.nimi}"><c:out value="${tayte.nimi}" /></option>
    </c:forEach>
</select>

<select name="tayte4">
		<option value=""><c:out value="" /></option>
    <c:forEach items="${Taytelista}" var="tayte">
        <option value="${tayte.nimi}"><c:out value="${tayte.nimi}" /></option>
    </c:forEach>
</select>
</c:if>
<input TYPE=checkbox name=mauste2 VALUE=Valkosipuli> Valkosipuli 
<input TYPE=checkbox name=mauste1 VALUE=Oregano> Oregano <br>
<button id="valinta" type="submit" value="${string2}" name="pizza_id">Lisää ostoskoriin</button>
</pre>
</form>


</c:forEach>
</p>
</div>

<div id="juomalista">
<p><b>JUOMALISTA</b>
<br>

<c:forEach var="columnHeader" items="${valmiitjuomat}">
<c:set var="string1" value="${columnHeader} }"></c:set>
<c:set var="index" value="${fn:indexOf(string1,' ')}"></c:set>
<c:set var="string2" value="${fn:substring(string1, 0, index-1)}"></c:set>

<br>

<form action="OstoskoriServlet" method="post">
<pre id ="pizzablock">
<c:out value="${columnHeader}"/><br>
<button id="valinta" type="submit" value="${string2}" name="juoma_id">Lisää ostoskoriin</button>
</pre>
</form>

</c:forEach>
</p>
</div>

<!-- Eclipsen valituksesta huolimatta lataus toimii oikein! -->
<p><a id="latauslinkki" href="menu/Menu.pdf" download>Lataa ruokalistamme tästä!</a></p>

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
