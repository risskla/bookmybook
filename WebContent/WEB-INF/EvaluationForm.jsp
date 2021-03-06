<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="beans.Book"%>
<%@page import="beans.Evaluation"%>
<%@page import="dao.EvaluationDao"%>

<%@include file="header.jsp"%>

<% Object obj=request.getAttribute("bEval");
Book b=(Book) obj; %>

<h1>Evaluation du livre : 
"<%=b.getTitre()%>" de <%=b.getAuteur()%></h1>
<br><br>

<form method='post' action='AddEval'>
Note globale : 
<INPUT type= "radio" name="note" value="1">1
<INPUT type= "radio" name="note" value="2">2
<INPUT type= "radio" name="note" value="3">3
<INPUT type= "radio" name="note" value="4">4
<br><br>
Qualit� d'�criture : 
<INPUT type= "radio" name="qualite" value="1">1
<INPUT type= "radio" name="qualite" value="2">2
<INPUT type= "radio" name="qualite" value="3">3
<INPUT type= "radio" name="qualite" value="4">4
<br><br>
Int�r�t sur le sujet : 
<INPUT type= "radio" name="interet" value="1">1
<INPUT type= "radio" name="interet" value="2">2
<INPUT type= "radio" name="interet" value="3">3
<INPUT type= "radio" name="interet" value="4">4
<br><br>
L'avez-vous lu jusqu'au bout ? 
<INPUT type= "radio" name="lecture" value="0">Non
<INPUT type= "radio" name="lecture" value="1">Oui
<br><br>

Souhaiteriez-vous lire un autre livre du m�me auteur ?
<INPUT type= "radio" name="souhaitAuteur" value="0">Non
<INPUT type= "radio" name="souhaitAuteur" value="1">Oui
<br><br>

Le recommanderiez-vous � un ami  ?
<INPUT type= "radio" name="recommandation" value="0">Non
<INPUT type= "radio" name="recommandation" value="1">Oui
<br><br>

<input type="hidden" name="idBook" id = "idBook" value="${bEval.id}"/>
<input class="btn btn-success" type='submit'  value='Valider'/>
<input class="btn btn-default" type='reset'  value='R�initialiser'/>
</form>

<%@include file="footer.jsp"%>