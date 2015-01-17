<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Choix des algorithme de Matches</title>
</head>
<body>
<h1>Choix des algorithmes de Matches</h1>
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

<form method='post' action='ChoixAlgoServlet'>
Pour suggérer un livre à lire (MatchBook) : <br> 
<INPUT type= "radio" name="algoMatchBook" value="1">Algorithme 1<br> 
<INPUT type= "radio" name="algoMatchBook" value="2">Algorithme 2 (au hasard)<br> 
<br> <br> 
Pour définir les lecteurs le plus loin et le plus proche (MatchReader) : <br> 
<INPUT type= "radio" name="algoMatchReader" value="1">Algorithme 1<br> 
<INPUT type= "radio" name="algoMatchReader" value="2">Algorithme 2 (au hasard)<br> 
<input type='submit'  value='SEND'/><br>

<a href="WEB-INF/adminMenu.jsp">Retour au menu administrateur</a>
</form>
</body>
</html>