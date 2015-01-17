<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="beans.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.io.PrintWriter" %>

<%@include file="header.jsp"%>

<h1>Liste des users en base</h1>
<div class="row">
	<div class="col-md-9"></div>
	<div class="col-md-3">
		<form method="post" action="GestionUser">
		Mot clé contenu dans le login :  <input class="form-control" type='text'  name='keyword'/> <input class="form-control btn btn-info" type='submit'  value='Recherche'/>
		</form>
		<form method="post" action="GestionUser">
		<input class="form-control" type='submit'  value='Reinitialiser'/>
		</form>
	</div>
</div>
<br>

<table class="table table-striped table-hover" border="1" cellpadding="5" cellspacing="5">
<tr>
	<th>LOGIN</th>
	<th>MAIL</th>
	<th>NOM</th>
	<th>PRENOM</th>
	<th>ACTION</th>	
</tr>
<%
		Object obj = request.getAttribute("listeU");
		if(obj!=null){
			List<User> lb = (List<User>)obj;
			int i = 0;
			for(User b : lb){
	%>
			<tr onclick="input" data-toggle="modal" href="#bookmodal<%=i %>"  >
			<!-- Modal -->
			<div class="modal fade" id="bookmodal<%=i%>" tabindex="<%=i %>" role="dialog" aria-labelledby="Informations Détaillées" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="bookmodal<%=i%>">Informations Détaillées sur <%=b.getLogin()%></h4>
			      </div>
			      <div class="modal-body">
			            <img src="http://www.kazeo.com/sites/fr/photos/425/dessiner-une-tete_4251109-L.jpeg" height='150px'>
                        <p><strong>Login : </strong><%=b.getLogin()%></p>
                        <p><strong>Mail : </strong><a href="mailto:<%=b.getMail()%>"><%=b.getMail()%></a></p>
                        <h3>Informations personnels : <br></h3>
                        <p><strong>Nom :</strong><%=b.getNom()%></p>
                        <p><strong>Prénom : </strong><%=b.getPrenom()%></p>
                        <p><strong>Age : </strong><%=b.getAge()%></p>
                        <p><strong>Sexe : </strong><%=b.getSexe()%></p>
                        <p><strong>Adresse : </strong><%=b.getAdresse()%></p>
                        <p><strong>CP : </strong><%=b.getCodepostale()%></p>
                        <p><strong>Ville : </strong><%=b.getVille()%></p>
                        <p><strong>Téléphone : </strong><%=b.getTelephone()%></p>
                        <h3>Informations admin : <br></h3>
                        <p><strong>Mot de passe : </strong><%=b.getMdp()%></p>
                        <p><strong>Role : </strong><%=b.getRole()%></p>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>			        
			      </div>
			    </div>
			  </div>
			</div>
				<td><%=b.getLogin()%></td>
				<td><%=b.getMail()%></td>
				<td><%=b.getNom()%></td>
				<td><%=b.getPrenom()%></td>


				<td>
					<a href="GestionUser?action=supprimer&id=<%=b.getId()%>">Supprimer</a>
					<a href="GestionUser?action=modifier&id=<%=b.getId()%>">Modifier</a>
					<a href="GestionUser?action=affichEvalForUserAdmin&id=<%=b.getId()%>">Afficher évaluations</a>	
				</td>
			</tr>
				<%i++;
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
        <td><a href="GestionUser?page=${currentPage - 1}"><span class="glyphicon glyphicon-chevron-left"></span></a></td>
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
        <td><a href="GestionUser?page=${currentPage + 1}"><span class="glyphicon glyphicon-chevron-right"></span></a></td>
        <%} }%>
<br></br>
<a href="GestionUser?action=ajouter">Ajouter un user</a>

<%@include file="footer.jsp"%>