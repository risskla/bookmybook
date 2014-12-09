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
import beans.IsbnComp;
import beans.TitleComp;
import dao.BooksDao;

/**
 * Servlet implementation class GestionBooks
 */
@WebServlet("/GestionBooks")
public class GestionBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionBooks() {
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
        List<Book> listeB = BooksDao.findAll((page-1)*recordsPerPage, recordsPerPage); //de page actuelle au max : de 0 à 5
        System.out.println(listeB); //ok
        int noOfRecords = BooksDao.countBooks(); //nb total d'enregistrement
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
				BooksDao.delete(id);
				PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				try {
				out.println("<!DOCTYPE html>");
				out.println("<html><head>"); 
				out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
				out.println("<title>Insertion d'un livre</title></head>");
				out.println("<body>");
				out.println("<h1>Livre supprimé de la base avec succès ! </h1>");
				out.println("</body>");
				out.println("</html>");
				//lien vers la page precedente 
				out.println("<a href='BooksList.jsp'>Retour vers la liste des livres</a>"); 
				}
				finally { out.close() ;}
				
			} else if (action.equals("modifier")) {
				request.setAttribute("bModif", BooksDao.find(id));
				request.getRequestDispatcher("ModifBook.jsp").forward(request, response);  
				
			} else if (action.equals("sort")) {

				Collections.sort(listeB);
			}
		}

		// recuperer une liste d'utilisateurs

		request.setAttribute("listeB", listeB);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

		// rediriger vers une page : on retourne sur la page davant 
		request.getRequestDispatcher("BooksList.jsp").forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//List<Book> listeB = BooksDao.findAll();

		String action = request.getParameter("action");
		
		/*gestion pagination*/
		int page = 1;
        int recordsPerPage = 5;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page")); //page actuelle
        List<Book> listeB = BooksDao.findAll((page-1)*recordsPerPage, recordsPerPage); //de page actuelle au max : de 0 à 5
        System.out.println(listeB); 
        int noOfRecords = BooksDao.countBooks(); //nb total d'enregistrement
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage); //nb total de pages possible 
        System.out.println(noOfPages); 
        System.out.println(page);
        
        /*fin bloc gestion pagination*/

		if (action != null) {
			if (action.equals("sort")) { //on trie simplement
				String sortType = request.getParameter("sortType");
				if (sortType.equals("1"))
					Collections.sort(listeB, new TitleComp());
				else if (sortType.equals("2"))
					Collections.sort(listeB, new IsbnComp());
			}
		} 
		request.setAttribute("listeB", listeB);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
		request.getRequestDispatcher("BooksList.jsp").forward(request, response);  
		// tri OK
		
	}
}

