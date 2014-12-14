<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="beans.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BookMyBook</title>
</head>
<body>

	<h3>Modifier les informations d'un user</h3>

	<form method="post" action="ModifUser">
			<label for="login">Login :</label>
			<input type="text" name="login" id="login" value="${uModif.login}">
			<br>
			<label for="mdp">Mdp :</label>
			<input type="text" name="mdp" id="mdp" value="${uModif.mdp}">
			<br>
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
		    
			<input type="hidden" name="idModif" id = "idModif" value="${uModif.id}">
			<input type="submit" value="Valider">
	</form>


</body>
</html>