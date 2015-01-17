<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="beans.User"%>

<%@include file="header.jsp"%>

	<h3>Créer / Modifier les informations d'un user</h3>
	
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

	<form method="post" action="ModifUser">
			<label for="login">Login :</label>
			<input type="text" name="login" id="login" value="${uModif.login}">
			<br>
			<!--<label for="mdp">Mdp :</label>
			<input type="text" name="mdp" id="mdp" value="${uModif.mdp}">
			<br> -->
			<label for="mail">Mail :</label>
			<input type="text" name="mail" id="mail" value="${uModif.mail}">
			<br>
			<label for="role">Admin :</label>
			<input type="checkbox" name="role" id="role"  value="1" 
				<%
					//Fonction permettant de bien cocher la case si l'user était déjà admin :
					String checkboxState=null; 
					User u = (User)request.getAttribute("uModif");
					if(u!= null){
						if(u.getRole()==1){
							checkboxState = "checked";
						}
					}
				%>
				<%=checkboxState%>
			>
		    <br>
		    
		    Informations personnelles :
            <label for="nom">Nom : </label>
            <input type="text" name="nom" value="${uModif.nom}" placeholder="John">
            <br>
            <label for="prenom">Prenom : </label>
            <input type="text" name="prenom" value="${uModif.prenom}" placeholder="Doe">
            <br>
            <label for="age">Age : </label>
            <input type="number" name="age" value="${uModif.age}" placeholder="99">
            <br>
            <label for="sexe">Sexe : </label>
            <input type="radio" name="sexe" value="F"
           	<%
					//Fonction permettant de bien cocher la case si l'user était déjà une femme :
					String SexeState=null;
					if(u!= null){
						if(u.getSexe().equals("F")){
							SexeState = "checked";
						}
					}
			%>
			<%=SexeState%>
            > Femme
            <input type="radio" name="sexe" value="H"
            <%
					//Fonction permettant de bien cocher la case si l'user était déjà une femme :
					SexeState=null; 
					if(u!= null){
						if(u.getSexe().equals("H")){
							SexeState = "checked";
						}
					}
			%>
			<%=SexeState%>
            > Homme<br>
            <br>
            <label for="adresse">Adresse postale: </label>
            <input type="text" name="adresse" value="${uModif.adresse}" placeholder="X rue YYYYYY">
            <br>
            <label for="codepostale">Code Postale: </label>
            <input type="number" name="codepostale" value="${uModif.codepostale}" placeholder="XXXXX">
            <br>
            <label for="ville">Ville: </label>
            <input type="text" name="ville" value="${uModif.ville}" placeholder="Compiègne">
            <br>
            <label for="telephone">Telephone : </label>
            <input type="tel" name="telephone" value="${uModif.telephone}" placeholder="0X XX XX XX XX" required>
            <br>
		    
			<input type="hidden" name="idModif" id = "idModif" value="${uModif.id}">
			<input type="submit" value="Valider">
	</form>


<%@include file="footer.jsp"%>