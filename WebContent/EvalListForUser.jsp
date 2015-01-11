<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="beans.Book"%>
<%@page import="beans.User"%>
<%@page import="beans.Evaluation"%>
<%@page import="beans.MatchBook"%>
<%@page import="dao.MatchBookDao"%>
<%@page import="dao.BooksDao"%>
<%@page import="dao.UserDao"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.io.PrintWriter" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>EvalList</title>
</head>
<body>
<h3>Liste des evaluations de l'utilisateur ${uInfo.login}</h3>
<table border="1" cellpadding="5" cellspacing="5">
<tr>
	<th>LIVREID</th>
	<th>USERID</th>
	<th>NOTE</th>
	<th>QUALITE</th>
	<th>INTERET</th>
	<th>LECTURE</th>
	<th>SOUHAITAUTEUR</th>
	<th>RECOMMANDATION</th>
	<th>IDENTIFIANT DU LIVRE SUGGERE</th>
	
</tr>
<%
		Object obj = request.getAttribute("EvalList");
		if(obj!=null){
			List<Evaluation> lb = (List<Evaluation>) obj; 
			for(Evaluation b : lb){
				MatchBook m=MatchBookDao.findByEval(b.getId()); 
				Book b2=BooksDao.find(m.getLivreSuggereId()); 
	%>
			<tr>
				<td><%=b.getLivreId()%></td>
				<td><%=b.getUserId()%></td>
				<td><%=b.getNote()%></td>
				<td><%=b.getQualite()%></td>
				<td><%=b.getInteret()%></td>
				<td><%=b.getLecture()%></td>
				<td><%=b.getSouhaitAuteur()%></td>
				<td><%=b.getRecommand()%></td>
				<td><%=b2.getId()%></td>
				
				<%
				int userId= (int)request.getSession().getAttribute("id");
				User u=UserDao.find(userId); 
				if(u.getRole()==1) {
				%>
				<td>
					<a href="GestionEval?action=supprimer&id=<%=b.getId()%>">Supprimer</a> <br></br>
					<a href="GestionEval?action=modifier&id=<%=b.getId()%>">Modifier</a>	
				</td>
				<% } %>
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
<br>
<%
				int userId= (int)request.getSession().getAttribute("id");
				User u=UserDao.find(userId); 
				if(u.getRole()==1) {
				%>
<a href='GestionUser'>Retour vers la liste des users</a>
<%} %>
</body>
</html>