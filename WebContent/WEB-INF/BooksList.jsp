<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="beans.Book"%>
<%@page import="dao.UserDao"%>
<%@page import="beans.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.io.PrintWriter" %>

<%@include file="header.jsp"%>


<h1>Liste des livres en base</h1>

<div class="row">
	<div class="col-md-3">
		<h4>Trier l'affichage des livres par : </h4>
		<form method="post" action="GestionBooks">
		<div class="radio">
		<label>
			<input name="sortType" type="radio" value="1"/>Titre
		</label>
		</div>
		<div class="radio">
		<label>
			<input name="sortType" type="radio" value="2"/>ISBN
		</label>
		</div>
			<input type="hidden" name="action" value="sort" />
			<input class="btn btn-info" type="submit" value="Afficher" />
		</form>
		</div>
	<div class="col-md-6"></div>
	<div class="col-md-3">
		<form method="post" action="GestionBooks">
		Mot clé contenu dans le titre :  <input class="form-control" type='text'  name='keyword'/> <input class="form-control btn btn-info" type='submit'  value='Recherche'/>
		</form>
		<form method="post" action="GestionBooks">
		<input class="form-control" type='submit'  value='Reinitialiser'/>
		</form>
	</div>
</div>


<br>

<table class="table table-striped" border="1" cellpadding="5" cellspacing="5">
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
			//User u= get le user actuel
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
				
				<%
					if((int)request.getAttribute("role")==1) {
				%>
					<a href="GestionBooks?action=supprimer&id=<%=b.getId()%>">Supprimer</a><br></br>
					<a href="GestionBooks?action=modifier&id=<%=b.getId()%>">Modifier</a><br></br>
					<a href="GestionEval?action=affichEvalForBook&idBook=<%=b.getId()%>">Afficher les evaluations pour ce livre</a><br></br>
				<% } %>
					<a href="GestionBooks?action=evaluer&id=<%=b.getId()%>">Evaluer</a>
				</td>
			</tr>
				<%
			}
			
			
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
        <td><a href="GestionBooks?page=${currentPage - 1}"><span class="glyphicon glyphicon-chevron-left"></span>
        </a></td>
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
        <td><a href="GestionBooks?page=${currentPage + 1}"><span class="glyphicon glyphicon-chevron-right"></span>
        </a></td>
        <%} }%>
<br></br>
<a href="GestionBooks?action=ajouter">Ajouter un livre</a>

<%@include file="footer.jsp"%>