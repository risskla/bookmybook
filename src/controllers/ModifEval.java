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
import beans.User;
import dao.BooksDao;
import dao.EvaluationDao;
import dao.UserDao;

/**
 * Servlet implementation class ModifBook
 */
@WebServlet("/ModifEval")
public class ModifEval extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifEval() {
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
		
		String bookId = request.getParameter("idBook");
		String userId = request.getParameter("idUser");
		String noteG = request.getParameter("note");
		String qualiteG = request.getParameter("qualite"); //à transformer en int
		String interetG = request.getParameter("interet");
		String lectureG = request.getParameter("lecture");
		String souhaitAuteurG = request.getParameter("souhaitAuteur");
		String recommandG = request.getParameter("recommandation");
		PrintWriter out2 = response.getWriter();
		
		int book=Integer.parseInt(bookId); 
		int user=Integer.parseInt(userId); 
		int note=Integer.parseInt(noteG); 
		int qualite=Integer.parseInt(qualiteG); 
		int interet=Integer.parseInt(interetG); 
		int lecture=Integer.parseInt(lectureG); 
		int souhaitAuteur=Integer.parseInt(souhaitAuteurG); 
		int recommand=Integer.parseInt(recommandG); 
		
		User u=UserDao.find(user); 
		Book b=BooksDao.find(book); 
		
		
		Evaluation e = new Evaluation (modif, book, user , note, qualite, interet, 
				lecture, souhaitAuteur, recommand);
		Evaluation e2=EvaluationDao.find(modif); 
		if (e2==null) System.out.println("NULL"); 
		else { EvaluationDao.update(e); 
		//GERER ICI LES UPDATE DE MATCHES : A FAIRE 
		
		out2.println("<!DOCTYPE html>");
		out2.println("<html><head>"); 
		out2.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
		out2.println("<title>Modification d'un livre</title></head>");
		out2.println("<body>");
		out2.println("<h1>Evaluation modifiee dans la base avec succes ! </h1>");
		out2.println("<p> Livre (isbn) : "+ b.getIsbn() +"</p");
		out2.println("<p> User (login) : "+ u.getLogin() +"</p");
		out2.println("<p> Note globale : "+request.getParameter("note") +"</p");
		out2.println("<p> Qualité d'écriture : "+request.getParameter("qualite") +"</p");
		out2.println("<p> Intérêt : "+request.getParameter("interet") +"</p");
		out2.println("<p> Lecture jusqu'à la fin : "+request.getParameter("lecture") +"</p");
		out2.println("<p> Souhait pour livre un livre du même auteur : "+request.getParameter("souhaitAuteur") +"</p");
		out2.println("<p> Livre recommandé :  "+request.getParameter("recommandation") +"</p");
		out2.println("</body>");
		out2.println("</html>");
		//lien vers la page precedente 
		out.println("<a href='GestionEval?action=afficher'>Retour vers la liste des evaluations</a>");
		}
	}

}
