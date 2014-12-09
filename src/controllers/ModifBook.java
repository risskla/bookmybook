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
		PrintWriter out = response.getWriter();
		int modif=Integer.parseInt(request.getParameter("idModif")); 
		System.out.println(modif);
		Book b = new Book (modif, request.getParameter("titre"), request.getParameter("auteur") , request.getParameter("editeur"), modif, request.getParameter("pays"), 
				request.getParameter("genre"), Integer.parseInt(request.getParameter("anneePubli")), request.getParameter("resume"));
		Book b2=BooksDao.find(modif); 
		if (b2==null) System.out.println("NULL"); 
		else { BooksDao.update(b); 
		
		out.println("<!DOCTYPE html>");
		out.println("<html><head>"); 
		out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
		out.println("<title>Modification d'un livre</title></head>");
		out.println("<body>");
		out.println("<h1>Livre modifie dans la base avec succes ! </h1>");
		out.println("<p> Titre : "+request.getParameter("titre") +"</p"); 
		out.println("<p> Auteur : "+ request.getParameter("auteur")+"</p");
		out.println("<p> Editeur : "+request.getParameter("editeur") +"</p");
		out.println("<p> ISBN : "+request.getParameter("isbn") +"</p");
		out.println("<p> Pays : "+request.getParameter("pays") +"</p");
		out.println("<p> Genre : "+request.getParameter("genre") +"</p");
		out.println("<p> Annee de publication : "+request.getParameter("anneePubli") +"</p");
		out.println("<p> Resume : "+request.getParameter("resume") +"</p");
		out.println("</body>");
		out.println("</html>");
		//lien vers la page precedente 
		out.println("<a href='BooksList.jsp'>Retour vers la liste des livres</a>"); 
		}
	}

}
