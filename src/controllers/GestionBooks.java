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
		   List<Book> lb = BooksDao.findAll();
		int id = 0;
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
				BooksDao.delete(id);
			} else if (action.equals("modifier")) {
				request.setAttribute("bModif", BooksDao.find(id));
				request.getRequestDispatcher("ModifBook.jsp").forward(request, response);  
				
			} else if (action.equals("sort")) {
				// ordina la lista implicitamente utilizzando il metodo
				// compareTo dell'interfaccia Comparable (vedere la classe
				// Users)
				Collections.sort(lb);
			}
		}

		// recuperer une liste d'utilisateurs

		request.setAttribute("listeB", lb);

		// rediriger vers une page : on retourne sur la page davant 
		request.getRequestDispatcher("BooksList.jsp").forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Book> listeB = BooksDao.findAll();

		String action = request.getParameter("action");

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
		request.getRequestDispatcher("BooksList.jsp").forward(request, response);  
		// tri OK
		
	}
}

