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
import beans.MatchReader;
import beans.User;
import dao.AdminParametersDao;
import dao.BooksDao;
import dao.EvaluationDao;
import dao.MatchBookDao;
import dao.MatchReaderDao;
import dao.UserDao;

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
		System.out.println("le livre est : "+book); 
		
		HttpSession session = request.getSession();
		int user= (int)session.getAttribute("id");
		
		Evaluation e = new Evaluation(0, book, user, note, qualite, interet, lecture, souhaitAuteur, recommand);
		
			EvaluationDao.insert(e);
			
			
			//CALCUL DU MATCH BOOK
			
			AdminParameters a=AdminParametersDao.find(AdminParametersDao.getLastParameters()); 
			MatchBook m=null; 
			MatchReader m2=null; 
			Book b=null;
			User userPlusProche=null;
			User userPlusLoin=null; 
			Evaluation e2=EvaluationDao.findByBookAndUser(book, user); 
			
			System.out.println("dans add eval avant matchbook, jai le user "+user+" et l'eval : "+e2.getId()); 
			if (a==null || a.getAlgoMatchBook()==1) m=MatchBookDao.calculMatchBook1(user, e2.getId());
			else m=MatchBookDao.calculMatchBook2(user, e2.getId());  
			
			if (m!=null) {
				MatchBookDao.insert(m); 
				b=BooksDao.find(m.getLivreSuggereId()); 
				System.out.println("livre conseille : "+m.getLivreSuggereId());
				
				request.setAttribute("Titre",b.getTitre());
				request.setAttribute("Auteur",b.getAuteur());
				request.setAttribute("Editeur",b.getEditeur());
				request.setAttribute("Isbn",b.getIsbn());
				request.setAttribute("Genre",b.getGenre());
				request.setAttribute("Pays",b.getPays());
				request.setAttribute("Resume",b.getResume());
			}
			
			//CALCUL DU MATCH USER
			
			if (a==null || a.getAlgoMatchReader()==1){
				m2=MatchReaderDao.calculMatchReader1(user, e2.getId());
			}
			else{ m2=MatchReaderDao.calculMatchReader2(user, e2.getId());
			}
			
			if (m2!=null)  {
				MatchReaderDao.insert(m2); 
				userPlusProche=UserDao.find(m2.getUserPlusProcheId()); 
				userPlusLoin=UserDao.find(m2.getUserPlusLoinId());
				request.setAttribute("userPlusProche",userPlusProche.getLogin());
				request.setAttribute("userPlusLoin",userPlusLoin.getLogin());
			}
			
			//recapitulatif de l'eval et du match nouvellement calculé
			

			request.setAttribute("note",request.getParameter("note"));
			request.setAttribute("qualite",request.getParameter("qualite"));
			request.setAttribute("interet",request.getParameter("interet"));
			request.setAttribute("lecture",request.getParameter("lecture"));
			request.setAttribute("souhaitAuteur",request.getParameter("souhaitAuteur"));
			request.setAttribute("recommandation",request.getParameter("recommandation"));
			
			request.setAttribute("m",m);
			request.setAttribute("m2",m2);
			
			
			request.getRequestDispatcher("WEB-INF/StatusAddEval.jsp").forward(request, response);
	}
	}



