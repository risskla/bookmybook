<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="beans.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.io.PrintWriter" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Liste des Users</title>
</head>
<body>
<h1>Liste des users en base</h1>
<br>

<%
	Object alert = request.getAttribute("alert");
	if(alert!=null){
		String alrt = (String)alert;
		%>
		<p>
			Message d'alert : <%=alrt%>	
		</p>	
		<%
	}
%>

<table border="1" cellpadding="5" cellspacing="5">
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
					<a href="GestionUser?action=evallist&id=<%=b.getId()%>">Afficher évaluations</a>	
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
        <td><a href="GestionUser?page=${currentPage - 1}">Previous</a></td>
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
        <td><a href="GestionUser?page=${currentPage + 1}">Next</a></td>
        <%} }%>
<br></br>
<a href="createUser.jsp">Ajouter un user</a>

</body>
</html>