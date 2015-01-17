<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="icon" href="../../favicon.ico">

        <title>BookMyBook</title>

        <!-- Bootstrap core CSS 
        <link href="css/bootstrap.min.css" rel="stylesheet">-->
        <link href="css/bootswatch.min.css" rel="stylesheet">
        <link href="css/mycustomtheme.css" rel="stylesheet">
        

        <!-- Custom styles for this template
        <link href="css/bootstrap-theme.min.css" rel="stylesheet"> -->
	</head>
<body>
<%
	//On insère l'id du user en variable de session :
	int role = (int)session.getAttribute("role");
%>
	<nav class="navbar navbar-default">
	  <div class="container-fluid">
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <%if(role==1){%>
	      <a class="navbar-brand" href="SwitchRole?action=adminmenu">BookMyBook</a>
		      <%} else {
		    	  if(role==0){%>
		   	  <a class="navbar-brand" href="SwitchRole?action=readermenu">BookMyBook</a>
		      <%  }
	    		}
	      	%>
	    </div>
	
	    <!-- Collect the nav links, forms, and other content for toggling -->
	      <ul class="nav navbar-nav navbar-right">
	      	<%if(role==1){%>
	        <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Gestion des Users <span class="caret"></span></a>
	          <ul class="dropdown-menu" role="menu">
	            <li><a href="GestionUser">Consulter/Chercher dans la liste de tous les users</a></li>
	            <li><a href="GestionUser?action=ajouter">Ajouter un user</a></li>
	          </ul>
	        </li>
	        <%}%>
	        <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Gestion des Livres <span class="caret"></span></a>
	          <ul class="dropdown-menu" role="menu">
	            <li><a href="GestionBooks">Consulter/Chercher dans la liste de tous les livres</a></li>
	            <li><a href="GestionBooks?action=ajouter">Ajouter un livre</a></li>
	          </ul>
	        </li>
	        <%if(role==1){%>
	        <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Gestion des Evaluations <span class="caret"></span></a>
	          <ul class="dropdown-menu" role="menu">
	            <li><a href="GestionEval?action=afficher">Consulter la liste de toutes les evaluations</a></li>
	          </ul>
	        </li>
	        <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Gestion des Matchs <span class="caret"></span></a>
	          <ul class="dropdown-menu" role="menu">
	            <li><a href="ChoixAlgoServlet?action=formalgo">Choisir les algorithmes</a></li>
	            <li><a href="ChoixAlgoServlet?action=afficher">Consulter l'historique des paramètres de match</a></li>
	          </ul>
	        </li>
	        <%}else{%>
	        <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Gestion des Matchs <span class="caret"></span></a>
	          <ul class="dropdown-menu" role="menu">
	            <li><a href="GestionUser?action=affichEvalForUser">Historique de vos évaluations et de vos matches</a></li>
	          </ul>
	        </li>
	        <%}%>
	        <li>
	          <a class="btn btn-default" href="Login?action=deconnexion" role="button"><span class="glyphicon glyphicon-off" aria-hidden="true"></span></a>
	        </li>
	      </ul>
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>
	
	<%
	Object alert = request.getAttribute("alert");
	if(alert!=null){
		String alrt = (String)alert;
		%>
		<div class="alert alert-info" role="alert">
			<strong>Message d'alert :</strong> <%=alrt%>	
		</div>
		<%
	}
	%>
	
	<div class="row">
		<div class="col-md-1"></div>
			<div class="col-md-10">
