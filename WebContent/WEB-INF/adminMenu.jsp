<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!-- 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu administrateur</title>
</head>
<body>-->

<%@include file="header_admin.jsp"%>


<h1>Bienvenue dans la vue administrateur</h1>
> Gestion des users : <a href="GestionUser"> Consulter la liste de tous les users (contient  : Chercher un user) </a><br>
> Gestion des livres : <a href="GestionBooks"> Consulter la liste de tous les livres </a> / <a href="SearchBooks"> Chercher un livre par mot clé </a> <br>
> Gestion des évaluations : <a href="GestionEval?action=afficher"> Consulter la liste de toutes les evaluations </a><br>
> Gestion des matches : <a href="ChoixAlgoServlet">Choisir les algorithmes</a> / <a href="ChoixAlgoServlet?action=afficher">Consulter l'historique des paramètres de match<br>
<br>

<%@include file="footer.jsp"%>