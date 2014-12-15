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

import beans.Book;
import beans.Evaluation;
import beans.IsbnComp;
import dao.BooksDao;
import dao.EvaluationDao;

/**
 * Servlet implementation class GestionEval
 */
@WebServlet("/GestionEval")
public class GestionEval extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionEval() {
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
		int page = 1;
        int recordsPerPage = 5;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page")); //page actuelle
        
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
				EvaluationDao.delete(id);
				PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				try {
				out.println("<!DOCTYPE html>");
				out.println("<html><head>"); 
				out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
				out.println("<title>Suppression d'une evaluation</title></head>");
				out.println("<body>");
				out.println("<h1>Evluation supprimée de la base avec succès ! </h1>");
				out.println("</body>");
				out.println("</html>");
				//lien vers la page precedente 
				out.println("<a href='AllEvalList.jsp'>Retour vers la liste des evaluations</a>"); 
				}
				finally { out.close() ;}
				
			} else if (action.equals("modifier")) {
				request.setAttribute("eModif", EvaluationDao.find(id));
				request.getRequestDispatcher("ModifEval.jsp").forward(request, response);  
				
			} 
			else if (action.equals("affichEval")) {
				int idBook = Integer.parseInt(request.getParameter("idBook"));
				int noOfRecords = EvaluationDao.countEvalByBook(idBook); //nb total d'enregistrement
		        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage); //nb total de pages possible 
		        List<Evaluation> listeE = EvaluationDao.findByBook(idBook, (page-1)*recordsPerPage, recordsPerPage ); 
				request.setAttribute("listeE", listeE );
				request.setAttribute("idBook", idBook );
				request.getRequestDispatcher("EvalListForBook.jsp").forward(request, response);  
				
			}
			
			else if (action.equals("afficher")) {
				/*gestion pagination*/
				int noOfRecords = EvaluationDao.countEval(); //nb total d'enregistrement
		        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage); //nb total de pages possible 

		        List<Evaluation> listeE = EvaluationDao.findAll((page-1)*recordsPerPage, recordsPerPage); //de page actuelle au max : de 0 à 5
		
				request.setAttribute("listeE", listeE);
		        request.setAttribute("noOfPages", noOfPages);
		        request.setAttribute("currentPage", page);
				request.getRequestDispatcher("AllEvalList.jsp").forward(request, response);
			} 
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doGet(request, response); 
		
	}

}
