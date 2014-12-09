<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="beans.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Liste des livres</title>
</head>
<body>
<h1>Liste des livres en base</h1>
<h4>Trier l'affichage des livres par : </h4>
<form method="post" action="GestionBooks">
	<input name="sortType" type="radio" value="1"/>Titre
	<input name="sortType" type="radio" value="2"/>ISBN
	<input type="hidden" name="action" value="sort" />
	<input type="submit" value="Afficher" />
</form>

<table border="1">
<tr>
	<th>TITRE</th>
	<th>AUTEUR</th>
	<th>EDITEUR</th>
	<th>ISBN</th>
	<th>PAYS</th>
	<th>GENRE</th>
	<th>ANNEE DE PUBLICATION</th>
	<th>RESUME</th>
	<th>ACTION SOUHAITEE</th>
	
</tr>
<%
		Object obj = request.getAttribute("listeB");
		if(obj!=null){
			List<Book> lb = (List<Book>)obj;
			for(Book b : lb){
	%>
			<tr>
				<td><%=b.getTitre()%></td>
				<td><%=b.getAuteur()%></td>
				<td><%=b.getEditeur()%></td>
				<td><%=b.getIsbn()%></td>
				<td><%=b.getPays()%></td>
				<td><%=b.getGenre()%></td>
				<td><%=b.getAnneePubli()%></td>
				<td><%=b.getResume()%></td>
				<td>
					<a href="GestionBooks?action=supprimer&id=<%=b.getId()%>">Supprimer</a>
					<a href="GestionBooks?action=modifier&id=<%=b.getId()%>">Modifier</a>	
				</td>
			</tr>
	<%
			}
			
			
		}
	%>
</table>

</body>
</html>