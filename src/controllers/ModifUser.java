package controllers;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.MailingTools;
import beans.Book;
import beans.User;
import dao.BooksDao;
import dao.UserDao;

/**
 * Servlet implementation class ModifUser
 */
@WebServlet("/ModifUser")
public class ModifUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String alert_msg = "<br>";
		
		int role;
		int error = 0;
		
		//Gestion de la checkbox du role :
		System.out.println(request.getParameter("role"));
		if (request.getParameter("role")==null){
			role = 0;
		}else{
			role = 1;
		}
		
		//****************************************************************
		//Gestion des erreurs sur le formulaire de modif/création d'user :
		//****************************************************************
		
		//sur le login :
		String login = request.getParameter("login");
		if(login==""){
			alert_msg = alert_msg + "Login vide !<br>";
			error = error + 1;
		} else {
			if(login.matches(".*\\s.*")){
				alert_msg = alert_msg + "Login erroné : contient au moins un espace !<br>";
				error = error + 1;
			}
		}
		//Integer.parseInt(
		//sur le mail :
		String mail = request.getParameter("mail");
		if(mail==""){
			alert_msg = alert_msg + "Mail vide !<br>";
			error = error + 1;
		} else {
			if(!mail.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
				alert_msg = alert_msg + "Mail erroné : " + mail + " n'est pas une adresse mail correcte !<br>";
				error = error + 1;
			}
		}
		//sur le nom :
		String nom = request.getParameter("nom");
		if(nom==""){
			alert_msg = alert_msg + "Nom vide !<br>";
			error = error + 1;
		} else {
			if(nom.matches(".*\\d.*")){
				alert_msg = alert_msg + "Le nom contient des caractères numériques !<br>";
				error = error + 1;
			}
		}
		//sur le prenom :
		String prenom = request.getParameter("prenom");
		if(prenom==""){
			alert_msg = alert_msg + "Prénom vide !<br>";
			error = error + 1;
		} else {
			if(prenom.matches(".*\\d.*")){
				alert_msg = alert_msg + "Le prénom contient des caractères numériques !<br>";
				error = error + 1;
			}
		}
		//sur l'age : INTEGER
		String age = request.getParameter("age");
		if(age==""){
			alert_msg = alert_msg + "Age vide !<br>";
			error = error + 1;
			age = "999";
		} else {
			if(!age.matches("\\d{1,3}")){
				alert_msg = alert_msg + "L'age : " + age + " est erroné !<br>";
				error = error + 1;
				age= "999";
			}
		}
		//sur le sexe :
		String sexe = request.getParameter("sexe");
		if(sexe=="" || sexe == null){
			alert_msg = alert_msg + "Sexe vide !<br>";
			error = error + 1;
			sexe = "";
		} else {
			if(!sexe.matches("F|H")){
				alert_msg = alert_msg + "Le sexe : " + sexe + " est erroné !<br>";
				error = error + 1;
			}
		}
		//sur l'adresse :
		String adresse = request.getParameter("adresse");
		if(adresse==""){
			alert_msg = alert_msg + "Adresse vide !<br>";
			error = error + 1;
		} 
		//sur le code postale : INTEGER
		String codepostale = request.getParameter("codepostale");
		if(codepostale==""){
			alert_msg = alert_msg + "Code postale vide !<br>";
			error = error + 1;
			codepostale = "99999";
		} else {
			if(!codepostale.matches("\\d{5}")){
				alert_msg = alert_msg + "Le code postale : " + codepostale + " est erroné !<br>";
				error = error + 1;
				codepostale= "99999";
			}
		}
		//sur la ville :
		String ville = request.getParameter("ville");
		if(ville==""){
			alert_msg = alert_msg + "Ville vide !<br>";
			error = error + 1;
		} 
		//sur le telephone : INTEGER
		String telephone = request.getParameter("telephone");
		if(telephone==""){
			alert_msg = alert_msg + "Téléphone vide !<br>";
			error = error + 1;
		} else {
			if(!telephone.matches("\\d{10}")){
				alert_msg = alert_msg + "Le téléphone : " + telephone + " est erroné !<br>";
				error = error + 1;
			}
		}
		
		//Pour pouvoir créé un user avec les infos précédement entrées et au cas où, le renvoyer au formulaire en cas d'erreur :
		User u;
		if(request.getParameter("idModif")!="" && request.getParameter("idModif")!="-1"){
			int modif=Integer.parseInt(request.getParameter("idModif")); 
			u = new User (modif, login, "", mail, role, nom, prenom, Integer.parseInt(age), sexe, adresse, Integer.parseInt(codepostale), ville, telephone);
		}
		else{
			u = new User (-1, login, "", mail, role, nom, prenom, Integer.parseInt(age), sexe, adresse, Integer.parseInt(codepostale), ville, telephone);
		}
		
		if(error == 0){
			//si il n'y a pas d'erreur
			if(u.getId()!=-1){
					
				UserDao.update(u);	

				alert_msg = alert_msg + "Modification de l'utilisateur " + u.getLogin() +  " réalisée avec Succés !<br>";
			}
			else
			{
				//Correspond à create user :
				//verif s'il existe pas déjà :
				if(UserDao.findByLogin(login)!=null){
					//Modification de l'user :
					alert_msg = alert_msg + "Login déjà utilisé !<br>";
					request.setAttribute("alert", alert_msg);
					request.setAttribute("uModif", u);
					request.getRequestDispatcher("WEB-INF/ModifUser.jsp").forward(request, response); 
					
				}
				else{
					//Création de l'user :
					String default_password = "password";
					u.setMdp(default_password);
					UserDao.insert(u); 			
					
					//mailing :
					String exp = "clarisse.durand.henriot@gmail.com";
					String msg="Bonjour, \n"
							+ "Merci d'avoir choisi BookMyBook pour gérer vos relations littéraires !\n\n"
							+ "Voici vos informations de connexion : !\n "
							+ "Votre Login : " + u.getLogin() + "\n " 
							+ "Votre Mot de Passe : " + default_password + "\n\n " 
							+ "A bientôt !\n\n " 
							+ "Cordialement,\n\n " 
							+ "Toute l'équipe de BookMyBook.\n ";
					
					String sujet="Création de votre compte utilisateur sur BookMyBook"; 

						
							try {
								System.out.println("Envoi du mail:");
								MailingTools.sendMail(exp, sujet, msg);	
								alert_msg = alert_msg + "Envoi du mail en cours...<br>";
								request.setAttribute("alert", alert_msg);
								request.getRequestDispatcher("GestionUser").forward(request, response);
							} catch (MessagingException e2) {
								System.out.println("PB lors de l'envoi !");
								alert_msg = alert_msg + "Erreur lors de l'envoi de votre demande<br>Message d'erreur : "+ e2.getMessage() +"<br>";
								alert_msg = alert_msg + "Création de l'utilisateur " + u.getLogin() +  " en base réalisée !<br>";
								request.setAttribute("alert", alert_msg);
								request.getRequestDispatcher("GestionUser").forward(request, response);
							}
					alert_msg = alert_msg + "Envoi du mail réalisé !<br>";	
					alert_msg = alert_msg + "Création de l'utilisateur " + u.getLogin() +  " réalisée avec Succés !<br>";
				}
			}
			request.setAttribute("alert", alert_msg);
			request.getRequestDispatcher("GestionUser").forward(request, response);  
		}
		else{
			alert_msg = alert_msg + "L'utilisateur n'a pas pu être modifié/créé !<br>";
			
			request.setAttribute("alert", alert_msg);
			request.setAttribute("uModif", u);
			request.getRequestDispatcher("WEB-INF/ModifUser.jsp").forward(request, response);  
		}
	}

}
