<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="beans.Book"%>
<%@page import="beans.User"%>
<%@page import="beans.Evaluation"%>
<%@page import="beans.MatchBook"%>
<%@page import="dao.MatchBookDao"%>
<%@page import="beans.MatchReader"%>
<%@page import="dao.MatchReaderDao"%>
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
	<th>LIVRE</th>
	<th>USER</th>
	<th>NOTE</th>
	<th>QUALITE</th>
	<th>INTERET</th>
	<th>LECTURE</th>
	<th>SOUHAITAUTEUR</th>
	<th>RECOMMANDATION</th>
	<th>LIVRE SUGGERE</th>
	<th>USER LE PLUS PROCHE</th>
	<th>USER LE PLUS LOIN</th>
	<%
	MatchBook m=null; 
	MatchReader m2=null; 
	Book bSug=null; 
	int userSource= (int)request.getSession().getAttribute("id");
	User u=UserDao.find(userSource); 
	if(u.getRole()==1) {%>
	<th>ACTION SOUHAITEE</th>
	<%} %>
</tr>
<%
		Object obj = request.getAttribute("EvalList");
		if(obj!=null){
			List<Evaluation> lb = (List<Evaluation>) obj; 
			for(Evaluation b : lb){
				m=MatchBookDao.findByEval(b.getId()); 
				if (m!=null) { bSug=BooksDao.find(m.getLivreSuggereId()); }
				m2=MatchReaderDao.findByEval(b.getId()); 
				
	%>
			<tr>
				<%Book b3=BooksDao.find(b.getLivreId()); %>
				<td><%=b3.getTitre()%> de <%=b3.getAuteur() %> (numéro : <%=b3.getId()%>) </td>
				<td><%=b.getUserId()%></td>
				<td><%=b.getNote()%></td>
				<td><%=b.getQualite()%></td>
				<td><%=b.getInteret()%></td>
				<td><%=b.getLecture()%></td>
				<td><%=b.getSouhaitAuteur()%></td>
				<td><%=b.getRecommand()%></td>
				
				<% if (m!=null) { %>
				<td><%=bSug.getTitre()%> de <%=bSug.getAuteur() %> (numéro : <%=bSug.getId()%>)</td>
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
				
				
				<%
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
    System.out.println("page actuelle : "+curPage); 
		
    if (curPage != 1) { 
    		if (u.getRole()==0) {%>
        <td><a href="GestionUser?action=evallist&page=${currentPage - 1}">Previous</a></td>
        <%} 
        else {%>
        <td><a href="GestionUser?action=evallistUserAdmin&id=${uInfo.id}&page=${currentPage - 1}">Previous</a></td>
        <%} 
        }%>
 
    <%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>
           <% 
           
           System.out.println("role de ladmin:"+u.getRole()); 
            int max; 
    		String max1=request.getAttribute("noOfPages").toString(); 
    		max=Integer.parseInt(max1); 
    		System.out.println("nb de pages au total : "+max); 
    		PrintWriter out2 = response.getWriter();
           
    		for (int i=1; i<=max; i++)
    		{  
    			if (u.getRole()==0) {%>
                        <a href="?action=evallist&page=<%=i%>"><%=i%></a>
                <% }
                else {
                System.out.println("ici"); %>
                        <a href="?action=evallistUserAdmin&id=${uInfo.id}&page=<%=i%>"><%=i%></a>
               <% }%>
        	<%
    		}
			
	%>
     
    <%--For displaying Next link --%>
     <% 
     if (curPage!= max) { 
     	if (u.getRole()==0) {%>
        <td><a href="GestionUser?action=evallist&page=${currentPage + 1}">Next</a></td>
        <%}
     	
     	else {%>
     		<td><a href="GestionUser?action=evallistUserAdmin&id=${uInfo.id}&page=${currentPage + 1}">Next</a></td>
     	<%}
     	}%>
<br></br>
<br>
<%
		if(u.getRole()==1) {
				%>
		<a href='GestionUser'>Retour vers la liste des users</a>
<%} }%>
</body>
</html>