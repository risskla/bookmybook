<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="beans.Book"%>
<%@page import="java.util.List"%>
<%@page import="java.io.PrintWriter" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recherche d'un livre</title>
</head>
<body>
<h1>Recherche de livres</h1>
<form method="post" action="SearchBooks">
Mot clé contenu dans le titre :  <input type='text'  name='keyword'/> <input type='submit'  value='SEND'/><br> <br>

Livres suggérés : 

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
		if(obj!=null){
			List<Book> lb = (List<Book>)obj;
			System.out.println(lb); 
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
</form>
<%--For displaying Previous link except for the 1st page --%>
    <%  
    if(request.getAttribute("noOfPages")!=null && request.getAttribute("currentPage")!=null ) {
    	String keyword=request.getAttribute("keyword").toString(); // OK 
    String curPage1 = request.getAttribute("currentPage").toString();
    int curPage=Integer.parseInt(curPage1); 
		
    if (curPage != 1) { 
    	curPage--; %>
        <td><a href="SearchBooks?page=<%=curPage%>&keyword=<%=keyword%>">Previous</a></td>
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

                        <a href="?page=<%=i%>&keyword=<%=keyword%>"><%=i%></a>
        	<%
    		}
			
	%>
     
    <%--For displaying Next link --%>
     <% 
     if (curPage!= max) { //OK
    	 curPage++; 
     out.println("hello fin jsp "+curPage+" "+keyword); 
     %>
     
        <td><a href="SearchBooks?page=<%=curPage%>&keyword=<%=keyword%>">Next</a></td>
        <%} }%>
<br></br>
</body>
</html>