<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modification d'une evaluation</title>
</head>
<body>

<h1>Modification d'une évaluation</h1> 
Livre : "${Titre}" de ${Auteur} (isbn : ${Isbn})<br>
User : ${Login}
<br><br>
<form method='post' action='ModifEval'>
Note globale : 
<%int note = (int)request.getAttribute("Note"); %>
<INPUT type= "radio" name="note" id="note" value="1" <%if (note==1) {%><%="checked"%><%}%> >1
<INPUT type= "radio" name="note" id="note" value="2" <%if (note==2) {%><%="checked"%><%}%> >2
<INPUT type= "radio" name="note" id="note" value="3" <%if (note==3) {%><%="checked"%><%}%> >3
<INPUT type= "radio" name="note" id="note" value="4" <%if (note==4) {%><%="checked"%><%}%> >4


<br><br>
Qualité d'écriture : 
<%int qualite = (int)request.getAttribute("Qualite");%>
<INPUT type= "radio" name="qualite" id="qualite" value="1" <%if (qualite==1) {%><%="checked"%><%}%> >1
<INPUT type= "radio" name="qualite" id="qualite" value="2" <%if (qualite==2) {%><%="checked"%><%}%> >2
<INPUT type= "radio" name="qualite" id="qualite" value="3" <%if (qualite==3) {%><%="checked"%><%}%> >3
<INPUT type= "radio" name="qualite" id="qualite" value="4" <%if (qualite==4) {%><%="checked"%><%}%> >4

<br><br>
Intérêt sur le sujet : 
<%int interet = (int)request.getAttribute("Interet"); %>
<INPUT type= "radio" name="interet" id="interet" value="1" <%if (interet==1) {%><%="checked"%><%}%> >1
<INPUT type= "radio" name="interet" id="interet" value="2" <%if (interet==2) {%><%="checked"%><%}%> >2
<INPUT type= "radio" name="interet" id="interet" value="3" <%if (interet==3) {%><%="checked"%><%}%> >3
<INPUT type= "radio" name="interet" id="interet" value="4" <%if (interet==4) {%><%="checked"%><%}%> >4

<br><br>

L'avez-vous lu jusqu'au bout ? 
<%int lecture = (int)request.getAttribute("Lecture"); %>
<INPUT type= "radio" name="lecture" id="lecture"  value="0" <%if (lecture==0) {%><%="checked"%><%}%> >Non
<INPUT type= "radio" name="lecture" id="lecture" value="1" <%if (lecture==1) {%><%="checked"%><%}%> >Oui

<br><br>

Souhaiteriez-vous lire un autre livre du même auteur ?
<%int souhaitauteur = (int)request.getAttribute("SouhaitAuteur"); %>
<INPUT type= "radio" name="souhaitAuteur" id="souhaitAuteur"  value="0" <%if (souhaitauteur==0) {%><%="checked"%><%}%> >Non
<INPUT type= "radio" name="souhaitAuteur" id="souhaitAuteur" value="1" <%if (souhaitauteur==1) {%><%="checked"%><%}%> >Oui

<br><br>

Le recommanderiez-vous à un ami  ?
<%int recommand = (int)request.getAttribute("Recommand"); %>
<INPUT type= "radio" name="recommandation" id="recommandation" value="0" <%if (recommand==0) {%><%="checked"%><%}%> >Non
<INPUT type= "radio" name="recommandation" id="recommandation" value="1" <%if (recommand==1) {%><%="checked"%><%}%> >Oui

<br><br>
<input type="hidden" name="idModif" id = "idModif" value="${IdModif}"/>
<input type="hidden" name="idBook" id = "idBook" value="${LivreId}" />
<input type="hidden" name="idUser" id = "idUser" value="${UserId}" />
<input type='submit'  value='SEND'/>
<input type='reset'  value='CLEAR'/>
</form>





</body>
</html>