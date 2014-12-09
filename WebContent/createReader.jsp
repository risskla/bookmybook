<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <title>Login</title>
   </head>
   <body>
      <h1>login</h1>
      <!-- TODO -> ACTION  -->
      <form method="post" action="TODO">
         <!-- les mdps seront générés aléatoirement,
         puis envoyés dans le mail de confirmation -->
         <label for="form_nom">Nom : </label>
         <input type="text" name="form_nom" placeholder="John">
         <br>
         <label for="form_prenom">Prenom : </label>
         <input type="text" name="form_prenom" placeholder="Doe">
         <br>
         <label for="form_age">Age : </label>
         <input type="number" name="form_age" placeholder="99">
         <br>
         <label for="form_sexe">Sexe : </label>
         <input type="checkbox" name="form_sexe" value="F"> Femme
         <input type="checkbox" name="form_sexe" value="H"> Homme<br>
         <br>
         <label for="form_email">Email : </label>
         <input type="email" name="form_email" placeholder="xxxxx@yyy.zzz" required>
         <br>
         <label for="form_adresse">Adresse postale: </label>
         <input type="text" name="form_adresse" placeholder="X rue YYYYYY">
         <br>
         <label for="form_codepostale">Code Postale: </label>
         <input type="number" name="form_codepostale" placeholder="XXXXX">
         <br>
         <label for="form_ville">Ville: </label>
         <input type="text" name="form_ville" placeholder="Compiègne">
         <br>
         <label for="form_telephone">Telephone : </label>
         <input type="tel" name="form_telephone" placeholder="0X XX XX XX XX" required>
         <br>
         <input type="submit" value="SEND"/>
         <input type="reset" value="CLEAR"/>
      </form>
   </body>
</html>