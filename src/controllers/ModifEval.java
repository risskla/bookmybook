package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		String alert_msg = "";
		
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
		
		int book=-1;
		int user=-1; 
		int note=0; 
		int qualite=0; 
		int interet=0; 
		int lecture=2;
		int souhaitAuteur=2; 
		int recommand=2; 
		
		if (bookId!=null) {
			book=Integer.parseInt(bookId); 
		}
		if (userId!=null) {
			user=Integer.parseInt(userId); 
		}
		
		if (noteG!=null) {
		    note=Integer.parseInt(noteG); 
		}
		if (qualiteG!=null) { 
			qualite=Integer.parseInt(qualiteG); 
		}
		if (interetG!=null) {
			interet=Integer.parseInt(interetG); 
		}
		if (lectureG!=null) { 
			lecture=Integer.parseInt(lectureG); 
		}
		if (souhaitAuteurG!=null) {
			souhaitAuteur=Integer.parseInt(souhaitAuteurG); 
		}
		if (recommandG!=null) {
			recommand=Integer.parseInt(recommandG); 
		} 
		
		User u=UserDao.find(user); 
		Book b=BooksDao.find(book); 
		
		
		Evaluation e = new Evaluation (modif, book, user , note, qualite, interet, 
				lecture, souhaitAuteur, recommand);
		Evaluation e2=EvaluationDao.find(modif); 
		if (e2==null) System.out.println("NULL"); 
		else { 
		EvaluationDao.update(e); 
		
		//GERER ICI LES UPDATE DE MATCHES : 
		AdminParameters a=AdminParametersDao.find(AdminParametersDao.getLastParameters()); 
		
		MatchBook matchbooksource=MatchBookDao.findByEval(modif);
		MatchReader matchreadersource=MatchReaderDao.findByEval(modif);
		
		MatchBook matchbookcurrent=null;
		MatchReader matchreadercurrent=null; 
		
		if (a==null || a.getAlgoMatchBook()==1){
			matchbookcurrent=MatchBookDao.calculMatchBook1(user, e2.getId());
		}
		else{
			matchbookcurrent=MatchBookDao.calculMatchBook2(user, e2.getId());  
		}
		
		if(matchbookcurrent!=matchbooksource && matchbookcurrent!=null){
			matchbooksource.setLivreSuggereId(matchbookcurrent.getLivreSuggereId());
			MatchBookDao.update(matchbooksource);
			alert_msg = alert_msg + "Match Book Mise à Jour !<br>";
		}

		//CALCUL DU MATCH USER
		
		if (a==null || a.getAlgoMatchReader()==1){
			matchreadercurrent=MatchReaderDao.calculMatchReader1(user, e2.getId());
		}
		else{ matchreadercurrent=MatchReaderDao.calculMatchReader2(user, e2.getId());
		}
		
		if (matchreadercurrent!=matchreadersource && matchreadercurrent!=null)  {
			matchreadersource.setUserPlusLoinId(matchreadercurrent.getUserPlusLoinId());
			matchreadersource.setUserPlusProcheId(matchreadercurrent.getUserPlusProcheId());
			MatchReaderDao.update(matchreadersource);
			alert_msg = alert_msg + "Match Reader Mise à Jour !<br>";
		}
		
		request.setAttribute("alert",alert_msg);
		
		request.setAttribute("action","modifadmin");
		
		request.setAttribute("isbn",b.getIsbn());
		request.setAttribute("login",u.getLogin());
		request.setAttribute("note",request.getParameter("note"));
		request.setAttribute("qualite",request.getParameter("qualite"));
		request.setAttribute("interet",request.getParameter("interet"));
		request.setAttribute("lecture",request.getParameter("lecture"));
		request.setAttribute("souhaitAuteur",request.getParameter("souhaitAuteur"));
		request.setAttribute("recommandation",request.getParameter("recommandation"));
		request.getRequestDispatcher("WEB-INF/StatusModifEval.jsp").forward(request, response);  

		}
	}

}
