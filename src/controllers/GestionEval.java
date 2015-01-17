package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import beans.MatchBook;
import beans.MatchReader;
import beans.TitleComp;
import beans.User;
import dao.BooksDao;
import dao.EvaluationDao;
import dao.MatchBookDao;
import dao.MatchReaderDao;
import dao.UserDao;

/**
 * Servlet implementation class GestionEval
 */
@WebServlet("/GestionEval")
public class GestionEval extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionEval() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//sécurité
		int userIdcheck= (int)request.getSession().getAttribute("id");
		if(userIdcheck==-1){
			request.setAttribute("alert", "Veuillez vous logger !");
			request.getRequestDispatcher("Login?action=deconnexion").forward(request, response);
		}
		
		//éxécuté lors de modif/suppr parce qu'on passe par des url 
		int id = 0;
		String action = request.getParameter("action");
		int page = 1;
        int recordsPerPage = 5;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page")); //page actuelle
        
        /*fin bloc gestion pagination*/
	
		if (action != null) {
			String idCh = request.getParameter("id");
			if (idCh != null) {
				try {
					id = Integer.parseInt(idCh);
				} catch (Exception e) {

				}
			}

			if (action.equals("supprimer")) {
				EvaluationDao.delete(id);
				System.out.println("id de l'eval à supprimer : "+id); 
				MatchBookDao.deleteByEval(id);  
				MatchReaderDao.deleteByEval(id); 
				request.setAttribute("alert", "Suppression de l'evaluation n° " + id +  " réalisée avec succes !");
				request.getRequestDispatcher("GestionEval?action=afficher").forward(request, response);  
				
			} else if (action.equals("modifier")) {
				Evaluation currenteval = EvaluationDao.find(id);
				Book currentbook=BooksDao.find(currenteval.getLivreId());
	            User currentuser=UserDao.find(currenteval.getUserId());
				
	            request.setAttribute("IdModif", currenteval.getId());
				
	            request.setAttribute("LivreId", currenteval.getLivreId());
	            request.setAttribute("UserId", currenteval.getUserId());
	            request.setAttribute("Note", currenteval.getNote());
	            request.setAttribute("Qualite", currenteval.getQualite());
	            request.setAttribute("Interet", currenteval.getInteret());
	            request.setAttribute("Lecture", currenteval.getLecture());
	            request.setAttribute("SouhaitAuteur", currenteval.getSouhaitAuteur());
	            request.setAttribute("Recommand", currenteval.getRecommand());
	            
	            request.setAttribute("Titre", currentbook.getTitre());
	            request.setAttribute("Auteur", currentbook.getAuteur());
	            request.setAttribute("Isbn", currentbook.getIsbn());

	            request.setAttribute("Login", currentuser.getLogin());
	            
				request.getRequestDispatcher("WEB-INF/ModifEval.jsp").forward(request, response);  
				
			} 
			 else if (action.equals("modifierByReader")) {
					request.setAttribute("eModif", EvaluationDao.find(id));
					request.getRequestDispatcher("WEB-INF/AskEvalModif.jsp").forward(request, response);  
					
				} 
			 else if (action.equals("affichEvalForUser")) {
					 System.out.println("dans gestion eval affichEvalForUser"); 
					//Si on désire afficher les évaluations d'un utilisateur :
					//-> On transmet des informations sur l'utilisateur:
					HttpSession session = request.getSession();
					int userId= (int)session.getAttribute("id");
					
					int noOfRecords = EvaluationDao.countEvalByUser(userId); //nb total d'enregistrement
				    List<Evaluation> EvalList=EvaluationDao.findbyuser(userId, (page-1)*recordsPerPage, recordsPerPage); 
				     
				    User u = UserDao.find(userId);
					request.setAttribute("uId", u.getId());
					request.setAttribute("uLogin", u.getLogin());
					request.setAttribute("uRole", u.getRole());
				    request.setAttribute("TypeDeListe","affichEvalForUser");
					request.setAttribute("noOfRecords", noOfRecords);
					request.setAttribute("EvalList", EvalList);
				    request.getRequestDispatcher("GestionEval?action=afficher").forward(request, response);			
				}
			else if (action.equals("affichEvalForUserAdmin")) {
				
				//Si on désire afficher les évaluations d'un utilisateur :
				//-> On transmet des informations sur l'utilisateur:
				 int userId = Integer.parseInt(request.getParameter("id"));
				 
				 int noOfRecords = EvaluationDao.countEvalByUser(userId); //nb total d'enregistrement
			     List<Evaluation> EvalList=EvaluationDao.findbyuser(userId, (page-1)*recordsPerPage, recordsPerPage); 
			     
			    User u = UserDao.find(userId);
				request.setAttribute("uId", u.getId());
				request.setAttribute("uLogin", u.getLogin());
				request.setAttribute("uRole", 1);
			    request.setAttribute("TypeDeListe","affichEvalForUserAdmin");   
				request.setAttribute("uInfo", UserDao.find(userId));
				request.setAttribute("noOfRecords", noOfRecords);
				request.setAttribute("EvalList", EvalList);
				
				request.getRequestDispatcher("GestionEval?action=afficher").forward(request, response);  
			}
			else if (action.equals("affichEvalForBook")) {
				System.out.println("dans GestionEval:affichEvalForBook"); 
				//afficher les evaluation pour un livre en particulier
				int idBook = Integer.parseInt(request.getParameter("idBook"));
				Book b = BooksDao.find(idBook);
				
				int noOfRecords = EvaluationDao.countEvalByBook(idBook); //nb total d'enregistrement
		        List<Evaluation> EvalList = EvaluationDao.findByBook(idBook, (page-1)*recordsPerPage, recordsPerPage ); 
				
		        System.out.println("liste d'eval pour ce bouquin : "+EvalList); 
		        
		        request.setAttribute("bTitre", b.getTitre());
		        request.setAttribute("bAuteur", b.getAuteur());
		        request.setAttribute("bIsbn", b.getIsbn());
		        request.setAttribute("bIdUrl", "&idBook=" + b.getId());
		        
				request.setAttribute("uRole", 1);
		        request.setAttribute("TypeDeListe","affichEvalForBook");
		        request.setAttribute("EvalList", EvalList );
		        request.setAttribute("noOfRecords", noOfRecords);
				request.setAttribute("infoBook", b.getTitre() + " de " + b.getAuteur());
				

				request.getRequestDispatcher("GestionEval?action=afficher").forward(request, response);  
				
			}
			
			else if (action.equals("afficher")) {
				//afficher toutes les evaluations
				int uRole;
				if(request.getAttribute("uRole")==null){
					uRole=1;
				}else{
					uRole=(int)request.getAttribute("uRole");
				}
				
				
				String TypeDeListe;
				if(request.getAttribute("TypeDeListe")==null){
					TypeDeListe = "afficher"; //nb total d'enregistrement
				}
				else
				{
					TypeDeListe = (String)request.getAttribute("TypeDeListe");
				}
				
				
				
				/*gestion pagination*/
				int noOfRecords;
				if(request.getAttribute("noOfRecords")==null){
					noOfRecords = EvaluationDao.countEval(); //nb total d'enregistrement
				}
				else
				{
					noOfRecords = (int)request.getAttribute("noOfRecords");
				}
				
		        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage); //nb total de pages possible 

		        System.out.println("dans gestionEval : pagination : nb denregistrements : "+ noOfRecords);
		        System.out.println("noOfPages : "+noOfPages); 
		        System.out.println("pageActuelle"+page); 
		        
		        List<Evaluation> EvalList = null;
				if(request.getAttribute("EvalList")==null){
					EvalList = EvaluationDao.findAll((page-1)*recordsPerPage, recordsPerPage);
				}
				else
				{
					EvalList = (List<Evaluation>)request.getAttribute("EvalList");
				}
		        System.out.println("ma liste paginee : "+ EvalList); 
		        List<String> ListUser = new ArrayList<String>();
		        List<String> ListBook = new ArrayList<String>();
		        List<String> ListMB = new ArrayList<String>();
		        List<String> ListMRLoin = new ArrayList<String>();
		        List<String> ListMRProche = new ArrayList<String>();
	            MatchBook m=null ;
	            MatchReader m2=null; 
	            Book b2=null; 
	            
				for(Evaluation e : EvalList){
				   User u=UserDao.find(e.getUserId()); 
				   ListUser.add(u.getLogin());
				   Book b=BooksDao.find(e.getLivreId());
				   ListBook.add(b.getTitre());
				   
				   m=MatchBookDao.findByEval(e.getId()); 
				   if (m!=null){ 
					   b2=BooksDao.find(m.getLivreSuggereId());
					   ListMB.add(b2.getTitre());
				   }else
				   {
					   ListMB.add("pas de match");
				   }
				   m2=MatchReaderDao.findByEval(e.getId());
		           if (m2!=null) { 
			           User up=UserDao.find(m2.getUserPlusProcheId()); 
			           ListMRProche.add(up.getLogin() + "\n(mail : <A HREF=\"mailto:" + up.getMail() + "\">" + up.getMail() + "</A>)");
			           
			           User ul=UserDao.find(m2.getUserPlusLoinId()); 
			           ListMRLoin.add(ul.getLogin() + "\n(mail : <A HREF=\"mailto:" + ul.getMail() + "\">" + ul.getMail() + "</A>)");
			       }else{
			    	   ListMRProche.add("pas de match");
			    	   ListMRLoin.add("pas de match");
			       }
				}
		        
				request.setAttribute("uRole", uRole);
				request.setAttribute("TypeDeListe", TypeDeListe);
				request.setAttribute("EvalList", EvalList);
				request.setAttribute("ListUser", ListUser);
				request.setAttribute("ListBook", ListBook);
				request.setAttribute("ListMB", ListMB);
				request.setAttribute("ListMRLoin", ListMRLoin);
				request.setAttribute("ListMRProche", ListMRProche);
				
				
		        request.setAttribute("noOfPages", noOfPages);
		        request.setAttribute("currentPage", page);
				request.getRequestDispatcher("WEB-INF/AllEvalList.jsp").forward(request, response);
			} 
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("GestionEval?action=afficher").forward(request, response);  
		
	}

}
