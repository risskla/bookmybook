<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="beans.Book"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.io.PrintWriter" %>
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
<br>

<table border="1" cellpadding="5" cellspacing="5">
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
System.out.println("JSP1");
System.out.println(request.getAttribute("listeB")); 
System.out.println(request.getAttribute("noOfPages"));
System.out.println(request.getAttribute("currentPage"));
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
<br>

<%--For displaying Previous link except for the 1st page --%>
    <% 
    if(request.getAttribute("noOfPages")!=null && request.getAttribute("currentPage")!=null ) {
    System.out.println("suite JSP"); 
    String curPage1 = request.getAttribute("currentPage").toString();
    int curPage=Integer.parseInt(curPage1); 
    System.out.println(curPage); 
		
    if (curPage != 1) { %>
        <td><a href="GestionBooks?page=${currentPage - 1}">Previous</a></td>
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
        <td><a href="GestionBooks?page=${currentPage + 1}">Next</a></td>
        <%} }%>
<br></br>
<a href="createBookForm.jsp">Ajouter un livre</a>

</body>
</html>