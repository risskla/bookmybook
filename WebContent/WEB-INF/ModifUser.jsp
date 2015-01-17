<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="beans.User"%>

<%@include file="header.jsp"%>


	<h3>Créer / Modifier les informations d'un user</h3>
	

	<form method="post" action="ModifUser">
		<div class="input-group">
			<span class="input-group-addon" id="sizing-addon1">Login :</span>
			<input class="form-control" type="text" name="login" id="login" value="${uModif.login}">
		</div>
		<div class="input-group">
			<span class="input-group-addon" id="sizing-addon1">Mail :</span>
			<input class="form-control" type="text" name="mail" id="mail" value="${uModif.mail}">
		</div>
		
		<div class="checkbox">
			<label>
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
			>: Admin
			</label>
		 </div>
		    
	    Informations personnelles :
	    
		<div class="input-group">
			<span class="input-group-addon" id="sizing-addon1">Nom :</span>
           <input class="form-control" type="text" name="nom" value="${uModif.nom}" placeholder="John">
        </div>
		<div class="input-group">
			<span class="input-group-addon" id="sizing-addon1">Prenom :</span>
           <input class="form-control" type="text" name="prenom" value="${uModif.prenom}" placeholder="Doe">
        </div>
		<div class="input-group">
			<span class="input-group-addon" id="sizing-addon1">Age :</span>
           <input class="form-control" type="number" name="age" value="${uModif.age}" placeholder="99">
        </div>
        <div class="control-group">
        	<label class="control-label">Sexe :</label>
            <div class="controls">
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
            > Femme<br>
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
			</div>
          </div>
           <div class="input-group">
				<span class="input-group-addon" id="sizing-addon1">Adresse postale :</span>
           		<input class="form-control" type="text" name="adresse" value="${uModif.adresse}" placeholder="X rue YYYYYY">
           </div>
           <div class="input-group">
				<span class="input-group-addon" id="sizing-addon1">Code postale :</span>
           		<input class="form-control" type="number" name="codepostale" value="${uModif.codepostale}" placeholder="XXXXX">
           </div>
           <div class="input-group">
				<span class="input-group-addon" id="sizing-addon1">Ville :</span>
           		<input class="form-control" type="text" name="ville" value="${uModif.ville}" placeholder="Compiègne">
           </div>
           <div class="input-group">
				<span class="input-group-addon" id="sizing-addon1">Téléphone :</span>
           		<input class="form-control" type="tel" name="telephone" value="${uModif.telephone}" placeholder="0X XX XX XX XX" required>
           </div>
	    
		<input type="hidden" name="idModif" id = "idModif" value="${uModif.id}">
		<br>
		<input class="btn btn-success" type="submit" value="Valider">
	</form>


<%@include file="footer.jsp"%>