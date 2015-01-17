<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@include file="header.jsp"%>

<h2><span class="label label-default">Modifier d'une évaluation de l'utilisateur  ${Login}</span></h2><br>
<h3>
	<span class="label label-info">Livre : "${Titre}" de ${Auteur} (ISBN : ${Isbn})
	</span>
</h3>

<br><br>
<form method='post' action='ModifEval'>
	<div class="input-group">
		Note globale :
		<%int note = (int)request.getAttribute("Note"); %>
		<INPUT type= "radio" name="note" id="note" value="1" <%if (note==1) {%><%="checked"%><%}%> >1
		<INPUT type= "radio" name="note" id="note" value="2" <%if (note==2) {%><%="checked"%><%}%> >2
		<INPUT type= "radio" name="note" id="note" value="3" <%if (note==3) {%><%="checked"%><%}%> >3
		<INPUT type= "radio" name="note" id="note" value="4" <%if (note==4) {%><%="checked"%><%}%> >4
	</div><br>
	
	<div class="input-group">
		Qualité d'écriture : 
		<%int qualite = (int)request.getAttribute("Qualite");%>
		<INPUT type= "radio" name="qualite" id="qualite" value="1" <%if (qualite==1) {%><%="checked"%><%}%> >1
		<INPUT type= "radio" name="qualite" id="qualite" value="2" <%if (qualite==2) {%><%="checked"%><%}%> >2
		<INPUT type= "radio" name="qualite" id="qualite" value="3" <%if (qualite==3) {%><%="checked"%><%}%> >3
		<INPUT type= "radio" name="qualite" id="qualite" value="4" <%if (qualite==4) {%><%="checked"%><%}%> >4
	</div><br>
	
	<div class="input-group">
		Intérêt sur le sujet : 
		<%int interet = (int)request.getAttribute("Interet"); %>
		<INPUT type= "radio" name="interet" id="interet" value="1" <%if (interet==1) {%><%="checked"%><%}%> >1
		<INPUT type= "radio" name="interet" id="interet" value="2" <%if (interet==2) {%><%="checked"%><%}%> >2
		<INPUT type= "radio" name="interet" id="interet" value="3" <%if (interet==3) {%><%="checked"%><%}%> >3
		<INPUT type= "radio" name="interet" id="interet" value="4" <%if (interet==4) {%><%="checked"%><%}%> >4
	</div><br>
	
	<div class="input-group">
		L'avez-vous lu jusqu'au bout ? 
		<%int lecture = (int)request.getAttribute("Lecture"); %>
		<INPUT type= "radio" name="lecture" id="lecture"  value="0" <%if (lecture==0) {%><%="checked"%><%}%> >Non
		<INPUT type= "radio" name="lecture" id="lecture" value="1" <%if (lecture==1) {%><%="checked"%><%}%> >Oui
	</div><br>
	
	<div class="input-group">
		Souhaiteriez-vous lire un autre livre du même auteur ?
		<%int souhaitauteur = (int)request.getAttribute("SouhaitAuteur"); %>
		<INPUT type= "radio" name="souhaitAuteur" id="souhaitAuteur"  value="0" <%if (souhaitauteur==0) {%><%="checked"%><%}%> >Non
		<INPUT type= "radio" name="souhaitAuteur" id="souhaitAuteur" value="1" <%if (souhaitauteur==1) {%><%="checked"%><%}%> >Oui
	</div><br>
	
	<div class="input-group">
		Le recommanderiez-vous à un ami  ?
		<%int recommand = (int)request.getAttribute("Recommand"); %>
		<INPUT type= "radio" name="recommandation" id="recommandation" value="0" <%if (recommand==0) {%><%="checked"%><%}%> >Non
		<INPUT type= "radio" name="recommandation" id="recommandation" value="1" <%if (recommand==1) {%><%="checked"%><%}%> >Oui
	</div><br>
	
	<input type="hidden" name="idModif" id = "idModif" value="${IdModif}"/>
	<input type="hidden" name="idBook" id = "idBook" value="${LivreId}" />
	<input type="hidden" name="idUser" id = "idUser" value="${UserId}" />
	<br>
	<input type='submit' class="btn btn-success" value='SEND'/>
	<input type='reset' class="btn btn-default" value='CLEAR'/>
</form>


<%@include file="footer.jsp"%>