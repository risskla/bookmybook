package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Book;
import beans.Evaluation;
import beans.IsbnComp;
import beans.TitleComp;
import beans.User;
import dao.BooksDao;
import dao.EvaluationDao;
import dao.MatchBookDao;
import dao.MatchReaderDao;
import dao.UserDao;

/**
 * Servlet implementation class GestionBooks
 */
@WebServlet("/GestionBooks")
public class GestionBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionBooks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//éxécuté lors de modif/suppr parce qu'on passe par des url 
		int id = 0;
		int forward=0; 
		
		String action = request.getParameter("action");


        PrintWriter out = response.getWriter();
		
		if (action != null) {
			String idCh = request.getParameter("id");
			if (idCh != null) {
				try {
					id = Integer.parseInt(idCh);
				} catch (Exception e) {

				}
			}

			if (action.equals("supprimer")) {
				BooksDao.delete(id);
				
				//il faut alors supprimer les eval liées à ce livre 
				List<Evaluation> le=EvaluationDao.findAllByBook(id); 
				int idEval=0; 
				for(Evaluation e : le){
					idEval=e.getId(); 
					EvaluationDao.delete(idEval); 
					//et supprimer tous les match pointant vers l'eval qui nexiste plus
					MatchBookDao.deleteByEval(idEval);  
					MatchReaderDao.deleteByEval(idEval); 
				}
				
				//et supprimer tous les matchbook qui suggerent le livre
				MatchBookDao.deleteByBook(id); 
	
				request.setAttribute("alert", "Suppression du livre n° " + id +  " réalisée avec succes !");
				doPost(request,response);
				
			} else if (action.equals("modifier")) {
				forward=1;
				request.setAttribute("bModif", BooksDao.find(id));
				request.getRequestDispatcher("WEB-INF/ModifBook.jsp").forward(request, response);  
				
			}
		
			else if (action.equals("sort")) {
				//cf dopost
			} else if (action.equals("evaluer")) {
				
				HttpSession session = request.getSession();
				int userId= (int)session.getAttribute("id");
				
				Evaluation e=EvaluationDao.findByBookAndUser(id, userId);
				if (e==null){
					forward=1;
					System.out.println("evaluation gestionboook"); 
				request.setAttribute("bEval", BooksDao.find(id)); 
				request.getRequestDispatcher("WEB-INF/EvaluationForm.jsp").forward(request, response);  
				System.out.println("redirection");
				}
				else {
					response.setContentType("text/html");
					try {
					int eid=e.getId(); 
					request.setAttribute("eModif",e);
					request.setAttribute("alert","Vous avez déjà évalué ce livre ! <br><a href='GestionEval?action=modifierByReader&id=" + eid + "'>Demander une modification</a>");
					doPost(request,response);
					}
					finally { out.close() ;}
				}
				
			}
		}

        if (forward==0) {
			doPost(request,response);
			
        }
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//List<Book> listeB = BooksDao.findAll();

		String action = request.getParameter("action");
		String keyword = request.getParameter("keyword");
		
		/*gestion pagination*/
		int page = 1;
        int recordsPerPage = 5;
        int noOfRecords;
        
        if(request.getParameter("page") != null){
            page = Integer.parseInt(request.getParameter("page")); //page actuelle
        }
        List<Book> listeB;
        if (keyword!=null){
            listeB = BooksDao.findByKeyword(keyword, (page-1)*recordsPerPage, recordsPerPage); //de page actuelle au max : de 0 à 5
            noOfRecords = BooksDao.countBooksByKeyword(keyword);//nb total d'enregistrement par keyword
        }
        else
        {
        	listeB = BooksDao.findAll((page-1)*recordsPerPage, recordsPerPage); //de page actuelle au max : de 0 à 5
            noOfRecords = BooksDao.countBooks();//nb total d'enregistrement
        }
        
        System.out.println(listeB); 
        System.out.println("nb book by keyword"); 
        System.out.println(noOfRecords); 
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage); //nb total de pages possible 
        System.out.println(noOfPages); 
        System.out.println(page);
        
        /*fin bloc gestion pagination*/
        
		if (action != null) {
			if (action.equals("sort")) { //on trie simplement
				String sortType = request.getParameter("sortType");
				if(sortType!=null){
					if (sortType.equals("1"))
						Collections.sort(listeB, new TitleComp());
					else if (sortType.equals("2"))
						Collections.sort(listeB, new IsbnComp());
				}
			}
		}
		
		request.setAttribute("listeB", listeB);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page); 
		
        int userId= (int)request.getSession().getAttribute("id");
		User u=UserDao.find(userId); 
		request.setAttribute("role", u.getRole());
		
		request.getRequestDispatcher("WEB-INF/BooksList.jsp").forward(request, response);  
		// tri OK
		
	}
}

