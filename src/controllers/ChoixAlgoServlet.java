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
		
		/*à mettre au format AAAA-MM-DD pour mysql*/

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
				request.getRequestDispatcher("choixAlgoMatchForm.jsp").forward(request, response);  
			} //OK ici
			
			else { //les derniers paramètres sont bien différents des nouveaux
				AdminParameters newParameters= new AdminParameters(0, matchBook, matchReader, dateS);
				AdminParametersDao.insert(newParameters); 
				request.setAttribute("alert", "Les paramètres ont été modifiés avec succès ! ");	
				request.getRequestDispatcher("choixAlgoMatchForm.jsp").forward(request, response); 
				
			}
		}
		
		else {//aucun parametre existant au préalable
			AdminParameters newParameters= new AdminParameters(0, matchBook, matchReader, dateS);
			AdminParametersDao.insert(newParameters); 
			request.setAttribute("alert", "Les paramètres ont été modifiés avec succès ! ");	
			request.getRequestDispatcher("choixAlgoMatchForm.jsp").forward(request, response); 
		}
	}


}
