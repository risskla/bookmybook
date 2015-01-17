package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
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
import services.MailingTools;

/**
 * Servlet implementation class ModifEvalByReader
 */
@WebServlet("/ModifEvalByReader")
public class ModifEvalByReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifEvalByReader() {
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
		
		//ENVOI D'UN MAIL A L'ADMINISTRATEUR 
		
			//expediteur : le mail du user actuel
			//User u= recup celui de la session
			//ici valeur juste de test
			String exp = "clarisse.durand.henriot@gmail.com"; 

			PrintWriter out2 = response.getWriter();
			
			int book=-1;
			int user=-1; 
			int note=0; 
			int qualite=0; 
			int interet=0; 
			int lecture=2;
			int souhaitAuteur=2; 
			int recommand=2; 
			
			if (request.getParameter("idBook")!=null) {
				String bookId = request.getParameter("idBook");
				book=Integer.parseInt(bookId); 
			}
			if (request.getParameter("idUser")!=null) {
				String userId = request.getParameter("idUser"); 
				user=Integer.parseInt(userId); 
			}
			
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
			
			Evaluation e=EvaluationDao.findByBookAndUser(book, user); 
			
			String msg="Bonjour, \n"
					+ "Merci de proceder a la modif de l'evaluation numero " + e.getId() + "\n "
					+ "Note globale : " + note + "\n " 
					+ "Qualite : " + qualite + "\n " 
					+ "Interet : " + interet + "\n " 
					+ "Lecture : " + lecture + "\n " 
					+ "Souhait de lire un livre du meme auteur : " + souhaitAuteur + "\n " 
					+ "Recommandation : " + recommand + "\n " ; 
			
			String sujet="Modif d'une evaluation demandee par un reader"; 


					try {
						MailingTools.sendMail(exp, sujet, msg);

						User u=UserDao.find(user); 
						Book b=BooksDao.find(book); 
						
						request.setAttribute("action","modifreader");
						
						request.setAttribute("isbn",b.getIsbn());
						request.setAttribute("login",u.getLogin());
						request.setAttribute("note",note);
						request.setAttribute("qualite",qualite);
						request.setAttribute("interet",interet);
						request.setAttribute("lecture",lecture);
						request.setAttribute("souhaitAuteur",souhaitAuteur);
						request.setAttribute("recommandation",recommand);
						request.getRequestDispatcher("WEB-INF/StatusModifEval.jsp").forward(request, response);  
						
					} catch (MessagingException e2) {
						request.setAttribute("alert","Erreur lors de l'envoi de votre demande<br>Message d'erreur : "+ e2.getMessage());
						request.getRequestDispatcher("GestionBooks").forward(request, response);

					}

				}

	}
