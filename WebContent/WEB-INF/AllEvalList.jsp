<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="beans.Evaluation"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.io.PrintWriter" %>


<%@include file="header.jsp"%>


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
				
	<%
		//Récupération des données d'entrées :
		Object OTypeDeListe = request.getAttribute("TypeDeListe");
		String TypeDeListe = (String)OTypeDeListe;
	
		Object OListUser = request.getAttribute("ListUser");
		List<String> ListUser = (List<String>)OListUser;
		
		Object OListBook = request.getAttribute("ListBook");
		List<String> ListBook = (List<String>)OListBook;
		
		Object OListMB = request.getAttribute("ListMB");
		List<String> ListMB = (List<String>)OListMB;
		
		Object OListMRLoin = request.getAttribute("ListMRLoin");
		List<String> ListMRLoin = (List<String>)OListMRLoin;

		Object OListMRProche = request.getAttribute("ListMRProche");
		List<String> ListMRProche = (List<String>)OListMRProche;
		
		Object obj = request.getAttribute("EvalList");
		
		Object OuRole = request.getAttribute("uRole");
		int uRole = (int)OuRole;
		
		if (TypeDeListe.equals("afficher")){
		%>
		<h1>Liste des évaluations en base</h1>
		<%
		}
		else
		{
			if(TypeDeListe.equals("affichEvalForBook")){%>
				<h3>Liste des évaluations en base pour le livre :<br>
				${bTitre} de ${bAuteur} (isbn : ${bIsbn})
				</h3>
			<%}if(TypeDeListe.equals("affichEvalForUser") || TypeDeListe.equals("affichEvalForUserAdmin")){%>
				<h3>Liste des evaluations de l'utilisateur ${uLogin}</h3>
		<%
			}
		} %>
		<table class="table table-striped" border="1" cellpadding="5" cellspacing="5">
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
			<%	if(uRole==1) {%>
				<th>ACTION SOUHAITEE</th>
			<%} %>		
		</tr>
		
		<%
		if(obj!=null){
			List<Evaluation> le = (List<Evaluation>)obj;
			if(le.isEmpty()){
		%>		<h3>Aucune évalution enregistrée pour ce livre</h3>
		<%} else {
			System.out.println("EvalList");
			
			int i = 0;	
			for(Evaluation e : le){
			
		%>
			<tr>
				<td><%=ListUser.get(i)%></td>
				<td><%=ListBook.get(i)%> </td>
				<td><%=e.getNote()%></td>
				<td><%=e.getQualite()%></td>
				<td><%=e.getInteret()%></td>
				<td><%=e.getLecture()%></td>
				<td><%=e.getSouhaitAuteur()%></td>
				<td><%=e.getRecommand()%></td>
				
				<% 
				if (ListMB.size()>0 && ListMB.get(i)!=null) { %>
				<td><%=ListMB.get(i)%></td>
				<%
				} 
				else 
				{%>
				<td>aucun</td>
				<%}%>
				
				<% 
				if (ListMRProche.size()>0 && ListMRLoin.size()>0 && ListMRProche.get(i)!=null && ListMRLoin.get(i)!=null){ %>
				<td><%=ListMRProche.get(i)%></td>
				<td><%=ListMRLoin.get(i)%></td>
				<%} 
				else {%>
				<td>aucun</td>
				<td>aucun</td>
				<%} %>
				
				<%
				if(uRole==1) {
				%>
				<td>
					<a href="GestionEval?action=supprimer&id=<%=e.getId()%>">Supprimer</a> <br></br>
					<a href="GestionEval?action=modifier&id=<%=e.getId()%>">Modifier</a>	
				</td>
				<% } %>
			</tr>
				<%
				i++;
			}
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
        <td><a href="GestionEval?action=${TypeDeListe}&page=${currentPage - 1}${bIdUrl}">Previous</a></td>
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

                        <a href="?action=${TypeDeListe}&page=<%=i%>${bIdUrl}"><%=i%></a>
        	<%
    		}
			
	%>
     
    <%--For displaying Next link --%>
     <% 
     if (curPage!= max) { 
    	 int newCurPage=curPage+1; 
     	System.out.println("la page suivante : "+ newCurPage); %>
        <td><a href="GestionEval?action=${TypeDeListe}&page=<%=newCurPage%>${bIdUrl}">Next</a></td>
	<%}}%>
<br></br>
<%if(uRole==1){ %>
<a href="GestionBooks">Retour à la liste globale</a>
<%} %>

<%@include file="footer.jsp"%>