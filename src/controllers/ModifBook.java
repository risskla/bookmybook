package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Book;
import dao.BooksDao;

/**
 * Servlet implementation class ModifBook
 */
@WebServlet("/ModifBook")
public class ModifBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifBook() {
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
		System.out.println(modif);	
		
		String titre = request.getParameter("titre");
		String auteur = request.getParameter("auteur");
		String editeur = request.getParameter("editeur");
		String isbn = request.getParameter("isbn"); //à transformer en int
		String pays = request.getParameter("pays");
		String genre = request.getParameter("genre");
		String anneePubli = request.getParameter("anneePubli");
		String resume = request.getParameter("resume");
		
		int ap=Integer.parseInt(anneePubli); 
		long intisbn=Long.parseLong(isbn);
		
		Book b = new Book(modif, titre, auteur, editeur, intisbn, pays, genre, ap, resume);
		
		Book b2=BooksDao.find(modif);
		if (b2==null) System.out.println("NULL"); 
		else { 
			BooksDao.update(b);
			request.setAttribute("titre",titre);
			request.setAttribute("auteur",auteur);
			request.setAttribute("editeur",editeur);
			request.setAttribute("isbn",isbn);
			request.setAttribute("pays",pays);
			request.setAttribute("genre",genre);
			request.setAttribute("anneePubli",anneePubli);
			request.setAttribute("resume",resume);
			request.getRequestDispatcher("StatusBookModifAdd.jsp").forward(request, response);  
		}
	}

}
