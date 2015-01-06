<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="beans.User"%>
<%@page import="dao.UserDao"%>
<%@page import="beans.Book"%>
<%@page import="dao.BooksDao"%>
<%@page import="beans.Evaluation"%>
<%@page import="dao.EvaluationDao"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.io.PrintWriter"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modification d'une evaluation</title>
</head>
<body>
<%Evaluation e=(Evaluation) request.getAttribute("eModif");
Book b=BooksDao.find(e.getLivreId());
User u=UserDao.find(e.getUserId());%>
<h1>Modification d'une évaluation</h1> 
<h3>Un mail va être envoyé à l'administrateur pour qu'il analyse puis traite votre demande</h3>
Livre : "<%=b.getTitre()%>" de <%=b.getAuteur()%> (isbn : <%=b.getIsbn()%>)<br>
User : <%=u.getLogin()%>
<br><br>
<form method='post' action='ModifEvalByReader'>
Note globale : 
<%if (e.getNote()==1) {%>
<INPUT type= "radio" name="note" id="note" value="1" checked="true">1
<INPUT type= "radio" name="note" id="note" value="2">2
<INPUT type= "radio" name="note" id="note" value="3">3
<INPUT type= "radio" name="note" id="note" value="4">4
<%} 
else if (e.getNote()==2) {%>
<INPUT type= "radio" name="note" id="note" value="1">1
<INPUT type= "radio" name="note" id="note" value="2" checked="true">2
<INPUT type= "radio" name="note" id="note" value="3">3
<INPUT type= "radio" name="note" id="note" value="4">4
<%} 
else if (e.getNote()==3) {%>
<INPUT type= "radio" name="note" id="note" value="1">1
<INPUT type= "radio" name="note" id="note" value="2">2
<INPUT type= "radio" name="note" id="note" value="3" checked="true">3
<INPUT type= "radio" name="note" id="note" value="4">4
<%} 
else if (e.getNote()==4) {%>
<INPUT type= "radio" name="note" id="note" value="1">1
<INPUT type= "radio" name="note" id="note" value="2">2
<INPUT type= "radio" name="note" id="note" value="3">3
<INPUT type= "radio" name="note" id="note" value="4" checked="true">4
<%}%>

<br><br>
Qualité d'écriture : 
<%if (e.getQualite()==1) {%>
<INPUT type= "radio" name="qualite" id="qualite" value="1" checked="true">1
<INPUT type= "radio" name="qualite" id="qualite" value="2">2
<INPUT type= "radio" name="qualite" id="qualite" value="3">3
<INPUT type= "radio" name="qualite" id="qualite" value="4">4
<%}
else if (e.getQualite()==2) { %>
<INPUT type= "radio" name="qualite" id="qualite" value="1">1
<INPUT type= "radio" name="qualite" id="qualite" value="2" checked="true">2
<INPUT type= "radio" name="qualite" id="qualite" value="3">3
<INPUT type= "radio" name="qualite" id="qualite" value="4">4
<%}
else if (e.getQualite()==3) { %>
<INPUT type= "radio" name="qualite" id="qualite" value="1">1
<INPUT type= "radio" name="qualite" id="qualite" value="2">2
<INPUT type= "radio" name="qualite" id="qualite" value="3" checked="true">3
<INPUT type= "radio" name="qualite" id="qualite" value="4">4
<%}
else if (e.getQualite()==4) { %>
<INPUT type= "radio" name="qualite" id="qualite" value="1">1
<INPUT type= "radio" name="qualite" id="qualite" value="2">2
<INPUT type= "radio" name="qualite" id="qualite" value="3">3
<INPUT type= "radio" name="qualite" id="qualite" value="4" checked="true">4
<%}%>

<br><br>
Intérêt sur le sujet : 
<%if (e.getInteret()==1) {%>
<INPUT type= "radio" name="interet" id="interet" value="1" checked="true">1
<INPUT type= "radio" name="interet" id="interet" value="2">2
<INPUT type= "radio" name="interet" id="interet" value="3">3
<INPUT type= "radio" name="interet" id="interet" value="4">4
<%}
else if (e.getInteret()==2) { %>
<INPUT type= "radio" name="interet" id="interet" value="1">1
<INPUT type= "radio" name="interet" id="interet" value="2" checked="true">2
<INPUT type= "radio" name="interet" id="interet" value="3">3
<INPUT type= "radio" name="interet" id="interet" value="4">4
<%}
else if (e.getInteret()==3) { %>
<INPUT type= "radio" name="interet" id="interet" value="1">1
<INPUT type= "radio" name="interet" id="interet" value="2">2
<INPUT type= "radio" name="interet" id="interet" value="3" checked="true">3
<INPUT type= "radio" name="interet" id="interet" value="4">4
<%}
else if (e.getInteret()==4) { %>
<INPUT type= "radio" name="interet" id="interet" value="1">1
<INPUT type= "radio" name="interet" id="interet" value="2">2
<INPUT type= "radio" name="interet" id="interet" value="3">3
<INPUT type= "radio" name="interet" id="interet" value="4" checked="true">4
<%}%>
<br><br>

L'avez-vous lu jusqu'au bout ? 
<%if (e.getLecture()==0) {%>
<INPUT type= "radio" name="lecture" id="lecture"  value="0" checked="true">Non
<INPUT type= "radio" name="lecture" id="lecture" value="1">Oui
<%}
else if (e.getLecture()==1) { %>
<INPUT type= "radio" name="lecture" id="lecture"  value="0">Non
<INPUT type= "radio" name="lecture" id="lecture" value="1" checked="true">Oui
<%}%>
<br><br>

Souhaiteriez-vous lire un autre livre du même auteur ?
<%if (e.getSouhaitAuteur()==0) {%>
<INPUT type= "radio" name="souhaitAuteur" id="souhaitAuteur"  value="0" checked="true">Non
<INPUT type= "radio" name="souhaitAuteur" id="souhaitAuteur" value="1">Oui
<%}
else if (e.getSouhaitAuteur()==1) { %>
<INPUT type= "radio" name="souhaitAuteur" id="souhaitAuteur"  value="0">Non
<INPUT type= "radio" name="souhaitAuteur" id="souhaitAuteur" value="1" checked="true">Oui
<%}%>
<br><br>

Le recommanderiez-vous à un ami  ?
<%if (e.getRecommand()==0) {%>
<INPUT type= "radio" name="recommandation" id="recommandation" value="0" checked="true">Non
<INPUT type= "radio" name="recommandation" id="recommandation" value="1">Oui
<%}
else if (e.getRecommand()==1) {%>
<INPUT type= "radio" name="recommandation" id="recommandation" value="0">Non
<INPUT type= "radio" name="recommandation" id="recommandation" value="1" checked="true">Oui
<%}%>
<br><br>
<input type="hidden" name="idModif" id = "idModif" value="${eModif.id}"/>
<input type="hidden" name="idBook" id = "idBook" value=<%=e.getLivreId()%> />
<input type="hidden" name="idUser" id = "idUser" value=<%=e.getUserId()%> />
<input type='submit'  value='SEND'/>
<input type='reset'  value='CLEAR'/>
</form>





</body>
</html>