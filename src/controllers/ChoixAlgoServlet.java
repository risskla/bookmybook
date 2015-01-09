package controllers;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AdminParameters;
import dao.AdminParametersDao;

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
		doPost(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int matchBook = Integer.parseInt(request.getParameter("algoMatchBook"));
		int matchReader = Integer.parseInt(request.getParameter("algoMatchReader"));
		
		/*� mettre au format AAAA-MM-DD pour mysql*/

		Calendar rightNow = Calendar.getInstance();
		int year=rightNow.get(Calendar.YEAR)-1900; 
		Date dateS = new Date(year, rightNow.get(Calendar.MONTH), rightNow.get(Calendar.DAY_OF_MONTH));

		AdminParameters a=null; 
		
		/*on ins�re ces changements de param�tres dans la base que s'ils sont diff�rents des param�tres actuels*/
		if (AdminParametersDao.countAdminParameters()!=0) {
			int last=AdminParametersDao.getLastParameters() ; 
			a=AdminParametersDao.find(last); 
		    
			if (matchBook==a.getAlgoMatchBook() && matchReader==a.getAlgoMatchReader()) {
				request.setAttribute("alert", "Les param�tres s�lectionn�s sont identiques � ceux d�j� existants ! ");
				request.getRequestDispatcher("choixAlgoMatchForm.jsp").forward(request, response);  
			} //OK ici
			
			else { //les derniers param�tres sont bien diff�rents des nouveaux
				AdminParameters newParameters= new AdminParameters(0, matchBook, matchReader, dateS);
				AdminParametersDao.insert(newParameters); 
				request.setAttribute("alert", "Les param�tres ont �t� modifi�s avec succ�s ! ");	
				request.getRequestDispatcher("choixAlgoMatchForm.jsp").forward(request, response); 
				
			}
		}
		
		else {//aucun parametre existant au pr�alable
			AdminParameters newParameters= new AdminParameters(0, matchBook, matchReader, dateS);
			AdminParametersDao.insert(newParameters); 
			request.setAttribute("alert", "Les param�tres ont �t� modifi�s avec succ�s ! ");	
			request.getRequestDispatcher("choixAlgoMatchForm.jsp").forward(request, response); 
		}
	}


}
