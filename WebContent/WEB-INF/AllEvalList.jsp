<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="beans.Evaluation"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.io.PrintWriter" %>


<%@include file="header.jsp"%>
		
		
	<%!
	//Petite fonction pour les choix binaires :
	public String choixplussimple(int choix){
	String output;
	switch(choix){
	        case 0: output = "<span class=\"glyphicon glyphicon-thumbs-down\"></span>";
	        break;
	
	        case 1: output = "<span class=\"glyphicon glyphicon-thumbs-up\"></span>";
	        break;
	
	        case 2: output = "<span class=\"glyphicon glyphicon-question-sign\"></span>";
	        break;
	
	        default: output = "ERROR";
	}
	
	return output;
	}
	//Petite fonction pour les évals :
	public String choixtostars(int choix){
	String output;
	switch(choix){
	        case 0: output = "<span class=\"glyphicon glyphicon-question-sign\"></span>";
	        break;
	
	        case 1: output = "<span class=\"glyphicon glyphicon-certificate\"></span>";
	        break;
	
	        case 2: output = "<span class=\"glyphicon glyphicon-certificate\"></span><span class=\"glyphicon glyphicon-certificate\"></span>";
	        break;
	        
	        case 3: output = "<span class=\"glyphicon glyphicon-certificate\"></span><span class=\"glyphicon glyphicon-certificate\"></span><span class=\"glyphicon glyphicon-certificate\"></span>";
	        break;
	        
	        case 4: output = "<span class=\"glyphicon glyphicon-certificate\"></span><span class=\"glyphicon glyphicon-certificate\"></span><span class=\"glyphicon glyphicon-certificate\"></span><span class=\"glyphicon glyphicon-certificate\"></span>";
	        break;
	
	        default: output = "ERROR";
	}
	
	return output;
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
		<table class="table table-striped table-hover" border="1" cellpadding="5">
		<tr>
			<th>USER</th>
			<th>LIVRE</th>
			<th>NOTE GLOBALE</th>
			<th>QUALITE D'ECRITURE</th>
			<th>INTERET</th>
			<th>LECTURE JUSQU'AU BOUT</th>
			<th>SOUHAIT POUR LIRE UN LIVRE DU MEME AUTEUR</th>
			<th>RECOMMANDATION</th>
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
			<tr onclick="input" data-toggle="modal" href="#bookmodal<%=i %>"  >
				<!-- Modal C'est le popup :)-->
				<div class="modal fade" id="bookmodal<%=i%>" tabindex="<%=i %>" role="dialog" aria-labelledby="Informations Détaillées" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				        <h4 class="modal-title" id="bookmodal<%=i%>">Informations sur les Matchs</h4>
				      </div>
				      <div class="modal-body">
				            <p><strong>Voici les matchs de </strong><%=ListUser.get(i)%>
				            <strong>sur le livre </strong><%=ListBook.get(i)%> </p>
				            <p><strong>Qu'il a noté globalement: </strong><%=choixtostars(e.getNote())%></p>
				            <p><strong>Qualité de l'écriture :</strong><%=choixtostars(e.getQualite())%></p>
				            <p><strong>Intérêt :</strong><%=choixtostars(e.getInteret())%></p>
				            <p><strong>Lecture jusqu'au bout :</strong><%=choixplussimple(e.getLecture())%></p>
				            <p><strong>Souhait pour lire un livre du même auteur :</strong><%=choixplussimple(e.getSouhaitAuteur())%></p>
				            <p><strong>Recommandé :</strong><%=choixplussimple(e.getRecommand())%></p>
				            <h3><STRONG>Matchs :<br></STRONG></h3>
				            <% 
				            if (ListMB.size()>0 && ListMB.get(i)!=null) { %>
				            <p><strong>Livre conseillé :</strong><a href="GestionBooks?keyword=<%=ListMB.get(i)%>"> <%= ListMB.get(i)%> </a>
	
				            </p>
				            <%
				            } 
				            else 
				            {%>
				            <p><strong>Livre conseillé :</strong> aucun</p>
				            <%}%>
				            
				            <% 
				            if (ListMRProche.size()>0 && ListMRLoin.size()>0 && ListMRProche.get(i)!=null && ListMRLoin.get(i)!=null){ %>
				            <p><strong>User le plus proche : </strong><%=ListMRProche.get(i)%></p>
				            <p><strong>User le plus loin : </strong><%=ListMRLoin.get(i)%></p>
				            <%} 
				            else {%>
				            <p><strong>User le plus proche :</strong> aucun</p>
				            <p><strong>User le plus loin :</strong> aucun</p>
				            <%} %>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				      </div>
				    </div>
				  </div>
				</div>
			
			
			
				<td><%=ListUser.get(i)%></td>
				<td><%=ListBook.get(i)%> </td>
				<td><%=choixtostars(e.getNote())%></td>
				<td><%=choixtostars(e.getQualite())%></td>
				<td><%=choixtostars(e.getInteret())%></td>
				<td><%=choixplussimple(e.getLecture())%></td>
				<td><%=choixplussimple(e.getSouhaitAuteur())%></td>
				<td><%=choixplussimple(e.getRecommand())%></td>
				
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
        <td><a href="GestionEval?action=${TypeDeListe}&page=${currentPage - 1}${bIdUrl}">
        <span class="glyphicon glyphicon-chevron-left"></span></a>
        </td></a></td>
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
  
        <td><a href="GestionEval?action=${TypeDeListe}&page=<%=newCurPage%>${bIdUrl}">
        <span class="glyphicon glyphicon-chevron-right"></span></a>
        </td>
	<%}}%>
<br>

<%@include file="footer.jsp"%>