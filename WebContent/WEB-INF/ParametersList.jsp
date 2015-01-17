<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="dao.AdminParametersDao"%>
<%@page import="beans.AdminParameters"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Historique des paramètres d'administration</title>
</head>
<body>
<h1>Historique des paramètres d'administration</h1>

<%
	Object alert = request.getAttribute("alert");
	if(alert!=null){
		String alrt = (String)alert;
		%>
		<p>
			<%=alrt%>	
		</p>	
		<%
	}
%>


<table border="1" cellpadding="5" cellspacing="5">
<tr>
	<th>ALGO MATCHBOOK</th>
	<th>ALGO MATCHREADER</th>
	<th>DATE DE SAISIE</th>
</tr>

<%
		Object obj = request.getAttribute("listeP");
		if(obj!=null){
			//User u= get le user actuel
			List<AdminParameters> lp = (List<AdminParameters>)obj;
			for(AdminParameters p : lp){
	%>
			<tr>
				<td><%=p.getAlgoMatchBook()%></td>
				<td><%=p.getAlgoMatchReader()%></td>
				<td><%=p.getDateSaisie()%></td>
			
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
        <td><a href="ChoixAlgoServlet?action=afficher&page=${currentPage - 1}">Previous</a></td>
        <%} %>
 
    <%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>
           <% 
            int max; 
    		String max1=request.getAttribute("noOfPages").toString(); 
    		max=Integer.parseInt(max1); 
 
           
    		for (int i=1; i<=max; i++)
    		{  %>

                        <a href="?action=afficher&page=<%=i%>"><%=i%></a>
        	<%
    		}
			
	%>
     
    <%--For displaying Next link --%>
     <% 
     if (curPage!= max) { %>
        <td><a href="ChoixAlgoServlet?action=afficher&page=${currentPage + 1}">Next</a></td>
        <%} }%>
<br></br>
<a href="WEB-INF/choixAlgoMatchForm.jsp">Entrer de nouveaux paramètres</a>

</body>

</html>