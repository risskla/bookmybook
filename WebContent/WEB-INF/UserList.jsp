<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="beans.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.io.PrintWriter" %>

<%@include file="header.jsp"%>

<h1>Liste des users en base</h1>
<div class="row">
	<div class="col-md-9"></div>
	<div class="col-md-3">
		<form method="post" action="GestionUser">
		Mot clé contenu dans le login :  <input class="form-control" type='text'  name='keyword'/> <input class="form-control btn btn-info" type='submit'  value='Recherche'/>
		</form>
		<form method="post" action="GestionUser">
		<input class="form-control" type='submit'  value='Reinitialiser'/>
		</form>
	</div>
</div>
<br>

<table class="table table-striped" border="1" cellpadding="5" cellspacing="5">
<tr>
	<th>LOGIN</th>
	<th>MDP</th>
	<th>MAIL</th>
	<th>ROLE</th>
	<th>NOM</th>
	<th>PRENOM</th>
	<th>AGE</th>
	<th>SEXE</th>
	<th>ADRESSE</th>
	<th>CODEPOSTALE</th>
	<th>VILLE</th>
	<th>TELEPHONE</th>
	<th>ACTION</th>	
</tr>
<%
		Object obj = request.getAttribute("listeU");
		if(obj!=null){
			List<User> lb = (List<User>)obj;
			for(User b : lb){
	%>
			<tr>
				<td><%=b.getLogin()%></td>
				<td><%=b.getMdp()%></td>
				<td><%=b.getMail()%></td>
				<td><%=b.getRole()%></td>
				<td><%=b.getNom()%></td>
				<td><%=b.getPrenom()%></td>
				<td><%=b.getAge()%></td>
				<td><%=b.getSexe()%></td>
				<td><%=b.getAdresse()%></td>
				<td><%=b.getCodepostale()%></td>
				<td><%=b.getVille()%></td>
				<td><%=b.getTelephone()%></td>
				

				<td>
					<a href="GestionUser?action=supprimer&id=<%=b.getId()%>">Supprimer</a>
					<a href="GestionUser?action=modifier&id=<%=b.getId()%>">Modifier</a>
					<a href="GestionUser?action=affichEvalForUserAdmin&id=<%=b.getId()%>">Afficher évaluations</a>	
				</td>
			</tr>
				<%
			}	
		}
		else {
			%>
				Soit la table est vide, soit on essaie d'accéder à la page sans passer par le servlet GestionUser!
			
			<%
		}
	%>
</table>
<br>

<%--For displaying Previous link except for the 1st page --%>
    <% 
    if(request.getAttribute("noOfPages")!=null && request.getAttribute("currentPage")!=null ) {
    System.out.println("suite JSP"); 
    String curPage1 = request.getAttribute("currentPage").toString();
    int curPage=Integer.parseInt(curPage1); 
    System.out.println(curPage); 
		
    if (curPage != 1) { %>
        <td><a href="GestionUser?page=${currentPage - 1}"><span class="glyphicon glyphicon-chevron-left"></span></a></td>
        <%} %>
 
    <%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>
           <% 
            int max; 
    		String max1=request.getAttribute("noOfPages").toString(); 
    		max=Integer.parseInt(max1); 
    		PrintWriter out2 = response.getWriter();
           
    		for (int i=1; i<=max; i++)
    		{  %>

                        <a href="?page=<%=i%>"><%=i%></a>
        	<%
    		}
			
	%>
     
    <%--For displaying Next link --%>
     <% 
     if (curPage!= max) { %>
        <td><a href="GestionUser?page=${currentPage + 1}"><span class="glyphicon glyphicon-chevron-right"></span></a></td>
        <%} }%>
<br></br>
<a href="GestionUser?action=ajouter">Ajouter un user</a>

<%@include file="footer.jsp"%>