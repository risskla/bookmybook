<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Contact us</title>
</head>
<body>
	<form method="post" action="ContactServlet">
		<label for="exp">Sender :</label>
		<input type="text" name="exp" id="exp"/>
		<br />
		<label for="sujet">Sujet :</label>
		<select name="sujet">
			<option value="1">Regards</option>
			<option value="2">Claims</option>
			<option value="3">FAQ</option>
		</select>
		<br />
		<label for="msg">Message :</label>
		<textarea name="msg" cols=10 rows=10 id="msg">your content</textarea> 
		<br />
		<input type="submit" value="Send" />
	</form>
	<% 
		Object obj = request.getAttribute("errorMsg");
		if (obj!=null)
			out.print((String)obj);
	%>
</body>
</html>