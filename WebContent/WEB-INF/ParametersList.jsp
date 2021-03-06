<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="dao.AdminParametersDao"%>
<%@page import="beans.AdminParameters"%>
<%@page import="java.util.List"%>

<%@include file="header.jsp"%>

<h1>Historique des paramètres d'administration</h1>

<table class="table table-striped" border="1" cellpadding="5" cellspacing="5">
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
        <td><a href="ChoixAlgoServlet?action=afficher&page=${currentPage - 1}">
        <span class="glyphicon glyphicon-chevron-left"></span></a></td>
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
        <td><a href="ChoixAlgoServlet?action=afficher&page=${currentPage + 1}">
        <span class="glyphicon glyphicon-chevron-right"></span></a></td>
        <%} }%>
<br></br>
<a href="ChoixAlgoServlet?action=formalgo">Entrer de nouveaux paramètres</a>

<%@include file="footer.jsp"%>