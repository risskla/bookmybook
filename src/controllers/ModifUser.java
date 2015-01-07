package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
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
		int modif=Integer.parseInt(request.getParameter("idModif")); 
		int role;
		
		//Gestion de la checkbox du role :
		System.out.println(request.getParameter("role"));
		if (request.getParameter("role")==null){
			role = 0;
		}else{
			role = 1;
		}
		System.out.println(modif);
		
		User b=UserDao.find(modif);
		if (b==null) {
			//Correspond à create user :
			b = new User (modif, request.getParameter("login"), request.getParameter("mdp"), request.getParameter("mail"), role, request.getParameter("nom"), request.getParameter("prenom"), Integer.parseInt(request.getParameter("age")), request.getParameter("sexe"),request.getParameter("adresse"), Integer.parseInt(request.getParameter("codepostale")), request.getParameter("ville"), Integer.parseInt(request.getParameter("telephone")));
			UserDao.insert(b); 
		}
		else { 
			//Correspond à modif user:
			b = new User (modif, request.getParameter("login"), request.getParameter("mdp"), request.getParameter("mail"), role, request.getParameter("nom"), request.getParameter("prenom"), Integer.parseInt(request.getParameter("age")), request.getParameter("sexe"),request.getParameter("adresse"), Integer.parseInt(request.getParameter("codepostale")), request.getParameter("ville"), Integer.parseInt(request.getParameter("telephone")));
			UserDao.update(b);	
		}
		request.setAttribute("alert", "Modification de l'utilisateur " + b.getLogin() +  " réalisée avec Succés !");
		request.getRequestDispatcher("GestionUser").forward(request, response);  
	}

}
