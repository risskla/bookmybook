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
import javax.servlet.http.HttpSession;

import beans.Evaluation;
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
		int forward=0; 
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
				forward=1; 
				request.setAttribute("uModif", UserDao.find(id));
				request.getRequestDispatcher("ModifUser.jsp").forward(request, response);  
				
			}
			 else if (action.equals("evallist")) {
				 System.out.println("dans gestion user evallist"); 
				 forward=1;
				//Si on désire afficher les évaluations d'un utilisateur :
				//-> On transmet des informations sur l'utilisateur:
				HttpSession session = request.getSession();
				int userId= (int)session.getAttribute("id");
				request.setAttribute("uInfo", UserDao.find(userId));
				
				noOfRecords = EvaluationDao.countEvalByUser(userId); //nb total d'enregistrement
		        noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage); //nb total de pages possible 

				//-> On transmet la liste de ses évaluations :
				List<Evaluation> EvalList=EvaluationDao.findbyuser(userId, (page-1)*recordsPerPage, recordsPerPage); 
				System.out.println("liste : "+ EvalList); 
				
				System.out.println("dans gestionEval : pagination : nb denregistrements : "+ noOfRecords);
		        System.out.println("noOfPages : "+noOfPages); 
		        System.out.println("pageActuelle"+page); 
				
		        request.setAttribute("noOfPages", noOfPages);
		        request.setAttribute("currentPage", page);
				request.setAttribute("EvalList", EvalList);
				request.getRequestDispatcher("EvalListForUser.jsp").forward(request, response);  
			}
			
			 else if (action.equals("evallistUserAdmin")) {
				 forward=1;
				//Si on désire afficher les évaluations d'un utilisateur :
				//-> On transmet des informations sur l'utilisateur:
				 int userId = Integer.parseInt(request.getParameter("id"));
				 noOfRecords = EvaluationDao.countEvalByUser(userId); //nb total d'enregistrement
			     noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage); //nb total de pages possible 
			     List<Evaluation> EvalList=EvaluationDao.findbyuser(userId, (page-1)*recordsPerPage, recordsPerPage); 
				 
				request.setAttribute("uInfo", UserDao.find(userId));
				//-> On transmet la liste de ses évaluations :
				System.out.println("liste : "+ EvalList); 
				System.out.println("dans gestionEval AdminUser: pagination : nb denregistrements : "+ noOfRecords);
		        System.out.println("noOfPages : "+noOfPages); 
		        System.out.println("pageActuelle"+page); 

		        request.setAttribute("noOfPages", noOfPages);
		        request.setAttribute("currentPage", page);
				request.setAttribute("EvalList", EvalList);
				request.getRequestDispatcher("EvalListForUser.jsp").forward(request, response);  
			}
		
		}
		// recuperer une liste d'utilisateurs
        if (forward==0) {
		request.setAttribute("listeU", listeU);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

		// rediriger vers une page : on retourne sur la page davant 
		request.getRequestDispatcher("UserList.jsp").forward(request, response);
        }
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

