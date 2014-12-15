package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Book;
import beans.Evaluation;
import dao.BooksDao;
import dao.EvaluationDao;

/**
 * Servlet implementation class AddBook
 */
@WebServlet("/AddEval")
public class AddEval extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEval() {
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
		String bookId = request.getParameter("idBook");
		//� r�cup�rer avec la session normalement : iduser
		String noteG = request.getParameter("note");
		String qualiteG = request.getParameter("qualite"); //� transformer en int
		String interetG = request.getParameter("interet");
		String lectureG = request.getParameter("lecture");
		String souhaitAuteurG = request.getParameter("souhaitAuteur");
		String recommandG = request.getParameter("recommandation");
		PrintWriter out = response.getWriter();
		
		int book=Integer.parseInt(bookId); 
		//int user=Integer.parseInt(userId); 
		int user=1; 
		int note=Integer.parseInt(noteG); 
		int qualite=Integer.parseInt(qualiteG); 
		int interet=Integer.parseInt(interetG); 
		int lecture=Integer.parseInt(lectureG); 
		int souhaitAuteur=Integer.parseInt(souhaitAuteurG); 
		int recommand=Integer.parseInt(recommandG); 
		
		Evaluation e = new Evaluation(0, book, user, note, qualite, interet, lecture, souhaitAuteur, recommand);
		
			EvaluationDao.insert(e);
			
			//GERER ICI LES MATCHES : A AJOUTER
			
			response.setContentType("text/html");
			try {
			
			out.println("<!DOCTYPE html>");
			out.println("<html><head>"); 
			out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
			out.println("<title>Insertion d'une evaluation</title></head>");
			out.println("<body>");
			out.println("<h1>Evaluation ins�r�e dans la base avec succ�s ! </h1>");
			out.println("<p> Note globale : "+request.getParameter("note") +"</p");
			out.println("<p> Qualit� d'�criture : "+request.getParameter("qualite") +"</p");
			out.println("<p> Int�r�t : "+request.getParameter("interet") +"</p");
			out.println("<p> Lecture jusqu'� la fin : "+request.getParameter("lecture") +"</p");
			out.println("<p> Souhait pour livre un livre du m�me auteur : "+request.getParameter("souhaitAuteur") +"</p");
			out.println("<p> Livre recommand� :  "+request.getParameter("recommandation") +"</p");
			out.println("</body>");
			out.println("</html>");
			//lien vers la page precedente 
			out.println("<a href='BooksList.jsp'>Retour � la liste des livres</a>"); 
			}
			
			finally {
				out.close(); 
			}
	}
	}



