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
 * Servlet implementation class AddBook
 */
@WebServlet("/AddBook")
public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBook() {
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
		String titre = request.getParameter("titre");
		String auteur = request.getParameter("auteur");
		String editeur = request.getParameter("editeur");
		String isbn = request.getParameter("isbn"); //à transformer en int
		String pays = request.getParameter("pays");
		String genre = request.getParameter("genre");
		String anneePubli = request.getParameter("anneePubli");
		String resume = request.getParameter("resume");
		PrintWriter out = response.getWriter();
		
		int ap=Integer.parseInt(anneePubli); 
		int intisbn=Integer.parseInt(isbn); 
		
		Book b = new Book(0, titre, auteur, editeur, intisbn, pays, genre, ap, resume);
		
			BooksDao.insert(b);
			response.setContentType("text/html");
			try {
			
			out.println("<!DOCTYPE html>");
			out.println("<html><head>"); 
			out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
			out.println("<title>Insertion d'un livre</title></head>");
			out.println("<body>");
			out.println("<h1>Livre inséré dans la base avec succès ! </h1>");
			out.println("<p> Titre : "+request.getParameter("titre") +"</p"); 
			out.println("<p> Auteur : "+ request.getParameter("auteur")+"</p");
			out.println("<p> Editeur : "+request.getParameter("editeur") +"</p");
			out.println("<p> ISBN : "+request.getParameter("isbn") +"</p");
			out.println("<p> Pays : "+request.getParameter("pays") +"</p");
			out.println("<p> Genre : "+request.getParameter("editeur") +"</p");
			out.println("<p> Annee de publication : "+request.getParameter("anneePubli") +"</p");
			out.println("<p> Resume : "+request.getParameter("resume") +"</p");
			out.println("</body>");
			out.println("</html>");
			//lien vers la page precedente 
			out.println("<a href='createBookForm.jsp'>Inserer un autre livre</a>"); 
			out.println("<a href='GestionBooks'>Retour vers la liste des livres</a>");
			}
			
			finally {
				out.close(); 
			}
	}
	}


