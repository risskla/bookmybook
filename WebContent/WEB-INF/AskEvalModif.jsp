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

<%@include file="header.jsp"%>

<%Evaluation e=(Evaluation) request.getAttribute("eModif");
Book b=BooksDao.find(e.getLivreId());
User u=UserDao.find(e.getUserId());%>
<h1>Modification d'une évaluation</h1> 
<h3>Un mail va être envoyé à l'administrateur pour qu'il analyse puis traite votre demande</h3>
Livre : "<%=b.getTitre()%>" de <%=b.getAuteur()%> (isbn : <%=b.getIsbn()%>)<br>
User : <%=u.getLogin()%>
<br><br>
<form method='post' action='ModifEvalByReader'>
	<div class="input-group">
		Note globale :
		<%int note = e.getNote(); %>
		<INPUT type= "radio" name="note" id="note" value="1" <%if (note==1) {%><%="checked"%><%}%> >1
		<INPUT type= "radio" name="note" id="note" value="2" <%if (note==2) {%><%="checked"%><%}%> >2
		<INPUT type= "radio" name="note" id="note" value="3" <%if (note==3) {%><%="checked"%><%}%> >3
		<INPUT type= "radio" name="note" id="note" value="4" <%if (note==4) {%><%="checked"%><%}%> >4
	</div><br>
	
	<div class="input-group">
		Qualité d'écriture : 
		<%int qualite = e.getQualite();%>
		<INPUT type= "radio" name="qualite" id="qualite" value="1" <%if (qualite==1) {%><%="checked"%><%}%> >1
		<INPUT type= "radio" name="qualite" id="qualite" value="2" <%if (qualite==2) {%><%="checked"%><%}%> >2
		<INPUT type= "radio" name="qualite" id="qualite" value="3" <%if (qualite==3) {%><%="checked"%><%}%> >3
		<INPUT type= "radio" name="qualite" id="qualite" value="4" <%if (qualite==4) {%><%="checked"%><%}%> >4
	</div><br>
	
	<div class="input-group">
		Intérêt sur le sujet : 
		<%int interet = e.getInteret(); %>
		<INPUT type= "radio" name="interet" id="interet" value="1" <%if (interet==1) {%><%="checked"%><%}%> >1
		<INPUT type= "radio" name="interet" id="interet" value="2" <%if (interet==2) {%><%="checked"%><%}%> >2
		<INPUT type= "radio" name="interet" id="interet" value="3" <%if (interet==3) {%><%="checked"%><%}%> >3
		<INPUT type= "radio" name="interet" id="interet" value="4" <%if (interet==4) {%><%="checked"%><%}%> >4
	</div><br>
	
	<div class="input-group">
		L'avez-vous lu jusqu'au bout ? 
		<%int lecture = e.getLecture(); %>
		<INPUT type= "radio" name="lecture" id="lecture"  value="0" <%if (lecture==0) {%><%="checked"%><%}%> >Non
		<INPUT type= "radio" name="lecture" id="lecture" value="1" <%if (lecture==1) {%><%="checked"%><%}%> >Oui
	</div><br>
	
	<div class="input-group">
		Souhaiteriez-vous lire un autre livre du même auteur ?
		<%int souhaitauteur = e.getSouhaitAuteur(); %>
		<INPUT type= "radio" name="souhaitAuteur" id="souhaitAuteur"  value="0" <%if (souhaitauteur==0) {%><%="checked"%><%}%> >Non
		<INPUT type= "radio" name="souhaitAuteur" id="souhaitAuteur" value="1" <%if (souhaitauteur==1) {%><%="checked"%><%}%> >Oui
	</div><br>
	
	<div class="input-group">
		Le recommanderiez-vous à un ami  ?
		<%int recommand = e.getRecommand(); %>
		<INPUT type= "radio" name="recommandation" id="recommandation" value="0" <%if (recommand==0) {%><%="checked"%><%}%> >Non
		<INPUT type= "radio" name="recommandation" id="recommandation" value="1" <%if (recommand==1) {%><%="checked"%><%}%> >Oui
	</div><br>
<input type="hidden" name="idModif" id = "idModif" value="${eModif.id}"/>
<input type="hidden" name="idBook" id = "idBook" value=<%=e.getLivreId()%> />
<input type="hidden" name="idUser" id = "idUser" value=<%=e.getUserId()%> />
<input class="btn btn-success" type='submit'  value='SEND'/>
<input class="btn btn-default" type='reset'  value='CLEAR'/>
</form>

<%@include file="footer.jsp"%>