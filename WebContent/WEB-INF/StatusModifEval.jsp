<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@include file="header.jsp"%>

	<h1>

	<%
		if(request.getAttribute("action")=="modifadmin"){ 
	%>
			Evaluation modifiee dans la base avec succes !
	<%
		}
		else
		{
			if(request.getAttribute("action")=="modifreader"){
	%>
				Votre demande a bien ete envoyee a l'administrateur !
	<% 
			}	
		}
	%>
	</h1>
	<p> Livre (isbn) : ${isbn}</p> 
	<p> User (login) : ${login}</p>
	<p> Note globale : ${note}</p>
	<p> Qualité d'écriture : ${qualite}</p>
	<p> Intérêt : ${interet}</p>
	<p> Lecture jusqu'à la fin : ${lecture}</p>
	<p> Souhait pour livre un livre du même auteur : ${souhaitAuteur}</p>
	<p> Livre recommandé : ${recommandation}</p>


	<%
		if(request.getAttribute("action")=="modifadmin"){ 
	%>
			<a href='GestionEval?action=afficher'>Retour vers la liste des evaluations</a>
	<%
		}
		else
		{
			if(request.getAttribute("action")=="modifreader"){
	%>
				<a href='GestionBooks'>Retour à la liste des livres</a>
	<%
			}
		}
	%>

<%@include file="footer.jsp"%>