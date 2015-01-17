package controllers;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AdminParameters;
import beans.Book;
import dao.AdminParametersDao;
import dao.BooksDao;

/**
 * Servlet implementation class ChoixAlgoServlet
 */
@WebServlet("/ChoixAlgoServlet")
public class ChoixAlgoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChoixAlgoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//affichage simple
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		if (request.getParameter("action")!=null && request.getParameter("action").equals("afficher")) {
			
			System.out.println("afficher admin parameters"); 
			int page = 1;
	        int recordsPerPage = 5;
	        if(request.getParameter("page") != null)
	            page = Integer.parseInt(request.getParameter("page")); //page actuelle
	        List<AdminParameters> listeP = AdminParametersDao.findAll((page-1)*recordsPerPage, recordsPerPage); //de page actuelle au max : de 0 à 5
	        int noOfRecords = AdminParametersDao.countAdminParameters(); //nb total d'enregistrement
	        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage); //nb total de pages possible 
	        
	        request.setAttribute("listeP", listeP);
	        request.setAttribute("noOfPages", noOfPages);
	        request.setAttribute("currentPage", page);
	    	request.getRequestDispatcher("WEB-INF/ParametersList.jsp").forward(request, response);
			
		}
		
		else {
		
		if (request.getParameter("action")!=null && request.getParameter("action").equals("formalgo")) {
			request.getRequestDispatcher("WEB-INF/choixAlgoMatchForm.jsp").forward(request, response);  			
		}
		else{
		
		int matchBook=0;
		int matchReader=0; 
		
		if(request.getParameter("algoMatchBook") != null)
		{ matchBook = Integer.parseInt(request.getParameter("algoMatchBook"));}
		
		if(request.getParameter("algoMatchReader") != null) {
		matchReader = Integer.parseInt(request.getParameter("algoMatchReader")); }

		Calendar rightNow = Calendar.getInstance();
		int year=rightNow.get(Calendar.YEAR)-1900; 
		Date dateS = new Date(year, rightNow.get(Calendar.MONTH), rightNow.get(Calendar.DAY_OF_MONTH));

		AdminParameters a=null; 
		
		/*on insère ces changements de paramètres dans la base que s'ils sont différents des paramètres actuels*/
		if (AdminParametersDao.countAdminParameters()!=0) {
			int last=AdminParametersDao.getLastParameters() ; 
			a=AdminParametersDao.find(last); 
		    
			if (matchBook==a.getAlgoMatchBook() && matchReader==a.getAlgoMatchReader()) {
				request.setAttribute("alert", "Les paramètres sélectionnés sont identiques à ceux déjà existants ! ");
				request.getRequestDispatcher("ChoixAlgoServlet?action=formalgo").forward(request, response);  
			} //OK ici
			
			else { //les derniers paramètres sont bien différents des nouveaux
				AdminParameters newParameters= new AdminParameters(0, matchBook, matchReader, dateS);
				AdminParametersDao.insert(newParameters); 
				request.setAttribute("alert", "Les paramètres ont été modifiés avec succès ! ");	
				request.getRequestDispatcher("ChoixAlgoServlet?action=formalgo").forward(request, response); 
				
			}
		}
		
		else {//aucun parametre existant au préalable
			AdminParameters newParameters= new AdminParameters(0, matchBook, matchReader, dateS);
			AdminParametersDao.insert(newParameters); 
			request.setAttribute("alert", "Les paramètres ont été modifiés avec succès ! ");	
			request.getRequestDispatcher("WEB-INF/choixAlgoMatchForm.jsp").forward(request, response); 
		}
		
		}
		}
	}


}
