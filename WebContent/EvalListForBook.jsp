<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="beans.Evaluation"%>
<%@page import="beans.User"%>
<%@page import="beans.Book"%>
<%@page import="dao.BooksDao"%>
<%@page import="beans.MatchBook"%>
<%@page import="dao.MatchBookDao"%>
<%@page import="beans.MatchReader"%>
<%@page import="dao.MatchReaderDao"%>
<%@page import="dao.UserDao"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.io.PrintWriter" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Liste des evaluations</title>
</head>
<body>
<h1>Liste des évaluations en base pour le livre : 
<% String idBookCh= request.getAttribute("idBook").toString(); 
int idBook=Integer.parseInt(idBookCh); 
Book b1= BooksDao.find(idBook); %>
"<%=b1.getTitre()%>" de <%=b1.getAuteur()%> (isbn : <%=b1.getIsbn()%>) </h1>

<%Object obj = request.getAttribute("listeE");
List<Evaluation> le = (List<Evaluation>)obj;
if (le.isEmpty()) {%> 
<h3>Aucune évalution enregistrée pour ce livre</h3>
<a href="BooksList.jsp">Retour à la liste des livres</a>
<%}
else {%>

<table border="1" cellpadding="5" cellspacing="5">
<tr>
	<th>USER</th>
	<th>LIVRE</th>
	<th>NOTE GLOBALE</th>
	<th>QUALITE D'ECRITURE</th>
	<th>INTERET</th>
	<th>LECTURE JUSQU'AU BOUT</th>
	<th>SOUHAIT POUR LIRE UN LIVRE DU MEME AUTEUR</th>
	<th>RECOMMANDATION</th>
	<th>LIVRE SUGGERE</th>
	<th>USER LE PLUS PROCHE</th>
	<th>USER LE PLUS LOIN</th>
	<th>ACTION SOUHAITEE</th>
	
</tr>
<%
			for(Evaluation e : le){
				User u=UserDao.find(e.getUserId()); 
				Book b=BooksDao.find(e.getLivreId()); 
				
				MatchBook m=MatchBookDao.findByEval(e.getId()); 
				Book b2=BooksDao.find(m.getLivreSuggereId()); 
				MatchReader m2=MatchReaderDao.findByEval(e.getId());
	%>
			<tr>
				<td><%=u.getLogin()%></td>
				<td><%=b.getTitre()%> de <%=b.getAuteur() %> (numéro : <%=b.getId()%>) </td>
				<td><%=e.getNote()%></td>
				<td><%=e.getQualite()%></td>
				<td><%=e.getInteret()%></td>
				<td><%=e.getLecture()%></td>
				<td><%=e.getSouhaitAuteur()%></td>
				<td><%=e.getRecommand()%></td>
				
				<% if (m!=null) { %>
				<td><%=b2.getTitre()%> de <%=b2.getAuteur() %> (numéro : <%=b2.getId()%>)</td>
				<%} 
				else {%>
				<td>aucun</td>
				<%} %>
				
				<% if (m2!=null) { 
				User up=UserDao.find(m2.getUserPlusProcheId()); 
				User ul=UserDao.find(m2.getUserPlusLoinId()); %>
				<td><%=up.getLogin()%></td>
				<td><%=ul.getLogin()%></td>
				<%} 
				else {%>
				<td>aucun</td>
				<td>aucun</td>
				<%} %>
				
				
				<td>
					<a href="GestionEval?action=supprimer&id=<%=e.getId()%>">Supprimer</a>
					<a href="GestionEval?action=modifier&id=<%=e.getId()%>">Modifier</a>
				</td>
			</tr>
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
        <td><a href="GestionEval?page=${currentPage - 1}">Previous</a></td>
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
        <td><a href="GestionEval?page=${currentPage + 1}">Next</a></td>
        <%} }%>
<br></br>
<a href="BooksList.jsp">Retour à la liste des livres</a>
<%} %>
</body>
</html>