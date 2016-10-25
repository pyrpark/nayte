<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

<title>Rekisteröinti onnistui - Kurun pizzeria</title>

<script src="functions.js"></script>

</head>
<body>


<div id="login_ja_cart">
<img src="kuvat/login.png"  height="30px" onClick="showLogin()">
<a href="ostoskori.jsp"><img src="kuvat/cart.png" width="50px" height="50px"></a>
<p style="padding-right:6px;"><span id="valitut_tuotteet">0</span> tuotetta valittu</p>
</div>

<div id="login">
<form action="" method="post">
<p>Käyttäjätunnus:</p> <input type="text" name="user" placeholder="Käyttäjätunnus">
<p>Salasana:</p> <input type="text" name="password" placeholder="Salasana">
<input type="submit" value="Submit">
</form>
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

<article id="rekOnnistui">

<p>Onneksi olkoon! Rekisteröityminen onnistui. Voit nyt kirjautua sisään!</p>


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