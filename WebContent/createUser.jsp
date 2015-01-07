<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BookMyBook</title>
</head>
<body>

	<h3>Créer les informations d'un user</h3>

	<form method="post" action="ModifUser">
		<label for="login">Login :</label>
		<input type="text" name="login" id="login">
		<br>
		<label for="mdp">Mdp :</label>
		<input type="text" name="mdp" id="mdp">
		<br>
		<label for="mail">Mail :</label>
		<input type="email" name="mail" placeholder="xxxxx@yyy.zzz" required>
		<br>
		<label for="role">Admin :</label>
		<input type="checkbox" name="role" id="role" value="1">
	    <br>
	    Informations personnelles :
	    <label for="nom">Nom : </label>
	    <input type="text" name="nom" placeholder="John">
      	<br>
      	<label for="prenom">Prenom : </label>
      	<input type="text" name="prenom" placeholder="Doe">
      	<br>
      	<label for="age">Age : </label>
      	<input type="number" name="age" placeholder="99">
      	<br>
      	<label for="sexe">Sexe : </label>
      	<input type="radio" name="sexe" value="F"> Femme
      	<input type="radio" name="sexe" value="H"> Homme<br>
      	<br>
      	<label for="adresse">Adresse postale: </label>
      	<input type="text" name="adresse" placeholder="X rue YYYYYY">
      	<br>
      	<label for="codepostale">Code Postale: </label>
      	<input type="number" name="codepostale" placeholder="XXXXX">
      	<br>
      	<label for="ville">Ville: </label>
      	<input type="text" name="ville" placeholder="Compiègne">
      	<br>
      	<label for="telephone">Telephone : </label>
      	<input type="tel" name="telephone" placeholder="0X XX XX XX XX" required>
      	<br>
      	<input type="hidden" name="idModif" id = "idModif" value="-1">
	  	<input type="submit" value="Valider">
	</form>


</body>
</html>