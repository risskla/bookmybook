package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import beans.IsbnComp;
import beans.TitleComp;
import dao.UserDao;
import dao.EvaluationDao;

/**
 * Servlet implementation class GestionUsers
 */
@WebServlet("/GestionUser")
public class GestionUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//éxécuté lors de modif/suppr parce qu'on passe par des url 
		int id = 0;
		String action = request.getParameter("action");
		/*gestion pagination*/
		int page = 1;
        int recordsPerPage = 5;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page")); //page actuelle
        List<User> listeU = UserDao.findAll((page-1)*recordsPerPage, recordsPerPage); //de page actuelle au max : de 0 à 5
        System.out.println(listeU); //ok
        int noOfRecords = UserDao.countUser(); //nb total d'enregistrement
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage); //nb total de pages possible 
        System.out.println(noOfPages); 
        System.out.println(page);
        
        /*fin bloc gestion pagination*/

		
		
		if (action != null) {
			String idCh = request.getParameter("id");
			if (idCh != null) {
				try {
					id = Integer.parseInt(idCh);
				} catch (Exception e) {

				}
			}

			if (action.equals("supprimer")) {
				String UserLogin = UserDao.find(id).getLogin();
				UserDao.delete(id);
				PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				try {
					request.setAttribute("alert", "Suppression de l'utilisateur " + UserLogin +  " réalisée avec Succés !");
					doPost(request,response);
				}
				finally { out.close() ;}
				
			} else if (action.equals("modifier")) {
				request.setAttribute("uModif", UserDao.find(id));
				request.getRequestDispatcher("ModifUser.jsp").forward(request, response);  
				
			}
			 else if (action.equals("evallist")) {
				//Si on désire afficher les évaluations d'un utilisateur :
				//-> On transmet des informations sur l'utilisateur:
				request.setAttribute("uInfo", UserDao.find(id));
				//-> On transmet la liste de ses évaluations :
				request.setAttribute("EvalList", EvaluationDao.findbyuser(id));
				request.getRequestDispatcher("EvalList.jsp").forward(request, response);  
			}
		
		}
		// recuperer une liste d'utilisateurs

		request.setAttribute("listeU", listeU);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

		// rediriger vers une page : on retourne sur la page davant 
		request.getRequestDispatcher("UserList.jsp").forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		String action = request.getParameter("action");
		
		/*gestion pagination*/
		int page = 1;
        int recordsPerPage = 5;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page")); //page actuelle
        List<User> listeU = UserDao.findAll((page-1)*recordsPerPage, recordsPerPage); //de page actuelle au max : de 0 à 5
        System.out.println(listeU); 
        int noOfRecords = UserDao.countUser(); //nb total d'enregistrement
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage); //nb total de pages possible 
        System.out.println(noOfPages); 
        System.out.println(page);
        
        /*fin bloc gestion pagination*/


		request.setAttribute("listeU", listeU);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
		request.getRequestDispatcher("UserList.jsp").forward(request, response);  
		// tri OK
		
	}
}

