<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@include file="header.jsp"%>

<h1>Choix des algorithmes de Matches</h1>

<form method='post' action='ChoixAlgoServlet'>
Pour suggérer un livre à lire (MatchBook) : <br> 
<INPUT type= "radio" name="algoMatchBook" value="1">Algorithme 1<br> 
<INPUT type= "radio" name="algoMatchBook" value="2">Algorithme 2 (au hasard)<br> 
<br> <br> 
Pour définir les lecteurs le plus loin et le plus proche (MatchReader) : <br> 
<INPUT type= "radio" name="algoMatchReader" value="1">Algorithme 1<br> 
<INPUT type= "radio" name="algoMatchReader" value="2">Algorithme 2 (au hasard)<br><br> 
<input class="btn btn-default" type='submit'  value='SEND'/><br>
<br><br>
<a href="SwitchRole?action=adminmenu">Retour au menu administrateur</a>
</form>

<%@include file="footer.jsp"%>