package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import dao.UserDao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Login = request.getParameter("login");
		//On recupère l'user lié au login :
		User b = UserDao.findByLogin(Login);
		if(b != null){
			//S'il n'est pas nul, on cherche à savoir si les mdps correspondent :
			if(request.getParameter("pwd").equals(b.getMdp())){
				//on créé une session :
				HttpSession session = request.getSession();
				
				//On insère l'id du user en variable de session :
				session.setAttribute( "id", b.getId());
				
				//Ensuite on va sur le bon menu en fonction du role :
				if(b.getRole() == 1){
					request.getRequestDispatcher("WEB-INF/adminMenu.jsp").forward(request, response);  
				} else {
					request.getRequestDispatcher("WEB-INF/readerMenu.jsp").forward(request, response);  
				}
			} 
			else
			{
				//Si le mot de passe est erroné on envoie le message suivant dans le page de login :
				request.setAttribute("alert", "Mot de passe erroné !");
				doGet(request,response);
			}
		}
		else
		{
			//Si l'utilisateur n'existe pas, on envoie le message suivant dans le page de login :
			request.setAttribute("alert", "Utilisateur inexistant !");
			doGet(request,response);	
		}
	}

}
