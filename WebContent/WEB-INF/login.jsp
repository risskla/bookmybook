<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../../favicon.ico">

        <title>Login-BookMyBook</title>

        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/login_theme.css" rel="stylesheet">
        <link href="css/bootstrap-theme.min.css" rel="stylesheet">
        
    </head>
    <body>
        <div class="container">
            <%
                Object alert = request.getAttribute("alert");
                if(alert!=null){
                    String alrt = (String)alert;
                    %>
                    <div class="alert alert-danger" role="alert">
                      <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                      <span class="sr-only">Message d'alerte : </span>
                      <%=alrt%>
                    </div>
                            
                    <%
                }
            %>
            <form class="form-signin" method="post" action="Login">
                <h2 class="form-signin-heading">Bienvenue !</h2>
                <label for="inputEmail" class="label label-info">Login</label>
                <input type="text" name="login" class="form-control" placeholder="aaa@bbb.ccc" required autofocus>
                <label for="inputPassword" class="label label-info">Mot de passe :</label>
                <input type="text" name="pwd" class="form-control" placeholder="xxxx" required>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Se connecter</button>
            </form>

         </div>
         <!-- 
         <h1>login</h1>
     
          <form method="post" action="Login">
              <label for="login">Login : </label>
              <input type="text" name="login" placeholder="email">
              <br>
              <label for="pwd">Mot de passe : </label>
              <input type="text" name="pwd" placeholder="pwd">
              <br>
              <input type="submit" value="SEND"/>
              <input type="reset" value="CLEAR"/>
          </form>
          -->
    </body>
</html>