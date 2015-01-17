package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import dao.UserDao;

/**
 * Servlet implementation class SwitchRole
 */
@WebServlet("/SwitchRole")
public class SwitchRole extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SwitchRole() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
        int userId= (int)request.getSession().getAttribute("id");
		User u=UserDao.find(userId); 
		int role = u.getRole();
		System.out.println("action:");
		System.out.println(action);
		if(action.equals("adminmenu"))
			if(role==1)
				request.getRequestDispatcher("WEB-INF/adminMenu.jsp").forward(request, response);
			else{
				request.setAttribute("alert", "Vous avez été déconnecté pour usage non autorisé du site !");
				request.getRequestDispatcher("Login?action=deconnexion").forward(request, response);
			}
		if(action.equals("readermenu"))
			if(role==0)
				request.getRequestDispatcher("WEB-INF/readerMenu.jsp").forward(request, response);
			else{
				request.setAttribute("alert", "Vous avez été déconnecté pour usage non autorisé du site !");
				request.getRequestDispatcher("Login?action=deconnexion").forward(request, response);
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
