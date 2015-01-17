<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@include file="header.jsp"%>

	<h1>Livre ${operation} dans la base avec succes ! </h1>
	<p> Titre : ${titre}</p> 
	<p> Auteur : ${auteur}</p>
	<p> Editeur : ${editeur}</p>
	<p> ISBN : ${isbn}</p>
	<p> Pays : ${pays}</p>
	<p> Genre : ${genre}</p>
	<p> Annee de publication : ${anneePubli}</p>
	<p> Resume : ${resume}</p>
	
	<a href='GestionBooks'> Retour vers la liste des livres</a> 

<%@include file="footer.jsp"%>