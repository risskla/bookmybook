<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@include file="header.jsp"%>


<h1>Bienvenue dans la vue administrateur</h1>
> Gestion des users : <a href="GestionUser"> Consulter la liste de tous les users (contient  : Chercher un user) </a><br>
> Gestion des livres : <a href="GestionBooks"> Consulter la liste de tous les livres (contient  : Chercher un book)</a> <br>
> Gestion des évaluations : <a href="GestionEval?action=afficher"> Consulter la liste de toutes les evaluations </a>/ <a href="UpdateMatchs">Mise à jour de toutes les evaluations</a><br>
> Gestion des matches : <a href="ChoixAlgoServlet?action=formalgo">Choisir les algorithmes</a> / <a href="ChoixAlgoServlet?action=afficher">Consulter l'historique des paramètres de match<br>
<br>

<%@include file="footer.jsp"%>