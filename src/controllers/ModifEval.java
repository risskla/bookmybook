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
		//GERER ICI LES UPDATE DE MATCHES : A FAIRE 
		
		request.setAttribute("action","modifadmin");
		
		request.setAttribute("isbn",b.getIsbn());
		request.setAttribute("login",u.getLogin());
		request.setAttribute("note",request.getParameter("note"));
		request.setAttribute("qualite",request.getParameter("qualite"));
		request.setAttribute("interet",request.getParameter("interet"));
		request.setAttribute("lecture",request.getParameter("lecture"));
		request.setAttribute("souhaitAuteur",request.getParameter("souhaitAuteur"));
		request.setAttribute("recommandation",request.getParameter("recommandation"));
		request.getRequestDispatcher("StatusModifEval.jsp").forward(request, response);  

		}
	}

}
