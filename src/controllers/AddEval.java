package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.AdminParameters;
import beans.Book;
import beans.Evaluation;
import beans.MatchBook;
import dao.AdminParametersDao;
import dao.BooksDao;
import dao.EvaluationDao;
import dao.MatchBookDao;

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
		//à récupérer avec la session normalement : iduser
		
		//valeur impossibles pour pas qu'il n'y ait pas d'erreur si formulaire non complété entierement 
		//donc pour les attributs normalement remplis de 1 à 4 : valeur 0 si vide
		//pour les attributs 0 et 1 : valeur 2 si vide
	
		int note=0; 
		int qualite=0; 
		int interet=0; 
		int lecture=2;
		int souhaitAuteur=2; 
		int recommand=2; 
		
		if (request.getParameter("note")!=null) {
			String noteG = request.getParameter("note");
		    note=Integer.parseInt(noteG); 
		}
		if (request.getParameter("qualite")!=null) {
			String qualiteG = request.getParameter("qualite"); 
			qualite=Integer.parseInt(qualiteG); 
		}
		if (request.getParameter("interet")!=null) {
			String interetG = request.getParameter("interet");
			interet=Integer.parseInt(interetG); 
		}
		if (request.getParameter("lecture")!=null) { 
			String lectureG = request.getParameter("lecture");
			lecture=Integer.parseInt(lectureG); 
		}
		if (request.getParameter("souhaitAuteur")!=null) {
			String souhaitAuteurG = request.getParameter("souhaitAuteur");
			souhaitAuteur=Integer.parseInt(souhaitAuteurG); 
		}
		if (request.getParameter("recommandation")!=null) {
			String recommandG = request.getParameter("recommandation");
			recommand=Integer.parseInt(recommandG); 
		}
		PrintWriter out = response.getWriter();
		
		int book=Integer.parseInt(bookId); 
		
		HttpSession session = request.getSession();
		int user= (int)session.getAttribute("id");
		
		Evaluation e = new Evaluation(0, book, user, note, qualite, interet, lecture, souhaitAuteur, recommand);
		
			EvaluationDao.insert(e);
			
			
			//CALCUL DU MATCH
			
			AdminParameters a=AdminParametersDao.find(AdminParametersDao.getLastParameters()); 
			MatchBook m=null; 
			Evaluation e2=EvaluationDao.findByBookAndUser(book, user); 
			if (a==null || a.getAlgoMatchBook()==1) m=MatchBookDao.calculMatchBook1(user, e2.getId());
			else m=MatchBookDao.calculMatchBook2(user, e2.getId()); 
			System.out.println("livre conseille : "+m.getLivreSuggereId()); 
			
			MatchBookDao.insert(m); 
			Book b=BooksDao.find(m.getLivreSuggereId()); 
			
			//recapitulatif de l'eval et du match nouvellement calculé
			response.setContentType("text/html");
			try {
			
			out.println("<!DOCTYPE html>");
			out.println("<html><head>"); 
			out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
			out.println("<title>Insertion d'une evaluation</title></head>");
			out.println("<body>");
			out.println("<h1>Evaluation insérée dans la base avec succès ! </h1>");
			out.println("<p> Note globale : "+request.getParameter("note") +"</p");
			out.println("<p> Qualité d'écriture : "+request.getParameter("qualite") +"</p");
			out.println("<p> Intérêt : "+request.getParameter("interet") +"</p");
			out.println("<p> Lecture jusqu'à la fin : "+request.getParameter("lecture") +"</p");
			out.println("<p> Souhait pour livre un livre du même auteur : "+request.getParameter("souhaitAuteur") +"</p");
			out.println("<p> Recommandation du livre :  "+request.getParameter("recommandation") +"</p");
			
			out.println("<h1>Nous vous proposons un prochain livre à lire ! </h1>");
			out.println("<p> Titre : "+b.getTitre() +"</p");
			out.println("<p> Auteur : "+b.getAuteur() +"</p");
			out.println("<p> Editeur : "+b.getEditeur() +"</p");
			out.println("<p> ISBN : "+b.getIsbn() +"</p");
			out.println("<p> Genre : "+b.getGenre() +"</p");
			out.println("<p> Pays :  "+b.getPays()+"</p");
			out.println("<p> Resume :  "+b.getResume() +"</p");
			out.println("</body>");
			out.println("</html>");
			//lien vers la page precedente 
			out.println("<a href='GestionBooks'>Retour à la liste des livres</a>"); 
			}
			
			finally {
				out.close(); 
			}
	}
	}



