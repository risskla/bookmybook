<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Evaluation</title>
</head>
<body>
<h1>Evaluation d'un livre !</h1>
<br><br>
<form method='post' action='GestionEval'>
Note globale : 
<INPUT type= "radio" name="note" value="1">1
<INPUT type= "radio" name="note" value="2">2
<INPUT type= "radio" name="note" value="3">3
<INPUT type= "radio" name="note" value="4">4
<br><br>
Qualité d'écriture : 
<INPUT type= "radio" name="qualite" value="1">1
<INPUT type= "radio" name="qualite" value="2">2
<INPUT type= "radio" name="qualite" value="3">3
<INPUT type= "radio" name="qualite" value="4">4
<br><br>
Intérêt sur le sujet : 
<INPUT type= "radio" name="interet" value="1">1
<INPUT type= "radio" name="interet" value="2">2
<INPUT type= "radio" name="interet" value="3">3
<INPUT type= "radio" name="interet" value="4">4
<br><br>
L'avez-vous lu jusqu'au bout ? 
<INPUT type= "radio" name="lecture" value="0">Non
<INPUT type= "radio" name="lecture" value="1">Oui
<br><br>

Souhaiteriez-vous lire un autre livre du même auteur ?
<INPUT type= "radio" name="souhaitAuteur" value="0">Non
<INPUT type= "radio" name="souhaitAuteur" value="1">Oui
<br><br>

Le recommanderiez-vous à un ami  ?
<INPUT type= "radio" name="recommandation" value="0">Non
<INPUT type= "radio" name="recommandation" value="1">Oui
<br><br>

<input type='submit'  value='SEND'/>
<input type='reset'  value='CLEAR'/>
</form>

</body>
</html>