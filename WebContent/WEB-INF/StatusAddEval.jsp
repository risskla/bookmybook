<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@include file="header.jsp"%>

         <h1>Evaluation ins�r�e dans la base avec succ�s ! </h1>
         <p> Note globale : ${note} </p>
         <p> Qualit� d'�criture : ${qualite} </p>
         <p> Int�r�t : ${interet} </p>
         <p> Lecture jusqu'� la fin : ${lecture} </p>
         <p> Souhait pour livre un livre du m�me auteur : ${souhaitAuteur} </p>
         <p> Recommandation du livre :  ${recommandation} </p>
         
         <%if (request.getAttribute("m")!=null) {%>
         <h1>Nous vous proposons un prochain livre � lire ! </h1>
         <p> Titre : ${Titre} </p>
         <p> Auteur : ${Auteur} </p>
         <p> Editeur : ${Editeur} </p>
         <p> ISBN : ${Isbn} </p>
         <p> Genre : ${Genre} </p>
         <p> Pays :  ${Pays}</p>
         <p> Resume :  ${Resume} </p>
         <%}
         else {%>
            <h1>Il n'y a pas assez de livres en base pour vous proposer un match livre!</h1>
         <%}
         if (request.getAttribute("m2")!=null) {%>
         <h1>Le lecteur le plus proche de vous est : </h1>
         <p> Login : ${userPlusProche} </p>
         <h1>Le lecteur le plus eloigne de vous est : </h1>
         <p> Login : ${userPlusLoin} </p>
         <%}
         else {%>
            <h1>Il n'y a pas assez de lecteurs en base pour vous proposer un match lecteur!</h1>
         <%}%>
         
 
         <a href='GestionBooks'>Retour � la liste des livres</a> 
         
<%@include file="footer.jsp"%>