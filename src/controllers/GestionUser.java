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
import dao.MatchBookDao;
import dao.MatchReaderDao;
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
		
		//sécurité
		int userIdcheck= (int)request.getSession().getAttribute("id");
		if(userIdcheck==-1){
			request.setAttribute("alert", "Veuillez vous logger !");
			request.getRequestDispatcher("Login?action=deconnexion").forward(request, response);
		}
		
		//éxécuté lors de modif/suppr parce qu'on passe par des url 
		int id = 0;
		int forward=0; 
		int noOfRecords;
		int noOfPages;
		int recordsPerPage = 5;
		int page = 1;
        if(request.getParameter("page") != null){
            page = Integer.parseInt(request.getParameter("page")); //page actuelle
        }
		String action = request.getParameter("action");
	
		
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
				//il faut alors supprimer les eval liées à ce user 
				List<Evaluation> le=EvaluationDao.findAllByUser(id); 
				
				int idEval=0; 
				for(Evaluation e : le){
					idEval=e.getId(); 
					EvaluationDao.delete(idEval); 
					//et supprimer tous les match pointant vers l'eval qui nexiste plus
					MatchBookDao.deleteByEval(idEval);  
					MatchReaderDao.deleteByEval(idEval); //supprime tous les matchreader avec usersource=le user qu'on supprime
				}
				
				//et supprimer tous les matchreader qui ont le user supprimé comme plus loin ou plus proche
				MatchReaderDao.deleteByUser(id); 

				PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				try {
					request.setAttribute("alert", "Suppression de l'utilisateur " + UserLogin +  " réalisée avec Succés !");
					doPost(request,response);
				}
				finally { out.close() ;}
				
			} else if (action.equals("ajouter")) {
				request.getRequestDispatcher("WEB-INF/ModifUser.jsp").forward(request, response);  	
			}
			else if (action.equals("modifier")) {
				forward=1; 
				request.setAttribute("uModif", UserDao.find(id));
				request.getRequestDispatcher("WEB-INF/ModifUser.jsp").forward(request, response);  
				
			}
			 else if (action.equals("affichEvalForUser")) {
				 forward=1;
				 request.getRequestDispatcher("GestionEval?action=affichEvalForUser").forward(request, response);  
			}
			
			 else if (action.equals("affichEvalForUserAdmin")) {
				 forward=1;
				 request.getRequestDispatcher("GestionEval?action=affichEvalForUserAdmin").forward(request, response);
			}
		
		}
		// recuperer une liste d'utilisateurs
        if (forward==0) {
        	doPost(request,response);
        }
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		String keyword = request.getParameter("keyword");
		
		/*gestion pagination*/
		int page = 1;
        int recordsPerPage = 5;
        int noOfRecords;
        
        if(request.getParameter("page") != null){
            page = Integer.parseInt(request.getParameter("page")); //page actuelle
        }
        List<User> listeU;
        if (keyword!=null){
            listeU = UserDao.findByKeyword(keyword, (page-1)*recordsPerPage, recordsPerPage); //de page actuelle au max : de 0 à 5
            noOfRecords = UserDao.countUserByKeyword(keyword);//nb total d'enregistrement par keyword
            request.setAttribute("alert", "Voici les utilisateurs dont le login contient le mot clef '" + keyword + "'");
        }
        else
        {
            listeU = UserDao.findAll((page-1)*recordsPerPage, recordsPerPage); //de page actuelle au max : de 0 à 5
            noOfRecords = UserDao.countUser();//nb total d'enregistrement
        }
        
        System.out.println(listeU); 
        System.out.println("nb user by keyword"); 
        System.out.println(noOfRecords); 
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage); //nb total de pages possible 
        System.out.println(noOfPages); 
        System.out.println(page);
        
        /*fin bloc gestion pagination*/


		request.setAttribute("listeU", listeU);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
		request.getRequestDispatcher("WEB-INF/UserList.jsp").forward(request, response);  
		// tri OK
		
	}
}

