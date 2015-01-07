<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
   <title>Login</title>
</head>
<body>

<%
	Object alert = request.getAttribute("alert");
	if(alert!=null){
		String alrt = (String)alert;
		%>
		<p>
			Message d'alert : <%=alrt%>	
		</p>	
		<%
	}
%>

   <h1>login</h1>
   <!-- TODO -> ACTION  -->
   <form method="post" action="TODO">
      <label for="form_login">Login : </label>
      <input type="text" name="form_login" placeholder="email">
      <br>
      <label for="form_pwd">Mot de passe : </label>
      <input type="text" name="form_pwd" placeholder="pwd">
      <br>
      <input type="submit" value="SEND"/>
      <input type="reset" value="CLEAR"/>
   </form>
</body>
</html>